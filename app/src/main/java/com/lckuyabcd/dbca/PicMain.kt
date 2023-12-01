package com.lckuyabcd.dbca

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.lckuyabcd.dbca.SUtil.getScreenSize
import java.io.File
import java.util.Timer
import java.util.TimerTask

class PicMain : Activity(), View.OnClickListener {
    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> {
                    TIMER_INDEX++
                    mTvTimer!!.text = "" + TIMER_INDEX
                }

                else -> {}
            }
        }
    }
    private var mPicSelected: Bitmap? = null
    private var mGvPiceMainDetail: GridView? = null
    private var mResId = 0
    private var mPicPath: String? = null
    private var mImageView: ImageView? = null
    private var mBtnBack: Button? = null
    private var mBtnImage: Button? = null
    private var mBtnRestart: Button? = null
    private var mainCount: TextView? = null
    private var mTvTimer: TextView? = null
    private val mBitmapItemLists: MutableList<Bitmap?> = ArrayList()
    private var mAdapter: ItemAdapter? = null
    private var mIsShowImg = false
    private var mTimer: Timer? = null
    private var mTimerTask: TimerTask? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_main)
        val picSelectedTemp: Bitmap
        mResId = intent.extras!!.getInt("picSelectedID")
        mPicPath = intent.extras!!.getString("mPicPath")
        picSelectedTemp = if (mResId != 0) {
            BitmapFactory.decodeResource(
                resources, mResId
            )
        } else {
            BitmapFactory.decodeFile(mPicPath)
        }
        TYPE = intent.extras!!.getInt("mType", 2)
        handlerImage(picSelectedTemp)
        initViews()
        generateGame()
        mGvPiceMainDetail!!.onItemClickListener =
            OnItemClickListener { arg0, view, position, arg3 ->
                if (GUtil.isMoveable(position)) {
                    GUtil.swapItems(
                        GUtil.mItemBeans[position],
                        GUtil.mBlankItemBean
                    )
                    recreateData()
                    mAdapter!!.notifyDataSetChanged()
                    COUNT_INDEX++
                    mainCount!!.text = "" + COUNT_INDEX
                    if (GUtil.isSuccess()) {
                        recreateData()
                        mBitmapItemLists.removeAt(TYPE * TYPE - 1)
                        mBitmapItemLists.add(mLastBitmap)
                        mAdapter!!.notifyDataSetChanged()
                        Toast.makeText(
                            this@PicMain, "success",
                            Toast.LENGTH_LONG
                        ).show()
                        mGvPiceMainDetail!!.isEnabled = false
                        mTimer!!.cancel()
                        mTimerTask!!.cancel()
                    }
                }
            }
        mBtnBack!!.setOnClickListener(this)
        mBtnImage!!.setOnClickListener(this)
        mBtnRestart!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btmain_back -> finish()
            R.id.btnmain_img -> {
                val animShow = AnimationUtils.loadAnimation(
                    this@PicMain, R.anim.image_show_anim
                )
                val animHide = AnimationUtils.loadAnimation(
                    this@PicMain, R.anim.image_hide_anim
                )
                if (mIsShowImg) {
                    mImageView!!.startAnimation(animHide)
                    mImageView!!.visibility = View.GONE
                    mIsShowImg = false
                } else {
                    mImageView!!.startAnimation(animShow)
                    mImageView!!.visibility = View.VISIBLE
                    mIsShowImg = true
                }
            }

            R.id.btnmain_restart -> {
                cleanConfig()
                generateGame()
                recreateData()
                mainCount!!.text = "" + COUNT_INDEX
                mAdapter!!.notifyDataSetChanged()
                mGvPiceMainDetail!!.isEnabled = true
            }

            else -> {}
        }
    }

    private fun generateGame() {
        ImgUtil().createInitBitmaps(
            TYPE, mPicSelected!!, this@PicMain
        )
        GUtil.getpicGenerator()
        for (temp in GUtil.mItemBeans) {
            mBitmapItemLists.add(temp.bitmap)
        }
        mAdapter = ItemAdapter(this, mBitmapItemLists as List<Bitmap>)
        mGvPiceMainDetail!!.adapter = mAdapter
        mTimer = Timer(true)
        mTimerTask = object : TimerTask() {
            override fun run() {
                val msg = Message()
                msg.what = 1
                mHandler.sendMessage(msg)
            }
        }
        mTimer!!.schedule(mTimerTask, 0, 1000)
    }

    private fun addImgView() {
        val relativeLayout = findViewById<View>(
            R.id.rl_main_main_layout
        ) as RelativeLayout
        mImageView = ImageView(this@PicMain)
        mImageView!!.setImageBitmap(mPicSelected)
        val x = (mPicSelected!!.width * 0.9f).toInt()
        val y = (mPicSelected!!.height * 0.9f).toInt()
        val params = RelativeLayout.LayoutParams(x, y)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        mImageView!!.layoutParams = params
        relativeLayout.addView(mImageView)
        mImageView!!.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        cleanConfig()
        finish()
    }

    private fun cleanConfig() {
        GUtil.mItemBeans.clear()
        mTimer!!.cancel()
        mTimerTask!!.cancel()
        COUNT_INDEX = 0
        TIMER_INDEX = 0
        if (mPicPath != null) {
            val file = File(MainActivity.TEMP_IMAGE_PATH)
            if (file.exists()) {
                file.delete()
            }
        }
    }

    private fun recreateData() {
        mBitmapItemLists.clear()
        for (temp in GUtil.mItemBeans) {
            mBitmapItemLists.add(temp.bitmap)
        }
    }

    private fun handlerImage(bitmap: Bitmap) {
        val screenWidth = getScreenSize(this).widthPixels
        val screenHeigt = getScreenSize(this).heightPixels
        mPicSelected = ImgUtil().resizeBitmap(
            screenWidth * 0.8f, screenHeigt * 0.6f, bitmap
        )
    }

    private fun initViews() {
        mBtnBack = findViewById<View>(R.id.btmain_back) as Button
        mBtnImage = findViewById<View>(R.id.btnmain_img) as Button
        mBtnRestart = findViewById<View>(R.id.btnmain_restart) as Button
        mIsShowImg = false
        mGvPiceMainDetail = findViewById<View>(
            R.id.gvmain_detail
        ) as GridView
        mGvPiceMainDetail!!.numColumns = TYPE
        val gridParams = RelativeLayout.LayoutParams(
            mPicSelected!!.width,
            mPicSelected!!.height
        )
        gridParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
        gridParams.addRule(
            RelativeLayout.BELOW,
            R.id.ll_main_spinner
        )
        mGvPiceMainDetail!!.layoutParams = gridParams
        mGvPiceMainDetail!!.horizontalSpacing = 0
        mGvPiceMainDetail!!.verticalSpacing = 0
        mainCount = findViewById<View>(
            R.id.tv_main_counts
        ) as TextView
        mainCount!!.text = "" + COUNT_INDEX
        mTvTimer = findViewById<View>(R.id.tvmain_time) as TextView
        mTvTimer!!.text = "0s"
        addImgView()
    }

    companion object {
        var mLastBitmap: Bitmap? = null

        @JvmField
        var TYPE = 2
        var COUNT_INDEX = 0
        var TIMER_INDEX = 0
    }
}