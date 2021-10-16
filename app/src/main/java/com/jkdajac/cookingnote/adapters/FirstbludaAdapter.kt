package com.jkdajac.cookingnote.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jkdajac.cookingnote.R
import com.jkdajac.cookingnote.MyIntentConstance
import com.jkdajac.cookingnote.database.firstbluda.Firstbluda
import com.jkdajac.cookingnote.firstbluda.EditFirstbludaActivity
import com.jkdajac.cookingnote.firstbluda.FirstBludaActivity
import kotlinx.android.synthetic.main.item_meat.view.*

class FirstbludaAdapter(
    val contextA: Context,
    val firstbludaList: ArrayList<Firstbluda>,
    val callback: FirstBludaActivity
) : RecyclerView.Adapter<FirstbludaAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(contextA).inflate(R.layout.item_meat, parent, false), contextA
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(firstbludaList[position])
        holder.englishWord?.text = firstbludaList[position].englishWord
        holder.translateWord?.text = firstbludaList[position].translateWord
        holder.deleteItem?.setOnClickListener {
            callback.deleteItem(position)
        }
    }

    override fun getItemCount(): Int {
        return firstbludaList.size
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

        fun setData(item: Firstbluda) {
            itemView.setOnClickListener {
                val intent = Intent(context, EditFirstbludaActivity::class.java).apply {
                    putExtra(MyIntentConstance.I_BLUDANAME_KEY, item.englishWord)
                    putExtra(MyIntentConstance.I_BLUDACONTENT_KEY, item.translateWord)
                }
                context.startActivity(intent)
            }
        }


    }
}