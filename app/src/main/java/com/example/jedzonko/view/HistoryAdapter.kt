package com.example.jedzonko.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.jedzonko.databinding.HistoryItemBinding
import com.example.jedzonko.model.database.ProductDB
import java.util.*

class HistoryAdapter(private val dataSet: LiveData<List<ProductDB>>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    lateinit var binding: HistoryItemBinding

    inner class ViewHolder(private val binding: HistoryItemBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.rowHistoryItem.setOnClickListener {
                val currentBarcode = dataSet.value?.get(adapterPosition)?.barcode
            // TODO Zaimplementować nawigacje (Marcin nie utworzył jeszcze klasy od fragmentu i nie mogłem tego zrobić)
                //val action = HistoryFragmentDirections.
                //actionHistoryFragmentToProduct(currentBarcode)
                //findNavController.navigate(action)

            }
        }

        fun bind(product: ProductDB){
            binding.tvHistoryProductName.text = product.productName
            binding.tvDate.text = product.date.toString()
        // TODO co zrobić z obrazkami?
        //binding.imgHistoryProduct;
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: ProductDB = dataSet.value?.get(position) ?:
        ProductDB("","", Date())
        holder.bind(product)
    }

    override fun getItemCount(): Int = dataSet.value?.size?:0
}