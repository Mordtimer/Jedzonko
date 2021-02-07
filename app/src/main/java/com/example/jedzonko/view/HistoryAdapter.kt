package com.example.jedzonko.view

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.drawToBitmap
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.jedzonko.databinding.HistoryItemBinding
import com.example.jedzonko.model.database.ProductDB
import kotlinx.coroutines.*
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


class HistoryAdapter(private val dataSet: LiveData<List<ProductDB>>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

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
            val result: Deferred<Bitmap?> = GlobalScope.async {
                getBitmapFromURL(product.label)
            }
            binding.tvHistoryProductName.text = product.productName
            binding.tvDate.text = product.date.toString()

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

    suspend fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.getInputStream()
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            null
        }
    }
}