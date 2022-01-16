package com.jkdajac.cookingnote.sous

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.jkdajac.cookingnote.MainActivity
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.adapters.SousAdapter
import com.jkdajac.cookingnote.database.sous.Sous
import kotlinx.android.synthetic.main.activity_sous.*

class SousActivity : AppCompatActivity(), SousAdapter.ViewHolder.ItemCallback {

    lateinit var adapter: SousAdapter
    lateinit var sousDatabase: com.jkdajac.cookingnote.database.sous.AppDatabase
    lateinit var sousList: ArrayList<Sous>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sous)

        sousList = ArrayList<Sous>()
        sousDatabase = com.jkdajac.cookingnote.database.sous.AppDatabase.getDatabase(this)
        getData()
        adapter = SousAdapter(this, sousList, this)
        rvSous.layoutManager = LinearLayoutManager(this)
        rvSous.adapter = adapter

        sousDatabase = com.jkdajac.cookingnote.database.sous.AppDatabase.getDatabase(this)

        btSousBack.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            tvSousBack.startAnimation(animation)
            btSousBack.startAnimation(animation)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()

        }
        floatingSovetiEdit.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            floatingSovetiEdit.startAnimation(animation)
            val intent = Intent(this, EditSousActivity::class.java)
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
        val wordFromDb: List<Sous> = sousDatabase.sousDao().getAll()
        sousList.clear()
        sousList.addAll(wordFromDb)
    }

    override fun deleteItem(index: Int) {

        val addDialog = AlertDialog.Builder(this)
        addDialog
            .setMessage("Вы действительно хотите удалить запись?")
            .setPositiveButton("Ok") { dialog, _ ->
                val sous = sousList[index]
                sousDatabase.sousDao().deleteSous(sous)
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