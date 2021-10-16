package com.jkdajac.cookingnote.database.juice

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface JuiceDao {
    @Query("SELECT * FROM Juice")
    fun getAll(): List<Juice>

    @Insert
    fun insertJuice(juice: Juice)

    @Delete
    fun deleteJuice(juice: Juice)
}