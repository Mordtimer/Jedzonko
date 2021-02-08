package com.example.jedzonko.model.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CalculatorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(calculatorDB: CalculatorDB)

    @Delete
    suspend fun delete(calculatorDB: CalculatorDB)

    @Query("DELETE FROM calculatorTable")
    suspend fun emptyCalculator()

    @Query("SELECT * FROM calculatorTable")
    fun all(): LiveData<List<CalculatorDB>>
}