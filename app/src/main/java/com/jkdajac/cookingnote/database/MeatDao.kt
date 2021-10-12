package com.jkdajac.cookingnote.database

import androidx.room.*

@Dao
interface MeatDao {
    @Query("SELECT * FROM meat")
    fun getAll(): List<Meat>

    @Insert
    fun insertMeat(meat:Meat)

    @Delete
    fun deleteMeat(meat:Meat)

    @Update
    fun updateMeat(meat:Meat)

    @Query("SELECT * FROM Meat WHERE id = :id")
    fun getMeatById(id: Int): Meat?
}