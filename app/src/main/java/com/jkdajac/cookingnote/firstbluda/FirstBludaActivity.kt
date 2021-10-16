package com.jkdajac.cookingnote.firstbluda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.jkdajac.cookingnote.MainActivity
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.adapters.FirstbludaAdapter
import com.jkdajac.cookingnote.database.firstbluda.AppDatabase
import com.jkdajac.cookingnote.database.firstbluda.Firstbluda
import kotlinx.android.synthetic.main.activity_first_bluda.*

class FirstBludaActivity : AppCompatActivity(), FirstbludaAdapter.ViewHolder.ItemCallback {

    lateinit var adapter: FirstbludaAdapter
    lateinit var bludaDatabase: AppDatabase
    lateinit var wordList: ArrayList<Firstbluda>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_bluda)

        wordList = ArrayList<Firstbluda>()
        bludaDatabase = AppDatabase.getDatabase(this)
        getData()
        adapter = FirstbludaAdapter(this, wordList, this)
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
        val wordFromDb: List<Firstbluda> = bludaDatabase.firstbludaDao().getAll()
        wordList.clear()
        wordList.addAll(wordFromDb)
    }

    override fun deleteItem(index: Int) {
        val firstbluda = wordList[index]
        bludaDatabase.firstbludaDao().deleteFirstbluda(firstbluda)
        getData()
        adapter.notifyDataSetChanged()
    }
}


