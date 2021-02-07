package com.example.jedzonko.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jedzonko.model.ProductRepository

class HistoryVMFactory(
    private val application: Application,
    private val repository: ProductRepository
): ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HistoryVM(application, repository) as T
    }
}