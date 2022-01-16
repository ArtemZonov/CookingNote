package com.jkdajac.cookingnote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.jkdajac.cookingnote.desert.DesertActivity
import com.jkdajac.cookingnote.firstbluda.FirstBludaActivity
import com.jkdajac.cookingnote.juice.JuiceActivity
import com.jkdajac.cookingnote.meat.MeatActivity
import com.jkdajac.cookingnote.salad.SaladActivity
import com.jkdajac.cookingnote.sous.SousActivity
import com.jkdajac.cookingnote.soveti.SovetiActivity
import com.jkdajac.cookingnote.vipechka.VipechkaActivity
import com.jkdajac.cookingnote.zakuski.ZakuskiActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btMenuMeat.setOnClickListener {
                val animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.scale)
                btMenuMeat.startAnimation(animation)
                tvMenuMeat.startAnimation(animation)
                ivMenuMeat.startAnimation(animation)
            val intent = Intent(this, MeatActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
            }

        btMenuZakuski.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            btMenuZakuski.startAnimation(animation)
            tvMenuZakuski.startAnimation(animation)
            ivMenuZakuski.startAnimation(animation)
            val intent = Intent(this, ZakuskiActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
        }

        btMenuVipechka.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            btMenuVipechka.startAnimation(animation)
            tvMenuVipechka.startAnimation(animation)
            ivMenuVipechka.startAnimation(animation)
            val intent = Intent(this, VipechkaActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
        }

        btMenuSous.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            btMenuSous.startAnimation(animation)
            tvMenuSous.startAnimation(animation)
            ivMenuSous.startAnimation(animation)
            val intent = Intent(this, SousActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
        }

        btMenuFirstBluda.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            btMenuFirstBluda.startAnimation(animation)
            tvMenuFirstBluda.startAnimation(animation)
            ivMenuFirstBluda.startAnimation(animation)
            val intent = Intent(this, FirstBludaActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
        }

        btMenuSalad.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            btMenuSalad.startAnimation(animation)
            tvMenuSalad.startAnimation(animation)
            ivMenuSalad.startAnimation(animation)
            val intent = Intent(this, SaladActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
        }

        btMenuDesert.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            btMenuDesert.startAnimation(animation)
            tvMenuDesert.startAnimation(animation)
            ivMenuDesert.startAnimation(animation)
            val intent = Intent(this, DesertActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
        }

        btMenuJuice.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            btMenuJuice.startAnimation(animation)
            tvMenuJuice.startAnimation(animation)
            ivMenuJuice.startAnimation(animation)
            val intent = Intent(this, JuiceActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
        }

        btMenuSoveti.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            btMenuSoveti.startAnimation(animation)
            tvMenuSoveti.startAnimation(animation)
            val intent = Intent(this, SovetiActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
        }
        btMenuBack.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            tvMenuBack.startAnimation(animation)
            btMenuBack.startAnimation(animation)
            finishAffinity()
        }
    }
}