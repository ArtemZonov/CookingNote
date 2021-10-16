package com.jkdajac.cookingnote.database.desert

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Desert(
    val englishWord: String,
    val translateWord: String
)

{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}