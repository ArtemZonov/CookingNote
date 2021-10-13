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
import com.jkdajac.cookingnote.database.MyIntentConstance
import com.jkdajac.cookingnote.database.Word
import com.jkdajac.cookingnote.meat.EditMeatActivity
import com.jkdajac.cookingnote.meat.MeatActivity
import kotlinx.android.synthetic.main.item_meat.view.*

class MeatAdapter(val contextA: Context,
                  val meatList: List<Word>,
                  val callback: ViewHolder.ItemCallback
) : RecyclerView.Adapter<MeatAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(contextA).inflate(R.layout.item_meat, parent, false), contextA)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(meatList[position])
        holder.englishWord?.text = meatList[position].englishWord
        holder.translateWord?.text = meatList[position].translateWord
        //holder.translateWord?.text = meatList[position].translateWord
//        holder.closeItem?.setOnClickListener {
//            callback.closeItem(position)
//        }
//        holder.openItem?.setOnClickListener {
//            callback.openItem(position)
//        }
        holder.deleteItem?.setOnClickListener {
            callback.deleteItem(position)

        }

    }

    override fun getItemCount(): Int {
        return meatList.size
    }

    class ViewHolder(itemView : View, contextV: Context)  : RecyclerView.ViewHolder(itemView){

        val context = contextV
        var englishWord: TextView? = null
        var translateWord: TextView? = null
        //var translateWord: TextView? = null
        var deleteItem : ImageView? = null
//        var openItem : ImageView? = null
//        var closeItem : ImageView? = null

        init{
            englishWord = itemView.tvItemMeat
            translateWord = itemView.tvItemContent
            //translateWord = itemView.tvI
            deleteItem = itemView.ivItemMeatDelete
//            openItem = itemView.ivOpenTranslate
//            closeItem = itemView.ivCloseTranslate

        }

        fun setData(item : Word){

            itemView.setOnClickListener {
                val intent = Intent(context, EditMeatActivity :: class.java).apply {
                    putExtra(MyIntentConstance.I_NAME_KEY, item.englishWord)
                    putExtra(MyIntentConstance.I_CONTENT_KEY, item.translateWord)
                }
                context.startActivity(intent)
            }


        }

        interface ItemCallback {
            fun deleteItem(index: Int)
//            fun openItem(index : Int)
//            fun closeItem(index : Int)


        }

    }
}