package com.jkdajac.cookingnote.vipechka

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
import com.jkdajac.cookingnote.adapters.VipechkaAdapter
import com.jkdajac.cookingnote.database.salad.AppDatabase
import com.jkdajac.cookingnote.database.salad.Salad
import com.jkdajac.cookingnote.database.vipechka.Vipechka
import com.jkdajac.cookingnote.salad.EditSaladActivity
import kotlinx.android.synthetic.main.activity_salad.*
import kotlinx.android.synthetic.main.activity_vipechka.*

class VipechkaActivity : AppCompatActivity(), VipechkaAdapter.ViewHolder.ItemCallback {

    lateinit var adapter: VipechkaAdapter
    lateinit var vipechkaDatabase: com.jkdajac.cookingnote.database.vipechka.AppDatabase
    lateinit var vipechkaList: ArrayList<Vipechka>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vipechka)

        vipechkaList = ArrayList<Vipechka>()
        vipechkaDatabase = com.jkdajac.cookingnote.database.vipechka.AppDatabase.getDatabase(this)
        getData()
        adapter = VipechkaAdapter(this, vipechkaList, this)
        rvVipechka.layoutManager = LinearLayoutManager(this)
        rvVipechka.adapter = adapter

        vipechkaDatabase = com.jkdajac.cookingnote.database.vipechka.AppDatabase.getDatabase(this)

        btVipechkaBack.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            tvVipechkaBack.startAnimation(animation)
            btVipechkaBack.startAnimation(animation)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        floatingVipechkaEdit.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            floatingVipechkaEdit.startAnimation(animation)
            val intent = Intent(this, EditVipechkaActivity::class.java)
            startActivity(intent)
        }
    }

    fun getData() {
        val wordFromDb: List<Vipechka> = vipechkaDatabase.vipechkaDao().getAll()
        vipechkaList.clear()
        vipechkaList.addAll(wordFromDb)
    }

    override fun deleteItem(index: Int) {

        val addDialog = AlertDialog.Builder(this)
        addDialog
            .setMessage("Вы действительно хотите удалить запись?")
            .setPositiveButton("Ok") { dialog, _ ->
                val vipechka = vipechkaList[index]
                vipechkaDatabase.vipechkaDao().deleteVipechka(vipechka)
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