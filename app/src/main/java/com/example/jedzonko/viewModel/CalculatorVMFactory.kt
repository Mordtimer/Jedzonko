package com.example.jedzonko.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jedzonko.model.ProductRepository

class CalculatorVMFactory(
    private val application: Application,
): ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CalculatorVM(application) as T
    }
}