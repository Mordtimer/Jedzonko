package com.example.jedzonko.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.jedzonko.databinding.CalculatorItemBinding
import com.example.jedzonko.model.database.ProductDB
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.jedzonko.R
import com.example.jedzonko.model.database.CalculatorDB
import com.example.jedzonko.util.Constants
import com.example.jedzonko.view.adapter.getBitmap
import com.example.jedzonko.viewModel.CalculatorVM
import kotlinx.coroutines.*

class CalculatorAdapter(private val dataSet: LiveData<List<ProductDB>>, private val quantity: LiveData<List<CalculatorDB>>, private val vm: CalculatorVM): RecyclerView.Adapter<CalculatorAdapter.ViewHolder>(),
    getBitmap {

    lateinit var binding: CalculatorItemBinding

    inner class ViewHolder(private val binding: CalculatorItemBinding): RecyclerView.ViewHolder(binding.root){
        init {
            // Nawigacja
            binding.rowCalculator.setOnClickListener {
                val currentBarcode = dataSet.value!![adapterPosition].barcode
                val action = CalculatorFragmentDirections.actionCalculatorFragmentToProductFragment(currentBarcode)
                if (binding.root.findNavController().currentDestination?.id == R.id.calculatorFragment) {
                     findNavController(binding.root).navigate(action)
                }
            }
        }

        fun bind(product: ProductDB){
            binding.tvCalculatorName.text = product.productName
            //todo: naprawić jednostki może dodać buttony ++ / --

            // If naprawia buga po którym wywalało NullPointerException
            if(quantity.value != null) {
                for (q in quantity.value!!) {
                    if (q.barcode == product.barcode) {
                        binding.tvCalculatorMass.text =
                            (Constants.NUTRIMENT_BASE * q.quantity).toString()
                        binding.tvNumber.text = q.quantity.toString()
                    }
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
            val result: Deferred<android.graphics.Bitmap?> = GlobalScope.async {
                getBitmapFromURL(product.label)
            }
            GlobalScope.launch(Dispatchers.Main) {
                val bitmap = result.await()
                bitmap.apply {
                    binding.imgCalculatorProduct.setImageBitmap(bitmap)
                }
            }
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