package com.example.jedzonko.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.jedzonko.model.ProductRepository
import com.example.jedzonko.model.database.MyDatabase
import com.example.jedzonko.model.database.ProductDB
import com.example.jedzonko.model.database.repository.ProductDbRepository

class HistoryVM(application: Application, private val repository: ProductRepository) : AndroidViewModel(application) {

    private val productRepository: ProductDbRepository =
        ProductDbRepository(MyDatabase.getDatabase(application).productDao())

    val products: LiveData<List<ProductDB>> = productRepository.readAll
}