package com.jkdajac.cookingnote.database.firstbluda

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
class Firstbluda (
val englishWord: String,
val translateWord: String
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}