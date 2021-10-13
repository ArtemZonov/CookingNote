package com.jkdajac.cookingnote.firstbluda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.jkdajac.cookingnote.MainActivity
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.adapters.MeatAdapter
import com.jkdajac.cookingnote.database.AppDatabase
import com.jkdajac.cookingnote.database.Word
import com.jkdajac.cookingnote.meat.EditMeatActivity
import kotlinx.android.synthetic.main.activity_first_bluda.*
import kotlinx.android.synthetic.main.activity_meat.*
import kotlinx.android.synthetic.main.activity_meat.btMeatBack
import kotlinx.android.synthetic.main.activity_meat.floatingMeat

class FirstBludaActivity : AppCompatActivity(), MeatAdapter.ViewHolder.ItemCallback {

    lateinit var adapter: MeatAdapter
    lateinit var bludaDatabase: AppDatabase
    lateinit var wordList: ArrayList<Word>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_bluda)

        wordList = ArrayList<Word>()
        bludaDatabase = AppDatabase.getDatabase(this)
        getData()
        adapter = MeatAdapter(this, wordList, this)
        rvFirstbluda.layoutManager = LinearLayoutManager(this)
        rvFirstbluda.adapter = adapter

        bludaDatabase = AppDatabase.getDatabase(this)

        btFirstbludaBack.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            tvFirstbludaBack.startAnimation(animation)
            btFirstbludaBack.startAnimation(animation)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        floatingEditFirstbluda.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            floatingEditFirstbluda.startAnimation(animation)
            val intent = Intent(this, EditFirstbludaActivity::class.java)
            startActivity(intent)
        }
    }

    fun getData() {
        val wordFromDb: List<Word> = bludaDatabase.wordDao().getAll()
        wordList.clear()
        wordList.addAll(wordFromDb)
    }

    override fun deleteItem(index: Int) {
        val word = wordList[index]
        bludaDatabase.wordDao().deleteWord(word)
        getData()
        adapter.notifyDataSetChanged()
    }
}