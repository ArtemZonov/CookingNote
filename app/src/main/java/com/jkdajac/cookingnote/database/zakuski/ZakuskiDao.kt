package com.jkdajac.cookingnote.database.zakuski

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query



@Dao
interface ZakuskiDao {
    @Query("SELECT * FROM Zakuski")
    fun getAll(): List<Zakuski>

    @Insert
    fun insertZakuski(zakuski: Zakuski)

    @Delete
    fun deleteZakuski(zakuski: Zakuski)
}