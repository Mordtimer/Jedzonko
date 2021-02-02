package com.example.jedzonko.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jedzonko.model.database.MyDatabase
import com.example.jedzonko.model.database.ProductDB
import com.example.jedzonko.model.database.repository.ProductDBRepository
import kotlinx.coroutines.launch
import java.util.*

class ProductViewModel(application: Application): AndroidViewModel(application) {
    var productCode:String = ""

    private val productRepository: ProductDBRepository =
        ProductDBRepository(MyDatabase.getDatabase(application).productDao())

    fun addProduct(){
        val date: Date = Calendar.getInstance().time
        viewModelScope.launch {
            productRepository.add(ProductDB(id=0, productCode,null, date))
        }
    }
}