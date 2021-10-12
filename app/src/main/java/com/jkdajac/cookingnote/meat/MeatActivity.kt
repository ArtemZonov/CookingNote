package com.jkdajac.cookingnote.meat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.jkdajac.cookingnote.MainActivity
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.adapters.MeatAdapter
import com.jkdajac.cookingnote.database.AppDatabase
import com.jkdajac.cookingnote.database.Meat
import com.jkdajac.cookingnote.database.MyIntentConstance
import kotlinx.android.synthetic.main.activity_edit_meat.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btMenuBack
import kotlinx.android.synthetic.main.activity_meat.*

class MeatActivity : AppCompatActivity(), MeatAdapter.ViewHolder.ItemCallback {

    lateinit var adapter: MeatAdapter
    lateinit var meatDatabase: AppDatabase
    lateinit var meatList: ArrayList<Meat>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meat)

        getMyIntents()

        meatList = ArrayList<Meat>()
        meatDatabase = AppDatabase.getDatabase(this)
        getData()
        adapter = MeatAdapter(this, meatList, this)
        rvMeat.layoutManager = LinearLayoutManager(this)
        rvMeat.adapter = adapter

        meatDatabase = AppDatabase.getDatabase(this)

        floatingMeatSave.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            floatingMeatSave.startAnimation(animation)

            if (etEditMeatName.text.isNotEmpty() && etEditMeatContent.text.isNotEmpty()) {
                val etEditMeatName: String = etEditMeatName.text.toString()
                val etEditMeatContent: String = etEditMeatContent.text.toString()


                val meat = Meat(
                    etEditMeatName = etEditMeatName,
                    etEditMeatContent = etEditMeatContent.toString()
                )
                Toast.makeText(this, "English word and translation full !", Toast.LENGTH_LONG)
                    .show()
                meatDatabase.meatDao().insertMeat(meat)

                val intent = Intent(this, MeatActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this, "Full in the fields" + "\"English word\"" + "and" + "\"Translate\"",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    btMeatBack.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            tvMeatBack.startAnimation(animation)
            btMeatBack.startAnimation(animation)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        floatingMeat.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            floatingMeat.startAnimation(animation)
            val intent = Intent(this, EditMeatActivity::class.java)
            startActivity(intent)
        }
    }
    fun getData() {
        val meatFromDb: List<Meat> = meatDatabase.meatDao().getAll()
        meatList.clear()
        meatList.addAll(meatFromDb)
    }

    fun getMyIntents(){

        val i = intent

        if(i != null){
            if(i.getStringExtra(MyIntentConstance.I_NAME_KEY) != null){
                etEditMeatName.setText(i.getStringExtra(MyIntentConstance.I_NAME_KEY))
                etEditMeatContent.setText(i.getStringExtra(MyIntentConstance.I_CONTENT_KEY))
            }
        }
    }

    override fun deleteItem(index: Int) {
        TODO("Not yet implemented")
    }
}