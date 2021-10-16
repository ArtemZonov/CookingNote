package com.jkdajac.cookingnote.desert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.jkdajac.cookingnote.MyIntentConstance
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.database.desert.AppDatabase
import com.jkdajac.cookingnote.database.desert.Desert
import com.jkdajac.cookingnote.database.salad.Salad
import com.jkdajac.cookingnote.salad.SaladActivity
import kotlinx.android.synthetic.main.activity_edit_desert.*
import kotlinx.android.synthetic.main.activity_edit_salad.*
import kotlinx.android.synthetic.main.activity_edit_salad.floatingSaladSaved

class EditDesertActivity : AppCompatActivity() {

    lateinit var desertDatabase: AppDatabase

            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_desert)
                getMyIntents()
                desertDatabase = AppDatabase.getDatabase(this)

                floatingDesertSaved.setOnClickListener {
                    val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
                    floatingDesertSaved.startAnimation(animation)

                    if (etEditDesertName.text.isNotEmpty() && etEditDesertContent.text.isNotEmpty()) {
                        val englishWord: String = etEditDesertName.text.toString()
                        val translateWord: String = etEditDesertContent.text.toString()


                        val desert = Desert(englishWord = englishWord, translateWord = translateWord)
                        Toast.makeText(this, "Ваш рецепт записан !", Toast.LENGTH_LONG)
                            .show()
                        desertDatabase.desertDao().insertDesert(desert)

                        val intent = Intent(this, DesertActivity::class.java)
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
            if (i.getStringExtra(MyIntentConstance.I_DESERTNAME_KEY) != null) {
                etEditDesertName.setText(i.getStringExtra(MyIntentConstance.I_DESERTNAME_KEY))
                etEditDesertContent.setText(i.getStringExtra(MyIntentConstance.I_DESERTCONTENT_KEY))
            }
        }
    }
}