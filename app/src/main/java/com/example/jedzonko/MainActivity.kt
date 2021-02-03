package com.example.jedzonko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jedzonko.model.ProductRepository
import com.example.jedzonko.viewModel.ProductVMFactory

class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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