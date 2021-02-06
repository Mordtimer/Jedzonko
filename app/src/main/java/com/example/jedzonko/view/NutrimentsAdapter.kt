package com.example.jedzonko.view

import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.jedzonko.model.Product
import com.example.jedzonko.model.database.ProductDB
import kotlin.reflect.KCallable

class NutrimentsAdapter(private val dataSet: LiveData<Product>):
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private val nutriments: Collection<KCallable<*>> get(){
        val tmp = dataSet.value!!.nutriments?.javaClass?.kotlin?.members

        tmp?.forEach {
           // it.
        }
        return tmp
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}