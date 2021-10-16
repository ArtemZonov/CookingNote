package com.jkdajac.cookingnote.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jkdajac.cookingnote.MyIntentConstance
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.database.juice.Juice
import com.jkdajac.cookingnote.juice.EditJuiceActivity
import com.jkdajac.cookingnote.juice.JuiceActivity
import kotlinx.android.synthetic.main.item_meat.view.*

class JuiceAdapter(val contextA: Context,
                   val juiceList: List<Juice>,
                   val callback: JuiceActivity
) : RecyclerView.Adapter<JuiceAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return JuiceAdapter.ViewHolder(
            LayoutInflater.from(contextA).inflate(R.layout.item_meat, parent, false), contextA
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(juiceList[position])
        holder.englishWord?.text = juiceList[position].englishWord
        holder.translateWord?.text = juiceList[position].translateWord
        holder.deleteItem?.setOnClickListener {
            callback.deleteItem(position)
        }
    }

    override fun getItemCount(): Int {
       return juiceList.size
    }

    class ViewHolder (itemView : View, contextV: Context)  : RecyclerView.ViewHolder(itemView) {
        val context = contextV
        var englishWord: TextView? = null
        var translateWord: TextView? = null
        var deleteItem : ImageView? = null


        init{
            englishWord = itemView.tvItemMeat
            translateWord = itemView.tvItemContent
            deleteItem = itemView.ivItemMeatDelete
        }
        interface ItemCallback {
            fun deleteItem(index: Int)
        }

        fun setData(item: Juice) {
            itemView.setOnClickListener {
                val intent = Intent(context, EditJuiceActivity::class.java).apply {
                    putExtra(MyIntentConstance.I_JUICENAME_KEY, item.englishWord)
                    putExtra(MyIntentConstance.I_JUICECONTENT_KEY, item.translateWord)
                }
                context.startActivity(intent)
            }
        }
    }
}