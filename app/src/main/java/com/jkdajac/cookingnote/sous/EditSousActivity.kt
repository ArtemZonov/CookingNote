package com.jkdajac.cookingnote.sous

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.jkdajac.cookingnote.MyIntentConstance
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.database.salad.Salad
import com.jkdajac.cookingnote.database.sous.AppDatabase
import com.jkdajac.cookingnote.database.sous.Sous
import com.jkdajac.cookingnote.salad.SaladActivity
import kotlinx.android.synthetic.main.activity_edit_salad.*
import kotlinx.android.synthetic.main.activity_edit_salad.floatingSaladSaved
import kotlinx.android.synthetic.main.activity_edit_sous.*

class EditSousActivity : AppCompatActivity() {

    lateinit var sousDatabase: AppDatabase

            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_sous)

                getMyIntents()
                sousDatabase = AppDatabase.getDatabase(this)

                floatingSousSaved.setOnClickListener {
                    val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
                    floatingSousSaved.startAnimation(animation)

                    if (etEditSousName.text.isNotEmpty() && etEditSousContent.text.isNotEmpty()) {
                        val englishWord: String = etEditSousName.text.toString()
                        val translateWord: String = etEditSousContent.text.toString()


                        val sous = Sous(englishWord = englishWord, translateWord = translateWord)
                        Toast.makeText(this, "Ваш рецепт записан !", Toast.LENGTH_LONG)
                            .show()
                        sousDatabase.sousDao().insertSous(sous)

                        val intent = Intent(this,SousActivity::class.java)
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
        val intent = Intent(this, SousActivity :: class.java)
        startActivity(intent)
        overridePendingTransition(0, R.anim.open_activity)
        finish()
    }

    fun getMyIntents() {

        val i = intent

        if (i != null) {
            if (i.getStringExtra(MyIntentConstance.I_SOUSNAME_KEY) != null) {
                etEditSousName.setText(i.getStringExtra(MyIntentConstance.I_SOUSNAME_KEY))
                etEditSousContent.setText(i.getStringExtra(MyIntentConstance.I_SOUSCONTENT_KEY))
            }
        }
    }
}