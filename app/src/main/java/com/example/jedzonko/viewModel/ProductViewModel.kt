package com.example.jedzonko.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jedzonko.model.MyDatabase
import com.example.jedzonko.model.Product
import com.example.jedzonko.model.repository.ProductRepository
import kotlinx.coroutines.launch
import java.util.*

class ProductViewModel(application: Application): AndroidViewModel(application) {
    var productCode:String = ""

    private val productRepository: ProductRepository =
        ProductRepository(MyDatabase.getDatabase(application).productDao())

    fun addProduct(){
        val date: Date = Calendar.getInstance().time
        viewModelScope.launch {
            productRepository.add(Product(id=0, productCode,null, date))
        }
    }
}