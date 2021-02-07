package com.example.jedzonko.view.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.jedzonko.databinding.HistoryItemBinding
import com.example.jedzonko.model.database.ProductDB
import com.example.jedzonko.view.HistoryFragmentDirections
import kotlinx.coroutines.*
import java.time.LocalDate
import java.util.*


class HistoryAdapter(private val dataSet: LiveData<List<ProductDB>>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>(), Bitmap {

    lateinit var binding: HistoryItemBinding

    inner class ViewHolder(private val binding: HistoryItemBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.rowHistoryItem.setOnClickListener {
                val currentBarcode = dataSet.value!![adapterPosition].barcode
                val action = HistoryFragmentDirections.actionHistoryFragmentToProductFragment(
                    currentBarcode
                )
                findNavController(binding.root).navigate(action)
            }
        }

        fun bind(product: ProductDB){
            val result: Deferred<android.graphics.Bitmap?> = GlobalScope.async {
                getBitmapFromURL(product.label)
            }
            binding.tvHistoryProductName.text = product.productName
            var tmpDate = product.date.toString()
            tmpDate = tmpDate.removeRange(10, tmpDate.length).removeRange(0,4)
            binding.tvDate.text = tmpDate

            GlobalScope.launch(Dispatchers.Main) {
                val bitmap = result.await()
                bitmap.apply {
                    binding.imgHistoryProduct.setImageBitmap(bitmap)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: ProductDB = dataSet.value?.get(position) ?:
        ProductDB("", "", "", Date())
        holder.bind(product)
    }

    override fun getItemCount(): Int = dataSet.value?.size?:0

}