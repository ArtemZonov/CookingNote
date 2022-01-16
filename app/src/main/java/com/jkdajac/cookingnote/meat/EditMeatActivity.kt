package com.jkdajac.cookingnote.meat


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.database.meat.AppDatabase
import com.jkdajac.cookingnote.MyIntentConstance
import com.jkdajac.cookingnote.database.meat.Word
import kotlinx.android.synthetic.main.activity_edit_meat.*


class EditMeatActivity : AppCompatActivity() {

    lateinit var wordDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_meat)
        getMyIntents()

        wordDatabase = AppDatabase.getDatabase(this)


        floatingMeatSaved.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            floatingMeatSaved.startAnimation(animation)

            if (etEditMeatName.text.isNotEmpty() && etEditMeatContent.text.isNotEmpty()) {
                val englishWord: String = etEditMeatName.text.toString()
                val translateWord: String = etEditMeatContent.text.toString()


                val word = Word(englishWord = englishWord, translateWord = translateWord)
                Toast.makeText(this, "Ваш рецепт записан !", Toast.LENGTH_LONG)
                    .show()
                wordDatabase.wordDao().insertWord(word)

                val intent = Intent(this, MeatActivity::class.java)
                startActivity(intent)
                overridePendingTransition(0, R.anim.open_activity)
                finish()
            } else {
                Toast.makeText(
                    this, "Пожалуйста, заполните пустые поля !",
                    Toast.LENGTH_LONG
                ).show()
            }
            finish()
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MeatActivity :: class.java)
        startActivity(intent)
        overridePendingTransition(0, R.anim.open_activity)
        finish()
    }
    fun getMyIntents() {

        val i = intent

        if (i != null) {
            if (i.getStringExtra(MyIntentConstance.I_NAME_KEY) != null) {
                etEditMeatName.setText(i.getStringExtra(MyIntentConstance.I_NAME_KEY))
                etEditMeatContent.setText(i.getStringExtra(MyIntentConstance.I_CONTENT_KEY))
            }
        }
    }

}