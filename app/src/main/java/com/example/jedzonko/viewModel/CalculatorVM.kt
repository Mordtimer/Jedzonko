package com.example.jedzonko.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.jedzonko.model.database.CalculatorDB
import com.example.jedzonko.model.database.MyDatabase
import com.example.jedzonko.model.database.NutrimentDB
import com.example.jedzonko.model.database.ProductDB
import com.example.jedzonko.model.database.repository.CalculatorDbRepository
import com.example.jedzonko.model.database.repository.ProductDbRepository
import kotlinx.coroutines.launch

class CalculatorVM(application: Application) : AndroidViewModel(application) {

    private val productRepository: CalculatorDbRepository =
        CalculatorDbRepository(MyDatabase.getDatabase(application).calculatorDao(), MyDatabase.getDatabase(application).productDao())
    private val nutrimentRepository: ProductDbRepository =
        ProductDbRepository(MyDatabase.getDatabase(application).productDao())
    val products: LiveData<List<ProductDB>> = productRepository.getCalculatorProducts()
    val quantities: LiveData<List<CalculatorDB>> = productRepository.readAll
    val nutriments: LiveData<List<NutrimentDB>> = nutrimentRepository.allNutriments()

    fun changeQuantity(new:Int, barcode:String){
        viewModelScope.launch {
            productRepository.update(CalculatorDB(barcode+"C", new, barcode))
        }
    }

    fun deleteFromCalculator(calculatorDB: CalculatorDB){
        viewModelScope.launch {
            productRepository.deleteProductFromCalculator(calculatorDB)
        }
    }

    fun emptyCalculator(){
        viewModelScope.launch {
            productRepository.emptyCalculator()
        }
    }
}