package com.example.jedzonko.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jedzonko.databinding.ProductFragmentBinding
import com.example.jedzonko.model.ProductRepository
import com.example.jedzonko.viewModel.ProductVM
import com.example.jedzonko.viewModel.ProductVMFactory
import kotlin.reflect.full.memberProperties

class ProductFragment : Fragment() {
    private var _binding: ProductFragmentBinding? = null
    private lateinit var viewModel: ProductVM
    val args: ProductFragmentArgs by navArgs()
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = ProductRepository()
        val vmFactory = ProductVMFactory(requireActivity().application, repository)
        viewModel = ViewModelProvider(this, vmFactory).get(ProductVM::class.java)
        viewModel.setCode(args.barcode)
        viewModel.getProductFromApi()
        viewModel.productResponse.observe(this, {

            if (it.isSuccessful) {
                Log.d("Name", it.body()?.product?.Name ?: "Nie Wyszlo")
                if (it.body()?.product != null) {
                    viewModel.addProduct()
                    binding.apply {
                        tvProductName.text = viewModel.product.value!!.Name
                        tvFatValue.text = viewModel.product.value!!.nutrimentsLevel.fat
                        tvSaltValue.text = viewModel.product.value!!.nutrimentsLevel.salt
                        tvSaturatedValue.text = viewModel.product.value!!.nutrimentsLevel.saturatedFat
                        tvSugarsValue.text = viewModel.product.value!!.nutrimentsLevel.sugars
                    }
                }

                Log.d("Product: ", it.body()?.product.toString())
                val tmp = it.body()?.product!!.nutriments.javaClass.kotlin.memberProperties
                tmp.forEach { c ->
                    val string = c.get(it.body()!!.product.nutriments)
                    Log.d("Name: ${c.name}", "value: $string")
                }
                Log.d("Nutriments: ", tmp.toString())
            }
            //TODO jak nie wyjdzie skan to coś tam
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProductFragmentBinding.inflate(
            layoutInflater, container, false)

        binding.btAdd.setOnClickListener {
            //TODO
            // Dodać dodawanie do kalkulatora
        }
        binding.btDetails.setOnClickListener {
            val action = ProductFragmentDirections.actionProductFragmentToDetailsFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }
}