package com.jkdajac.cookingnote.firstbluda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.database.AppDatabase
import com.jkdajac.cookingnote.database.MyIntentConstance
import com.jkdajac.cookingnote.database.Word
import kotlinx.android.synthetic.main.activity_edit_firstbluda.*

class EditFirstbludaActivity : AppCompatActivity() {

    lateinit var bludaDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_firstbluda)

        getMyIntents()
        bludaDatabase = AppDatabase.getDatabase(this)

        floatingFirstbludaSaved.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            floatingFirstbludaSaved.startAnimation(animation)

            if (etEditFirstbludaName.text.isNotEmpty() && etEditFirstbludaContent.text.isNotEmpty()) {
                val englishWord: String = etEditFirstbludaName.text.toString()
                val translateWord: String = etEditFirstbludaContent.text.toString()


                val word = Word(englishWord = englishWord, translateWord = translateWord)
                Toast.makeText(this, "Ваш рецепт записан !", Toast.LENGTH_LONG)
                    .show()
               bludaDatabase.wordDao().insertWord(word)

                val intent = Intent(this, FirstBludaActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this, "Пожалуйста, заполните пустые поля !",
                    Toast.LENGTH_LONG
                ).show()
            }
            finish()
        }
    }

    fun getMyIntents() {

        val i = intent

        if (i != null) {
            if (i.getStringExtra(MyIntentConstance.I_BLUDANAME_KEY) != null) {
                etEditFirstbludaName.setText(i.getStringExtra(MyIntentConstance.I_BLUDANAME_KEY))
                etEditFirstbludaContent.setText(i.getStringExtra(MyIntentConstance.I_BLUDACONTENT_KEY))
            }
        }
    }
}