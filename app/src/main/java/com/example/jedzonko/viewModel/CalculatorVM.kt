package com.example.jedzonko.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jedzonko.model.ProductRepository
import com.example.jedzonko.model.Request
import com.example.jedzonko.model.database.MyDatabase
import com.example.jedzonko.model.database.NutrimentDB
import com.example.jedzonko.model.database.ProductDB
import com.example.jedzonko.model.database.repository.CalculatorDbRepository
import com.example.jedzonko.model.database.repository.ProductDbRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*

class CalculatorVM(application: Application) : AndroidViewModel(application) {

    //private val productRepository: CalculatorDbRepository =
      //  CalculatorDbRepository(MyDatabase.getDatabase(application).calculatorDao(), MyDatabase.getDatabase(application).productDao())
    //val products: LiveData<ProductDB> = productRepository.readAll()
}