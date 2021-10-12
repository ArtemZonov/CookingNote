package com.jkdajac.cookingnote.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Meat(
    val etEditMeatName: String,
    val etEditMeatContent: String
)

{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}