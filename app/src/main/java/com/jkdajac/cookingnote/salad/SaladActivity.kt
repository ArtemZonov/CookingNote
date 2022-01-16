package com.jkdajac.cookingnote.salad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.jkdajac.cookingnote.MainActivity
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.adapters.SaladAdapter
import com.jkdajac.cookingnote.adapters.ZakuskiAdapter
import com.jkdajac.cookingnote.database.salad.AppDatabase
import com.jkdajac.cookingnote.database.salad.Salad
import com.jkdajac.cookingnote.database.zakuski.Zakuski
import com.jkdajac.cookingnote.zakuski.EditZakuskiActivity
import kotlinx.android.synthetic.main.activity_salad.*
import kotlinx.android.synthetic.main.activity_zakuski.*

class SaladActivity : AppCompatActivity(), SaladAdapter.ViewHolder.ItemCallback {

    lateinit var adapter: SaladAdapter
    lateinit var saladDatabase: AppDatabase
    lateinit var saladList: ArrayList<Salad>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salad)

        saladList = ArrayList<Salad>()
        saladDatabase = AppDatabase.getDatabase(this)
        getData()
        adapter = SaladAdapter(this, saladList, this)
        rvSalad.layoutManager = LinearLayoutManager(this)
        rvSalad.adapter = adapter

        saladDatabase = AppDatabase.getDatabase(this)

        btSaladBack.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            tvSaladBack.startAnimation(animation)
            btSaladBack.startAnimation(animation)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()

        }
        floatingSaladEdit.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            floatingSaladEdit.startAnimation(animation)
            val intent = Intent(this, EditSaladActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity :: class.java)
        startActivity(intent)
        overridePendingTransition(0, R.anim.open_activity)
        finish()
    }


    fun getData() {
        val wordFromDb: List<Salad> = saladDatabase.saladDao().getAll()
        saladList.clear()
        saladList.addAll(wordFromDb)
    }
    override fun deleteItem(index: Int) {

        val addDialog = AlertDialog.Builder(this)
        addDialog
            .setMessage("Вы действительно хотите удалить запись?")
            .setPositiveButton("Ok") { dialog, _ ->
                val salad = saladList[index]
                saladDatabase.saladDao().deleteSalad(salad)
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