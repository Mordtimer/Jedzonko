package com.example.jedzonko.view

import android.os.Bundle
import android.provider.SyncStateContract
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
import com.example.jedzonko.databinding.SummaryFragmentBinding
import com.example.jedzonko.model.database.CalculatorDB
import com.example.jedzonko.model.database.NutrimentDB
import com.example.jedzonko.model.database.ProductDB
import com.example.jedzonko.util.Constants
import com.example.jedzonko.viewModel.CalculatorVM
import com.example.jedzonko.viewModel.CalculatorVMFactory
import com.example.jedzonko.viewModel.SummaryVM
import com.example.jedzonko.viewModel.SummaryVMFactory
import kotlin.reflect.jvm.internal.impl.load.java.Constant

class SummaryFragment : Fragment(R.layout.summary_fragment) {
    private var _binding: SummaryFragmentBinding? = null
    private lateinit var viewModel: SummaryVM
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vmFactory = SummaryVMFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, vmFactory).get(SummaryVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.buttonBackToCalculator).setOnClickListener {
            view.findNavController().navigate(R.id.action_summaryFragment_to_calculatorFragment)
        }
        viewModel.nutriments.observe(viewLifecycleOwner, { nutris ->
            viewModel.quantities.observe(viewLifecycleOwner, {

                var sumKcal = 0.0
                var sumSugar = 0.0
                var sumFat = 0.0
                var sumSatFat = 0.0
                var sumSalt = 0.0

                for (nutri in nutris) {
                    for (calc in it) {
                        if (nutri.barcode == calc.barcode) {
                            sumKcal += nutri.energykcal!!.toDouble() / 100 * Constants.NUTRIMENT_BASE * calc.quantity
                            sumSugar += nutri.sugars!!.toDouble() / 100 * Constants.NUTRIMENT_BASE * calc.quantity
                            sumFat += nutri.fat!!.toDouble() / 100 * Constants.NUTRIMENT_BASE * calc.quantity
                            sumSatFat += nutri.saturatedFat!!.toDouble() / 100 * Constants.NUTRIMENT_BASE * calc.quantity
                            sumSalt += nutri.salt!!.toDouble() / 100 * Constants.NUTRIMENT_BASE * calc.quantity
                        }
                    }
                }

                binding.tvCaloriesInput.text = sumKcal.toString()
                binding.tvSugarsInput.text = sumSugar.toString()
                binding.tvFatInput.text = sumFat.toString()
                binding.tvSaturatedSummaryInput.text = sumSatFat.toString()
                binding.tvSaltInput.text = sumSalt.toString()

                if(sumKcal>Constants.MAX_KCAL)
                    binding.tvSummaryComment.text = binding.tvSummaryComment.text.toString()+"Uwaga! Nadmiar kalorii \n"
                if(sumSugar>Constants.MAX_SUGAR)
                    binding.tvSummaryComment.text = binding.tvSummaryComment.text.toString()+"Uwaga! Nadmiar cukrów \n"
                if(sumFat>Constants.MAX_FAT)
                    binding.tvSummaryComment.text = binding.tvSummaryComment.text.toString()+"Uwaga! Nadmiar tłuszczów \n"
                if(sumSatFat>Constants.MAX_SAT_FAT)
                    binding.tvSummaryComment.text = binding.tvSummaryComment.text.toString()+"Uwaga! Nadmiar tłuszczów nasyconych \n"
                if(sumSalt>Constants.MAX_SALT)
                    binding.tvSummaryComment.text = binding.tvSummaryComment.text.toString()+"Uwaga! Nadmiar soli \n"

                //todo obrazek

            })
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SummaryFragmentBinding.inflate(
            layoutInflater, container, false
        )
        return binding.root
    }
}