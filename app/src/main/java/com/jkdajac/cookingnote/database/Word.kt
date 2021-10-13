package com.jkdajac.cookingnote.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Word(
    val englishWord: String,
    val translateWord: String
        )

{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}