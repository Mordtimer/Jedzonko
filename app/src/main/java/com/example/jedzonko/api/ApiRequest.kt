package com.example.jedzonko.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequest {
    @GET("product/{barcode}.json")
    fun getProduct(@Path("barcode") barcode: String): Call<Request>
}