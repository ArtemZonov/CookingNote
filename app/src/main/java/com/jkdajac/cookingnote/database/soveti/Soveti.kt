package com.jkdajac.cookingnote.database.soveti

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Soveti (
    val englishWord: String,
    val translateWord: String
)

{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}