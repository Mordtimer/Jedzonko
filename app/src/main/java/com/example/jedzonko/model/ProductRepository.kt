package com.example.jedzonko.model

import com.example.jedzonko.api.ApiRequest
import com.example.jedzonko.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Response
import retrofit2.awaitResponse

//private val api: ApiRequest
class ProductRepository() {
    suspend fun getProduct(barcode: String): Response<Request> {
        return RetrofitInstance.api.getProduct("5900334008381")
    }
}