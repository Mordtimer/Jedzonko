package com.example.jedzonko.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "productTable")
data class Product(@PrimaryKey(autoGenerate = true) val id: Int, val barcode:String, val productName:String?, val date:Date)