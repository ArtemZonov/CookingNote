package com.jkdajac.cookingnote.soveti

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jkdajac.cookingnote.MainActivity
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.adapters.SaladAdapter
import com.jkdajac.cookingnote.adapters.SovetiAdapter
import com.jkdajac.cookingnote.database.salad.AppDatabase
import com.jkdajac.cookingnote.database.salad.Salad
import com.jkdajac.cookingnote.database.soveti.Soveti
import com.jkdajac.cookingnote.salad.EditSaladActivity
import kotlinx.android.synthetic.main.activity_salad.*
import kotlinx.android.synthetic.main.activity_soveti.*

class SovetiActivity : AppCompatActivity(), SovetiAdapter.ViewHolder.ItemCallback  {

    lateinit var adapter: SovetiAdapter
    lateinit var sovetiDatabase: com.jkdajac.cookingnote.database.soveti.AppDatabase
    lateinit var sovetiList: ArrayList<Soveti>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soveti)

        sovetiList = ArrayList<Soveti>()
        sovetiDatabase = com.jkdajac.cookingnote.database.soveti.AppDatabase.getDatabase(this)
        getData()
        adapter = SovetiAdapter(this, sovetiList, this)
        rvSoveti.layoutManager = LinearLayoutManager(this)
        rvSoveti.adapter = adapter

        sovetiDatabase = com.jkdajac.cookingnote.database.soveti.AppDatabase.getDatabase(this)

        btMainMenuBackSoveti.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            tvMainMenuBackSoveti.startAnimation(animation)
            btMainMenuBackSoveti.startAnimation(animation)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()

        }
        floatingSovetiEdit.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            floatingSovetiEdit.startAnimation(animation)
            val intent = Intent(this, EditSovetiActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
        }



        btPicturesSoveti.setOnClickListener {
            val intent = Intent(this, Soveti2Activity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
        }

        btProstoSoveti.setOnClickListener {
            val intent = Intent(this, Soveti3Activity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
        }

//        val sharedPreference = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)
//        val name = sharedPreference.getString("222", "")
//        etSovet.setText(name)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity :: class.java)
        startActivity(intent)
        overridePendingTransition(0, R.anim.open_activity)
        finish()
    }

    fun getData() {
        val wordFromDb: List<Soveti> = sovetiDatabase.sovetiDao().getAll()
        sovetiList.clear()
        sovetiList.addAll(wordFromDb)
    }
    override fun deleteItem(index: Int) {

        val addDialog = AlertDialog.Builder(this)
        addDialog
            .setMessage("Вы действительно хотите удалить запись?")
            .setPositiveButton("Ok") { dialog, _ ->
                val soveti = sovetiList[index]
                sovetiDatabase.sovetiDao().deleteSoveti(soveti)
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

//    private  fun save(){
//        val sharedPreference = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)
//        val editor = sharedPreference.edit()
//        editor.putString("222", etSovet.text.toString())
//        editor.apply()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        save()
//    }
}