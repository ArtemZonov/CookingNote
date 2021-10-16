package com.jkdajac.cookingnote.database.zakuski

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Zakuski (
    val englishWord: String,
    val translateWord: String
)

{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}