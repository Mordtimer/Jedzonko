package com.example.jedzonko.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.jedzonko.model.ProductRepository
import com.example.jedzonko.model.database.CalculatorDB
import com.example.jedzonko.model.database.MyDatabase
import com.example.jedzonko.model.database.NutrimentDB
import com.example.jedzonko.model.database.repository.CalculatorDbRepository
import com.example.jedzonko.model.database.repository.ProductDbRepository

class SummaryVM(application: Application) : AndroidViewModel(application) {
    private val productRepository: ProductDbRepository =
        ProductDbRepository(MyDatabase.getDatabase(application).productDao())
    private val calcRepository: CalculatorDbRepository =
        CalculatorDbRepository(MyDatabase.getDatabase(application).calculatorDao(), MyDatabase.getDatabase(application).productDao())

    val nutriments: LiveData<List<NutrimentDB>> = productRepository.allNutriments()

    val quantities: LiveData<List<CalculatorDB>> = calcRepository.readAll
}