package com.jkdajac.cookingnote.database.firstbluda

import androidx.room.*


@Dao
interface FirstbludaDao {
    @Query("SELECT * FROM firstbluda")
    fun getAll(): List<Firstbluda>

    @Insert
    fun insertFirstbluda(firstbluda: Firstbluda)

    @Delete
    fun deleteFirstbluda(firstbluda: Firstbluda)

    @Update
    fun updateFirstbluda(firstbluda: Firstbluda)

    @Query("SELECT * FROM Firstbluda WHERE id = :id")
    fun getFirstbludaById(id: Int): Firstbluda?
}