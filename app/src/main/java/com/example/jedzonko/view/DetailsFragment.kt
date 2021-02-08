package com.example.jedzonko.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jedzonko.R
import com.example.jedzonko.databinding.DetailsFragmentBinding
import com.example.jedzonko.model.ProductRepository
import com.example.jedzonko.view.adapter.SimpleAdapter
import com.example.jedzonko.viewModel.DetailsVM
import com.example.jedzonko.viewModel.DetailsVMFactory

class DetailsFragment : Fragment(R.layout.details_fragment) {
    private var _binding: DetailsFragmentBinding? = null
    private lateinit var viewModel: DetailsVM
    private lateinit var recyclerView: RecyclerView
    private lateinit var detailsAdapter: SimpleAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var isNutriment: Boolean = false
    val args: DetailsFragmentArgs by navArgs()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repository = ProductRepository()
        val vmFactory = DetailsVMFactory(requireActivity().application, repository)
        viewModel = ViewModelProvider(this,vmFactory).get(DetailsVM::class.java)

        viewManager = LinearLayoutManager(this.context)
        _binding = DetailsFragmentBinding.inflate(layoutInflater, container, false)
        viewModel.setCode(args.barcode)
        viewModel.getProductFromApi()
        detailsAdapter = SimpleAdapter(viewModel.activeList)


        // Initial Buttons status
        binding.apply {
            btNutriments.isClickable = true
            btNutriments.isEnabled = true
            btIngredients.isClickable = false
            btIngredients.isEnabled = false
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.productResponse.observe(viewLifecycleOwner,{
            viewModel.product = it.body()!!.product
            viewModel.activeList.value = viewModel.getIngredients()
        // TODO sypie się z obrazkiem
        // viewModel.getBitmap(viewModel.product.imageUrl)
        })

        viewModel.productResponse.observe(viewLifecycleOwner,{
            binding.tvDetailsProductName.text = viewModel.product.Name
            var imageView = view.findViewById<ImageView>(R.id.imgDetails);
            Glide.with(activity).load(viewModel.product.imageUrl).into(imageView);
        })


        binding.btIngredients.setOnClickListener {
            viewModel.activeList.value = viewModel.getIngredients()
            binding.apply {
                btNutriments.isClickable = true
                btNutriments.isEnabled = true
                btIngredients.isClickable = false
                btIngredients.isEnabled = false
            }
            recyclerView.swapAdapter(SimpleAdapter(viewModel.activeList), false)
        }

        binding.btNutriments.setOnClickListener {
                viewModel.activeList.value = viewModel.getNutriments()
                binding.apply{
                    btIngredients.isClickable = true
                    btIngredients.isEnabled = true
                    btNutriments.isClickable = false
                    btNutriments.isEnabled = false

                }
            recyclerView.swapAdapter(SimpleAdapter(viewModel.activeList), false)
        }

        recyclerView = binding.rvInfo
        recyclerView.apply{
            adapter = detailsAdapter
            layoutManager = viewManager

            }

        viewModel.image.observe(viewLifecycleOwner,{
            binding.imgDetails.setImageBitmap(it)
        })

        viewModel.activeList.observe(viewLifecycleOwner,{
            recyclerView.post { recyclerView.scrollToPosition(0) }
        })
        }

    }
