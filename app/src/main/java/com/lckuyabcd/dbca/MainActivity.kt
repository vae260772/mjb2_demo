package com.lckuyabcd.dbca

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Environment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.PopupWindow
import android.widget.TextView
import com.lckuyabcd.dbca.SUtil.getDeviceDensity

class MainActivity : Activity(), View.OnClickListener {
    private var mGvPicList: GridView? = null
    private var mPicList: MutableList<Bitmap?>? = null
    private lateinit var mResPicId: IntArray
    private var mTvTypeslected: TextView? = null
    private var mLayoutInflater: LayoutInflater? = null
    private var mPopupWindow: PopupWindow? = null
    private var mPopupView: View? = null
    private var mTvType2: TextView? = null
    private var mTvType3: TextView? = null
    private var mTvType4: TextView? = null
    private var mType = 2
    private val mCustomItems = arrayOf("local")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        TEMP_IMAGE_PATH = Environment.getExternalStorageDirectory().path +
                "/temp.png"
        mPicList = ArrayList()
        initViews()
        mGvPicList!!.adapter = PListAdapter(
            this@MainActivity, mPicList
        )
        mGvPicList!!.onItemClickListener = OnItemClickListener { arg0, view, position, arg3 ->
            if (position == mResPicId.size - 1) {
            } else {
                val intent = Intent(
                    this@MainActivity,
                    PicMain::class.java
                )
                intent.putExtra("picSelectedID", mResPicId[position])
                intent.putExtra("mType", mType)
                startActivity(intent)
            }
        }
        mTvTypeslected!!.setOnClickListener { v -> popupShow(v) }
    }


    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == RESULT_IMAGE && data != null) {
                val cursor = this.contentResolver.query(
                    data.data!!, null, null, null, null
                )
                cursor!!.moveToFirst()
                val imagePath = cursor.getString(
                    cursor.getColumnIndex("_data")
                )
                val intent = Intent(
                    this@MainActivity,
                    PicMain::class.java
                )
                intent.putExtra("picPath", imagePath)
                intent.putExtra("mType", mType)
                cursor.close()
                startActivity(intent)
            } else if (requestCode == RESULT_CAMERA) {
                val intent = Intent(
                    this@MainActivity,
                    PicMain::class.java
                )
                intent.putExtra("mPicPath", TEMP_IMAGE_PATH)
                intent.putExtra("mType", mType)
                startActivity(intent)
            }
        }
    }

    private fun popupShow(view: View) {
        val density = getDeviceDensity(this).toInt()
        mPopupWindow = PopupWindow(
            mPopupView,
            200 * density, 50 * density
        )
        mPopupWindow!!.isFocusable = true
        mPopupWindow!!.isOutsideTouchable = true
        val transpent: Drawable = ColorDrawable(Color.TRANSPARENT)
        mPopupWindow!!.setBackgroundDrawable(transpent)
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        mPopupWindow!!.showAtLocation(
            view,
            Gravity.NO_GRAVITY,
            location[0] - 40 * density,
            location[1] + 30 * density
        )
    }

    private fun initViews() {
        mGvPicList = findViewById<View>(
            R.id.gv_main_pic_list
        ) as GridView
        mResPicId = intArrayOf(
            R.drawable.p1, R.drawable.p2, R.drawable.p3,
            R.drawable.p4, R.drawable.p5, R.drawable.p6,
            R.drawable.p7, R.drawable.p8, R.drawable.p9,
            R.drawable.p10, R.drawable.p11, R.drawable.p12,
            R.drawable.p13, R.drawable.p14,
            R.drawable.p15, R.mipmap.ic_launcher
        )
        val bitmaps = arrayOfNulls<Bitmap>(mResPicId.size)
        for (i in bitmaps.indices) {
            bitmaps[i] = BitmapFactory.decodeResource(
                resources, mResPicId[i]
            )
            mPicList!!.add(bitmaps[i])
        }
        mTvTypeslected = findViewById<View>(
            R.id.tv_main_type_selected
        ) as TextView
        mLayoutInflater = getSystemService(
            LAYOUT_INFLATER_SERVICE
        ) as LayoutInflater
        // mType view
        mPopupView = mLayoutInflater!!.inflate(
            R.layout.main_type_selected, null
        )
        mTvType2 = mPopupView!!.findViewById<View>(R.id.tv_main_type_2) as TextView
        mTvType3 = mPopupView!!.findViewById<View>(R.id.tv_main_type_3) as TextView
        mTvType4 = mPopupView!!.findViewById<View>(R.id.tv_main_type_4) as TextView
        mTvType2!!.setOnClickListener(this)
        mTvType3!!.setOnClickListener(this)
        mTvType4!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_main_type_2 -> {
                mType = 2
                mTvTypeslected!!.text = "2 X 2"
            }

            R.id.tv_main_type_3 -> {
                mType = 3
                mTvTypeslected!!.text = "3 X 3"
            }

            R.id.tv_main_type_4 -> {
                mType = 4
                mTvTypeslected!!.text = "4 X 4"
            }

            else -> {}
        }
        mPopupWindow!!.dismiss()
    }

    companion object {
        private const val RESULT_IMAGE = 100
        private const val RESULT_CAMERA = 200
        private const val IMAGE_TYPE = "image/*"
        var TEMP_IMAGE_PATH: String? = null
    }
}