package com.example.jedzonko.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.jedzonko.R
import com.example.jedzonko.databinding.HistoryItemBinding
import com.example.jedzonko.model.database.ProductDB
import java.io.InputStream
import java.net.URL
import java.util.*

class HistoryAdapter(private val dataSet: LiveData<List<ProductDB>>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    lateinit var binding: HistoryItemBinding

    inner class ViewHolder(private val binding: HistoryItemBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.rowHistoryItem.setOnClickListener {
                val currentBarcode = dataSet.value?.get(adapterPosition)?.barcode
                val action = HistoryFragmentDirections.actionHistoryFragmentToProductFragment(currentBarcode!!)
                Navigation.findNavController(it).navigate(action)
            }
        }

        fun bind(product: ProductDB){
            binding.tvHistoryProductName.text = product.productName
            binding.tvDate.text = product.date.toString()
            //todo
            /*skąd wziąć url??
            var bitmap: Bitmap = BitmapFactory.decodeStream((InputStream)new URL(product.))
            binding.imgHistoryProduct.
             */
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(dataSet.value!!.get(position))
    }

    override fun getItemCount(): Int = dataSet.value?.size?:0
}