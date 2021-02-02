package com.example.jedzonko.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface ProductDao {

    @Insert
    suspend fun insert(product: ProductDB)

    @Delete
    suspend fun delete(product: ProductDB)

    @Query("SELECT * FROM productTable")
    fun all():LiveData<List<ProductDB>>

    //@Update("")
}