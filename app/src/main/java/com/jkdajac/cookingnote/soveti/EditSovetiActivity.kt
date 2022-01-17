package com.jkdajac.cookingnote.soveti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.jkdajac.cookingnote.MyIntentConstance
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.database.salad.Salad
import com.jkdajac.cookingnote.database.soveti.AppDatabase
import com.jkdajac.cookingnote.database.soveti.Soveti
import com.jkdajac.cookingnote.salad.SaladActivity
import kotlinx.android.synthetic.main.activity_edit_salad.*
import kotlinx.android.synthetic.main.activity_edit_salad.floatingSaladSaved
import kotlinx.android.synthetic.main.activity_edit_soveti.*

class EditSovetiActivity : AppCompatActivity() {

    lateinit var sovetiDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_soveti)


        getMyIntents()
        sovetiDatabase = AppDatabase.getDatabase(this)

        floatingSovetiSaved.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            floatingSovetiSaved.startAnimation(animation)

            if (etEditSovetiName.text.isNotEmpty() && etEditSovetiContent.text.isNotEmpty()) {
                val englishWord: String = etEditSovetiName.text.toString()
                val translateWord: String = etEditSovetiContent.text.toString()


                val soveti = Soveti(englishWord = englishWord, translateWord = translateWord)
                Toast.makeText(this, "Ваш рецепт записан !", Toast.LENGTH_LONG)
                    .show()
                sovetiDatabase.sovetiDao().insertSoveti(soveti)

                val intent = Intent(this, SovetiActivity::class.java)
                startActivity(intent)
                overridePendingTransition(0, R.anim.open_activity)
                finish()
            } else {
                Toast.makeText(
                    this, "Пожалуйста, заполните пустые поля !",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, SovetiActivity :: class.java)
        startActivity(intent)
        overridePendingTransition(0, R.anim.open_activity)
        finish()
    }

    fun getMyIntents() {

        val i = intent

        if (i != null) {
            if (i.getStringExtra(MyIntentConstance.I_SOVETINAME_KEY) != null) {
                etEditSovetiName.setText(i.getStringExtra(MyIntentConstance.I_SOVETINAME_KEY))
                etEditSovetiContent.setText(i.getStringExtra(MyIntentConstance.I_SOVETICONTENT_KEY))
            }
        }
    }
}