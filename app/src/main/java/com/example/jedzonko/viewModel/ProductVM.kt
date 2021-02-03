package com.example.jedzonko.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jedzonko.model.Product
import com.example.jedzonko.model.ProductRepository
import com.example.jedzonko.model.Request
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class ProductVM(application: Application, private val repository: ProductRepository) : AndroidViewModel(application) {

    val productResponse: MutableLiveData<Response<Request>> = MutableLiveData()

    fun getProduct(barcode: String) {
        viewModelScope.launch {
            val response = repository.getProduct(barcode)
            productResponse.value = response
        }
    }
}