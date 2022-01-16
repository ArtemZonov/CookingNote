package com.jkdajac.cookingnote.salad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.jkdajac.cookingnote.MyIntentConstance
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.database.salad.Salad
import kotlinx.android.synthetic.main.activity_edit_salad.*

class EditSaladActivity : AppCompatActivity() {

    lateinit var saladDatabase: com.jkdajac.cookingnote.database.salad.AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_salad)

        getMyIntents()
        saladDatabase = com.jkdajac.cookingnote.database.salad.AppDatabase.getDatabase(this)

        floatingSaladSaved.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            floatingSaladSaved.startAnimation(animation)

            if (etEditSaladName.text.isNotEmpty() && etEditSaladContent.text.isNotEmpty()) {
                val englishWord: String = etEditSaladName.text.toString()
                val translateWord: String = etEditSaladContent.text.toString()


                val salad = Salad(englishWord = englishWord, translateWord = translateWord)
                Toast.makeText(this, "Ваш рецепт записан !", Toast.LENGTH_LONG)
                    .show()
                saladDatabase.saladDao().insertSalad(salad)

                val intent = Intent(this, SaladActivity::class.java)
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
        val intent = Intent(this, SaladActivity :: class.java)
        startActivity(intent)
        overridePendingTransition(0, R.anim.open_activity)
        finish()
    }

    fun getMyIntents() {

        val i = intent

        if (i != null) {
            if (i.getStringExtra(MyIntentConstance.I_SALADNAME_KEY) != null) {
                etEditSaladName.setText(i.getStringExtra(MyIntentConstance.I_SALADNAME_KEY))
                etEditSaladContent.setText(i.getStringExtra(MyIntentConstance.I_SALADCONTENT_KEY))
            }
        }
    }
    }
