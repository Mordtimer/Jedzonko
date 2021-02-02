package com.example.jedzonko.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import androidx.room.Dao
import java.util.*

@Dao
interface ProductDao {

    @Insert
    suspend fun insert(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Query("SELECT * FROM productTable")
    fun all():LiveData<List<Product>>

    //@Update("")
}