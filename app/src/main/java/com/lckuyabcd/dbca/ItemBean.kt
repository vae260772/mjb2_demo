package com.lckuyabcd.dbca

import android.graphics.Bitmap

class ItemBean {
    var itemId = 0
    var bitmapId = 0
    var bitmap: Bitmap? = null

    constructor() {}
    constructor(mItemId: Int, mBitmapId: Int, mBitmap: Bitmap?) {
        itemId = mItemId
        bitmapId = mBitmapId
        bitmap = mBitmap
    }
}