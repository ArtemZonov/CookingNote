package com.jkdajac.cookingnote.meat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.jkdajac.cookingnote.R
import kotlinx.android.synthetic.main.activity_edit_meat.*
import kotlinx.android.synthetic.main.activity_meat.*
import kotlinx.android.synthetic.main.activity_meat.floatingMeat

class EditMeatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_meat)

//        floatingMeatSave.setOnClickListener {
//            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
//            floatingMeatSave.startAnimation(animation)
//            val intent = Intent(this, MeatActivity::class.java)
//            startActivity(intent)
//        }



    }
}