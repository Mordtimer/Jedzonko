package com.example.jedzonko.api

import com.example.jedzonko.model.Request
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequest {
    @GET("product/{barcode}.json")
    suspend fun getProduct(@Path("barcode") barcode: String): Response<Request>
}