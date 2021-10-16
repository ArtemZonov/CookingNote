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
import com.jkdajac.cookingnote.database.desert.Desert
import com.jkdajac.cookingnote.database.salad.Salad
import com.jkdajac.cookingnote.desert.DesertActivity
import com.jkdajac.cookingnote.desert.EditDesertActivity
import com.jkdajac.cookingnote.salad.EditSaladActivity
import com.jkdajac.cookingnote.salad.SaladActivity
import kotlinx.android.synthetic.main.item_meat.view.*

class DesertAdapter(val contextA: Context,
                    val desertList: List<Desert>,
                    val callback: DesertActivity
) : RecyclerView.Adapter<DesertAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(contextA).inflate(R.layout.item_meat, parent, false), contextA
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(desertList[position])
        holder.englishWord?.text = desertList[position].englishWord
        holder.translateWord?.text = desertList[position].translateWord
        holder.deleteItem?.setOnClickListener {
            callback.deleteItem(position)
        }
    }

    override fun getItemCount(): Int {
        return desertList.size
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

        fun setData(item: Desert) {
            itemView.setOnClickListener {
                val intent = Intent(context, EditDesertActivity::class.java).apply {
                    putExtra(MyIntentConstance.I_DESERTNAME_KEY, item.englishWord)
                    putExtra(MyIntentConstance.I_DESERTCONTENT_KEY, item.translateWord)
                }
                context.startActivity(intent)
            }
        }
    }
}