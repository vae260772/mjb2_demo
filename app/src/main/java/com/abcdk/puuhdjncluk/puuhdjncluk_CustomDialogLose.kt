package com.abcdk.puuhdjncluk

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageButton

class puuhdjncluk_CustomDialogLose(activity: Activity) : Dialog(activity), View.OnClickListener {
    private var mClickListener: puuhdjncluk_CustomDialogClass.ClickListener? = null
    private val activity: Activity = activity
    private lateinit var btnClaim: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_lose_puuhdjncluk)
        btnClaim = findViewById(R.id.btnRestart)
        btnClaim.setOnClickListener(this)
    }

    fun setClicklistener(clickListener: puuhdjncluk_CustomDialogClass.ClickListener?) {
        mClickListener = clickListener
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btnRestart) {
            mClickListener?.onClick(v)
        }
    }
}
 