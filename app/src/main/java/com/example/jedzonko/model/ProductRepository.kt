package com.example.jedzonko.model

import retrofit2.awaitResponse

class ProductRepository(private val api: ApiRequest) {
    suspend fun getProduct(barcode: String):Request?{
        val data = api.getProduct(barcode).awaitResponse()

        if(data.isSuccessful)
            return data.body()!!
        return null
    }
}