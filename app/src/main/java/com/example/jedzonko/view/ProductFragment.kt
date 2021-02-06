package com.example.jedzonko.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.jedzonko.R
import com.example.jedzonko.databinding.ProductFragmentBinding
import com.example.jedzonko.model.ProductRepository
import com.example.jedzonko.viewModel.ProductVM
import com.example.jedzonko.viewModel.ProductVMFactory

class ProductFragment : Fragment() {
    private lateinit var viewModel: ProductVM
    val args: ProductArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ProductFragmentBinding.inflate(
            layoutInflater)
        val repository = ProductRepository()
        val vmFactory = ProductVMFactory(requireActivity().application, repository)
        viewModel = ViewModelProvider(this,vmFactory).get(ProductVM::class.java)
        viewModel.setCode(args.barcode)
        viewModel.getProductFromApi()
        viewModel.productResponse.observe(this,{

            if(it.isSuccessful) {
                Log.d("Name", it.body()?.product?.Name?:"Nie Wyszlo")
                if(it.body()?.product!=null)
                    viewModel.addProduct()
                view?.findViewById<TextView>(R.id.tvProductName)?.text = it.body()?.product?.Name?:"Nie Wyszlo"
                binding.tvProductName.setText("XD")
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
    ): View? {
        return inflater.inflate(R.layout.product_fragment, container, false)
    }
}