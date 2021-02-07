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

        viewModel.productsFromDB.observe(this,{
            if (!viewModel.isProductInDb()) {
                viewModel.getProductFromApi()
                viewModel.productResponse.observe(this, {

                    if (it.isSuccessful) {
                        if (it.body()?.product != null) {
                            viewModel.addProduct()
                            binding.apply {
                                tvProductName.text = viewModel.product.value!!.Name
                                tvFatValue.text = viewModel.product.value!!.nutrimentsLevel.fat
                                tvSaltValue.text = viewModel.product.value!!.nutrimentsLevel.salt
                                tvSaturatedValue.text =
                                    viewModel.product.value!!.nutrimentsLevel.saturatedFat
                                tvSugarsValue.text =
                                    viewModel.product.value!!.nutrimentsLevel.sugars
                            }
                        } else {
                            val action =
                                ProductFragmentDirections.actionProductFragmentToNotFoundFragment2(
                                    viewModel.productCode
                                )
                            findNavController().navigate(action)
                        }
                    }
                })
            } else {
                viewModel.isProductInDb()
                viewModel.productFromDB!!.observe(this,{
                    binding.tvProductName.text = viewModel.productFromDB!!.value!!.productName})
                viewModel.nutrimentFromDB!!.observe(this, {
                    binding.tvFatValue.text = viewModel.nutrimentFromDB!!.value!!.fat
                    binding.tvSaltValue.text = viewModel.nutrimentFromDB!!.value!!.salt
                    binding.tvSaturatedValue.text =
                            viewModel.nutrimentFromDB!!.value!!.saturatedFat
                    binding.tvSugarsValue.text = viewModel.nutrimentFromDB!!.value!!.sugars
            })
                viewModel.updateProduct()
            }
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
            viewModel.addProductToCalculator()
            val action = ProductFragmentDirections.actionProductFragmentToCalculatorFragment()
            findNavController().navigate(action)
        }

        binding.btDetails.setOnClickListener {
            val action = ProductFragmentDirections
                .actionProductFragmentToDetailsFragment(args.barcode)
            findNavController().navigate(action)
        }
        return binding.root
    }
}