package com.example.jedzonko.model.database.repository

import androidx.lifecycle.LiveData
import com.example.jedzonko.model.database.CalculatorDB
import com.example.jedzonko.model.database.NutrimentDB
import com.example.jedzonko.model.database.ProductDB
import com.example.jedzonko.model.database.ProductDao

class ProductDBRepository(private val productDao: ProductDao) {
    val readAll: LiveData<List<ProductDB>> = productDao.all()

    suspend fun add(product: ProductDB, nutriment: NutrimentDB) {
            productDao.insertProduct(product)
            productDao.insertNutriment(nutriment)
    }

    fun getProduct(barcode:String) : LiveData<ProductDB> = productDao.getProductFromBarcode(barcode)

    fun getNutriment(barcode:String) : LiveData<NutrimentDB> = productDao.getNutrimentFromBarcode(barcode)

    suspend fun deleteProduct(product: ProductDB) = productDao.delete(product)
}