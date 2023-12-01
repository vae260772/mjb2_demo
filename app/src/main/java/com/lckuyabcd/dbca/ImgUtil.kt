package com.lckuyabcd.dbca

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix

class ImgUtil {
    var itemBean: ItemBean? = null
    fun createInitBitmaps(
        type: Int, picSelected: Bitmap,
        context: Context
    ) {
        var bitmap: Bitmap? = null
        val bitmapItems: MutableList<Bitmap?> = ArrayList()
        val itemWidth = picSelected.width / type
        val itemHeight = picSelected.height / type
        for (i in 1..type) {
            for (j in 1..type) {
                bitmap = Bitmap.createBitmap(
                    picSelected,
                    (j - 1) * itemWidth,
                    (i - 1) * itemHeight,
                    itemWidth,
                    itemHeight
                )
                bitmapItems.add(bitmap)
                itemBean = ItemBean(
                    (i - 1) * type + j,
                    (i - 1) * type + j,
                    bitmap
                )
                GUtil.mItemBeans.add(itemBean)
            }
        }
        PicMain.mLastBitmap = bitmapItems[type * type - 1]
        bitmapItems.removeAt(type * type - 1)
        GUtil.mItemBeans.removeAt(type * type - 1)
        var blankBitmap = BitmapFactory.decodeResource(
            context.resources, R.drawable.blank
        )
        blankBitmap = Bitmap.createBitmap(
            blankBitmap, 0, 0, itemWidth, itemHeight
        )
        bitmapItems.add(blankBitmap)
        GUtil.mItemBeans.add(ItemBean(type * type, 0, blankBitmap))
        GUtil.mBlankItemBean = GUtil.mItemBeans[type * type - 1]
    }

    fun resizeBitmap(
        newWidth: Float,
        newHeight: Float,
        bitmap: Bitmap
    ): Bitmap {
        val matrix = Matrix()
        matrix.postScale(
            newWidth / bitmap.width,
            newHeight / bitmap.height
        )
        return Bitmap.createBitmap(
            bitmap, 0, 0,
            bitmap.width,
            bitmap.height,
            matrix, true
        )
    }
}