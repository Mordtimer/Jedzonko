package com.example.jedzonko.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ProductViewModel(application: Application): AndroidViewModel(application) {
    var productCode:String? = null
}