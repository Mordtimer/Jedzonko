package com.example.jedzonko.model.database

import androidx.lifecycle.LiveData
import androidx.room.*

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
    fun getProductFromBarcode(barcode: String):LiveData<ProductDB>

    @Query("SELECT * FROM nutrimentTable WHERE barcode == :barcode")
    fun getNutrimentFromBarcode(barcode: String):LiveData<NutrimentDB>

    @Query("Select nutrimentTable.id, energykcal, salt, sugars, fat, saturatedFat, nutrimentTable.barcode from nutrimentTable inner join calculatorTable")
    fun allNutriments():LiveData<List<NutrimentDB>>

    @Query("Select id, productTable.barcode, productName, label, date from productTable inner join calculatorTable on productTable.barcode=calculatorTable.barcode")
    fun getCalculatorProducts():LiveData<List<ProductDB>>

    @Update()
    fun updateProduct(product: ProductDB)
}