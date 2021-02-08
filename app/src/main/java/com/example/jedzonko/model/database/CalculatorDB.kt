package com.example.jedzonko.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calculatorTable",
        foreignKeys = [
            androidx.room.ForeignKey(
                    entity = ProductDB::class,
                    parentColumns = ["barcode"],
                    childColumns = ["barcode"],
                    onDelete = androidx.room.ForeignKey.CASCADE
            )
        ])
/*
* Aby dodać nowy rekord calculatora jako id użyć barcode+"C"
* */
class CalculatorDB(@PrimaryKey() val id: String, val quantity:Int, var barcode:String)