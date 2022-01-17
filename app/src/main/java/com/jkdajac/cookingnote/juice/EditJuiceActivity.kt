package com.jkdajac.cookingnote.juice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.jkdajac.cookingnote.MyIntentConstance
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.database.juice.AppDatabase
import com.jkdajac.cookingnote.database.juice.Juice
import com.jkdajac.cookingnote.database.salad.Salad
import com.jkdajac.cookingnote.salad.SaladActivity
import kotlinx.android.synthetic.main.activity_edit_juice.*
import kotlinx.android.synthetic.main.activity_edit_salad.*
import kotlinx.android.synthetic.main.activity_edit_salad.floatingSaladSaved

class EditJuiceActivity : AppCompatActivity() {

    lateinit var juiceDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_juice)

        getMyIntents()
        juiceDatabase = AppDatabase.getDatabase(this)

        floatingJuiceSaved.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            floatingJuiceSaved.startAnimation(animation)

            if (etEditJuiceName.text.isNotEmpty() && etEditJuiceContent.text.isNotEmpty()) {
                val englishWord: String = etEditJuiceName.text.toString()
                val translateWord: String = etEditJuiceContent.text.toString()


                val juice = Juice(englishWord = englishWord, translateWord = translateWord)
                Toast.makeText(this, "Ваш рецепт записан !", Toast.LENGTH_LONG)
                    .show()
                juiceDatabase.juiceDao().insertJuice(juice)

                val intent = Intent(this, JuiceActivity::class.java)
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
        val intent = Intent(this,JuiceActivity :: class.java)
        startActivity(intent)
        overridePendingTransition(0, R.anim.open_activity)
        finish()
    }

    fun getMyIntents() {

        val i = intent

        if (i != null) {
            if (i.getStringExtra(MyIntentConstance.I_JUICENAME_KEY) != null) {
                etEditJuiceName.setText(i.getStringExtra(MyIntentConstance.I_JUICENAME_KEY))
                etEditJuiceContent.setText(i.getStringExtra(MyIntentConstance.I_JUICECONTENT_KEY))
            }
        }
    }
}