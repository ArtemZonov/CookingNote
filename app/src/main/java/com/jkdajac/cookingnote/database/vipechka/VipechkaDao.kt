package com.jkdajac.cookingnote.database.vipechka

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jkdajac.cookingnote.database.salad.Salad

@Dao
interface VipechkaDao {
    @Query("SELECT * FROM Vipechka")
    fun getAll(): List<Vipechka>

    @Insert
    fun insertVipechka(vipechka: Vipechka)

    @Delete
    fun deleteVipechka(vipechka: Vipechka)
}