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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCalculator(calculatorDB: CalculatorDB)

    @Delete
    suspend fun delete(product: ProductDB)

    @Query("SELECT * FROM productTable")
    fun all():LiveData<List<ProductDB>>

    @Query("SELECT * FROM productTable WHERE barcode == :barcode")
    fun getFromBarcode(barcode: String):LiveData<List<ProductDB>>
/*
    @Query("UPDATE productTable SET date = :date WHERE barcode == :barcode")
    fun updateDate(date:Date, barcode: String):LiveData<List<ProductDB>>
*/
    @Update()
    fun updateProduct(product: ProductDB)
}