package com.example.jedzonko.model

import com.google.gson.annotations.SerializedName

data class Request(
        val product: Product,
        @SerializedName("status_verbose")
        val Status: String
)