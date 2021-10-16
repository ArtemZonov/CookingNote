package com.jkdajac.cookingnote.database.desert

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jkdajac.cookingnote.database.salad.Salad
import com.jkdajac.cookingnote.database.salad.SaladDao


@Database(entities = arrayOf(Desert::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun desertDao(): DesertDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "desert_database")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}