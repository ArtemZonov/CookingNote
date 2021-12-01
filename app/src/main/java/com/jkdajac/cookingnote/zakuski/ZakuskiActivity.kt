package com.jkdajac.cookingnote.zakuski

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.jkdajac.cookingnote.MainActivity
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.adapters.FirstbludaAdapter
import com.jkdajac.cookingnote.adapters.ZakuskiAdapter
import com.jkdajac.cookingnote.database.zakuski.Zakuski
import kotlinx.android.synthetic.main.activity_zakuski.*

class ZakuskiActivity : AppCompatActivity(), ZakuskiAdapter.ViewHolder.ItemCallback {

    lateinit var adapter: ZakuskiAdapter
    lateinit var zakuskiDatabase: com.jkdajac.cookingnote.database.zakuski.AppDatabase
    lateinit var zakuskiList: ArrayList<Zakuski>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zakuski)

        zakuskiList = ArrayList<Zakuski>()
        zakuskiDatabase = com.jkdajac.cookingnote.database.zakuski.AppDatabase.getDatabase(this)
        getData()
        adapter = ZakuskiAdapter(this, zakuskiList, this)
        rvZakuski.layoutManager = LinearLayoutManager(this)
        rvZakuski.adapter = adapter

        zakuskiDatabase = com.jkdajac.cookingnote.database.zakuski.AppDatabase.getDatabase(this)

        btZakuskiBack.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            tvZakuskiBack.startAnimation(animation)
            btZakuskiBack.startAnimation(animation)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        floatingZakuskiEdit.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            floatingZakuskiEdit.startAnimation(animation)
            val intent = Intent(this, EditZakuskiActivity::class.java)
            startActivity(intent)
        }

    }

    fun getData() {
        val wordFromDb: List<Zakuski> = zakuskiDatabase.zakuskiDao().getAll()
        zakuskiList.clear()
        zakuskiList.addAll(wordFromDb)
    }

    override fun deleteItem(index: Int) {

        val addDialog = AlertDialog.Builder(this)
        addDialog
            .setMessage("Вы действительно хотите удалить запись?")
            .setPositiveButton("Ok") { dialog, _ ->
                val zakuski = zakuskiList[index]
                zakuskiDatabase.zakuskiDao().deleteZakuski(zakuski)
                getData()
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Запись удалена!", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}