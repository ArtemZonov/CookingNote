package com.jkdajac.cookingnote.vipechka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.jkdajac.cookingnote.MyIntentConstance
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.database.salad.Salad
import com.jkdajac.cookingnote.database.vipechka.AppDatabase
import com.jkdajac.cookingnote.database.vipechka.Vipechka
import com.jkdajac.cookingnote.salad.SaladActivity
import kotlinx.android.synthetic.main.activity_edit_salad.*
import kotlinx.android.synthetic.main.activity_edit_vipechka.*

class EditVipechkaActivity : AppCompatActivity() {

    lateinit var vipechkaDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_vipechka)

        getMyIntents()
        vipechkaDatabase = AppDatabase.getDatabase(this)

        floatingVipechkaSaved.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            floatingVipechkaSaved.startAnimation(animation)

            if (etEditVipechkaName.text.isNotEmpty() && etEditVipechkaContent.text.isNotEmpty()) {
                val englishWord: String = etEditVipechkaName.text.toString()
                val translateWord: String = etEditVipechkaContent.text.toString()


                val vipechka = Vipechka(englishWord = englishWord, translateWord = translateWord)
                Toast.makeText(this, "Ваш рецепт записан !", Toast.LENGTH_LONG)
                    .show()
                vipechkaDatabase.vipechkaDao().insertVipechka(vipechka)

                val intent = Intent(this, VipechkaActivity::class.java)
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
        val intent = Intent(this, VipechkaActivity :: class.java)
        startActivity(intent)
        overridePendingTransition(0, R.anim.open_activity)
        finish()
    }
    fun getMyIntents() {

        val i = intent

        if (i != null) {
            if (i.getStringExtra(MyIntentConstance.I_VIPECHKANAME_KEY) != null) {
                etEditVipechkaName.setText(i.getStringExtra(MyIntentConstance.I_VIPECHKANAME_KEY))
                etEditVipechkaContent.setText(i.getStringExtra(MyIntentConstance.I_VIPECHKACONTENT_KEY))
            }
        }
    }
}