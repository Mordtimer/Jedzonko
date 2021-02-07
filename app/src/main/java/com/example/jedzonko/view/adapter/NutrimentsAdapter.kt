package com.example.jedzonko.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jedzonko.databinding.NutrimentsItemBinding

class NutrimentsAdapter(private val dataSet: Map<String, String>):
    RecyclerView.Adapter<NutrimentsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: NutrimentsItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(){
            binding.tvNutrimentName.text = dataSet.keys.toTypedArray()[adapterPosition]
            binding.tvNutrimentValue.text = dataSet.values.toTypedArray()[adapterPosition]
        }
    }

    lateinit var binding: NutrimentsItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = NutrimentsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = dataSet.keys.size

}