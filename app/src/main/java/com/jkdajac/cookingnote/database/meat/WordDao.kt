package com.jkdajac.cookingnote.database.meat

import androidx.room.*
import com.jkdajac.cookingnote.database.meat.Word


@Dao
    interface WordDao {
        @Query("SELECT * FROM word")
        fun getAll(): List<Word>

        @Insert
        fun insertWord(word: Word)

        @Delete
        fun deleteWord(word : Word)

        @Update
        fun updateWord(word : Word)

        @Query("SELECT * FROM Word WHERE id = :id")
        fun getWordById(id: Int): Word?
    }
