package com.jkdajac.cookingnote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.jkdajac.cookingnote.meat.MeatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        tvMenuBack.setOnClickListener {
            finishAffinity()
        }

        btMenuMeat.setOnClickListener {
                val animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.scale)
                btMenuMeat.startAnimation(animation)
                tvMenuMeat.startAnimation(animation)
                ivMenuMeat.startAnimation(animation)
            val intent = Intent(this, MeatActivity::class.java)
            startActivity(intent)
            }

        btMenuZakuski.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            btMenuZakuski.startAnimation(animation)
            tvMenuZakuski.startAnimation(animation)
            ivMenuZakuski.startAnimation(animation)
        }

        btMenuVipechka.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            btMenuVipechka.startAnimation(animation)
            tvMenuVipechka.startAnimation(animation)
            ivMenuVipechka.startAnimation(animation)
        }

        btMenuSous.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            btMenuSous.startAnimation(animation)
            tvMenuSous.startAnimation(animation)
            ivMenuSous.startAnimation(animation)
        }

        btMenuFirstBluda.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            btMenuFirstBluda.startAnimation(animation)
            tvMenuFirstBluda.startAnimation(animation)
            ivMenuFirstBluda.startAnimation(animation)
        }

        btMenuSalad.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            btMenuSalad.startAnimation(animation)
            tvMenuSalad.startAnimation(animation)
            ivMenuSalad.startAnimation(animation)
        }

        btMenuDesert.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            btMenuDesert.startAnimation(animation)
            tvMenuDesert.startAnimation(animation)
            ivMenuDesert.startAnimation(animation)
        }

        btMenuJuice.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            btMenuJuice.startAnimation(animation)
            tvMenuJuice.startAnimation(animation)
            ivMenuJuice.startAnimation(animation)
        }

        btMenuSoveti.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            btMenuSoveti.startAnimation(animation)
            tvMenuSoveti.startAnimation(animation)
        }
        btMenuBack.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            tvMenuBack.startAnimation(animation)
            btMenuBack.startAnimation(animation)
        }
    }
}