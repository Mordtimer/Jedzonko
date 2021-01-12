package com.example.jedzonko.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jedzonko.R
import com.example.jedzonko.viewModel.ProductViewModel

class ProductFragment: Fragment(){

    private lateinit var productViewModel: ProductViewModel
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        productViewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)
        return inflater.inflate(R.layout.product_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //todo set productCode value
        val textProductCode = view.findViewById<TextView>(R.id.textProductCode)
        textProductCode.text = productViewModel.productCode
    }
}