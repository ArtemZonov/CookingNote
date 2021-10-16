package com.jkdajac.cookingnote.soveti

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jkdajac.cookingnote.R
import kotlinx.android.synthetic.main.activity_soveti.*

class SovetiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soveti)

        btPicturesSoveti.setOnClickListener {
            val intent = Intent(this, Soveti2Activity::class.java)
            startActivity(intent)
        }

        btProstoSoveti.setOnClickListener {
            val intent = Intent(this, Soveti3Activity::class.java)
            startActivity(intent)
        }

        val sharedPreference = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)
        val name = sharedPreference.getString("222", "")
        etSovet.setText(name)

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