package com.jkdajac.cookingnote.database.sous

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Sous (
    val englishWord: String,
    val translateWord: String
)

{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}