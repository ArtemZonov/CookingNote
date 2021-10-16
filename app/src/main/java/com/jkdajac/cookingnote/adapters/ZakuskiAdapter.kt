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
import com.jkdajac.cookingnote.database.zakuski.Zakuski
import com.jkdajac.cookingnote.firstbluda.EditFirstbludaActivity
import com.jkdajac.cookingnote.zakuski.EditZakuskiActivity

import com.jkdajac.cookingnote.zakuski.ZakuskiActivity
import kotlinx.android.synthetic.main.item_meat.view.*

class ZakuskiAdapter (val contextA: Context,
                         val zakuskiList: List<Zakuski>,
                         val callback: ZakuskiActivity
) : RecyclerView.Adapter<ZakuskiAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZakuskiAdapter.ViewHolder {
        return ZakuskiAdapter.ViewHolder(
            LayoutInflater.from(contextA).inflate(R.layout.item_meat, parent, false), contextA
        )
    }

    override fun onBindViewHolder(holder: ZakuskiAdapter.ViewHolder, position: Int) {
        holder.setData(zakuskiList[position])
        holder.englishWord?.text = zakuskiList[position].englishWord
        holder.translateWord?.text = zakuskiList[position].translateWord
        holder.deleteItem?.setOnClickListener {
            callback.deleteItem(position)
        }
    }

    override fun getItemCount(): Int {
        return  zakuskiList.size
    }

    class ViewHolder(itemView : View, contextV: Context)  : RecyclerView.ViewHolder(itemView) {
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

        fun setData(item: Zakuski) {
            itemView.setOnClickListener {
                val intent = Intent(context, EditZakuskiActivity::class.java).apply {
                    putExtra(MyIntentConstance.I_ZAKUSKINAME_KEY, item.englishWord)
                    putExtra(MyIntentConstance.I_ZAKUSKICONTENT_KEY, item.translateWord)
                }
                context.startActivity(intent)
            }
        }
    }
}