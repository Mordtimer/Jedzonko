package com.example.jedzonko

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.jedzonko.model.ProductRepository
import com.example.jedzonko.viewModel.ProductVM
import com.example.jedzonko.viewModel.ProductVMFactory

class Product : Fragment() {
    private lateinit var viewModel: ProductVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = ProductRepository()
        val vmFactory = ProductVMFactory(requireActivity().application, repository)
        viewModel = ViewModelProvider(this,vmFactory).get(ProductVM::class.java)
        //viewModel.getProduct()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_fragment, container, false)
    }
}