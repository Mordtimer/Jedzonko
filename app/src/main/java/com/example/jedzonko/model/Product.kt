package com.example.jedzonko.model

import com.google.gson.annotations.SerializedName

data class Product (
    @SerializedName("ingredients_hierarchy")
    val ingredients: List<String>,
    @SerializedName("code")
    val barcode: String,
    @SerializedName("product_name")
    val Name: String,
    @SerializedName("nutriments")
    val nutriments : Nutriments,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("image_small_url")
    val imageSmallUrl: String,
    @SerializedName("nutrient_levels")
    val nutrimentsLevel: NutrientLevel,
    @SerializedName("nutriscore_grade")
    val nutrimentGrade: String
)
