package com.jkdajac.cookingnote.database.salad

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Salad (
    val englishWord: String,
    val translateWord: String
)

{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}