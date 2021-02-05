package com.example.jedzonko.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalculatorDao {

    @Insert
    suspend fun insert(calculatorDB: CalculatorDB)

    @Delete
    suspend fun delete(calculatorDB: CalculatorDB)

    @Query("SELECT * FROM calculatorTable")
    fun all(): LiveData<List<CalculatorDB>>
}