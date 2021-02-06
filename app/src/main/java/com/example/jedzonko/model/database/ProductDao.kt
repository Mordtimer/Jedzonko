package com.example.jedzonko.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import java.util.*

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNutriment(nutriment: NutrimentDB)

    @Delete
    suspend fun delete(product: ProductDB)

    @Query("SELECT * FROM productTable")
    fun all():LiveData<List<ProductDB>>

    @Query("SELECT * FROM productTable WHERE barcode == :barcode")
    fun getProductFromBarcode(barcode: String):LiveData<List<ProductDB>>

    @Query("SELECT * FROM nutrimentTable WHERE barcode == :barcode")
    fun getNutrimentFromBarcode(barcode: String):LiveData<List<NutrimentDB>>

    @Update()
    fun updateProduct(product: ProductDB)
}