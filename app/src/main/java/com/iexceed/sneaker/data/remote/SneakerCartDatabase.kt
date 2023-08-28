package com.iexceed.sneaker.data.remote

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iexceed.sneaker.domain.model.SneakerCart


@Database(entities = [SneakerCart::class], version = 1, exportSchema = false)
abstract class SneakerCartDatabase : RoomDatabase() {
    abstract fun cartDao(): SneakerCartDao
    companion object {
        @Volatile
        private var INSTANCE: SneakerCartDatabase? = null
        fun getDatabase(context: Context): SneakerCartDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SneakerCartDatabase::class.java,
                    "sneaker_cart_table"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}