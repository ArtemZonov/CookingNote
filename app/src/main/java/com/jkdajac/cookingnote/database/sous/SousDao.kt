package com.jkdajac.cookingnote.database.sous

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface SousDao {
    @Query("SELECT * FROM Sous")
    fun getAll(): List<Sous>

    @Insert
    fun insertSous(sous: Sous)

    @Delete
    fun deleteSous(sous: Sous)
}