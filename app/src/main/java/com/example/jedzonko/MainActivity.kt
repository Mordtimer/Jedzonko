package com.example.jedzonko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.navigation.findNavController
import com.example.jedzonko.model.ProductRepository
import com.example.jedzonko.viewModel.ProductVMFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            this.supportActionBar?.hide()
        }
}