package com.example.jedzonko.model.database.repository

import androidx.lifecycle.LiveData
import com.example.jedzonko.model.database.NutrimentDB
import com.example.jedzonko.model.database.ProductDB
import com.example.jedzonko.model.database.ProductDao

class ProductDBRepository(private val productDao: ProductDao) {
    val readAll: LiveData<List<ProductDB>> = productDao.all()

    suspend fun add(product: ProductDB, nutriment: NutrimentDB) {
        val id = productDao.insertProduct(product)
        nutriment.productId = id.toInt()
        productDao.insertNutriment(nutriment)
    }

    suspend fun delete(product: ProductDB)=productDao.delete(product)
}