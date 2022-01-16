package com.jkdajac.cookingnote.soveti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jkdajac.cookingnote.MainActivity
import com.jkdajac.cookingnote.R

class Soveti3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soveti3)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, SovetiActivity :: class.java)
        startActivity(intent)
        overridePendingTransition(0, R.anim.open_activity)
        finish()
    }
}