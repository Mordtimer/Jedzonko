package com.example.jedzonko.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jedzonko.model.Product
import com.example.jedzonko.model.ProductRepository
import com.example.jedzonko.model.Request
import com.example.jedzonko.model.database.MyDatabase
import com.example.jedzonko.model.database.ProductDB
import com.example.jedzonko.model.database.repository.ProductDBRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.util.*

class ProductVM(application: Application, private val repository: ProductRepository) : AndroidViewModel(application) {

    val productResponse: MutableLiveData<Response<Request>> = MutableLiveData()
    var productCode:String = ""

    private val productRepository: ProductDBRepository =
            ProductDBRepository(MyDatabase.getDatabase(application).productDao())

    fun addProduct(){
        val date: Date = Calendar.getInstance().time
        viewModelScope.launch {
            productRepository.add(ProductDB(id=0, productCode,null, date))
        }
    }

    fun setCode(code: String){
        productCode = code;
    }

    fun getProductFromApi() {
        viewModelScope.launch {
            val response = repository.getProduct(productCode)
            productResponse.value = response
        }
    }
}