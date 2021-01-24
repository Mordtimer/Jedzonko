package com.example.jedzonko.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jedzonko.R
import android.util.Log
import com.example.jedzonko.api.ApiRequest
import com.example.jedzonko.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun getProduct(){
        val api = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRequest::class.java)

        GlobalScope.launch(Dispatchers.IO){
           val response = api.getProduct("3228857000166").awaitResponse()
           // val response = api.getProduct("5900334008381").awaitResponse()
            if(response.isSuccessful){
                val data = response.body()
                Log.d("Product: ", data.toString() + data?.product.toString())
            }
        }
    }
}