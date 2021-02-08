package com.example.jedzonko.model.database


import android.content.Context
import androidx.room.*
import java.util.*

@Database(entities = [ProductDB::class, NutrimentDB::class, CalculatorDB::class],version = 2,exportSchema = false)
@TypeConverters(Converters::class)
abstract class MyDatabase:RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun calculatorDao(): CalculatorDao

    companion object{
        @Volatile
        private var INSTANCE: MyDatabase ?= null

        fun getDatabase(context: Context): MyDatabase {
            val tempInstance= INSTANCE

            if(tempInstance!=null)
                return tempInstance
            else
                synchronized(this)
                {
                    val instance= Room.databaseBuilder(
                            context.applicationContext,
                            MyDatabase::class.java,
                            "MyDatabase"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                    return instance
                }
        }
    }
}
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}