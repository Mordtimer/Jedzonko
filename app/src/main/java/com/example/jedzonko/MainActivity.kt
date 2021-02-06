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
    //private lateinit

   // private fun getProduct(){
        /*
        GlobalScope.launch(Dispatchers.IO){
           val response = api.getProduct("3228857000166")
           // val response = api.getProduct("5900334008381").awaitResponse()
            if(response.isSuccessful){
                val data = response.body()
                Log.d("Product: ", data.toString() + data?.product.toString())

        }


    }*/
}