package com.example.jedzonko.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import androidx.room.Dao
import java.util.*

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