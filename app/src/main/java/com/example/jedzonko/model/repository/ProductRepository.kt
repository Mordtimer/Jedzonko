package com.example.jedzonko.model.repository

import androidx.lifecycle.LiveData
import com.example.jedzonko.model.ProductDB
import com.example.jedzonko.model.ProductDao
import java.util.*

class ProductRepository(private val productDao: ProductDao) {
    val readAll: LiveData<List<ProductDB>> = productDao.all()

    suspend fun add(product: ProductDB) {
        productDao.insert(product)
    }

    suspend fun delete(product: ProductDB)=productDao.delete(product)
}