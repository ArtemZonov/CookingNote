package com.jkdajac.cookingnote.zakuski

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.jkdajac.cookingnote.MyIntentConstance
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.database.firstbluda.Firstbluda
import com.jkdajac.cookingnote.database.zakuski.AppDatabase
import com.jkdajac.cookingnote.database.zakuski.Zakuski
import com.jkdajac.cookingnote.firstbluda.FirstBludaActivity
import kotlinx.android.synthetic.main.activity_edit_firstbluda.*
import kotlinx.android.synthetic.main.activity_edit_firstbluda.floatingFirstbludaSaved
import kotlinx.android.synthetic.main.activity_edit_zakuski.*

class EditZakuskiActivity : AppCompatActivity() {

    lateinit var zakuskiDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_zakuski)

        getMyIntents()
        zakuskiDatabase = AppDatabase.getDatabase(this)

        floatingZakuskiSaved.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            floatingZakuskiSaved.startAnimation(animation)

            if (etEditZakuskiName.text.isNotEmpty() && etEditZakuskiContent.text.isNotEmpty()) {
                val englishWord: String = etEditZakuskiName.text.toString()
                val translateWord: String = etEditZakuskiContent.text.toString()


                val zakuski = Zakuski(englishWord = englishWord, translateWord = translateWord)
                Toast.makeText(this, "Ваш рецепт записан !", Toast.LENGTH_LONG)
                    .show()
                zakuskiDatabase.zakuskiDao().insertZakuski(zakuski)

                val intent = Intent(this, ZakuskiActivity::class.java)
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
        val intent = Intent(this, ZakuskiActivity :: class.java)
        startActivity(intent)
        overridePendingTransition(0, R.anim.open_activity)
        finish()
    }

    fun getMyIntents() {

        val i = intent

        if (i != null) {
            if (i.getStringExtra(MyIntentConstance.I_ZAKUSKINAME_KEY) != null) {
                etEditZakuskiName.setText(i.getStringExtra(MyIntentConstance.I_ZAKUSKINAME_KEY))
                etEditZakuskiContent.setText(i.getStringExtra(MyIntentConstance.I_ZAKUSKICONTENT_KEY))
            }
        }
    }
    }
