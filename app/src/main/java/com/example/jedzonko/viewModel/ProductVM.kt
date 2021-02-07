package com.example.jedzonko.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jedzonko.model.Product
import com.example.jedzonko.model.ProductRepository
import com.example.jedzonko.model.Request
import com.example.jedzonko.model.database.MyDatabase
import com.example.jedzonko.model.database.NutrimentDB
import com.example.jedzonko.model.database.ProductDB
import com.example.jedzonko.model.database.repository.ProductDbRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*
import kotlin.reflect.full.memberProperties

class ProductVM(application: Application, private val repository: ProductRepository) : AndroidViewModel(application) {

    val productResponse: MutableLiveData<Response<Request>> = MutableLiveData()
    var productCode:String = ""
    val product:MutableLiveData<Product> = MutableLiveData()

    private val productRepository: ProductDbRepository =
            ProductDbRepository(MyDatabase.getDatabase(application).productDao())


    fun getNutriments(): Map<String, String> {
        // tworzy liste atrybutów klasy nutriments czyli wartości odżywczych
        val tmp = product.value!!.nutriments.javaClass.kotlin.memberProperties
        val names = mutableListOf<String>()
        val values = mutableListOf<String>()
        tmp.forEach{
            // Jeżeli wartosć danego atrybutu jest różna od null dodaje go do listy
            if(it.get(product.value!!.nutriments) != null){
                names.add(it.name)
                values.add(it.get(product.value!!.nutriments).toString())
            }
        }
        return names.zip(values).toMap()
    }

    fun getIngredients(): Map<String, String> {
        // tworzy liste atrybutów klasy ingredients czyli składników
        val tmp = product.value!!.ingredients.javaClass.kotlin.memberProperties
        val names = mutableListOf<String>()
        val values = mutableListOf<String>()
        tmp.forEach{
            // Jeżeli wartosć danego atrybutu jest różna od null dodaje go do listy
            if(it.get(product.value!!.ingredients) != null){
                names.add(it.name)
                values.add(it.get(product.value!!.ingredients).toString())
            }
        }
        // zwraca zmapowaną liste stringów
        return names.zip(values).toMap()
    }

    fun addProduct(){
        if(productResponse.value?.body() != null) {
            val productResult = productResponse.value!!.body()!!.product
            val date: Date = Calendar.getInstance().time

            viewModelScope.launch {
                productRepository.add(ProductDB(productCode,productResult.Name, date),
                        NutrimentDB(productCode+"N",
                        productResult.nutriments.energykcal100g,
                        productResult.nutrimentsLevel.salt,
                        productResult.nutrimentsLevel.sugars,
                        productResult.nutrimentsLevel.fat,
                        productResult.imageSmallUrl,productCode))
            }
            product.value = productResponse.value!!.body()!!.product
        }
    }

    fun setCode(code: String){
        productCode = code;
    }

    fun getProductFromApi() {
        viewModelScope.launch {
            val response = repository.getProduct(productCode)
            productResponse.value = response
        }
    }
}