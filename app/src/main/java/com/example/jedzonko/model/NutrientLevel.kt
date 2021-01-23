package com.example.jedzonko.model
import com.google.gson.annotations.SerializedName

data class NutrientLevel(
    @SerializedName("salt")
    val salt: String,
    @SerializedName("sugars")
    val sugars: String,
    @SerializedName("fat")
    val fat: String,
    @SerializedName("saturated-fat")
    val saturatedFat: String,
)