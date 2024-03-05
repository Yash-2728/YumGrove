package com.example.yumgrove

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Recipe::class ], exportSchema = false, version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getDao():Dao


    companion object{
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if(INSTANCE !=null) return INSTANCE!!

            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "database")
                .allowMainThreadQueries()
                .build()

            INSTANCE = instance
            return instance
        }
    }
}