package com.jkdajac.cookingnote.soveti

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jkdajac.cookingnote.MainActivity
import com.jkdajac.cookingnote.R
import kotlinx.android.synthetic.main.activity_soveti.*

class SovetiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soveti)

        btPicturesSoveti.setOnClickListener {
            val intent = Intent(this, Soveti2Activity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
        }

        btProstoSoveti.setOnClickListener {
            val intent = Intent(this, Soveti3Activity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
        }

        val sharedPreference = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)
        val name = sharedPreference.getString("222", "")
        etSovet.setText(name)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity :: class.java)
        startActivity(intent)
        overridePendingTransition(0, R.anim.open_activity)
        finish()
    }

    private  fun save(){
        val sharedPreference = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("222", etSovet.text.toString())
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        save()
    }
}