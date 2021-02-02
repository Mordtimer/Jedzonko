package com.example.jedzonko.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "productTable")
data class ProductDB(@PrimaryKey(autoGenerate = true) val id: Int, val barcode:String, val productName:String?, val date:Date)