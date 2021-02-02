package com.example.jedzonko.model.repository

import androidx.lifecycle.LiveData
import com.example.jedzonko.model.Product
import com.example.jedzonko.model.ProductDao
import java.util.*

class ProductRepository(private val productDao: ProductDao) {
    val readAll: LiveData<List<Product>> = productDao.all()

    suspend fun add(product: Product) {
        productDao.insert(product)
    }

    suspend fun delete(product: Product)=productDao.delete(product)
}