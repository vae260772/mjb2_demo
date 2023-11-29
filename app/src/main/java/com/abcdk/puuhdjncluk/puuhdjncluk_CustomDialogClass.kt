package com.abcdk.puuhdjncluk

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageButton

class puuhdjncluk_CustomDialogClass(activity: Activity) : Dialog(activity), View.OnClickListener {
    private var mClickListener: ClickListener? = null
    private val activity: Activity = activity
    private lateinit var btnClaim: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_bonus_puuhdjncluk)
        btnClaim = findViewById(R.id.btnClaimBonus)
        btnClaim.setOnClickListener(this)
    }

    fun setClicklistener(clickListener: ClickListener?) {
        mClickListener = clickListener
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btnClaimBonus) {
            mClickListener?.onClick(v)
        }
    }

    interface ClickListener {
        fun onClick(view: View)
    }
}
 