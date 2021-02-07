package com.example.jedzonko.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.jedzonko.databinding.CalculatorItemBinding
import com.example.jedzonko.model.database.ProductDB
import androidx.navigation.Navigation.findNavController
import com.example.jedzonko.model.database.CalculatorDB
import com.example.jedzonko.viewModel.CalculatorVM
import com.google.android.datatransport.runtime.util.PriorityMapping.toInt

import java.util.*

class CalculatorAdapter(private val dataSet: LiveData<List<ProductDB>>, private val quantity: LiveData<List<CalculatorDB>>, private val vm: CalculatorVM): RecyclerView.Adapter<CalculatorAdapter.ViewHolder>() {

    lateinit var binding: CalculatorItemBinding

    inner class ViewHolder(private val binding: CalculatorItemBinding): RecyclerView.ViewHolder(binding.root){
        init {
            // Nawigacja
            binding.rowCalculator.setOnClickListener {
                val currentBarcode = dataSet.value!![adapterPosition].barcode
                val action = CalculatorFragmentDirections.actionCalculatorFragmentToProductFragment(currentBarcode)
                findNavController(binding.root).navigate(action)
            }
        }

        fun bind(product: ProductDB){
            binding.tvCalculatorName.text = product.productName
            //todo: naprawić jednostki może dodać buttony ++ / --
            for(q in quantity.value!!){
                if(q.barcode == product.barcode){
                    binding.tvCalculatorMass.text = (100*q.quantity).toString()
                    binding.tvNumber.text = q.quantity.toString()
                }
            }
            var quantity = binding.tvNumber.text.toString().toInt()
            binding.buttonAddProduct.setOnClickListener{
                vm.changeQuantity(quantity+1,product.barcode)
                notifyDataSetChanged()
            }
            binding.buttonDeleteProduct.setOnClickListener{
                if(quantity==1){
                    vm.deleteFromCalculator(CalculatorDB(product.barcode+"C", quantity, product.barcode))
                }else{
                    vm.changeQuantity(quantity-1,product.barcode)
                }

                notifyDataSetChanged()
            }

        // TODO co zrobić z obrazkami?
        //binding.imgHistoryProduct;
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = CalculatorItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: ProductDB = dataSet.value!![position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = dataSet.value?.size?:0
}