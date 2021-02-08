package com.example.jedzonko.model

import com.example.jedzonko.api.RetrofitInstance
import retrofit2.Response

//private val api: ApiRequest
class ProductRepository() {
    suspend fun getProduct(barcode: String): Response<Request> {
        return RetrofitInstance.api.getProduct(barcode)
    }
}