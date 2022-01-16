package com.jkdajac.cookingnote.juice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.jkdajac.cookingnote.MainActivity
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.adapters.JuiceAdapter
import com.jkdajac.cookingnote.adapters.SaladAdapter
import com.jkdajac.cookingnote.database.juice.Juice
import com.jkdajac.cookingnote.database.salad.AppDatabase
import com.jkdajac.cookingnote.database.salad.Salad
import com.jkdajac.cookingnote.salad.EditSaladActivity
import kotlinx.android.synthetic.main.activity_juice.*
import kotlinx.android.synthetic.main.activity_salad.*
import kotlinx.android.synthetic.main.activity_salad.btSaladBack

class JuiceActivity : AppCompatActivity(), JuiceAdapter.ViewHolder.ItemCallback {

    lateinit var adapter: JuiceAdapter
    lateinit var juiceDatabase: com.jkdajac.cookingnote.database.juice.AppDatabase
    lateinit var juiceList: ArrayList<Juice>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juice)

        juiceList = ArrayList<Juice>()
       juiceDatabase = com.jkdajac.cookingnote.database.juice.AppDatabase.getDatabase(this)
        getData()
        adapter = JuiceAdapter(this, juiceList, this)
        rvJuice.layoutManager = LinearLayoutManager(this)
        rvJuice.adapter = adapter

        juiceDatabase = com.jkdajac.cookingnote.database.juice.AppDatabase.getDatabase(this)

        btJuiceBack.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            tvJuiceBack.startAnimation(animation)
            btJuiceBack.startAnimation(animation)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()

        }
        floatingJuiceEdit.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            floatingJuiceEdit.startAnimation(animation)
            val intent = Intent(this, EditJuiceActivity::class.java)
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
        val wordFromDb: List<Juice> = juiceDatabase.juiceDao().getAll()
        juiceList.clear()
        juiceList.addAll(wordFromDb)
    }

    override fun deleteItem(index: Int) {

        val addDialog = AlertDialog.Builder(this)
        addDialog
            .setMessage("Вы действительно хотите удалить запись?")
            .setPositiveButton("Ok") { dialog, _ ->
                val juice = juiceList[index]
                juiceDatabase.juiceDao().deleteJuice(juice)
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