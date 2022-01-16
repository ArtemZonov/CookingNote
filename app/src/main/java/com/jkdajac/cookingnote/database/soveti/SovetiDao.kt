package com.jkdajac.cookingnote.database.soveti

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface SovetiDao {
    @Query("SELECT * FROM Soveti")
    fun getAll(): List<Soveti>

    @Insert
    fun insertSoveti(soveti: Soveti)

    @Delete
    fun deleteSoveti(soveti: Soveti)
}