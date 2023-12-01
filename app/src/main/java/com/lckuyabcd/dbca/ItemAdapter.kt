package com.lckuyabcd.dbca

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView

class ItemAdapter(private val mContext: Context, private val mBitmapItemLists: List<Bitmap>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return mBitmapItemLists.size
    }

    override fun getItem(position: Int): Any {
        return mBitmapItemLists[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var iv_pic_item: ImageView? = null
        if (convertView == null) {
            iv_pic_item = ImageView(mContext)
            iv_pic_item.layoutParams = AbsListView.LayoutParams(
                mBitmapItemLists[position].width,
                mBitmapItemLists[position].height
            )
            iv_pic_item.scaleType = ImageView.ScaleType.FIT_CENTER
        } else {
            iv_pic_item = convertView as ImageView
        }
        iv_pic_item.setImageBitmap(mBitmapItemLists[position])
        return iv_pic_item
    }
}