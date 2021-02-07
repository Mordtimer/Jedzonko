package com.example.jedzonko.model.database.repository

import androidx.lifecycle.LiveData
import com.example.jedzonko.model.database.*

class CalculatorDbRepository(private val calculatorDao: CalculatorDao, private val productDao: ProductDao) {
    val readAll: LiveData<List<CalculatorDB>> = calculatorDao.all()

    suspend fun addProductToCalculator(product: ProductDB, nutriment: NutrimentDB, calculator: CalculatorDB){
        productDao.insertProduct(product)
        productDao.insertNutriment(nutriment)
        calculatorDao.insert(calculator)
    }

    fun getCalculatorProducts():LiveData<List<ProductDB>>{
        return productDao.getCalculatorProducts()
    }

    suspend fun update(calculator: CalculatorDB){
        calculatorDao.insert(calculator)
    }

    suspend fun deleteProductFromCalculator(calculator: CalculatorDB){
        calculatorDao.delete(calculator)
    }
}