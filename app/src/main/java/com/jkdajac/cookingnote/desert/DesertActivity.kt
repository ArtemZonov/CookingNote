package com.jkdajac.cookingnote.desert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.jkdajac.cookingnote.MainActivity
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.adapters.DesertAdapter
import com.jkdajac.cookingnote.adapters.SaladAdapter
import com.jkdajac.cookingnote.database.desert.Desert
import com.jkdajac.cookingnote.database.salad.AppDatabase
import com.jkdajac.cookingnote.database.salad.Salad
import com.jkdajac.cookingnote.salad.EditSaladActivity
import kotlinx.android.synthetic.main.activity_desert.*
import kotlinx.android.synthetic.main.activity_salad.*
import kotlinx.android.synthetic.main.activity_salad.rvSalad

class DesertActivity : AppCompatActivity(), DesertAdapter.ViewHolder.ItemCallback {

    lateinit var adapter: DesertAdapter
    lateinit var desertDatabase: com.jkdajac.cookingnote.database.desert.AppDatabase
    lateinit var desertList: ArrayList<Desert>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desert)

        desertList = ArrayList<Desert>()
        desertDatabase = com.jkdajac.cookingnote.database.desert.AppDatabase.getDatabase(this)
        getData()
        adapter = DesertAdapter(this, desertList, this)
        rvDesert.layoutManager = LinearLayoutManager(this)
        rvDesert.adapter = adapter

        desertDatabase = com.jkdajac.cookingnote.database.desert.AppDatabase.getDatabase(this)

        btDesertBack.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            tvDesertBack.startAnimation(animation)
            btDesertBack.startAnimation(animation)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        floatingDesertEdit.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            floatingDesertEdit.startAnimation(animation)
            val intent = Intent(this, EditDesertActivity::class.java)
            startActivity(intent)
        }
    }

    fun getData() {
        val wordFromDb: List<Desert> = desertDatabase.desertDao().getAll()
        desertList.clear()
        desertList.addAll(wordFromDb)
    }

    override fun deleteItem(index: Int) {
        val desert = desertList[index]
        desertDatabase.desertDao().deleteDesert(desert)
        getData()
        adapter.notifyDataSetChanged()
    }
}