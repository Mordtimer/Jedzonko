package com.example.jedzonko.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jedzonko.R
import com.example.jedzonko.databinding.HistoryFragmentBinding
import com.example.jedzonko.model.ProductRepository
import com.example.jedzonko.model.database.ProductDB
import com.example.jedzonko.view.adapter.HistoryAdapter
import com.example.jedzonko.viewModel.HistoryVM
import com.example.jedzonko.viewModel.HistoryVMFactory

class HistoryFragment : Fragment(R.layout.history_fragment) {

    private var _binding: HistoryFragmentBinding? = null
    private lateinit var viewModel: HistoryVM
    private val binding get() = _binding!!

    private lateinit var  recyclerView: RecyclerView
    private lateinit var  historyAdapter: RecyclerView.Adapter<*>
    private lateinit var products: LiveData<List<ProductDB>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.rvHistory
        recyclerView.adapter = historyAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repository = ProductRepository()
        val vmFactory = HistoryVMFactory(requireActivity().application, repository)
        viewModel = ViewModelProvider(this,vmFactory).get(HistoryVM::class.java)
        historyAdapter = HistoryAdapter(viewModel.products)
        products = viewModel.products
        _binding = HistoryFragmentBinding.inflate(
            layoutInflater, container, false)
        products.observe(viewLifecycleOwner,{
            historyAdapter.notifyDataSetChanged()
        })
        return binding.root
    }
}