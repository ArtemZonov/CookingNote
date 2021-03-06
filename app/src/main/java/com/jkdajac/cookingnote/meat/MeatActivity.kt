package com.jkdajac.cookingnote.meat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.jkdajac.cookingnote.MainActivity
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.adapters.MeatAdapter
import com.jkdajac.cookingnote.database.meat.AppDatabase
import com.jkdajac.cookingnote.database.meat.Word
import kotlinx.android.synthetic.main.activity_meat.*

class MeatActivity : AppCompatActivity(), MeatAdapter.ViewHolder.ItemCallback {

    lateinit var adapter: MeatAdapter
    lateinit var wordDatabase: AppDatabase
    lateinit var wordList: ArrayList<Word>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meat)



        wordList = ArrayList<Word>()
        wordDatabase = AppDatabase.getDatabase(this)
        getData()
        adapter = MeatAdapter(this, wordList, this)
        rvMeat.layoutManager = LinearLayoutManager(this)
        rvMeat.adapter = adapter

        wordDatabase = AppDatabase.getDatabase(this)




        btMeatBack.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            tvZakuskiBack.startAnimation(animation)
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
        val wordFromDb: List<Word> = wordDatabase.wordDao().getAll()
        wordList.clear()
        wordList.addAll(wordFromDb)
    }



    override fun deleteItem(index: Int) {
        val word = wordList[index]
        wordDatabase.wordDao().deleteWord(word)
        getData()
        adapter.notifyDataSetChanged()
    }

}
