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
import com.jkdajac.cookingnote.database.salad.Salad
import com.jkdajac.cookingnote.salad.EditSaladActivity
import com.jkdajac.cookingnote.salad.SaladActivity

import kotlinx.android.synthetic.main.item_meat.view.*


class SaladAdapter(val contextA: Context,
                   val saladList: List<Salad>,
                   val callback: SaladActivity
) : RecyclerView.Adapter<SaladAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(LayoutInflater.from(contextA).inflate(R.layout.item_meat, parent, false), contextA)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(saladList[position])
        holder.englishWord?.text = saladList[position].englishWord
        holder.translateWord?.text = saladList[position].translateWord
        holder.deleteItem?.setOnClickListener {
            callback.deleteItem(position)
        }
    }

    override fun getItemCount(): Int {
        return  saladList.size
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

        fun setData(item: Salad) {
            itemView.setOnClickListener {
                val intent = Intent(context, EditSaladActivity::class.java).apply {
                    putExtra(MyIntentConstance.I_SALADNAME_KEY, item.englishWord)
                    putExtra(MyIntentConstance.I_SALADCONTENT_KEY, item.translateWord)
                }
                context.startActivity(intent)
            }
        }
    }

}