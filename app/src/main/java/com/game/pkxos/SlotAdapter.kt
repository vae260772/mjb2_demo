package com.game.pkxos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections

class SlotAdapter(myDataset: List<Int>?) : RecyclerView.Adapter<SlotAdapter.ViewHolder>() {
    private val mDataset: List<Int>

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var mImageView: ImageView

        init {
            mImageView = v.findViewById(R.id.image_view)
        }
    }

    init {
        mDataset = ArrayList(myDataset)
        Collections.shuffle(mDataset)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataIndex = mDataset[position % mDataset.size]
        holder.mImageView.setImageResource(dataIndex)
        holder.mImageView.tag = dataIndex
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }
}