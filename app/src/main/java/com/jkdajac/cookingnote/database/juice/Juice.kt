package com.jkdajac.cookingnote.database.juice

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Juice(
    val englishWord: String,
    val translateWord: String
)

{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}