package com.abcdk.puuhdjncluk

import android.graphics.drawable.Drawable

class puuhdjncluk_Item(private var Id: Int, private var Image: Drawable?) {

    fun getId(): Int {
        return Id
    }

    fun setId(id: Int) {
        Id = id
    }

    fun getImage(): Drawable? {
        return Image
    }

    fun setImage(image: Drawable?) {
        Image = image
    }

    constructor(image: Drawable?) : this(0, image)
}
 