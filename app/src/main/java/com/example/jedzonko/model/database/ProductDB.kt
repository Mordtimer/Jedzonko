package com.example.jedzonko.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "productTable")
data class ProductDB(@PrimaryKey() val barcode:String, val productName:String?, val label: String?, val nutriScore: String?, var date:Date)