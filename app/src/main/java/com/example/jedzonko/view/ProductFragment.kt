package com.example.jedzonko.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jedzonko.R
import com.example.jedzonko.databinding.ProductFragmentBinding
import com.example.jedzonko.model.ProductRepository
import com.example.jedzonko.viewModel.ProductVM
import com.example.jedzonko.viewModel.ProductVMFactory

class ProductFragment : Fragment() {
    private var _binding: ProductFragmentBinding? = null
    private lateinit var viewModel: ProductVM
    val args: ProductFragmentArgs by navArgs()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repository = ProductRepository()
        val vmFactory = ProductVMFactory(requireActivity().application, repository)
        viewModel = ViewModelProvider(this, vmFactory).get(ProductVM::class.java)
        viewModel.setCode(args.barcode)

        viewModel.productsFromDB.observe(viewLifecycleOwner,{
            if (!viewModel.isProductInDb()) {
                viewModel.getProductFromApi()
                viewModel.productResponse.observe(viewLifecycleOwner, {

                    if (it.isSuccessful) {
                        if (it.body()?.product != null) {
                            viewModel.addProduct()
                            binding.apply {
                                tvProductName.text = viewModel.product.value?.Name
                                tvFatValue.text = viewModel.product.value?.nutrimentsLevel?.fat
                                tvSaltValue.text = viewModel.product.value?.nutrimentsLevel?.salt?:getString(
                                    R.string.NODATA)
                                tvSaturatedValue.text =
                                    viewModel.product.value?.nutrimentsLevel?.saturatedFat?:getString(
                                        R.string.NODATA)
                                tvSugarsValue.text =
                                    viewModel.product.value?.nutrimentsLevel?.sugars?:getString(
                                        R.string.NODATA)
                            }
                        } else {
                            val action =
                                ProductFragmentDirections.actionProductFragmentToNotFoundFragment2(
                                    viewModel.productCode
                                )
                            if (view?.findNavController()?.currentDestination?.id == R.id.productFragment) {
                                findNavController().navigate(action)
                            }
                        }
                    }
                })
            } else {
                viewModel.isProductInDb()
                viewModel.productFromDB!!.observe(viewLifecycleOwner,{
                    binding.tvProductName.text = viewModel.productFromDB?.value?.productName})
                viewModel.nutrimentFromDB!!.observe(viewLifecycleOwner, {
                    binding.tvFatValue.text = viewModel.nutrimentFromDB?.value?.fat?:getString(
                        R.string.NODATA)
                    binding.tvSaltValue.text = viewModel.nutrimentFromDB?.value?.salt?:getString(
                        R.string.NODATA)
                    binding.tvSaturatedValue.text =
                        viewModel.nutrimentFromDB?.value?.saturatedFat?:getString(
                            R.string.NODATA)
                    binding.tvSugarsValue.text = viewModel.nutrimentFromDB?.value?.sugars?:getString(
                        R.string.NODATA)
                })
                //viewModel.updateProduct()
            }
        })
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