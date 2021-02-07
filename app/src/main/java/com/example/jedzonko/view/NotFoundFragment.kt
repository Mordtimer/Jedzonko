package com.example.jedzonko.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jedzonko.R
import com.example.jedzonko.databinding.HistoryFragmentBinding
import com.example.jedzonko.databinding.NotFoundFragmentBinding
import com.example.jedzonko.databinding.NutrimentsFragmentBinding
import com.example.jedzonko.model.ProductRepository
import com.example.jedzonko.view.adapter.HistoryAdapter
import com.example.jedzonko.viewModel.HistoryVM
import com.example.jedzonko.viewModel.HistoryVMFactory

class NotFoundFragment : Fragment(R.layout.not_found_fragment) {
    private var _binding: NotFoundFragmentBinding? = null
    private val binding get() = _binding!!

    val args: NotFoundFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvBarcodeNotFound.text = args.barcode

        binding.buttonReturnToMenu.setOnClickListener {
            val action = NotFoundFragmentDirections.actionNotFoundFragmentToMainFragment()
            findNavController().navigate(action)
        }
        binding.buttonTryAgain.setOnClickListener {
            val action = NotFoundFragmentDirections.actionNotFoundFragmentToScanFragment()
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NotFoundFragmentBinding.inflate(
            layoutInflater, container, false)
        return binding.root
    }
}