package com.example.jedzonko.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import androidx.room.Database
import com.example.jedzonko.model.Product
import com.example.jedzonko.model.ProductRepository
import com.example.jedzonko.model.Request
import com.example.jedzonko.model.database.MyDatabase
import com.example.jedzonko.model.database.NutrimentDB
import com.example.jedzonko.model.database.ProductDB
import com.example.jedzonko.model.database.repository.ProductDbRepository
import com.example.jedzonko.view.DetailsFragmentArgs
import com.example.jedzonko.view.ProductFragmentArgs
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Math.round
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.reflect.full.memberProperties

class DetailsVM(application: Application, private val repository: ProductRepository) : AndroidViewModel(application) {

    val productResponse: MutableLiveData<Response<Request>> = MutableLiveData()
    var productCode: String = ""
    lateinit var product: Product
    var activeList: MutableLiveData<Map<String, String>> = MutableLiveData(emptyMap())

    private val productRepository: ProductDbRepository =
        ProductDbRepository(MyDatabase.getDatabase(application).productDao())

    fun getNutriments(): Map<String, String> {
        // tworzy liste atrybutów klasy nutriments czyli wartości odżywczych
        val tmp = product.nutriments.javaClass.kotlin.memberProperties
        val names = mutableListOf<String>()
        val values = mutableListOf<String>()
        tmp.forEach {
            // Jeżeli wartosć danego atrybutu jest różna od null dodaje go do listy
            if (it.get(product.nutriments) != null) {
                val str = it.name
                val df = DecimalFormat("#.###")
                df.roundingMode = RoundingMode.CEILING
                //TODO crashuje nie wiadomo czemu

                var value = it.get(product.nutriments).toString().toBigDecimal()
                var strValue: String

                if(value == BigDecimal.ZERO){
                    strValue = "-"
                }
                else if (value < BigDecimal(0.001)){

                    value *= BigDecimal(1000000)
                    value = value.setScale(0, RoundingMode.CEILING)
                    strValue = value.toString() + "µm"
                }
                else if (value < BigDecimal(1)){

                    value *= BigDecimal(1000)
                    value = value.setScale(0, RoundingMode.CEILING)
                    strValue = value.toString() + "mg"
                }
                else{
                    value = value.setScale(0, RoundingMode.CEILING)
                    strValue = value.toString() + "g"
                }

                //names.add(str)
                values.add(strValue)
                names.add(str.removeRange(str.length - 4, str.length))
            }
        }
        return names.zip(values).toMap()
    }

    fun getIngredients(): Map<String, String> {
        val names = mutableListOf<String>()
        val values = mutableListOf<String>()
        product.ingredients.toMutableList().forEach {
                names.add(it.removeRange(0,3))
                values.add("")
            }
        // zwraca zmapowaną liste stringów
        return names.zip(values).toMap()
    }


    fun setCode(code: String) {
        productCode = code;
    }

    fun getProductFromApi() {
        viewModelScope.launch {
            val response = repository.getProduct(productCode)
            productResponse.value = response
            product = response.body()!!.product
        }
    }
}
