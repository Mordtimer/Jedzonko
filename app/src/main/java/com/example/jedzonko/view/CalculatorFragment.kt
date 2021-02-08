package com.example.jedzonko.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jedzonko.R
import com.example.jedzonko.databinding.CalculatorFragmentBinding
import com.example.jedzonko.databinding.HistoryFragmentBinding
import com.example.jedzonko.model.ProductRepository
import com.example.jedzonko.model.database.CalculatorDB
import com.example.jedzonko.model.database.ProductDB
import com.example.jedzonko.util.Constants
import com.example.jedzonko.viewModel.CalculatorVM
import com.example.jedzonko.viewModel.CalculatorVMFactory
import com.example.jedzonko.viewModel.HistoryVM
import com.example.jedzonko.viewModel.HistoryVMFactory
import kotlin.math.roundToInt

class CalculatorFragment : Fragment(R.layout.calculator_fragment) {

    private var _binding: CalculatorFragmentBinding? = null
    private lateinit var viewModel: CalculatorVM
    private val binding get() = _binding!!

    private lateinit var  recyclerView: RecyclerView
    private lateinit var  calculatorAdapter: RecyclerView.Adapter<*>
    private lateinit var  products: LiveData<List<ProductDB>>
    private lateinit var  quantities: LiveData<List<CalculatorDB>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.rvCalculator
        recyclerView.adapter = calculatorAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        /*
        view.findViewById<Button>(R.id.buttonSummary).setOnClickListener {
            view.findNavController().navigate(R.id.action_calculatorFragment_to_summaryFragment)
        }
        */
        view.findViewById<Button>(R.id.buttonAddProduct).setOnClickListener {
            if (view.findNavController().currentDestination?.id == R.id.calculatorFragment) {
                view.findNavController().navigate(R.id.action_calculatorFragment_to_scanFragment)
            }
        }

        viewModel.nutriments.observe(viewLifecycleOwner, { nutris ->
            viewModel.quantities.observe(viewLifecycleOwner, {

                var sumKcal = 0.0

                for (nutri in nutris) {
                    for (calc in it) {
                        if (nutri.barcode == calc.barcode) {
                            sumKcal += nutri.energykcal!!.toDouble() / 300 * Constants.NUTRIMENT_BASE * calc.quantity
                            //nie wiem czemu 300 lol ale działa
                        }
                    }
                }
                binding.tvKcal.text = sumKcal.roundToInt().toString()+"/"+Constants.MAX_KCAL+"kcal   "
                if(sumKcal.roundToInt()>Constants.MAX_KCAL)
                    binding.tvKcal.text = binding.tvKcal.text.toString() + "Nadmiar kalorii!"
            })
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val vmFactory = CalculatorVMFactory(requireActivity().application)
        viewModel = ViewModelProvider(this,vmFactory).get(CalculatorVM::class.java)
        calculatorAdapter = CalculatorAdapter(viewModel.products, viewModel.quantities, viewModel)
        products = viewModel.products
        quantities = viewModel.quantities
        _binding = CalculatorFragmentBinding.inflate(
            layoutInflater, container, false)
        products.observe(viewLifecycleOwner,{
            calculatorAdapter.notifyDataSetChanged()
        })
        quantities.observe(viewLifecycleOwner,{
            calculatorAdapter.notifyDataSetChanged()
        })
        return binding.root
    }
}