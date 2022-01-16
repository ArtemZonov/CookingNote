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
import com.jkdajac.cookingnote.database.soveti.Soveti
import com.jkdajac.cookingnote.soveti.EditSovetiActivity
import com.jkdajac.cookingnote.soveti.SovetiActivity
import kotlinx.android.synthetic.main.item_meat.view.*

class SovetiAdapter(val contextA: Context,
                    val sovetiList: List<Soveti>,
                    val callback: SovetiActivity
) : RecyclerView.Adapter<SovetiAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return SovetiAdapter.ViewHolder(
            LayoutInflater.from(contextA).inflate(R.layout.item_meat, parent, false), contextA
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(sovetiList[position])
        holder.englishWord?.text = sovetiList[position].englishWord
        holder.translateWord?.text = sovetiList[position].translateWord
        holder.deleteItem?.setOnClickListener {
            callback.deleteItem(position)
        }
    }

    override fun getItemCount(): Int {
        return sovetiList.size
    }

    class ViewHolder(itemView: View, contextV: Context) : RecyclerView.ViewHolder(itemView) {
        val context = contextV
        var englishWord: TextView? = null
        var translateWord: TextView? = null
        var deleteItem: ImageView? = null


        init {
            englishWord = itemView.tvItemMeat
            translateWord = itemView.tvItemContent
            deleteItem = itemView.ivItemMeatDelete
        }

        interface ItemCallback {
            fun deleteItem(index: Int)
        }

        fun setData(item: Soveti) {
            itemView.setOnClickListener {
                val intent = Intent(context, EditSovetiActivity::class.java).apply {
                    putExtra(MyIntentConstance.I_SOVETINAME_KEY, item.englishWord)
                    putExtra(MyIntentConstance.I_SOVETICONTENT_KEY, item.translateWord)
                }
                context.startActivity(intent)
            }
        }
    }
}