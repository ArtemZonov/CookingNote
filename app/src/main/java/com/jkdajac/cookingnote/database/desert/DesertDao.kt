package com.jkdajac.cookingnote.database.desert

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jkdajac.cookingnote.database.salad.Salad


@Dao
interface DesertDao {
    @Query("SELECT * FROM Desert")
    fun getAll(): List<Desert>

    @Insert
    fun insertDesert(desert: Desert)

    @Delete
    fun deleteDesert(desert: Desert)
}