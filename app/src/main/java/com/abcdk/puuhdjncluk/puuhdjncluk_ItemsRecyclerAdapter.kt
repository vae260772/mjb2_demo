package com.abcdk.puuhdjncluk

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class puuhdjncluk_ItemsRecyclerAdapter(context: Context, items: ArrayList<puuhdjncluk_Item>) :
    RecyclerView.Adapter<puuhdjncluk_ItemsRecyclerAdapter.ViewHolder>() {

    private val items: ArrayList<puuhdjncluk_Item> = items
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private var mClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = layoutInflater.inflate(R.layout.recycler_item_puuhdjncluk, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val positionInList = position % items.size
        holder.imageView.setImageDrawable(items[positionInList].getImage())
    }

    fun getItem(id: Int): puuhdjncluk_Item {
        return items[id]
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var imageView: ImageView = itemView.findViewById(R.id.recyclerImage)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            mClickListener?.onItemClick(view, adapterPosition)
        }
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}
 