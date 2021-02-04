package com.example.jedzonko.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "nutrimentTable",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = NutrimentDB::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = androidx.room.ForeignKey.CASCADE
        )
    ])
class NutrimentDB(@PrimaryKey(autoGenerate = true) val id: Int, val energykcal:String?, val salt: String?, val sugars: String?,
                  val fat:String?, val label: String?, var productId:Int)