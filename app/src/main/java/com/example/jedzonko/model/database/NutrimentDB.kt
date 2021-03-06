package com.example.jedzonko.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nutrimentTable",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = ProductDB::class,
            parentColumns = ["barcode"],
            childColumns = ["barcode"],
            onDelete = androidx.room.ForeignKey.CASCADE
        )
    ])
class NutrimentDB(@PrimaryKey() val id: String, val energykcal:String?, val salt: String?, val sugars: String?,
                  val fat:String?, val saturatedFat:String?, var barcode:String)