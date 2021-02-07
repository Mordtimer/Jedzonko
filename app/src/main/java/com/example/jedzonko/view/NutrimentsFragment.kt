package com.example.jedzonko.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.jedzonko.databinding.NutrimentsFragmentBinding
import com.example.jedzonko.databinding.ProductFragmentBinding
import com.example.jedzonko.viewModel.ProductVM

class NutrimentsFragment : Fragment() {
    private var _binding: NutrimentsFragmentBinding? = null
    private lateinit var viewModel: ProductVM
    private lateinit var recyclerView: RecyclerView

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NutrimentsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}