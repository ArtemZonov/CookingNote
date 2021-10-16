package com.jkdajac.cookingnote.database.salad

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface SaladDao {
    @Query("SELECT * FROM Salad")
    fun getAll(): List<Salad>

    @Insert
    fun insertSalad(salad: Salad)

    @Delete
    fun deleteSalad(salad: Salad)
}