package com.jkdajac.cookingnote.database.vipechka

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Vipechka (
    val englishWord: String,
    val translateWord: String
)

{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}