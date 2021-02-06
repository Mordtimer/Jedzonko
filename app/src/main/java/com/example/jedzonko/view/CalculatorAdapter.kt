package com.example.jedzonko.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.jedzonko.databinding.CalculatorItemBinding
import com.example.jedzonko.model.database.ProductDB
import androidx.navigation.Navigation.findNavController

import java.util.*

class CalculatorAdapter(private val dataSet: LiveData<List<ProductDB>>): RecyclerView.Adapter<CalculatorAdapter.ViewHolder>() {

    lateinit var binding: CalculatorItemBinding

    inner class ViewHolder(private val binding: CalculatorItemBinding): RecyclerView.ViewHolder(binding.root){
        init {
            // Navigacja
            binding.rowCalculator.setOnClickListener {
                val currentBarcode = dataSet.value!![adapterPosition].barcode
                val action = HistoryFragmentDirections.actionHistoryFragmentToProductFragment(currentBarcode)
                findNavController(binding.root).navigate(action)
            }
        }

        fun bind(product: ProductDB){
           binding.tvCalculatorName.text = product.productName
            binding.tvCalculatorMass .text = product.date.toString()
        // TODO co zrobić z obrazkami?
        //binding.imgHistoryProduct;
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = CalculatorItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: ProductDB = dataSet.value!![position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = dataSet.value?.size?:0
}