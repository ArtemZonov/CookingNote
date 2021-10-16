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
import com.jkdajac.cookingnote.database.sous.Sous
import com.jkdajac.cookingnote.sous.EditSousActivity
import com.jkdajac.cookingnote.sous.SousActivity
import kotlinx.android.synthetic.main.item_meat.view.*

class SousAdapter(val contextA: Context,
                  val sousList: List<Sous>,
                  val callback: SousActivity
) : RecyclerView.Adapter<SousAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return SousAdapter.ViewHolder(
            LayoutInflater.from(contextA).inflate(R.layout.item_meat, parent, false), contextA
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(sousList[position])
        holder.englishWord?.text = sousList[position].englishWord
        holder.translateWord?.text = sousList[position].translateWord
        holder.deleteItem?.setOnClickListener {
            callback.deleteItem(position)
        }
    }

    override fun getItemCount(): Int {
        return sousList.size
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

        fun setData(item: Sous) {
            itemView.setOnClickListener {
                val intent = Intent(context, EditSousActivity::class.java).apply {
                    putExtra(MyIntentConstance.I_SOUSNAME_KEY, item.englishWord)
                    putExtra(MyIntentConstance.I_SOUSCONTENT_KEY, item.translateWord)
                }
                context.startActivity(intent)
            }
        }
    }
}