package com.dxxy.game2

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.View.OnTouchListener
import com.ashdot.safeount.R
import com.dxxy.game2.jdnbdjnfnbc_Point.STATUS
import java.util.Timer
import java.util.TimerTask
import java.util.Vector

class jdnbdjnfnbc_GaView(context: Context) : SurfaceView(context), OnTouchListener {

    companion object {
        private const val ROW = 9
        private const val COL = 9
        private const val BOCKS = COL * ROW / 5
    }
    private var SCREEN_WIDTH = 0
    private var WIDTH = 0
    private var DISTANCE = 0
    private var OFFSET = 0
    private var length = 0
    private var cat_drawable: Drawable? = null
    private var background: Drawable? = null
    private var index = 0
    private val matrix: Array<Array<jdnbdjnfnbc_Point?>> = Array(ROW) { arrayOfNulls(COL) }
    private var cat: jdnbdjnfnbc_Point? = null
    private var timer: Timer? = null
    private var timerTask: TimerTask? = null
    private val context: Context
    private var steps = 0
    private var canMove = true
    private val images = intArrayOf(
        R.drawable.cat1, R.drawable.cat2, R.drawable.cat3,
        R.drawable.cat4, R.drawable.cat5, R.drawable.cat6, R.drawable.cat7,
        R.drawable.cat8, R.drawable.cat9, R.drawable.cat10,
        R.drawable.cat11, R.drawable.cat12, R.drawable.cat13,
        R.drawable.cat14, R.drawable.cat15, R.drawable.cat16
    )

    private fun initGame() {
        steps = 0
        for (i in 0 until ROW) {
            for (j in 0 until COL) {
                matrix[i][j] = jdnbdjnfnbc_Point(j, i)
            }
        }
        for (i in 0 until ROW) {
            for (j in 0 until COL) {
                matrix[i][j]!!.status = STATUS.STATUS_OFF
            }
        }
        cat = jdnbdjnfnbc_Point(COL / 2 - 1, ROW / 2 - 1)
        getDot(cat!!.x, cat!!.y)!!.status = STATUS.STATUS_IN
        var i = 0
        while (i < BOCKS) {
            val x = (Math.random() * 100 % COL).toInt()
            val y = (Math.random() * 100 % ROW).toInt()
            if (getDot(x, y)!!.status === STATUS.STATUS_OFF) {
                getDot(x, y)!!.status = STATUS.STATUS_ON
                i++
            }
        }
    }

    private fun redraw() {
        val canvas = holder.lockCanvas()
        canvas.drawColor(Color.rgb(0, 0x8c, 0xd7))
        val paint = Paint()
        paint.flags = Paint.ANTI_ALIAS_FLAG
        for (i in 0 until ROW) {
            for (j in 0 until COL) {
                DISTANCE = 0
                if (i % 2 != 0) {
                    DISTANCE = WIDTH / 2
                }
                val dot = getDot(j, i)
                when (dot!!.status) {
                    STATUS.STATUS_IN -> paint.color = -0x111112
                    STATUS.STATUS_ON -> paint.color = -0x5600
                    STATUS.STATUS_OFF -> paint.color = 0X74000000
                    else -> {}
                }
                canvas.drawOval(
                    RectF(
                        (dot.x * WIDTH + DISTANCE
                                + length).toFloat(),
                        (dot.y * WIDTH + OFFSET).toFloat(),
                        (((dot.x + 1)
                                * WIDTH) + DISTANCE + length).toFloat(),
                        ((dot.y + 1) * WIDTH
                                + OFFSET).toFloat()
                    ), paint
                )
            }
        }
        val left: Int
        val top: Int
        left = if (cat!!.y % 2 == 0) {
            cat!!.x * WIDTH
        } else {
            WIDTH / 2 + cat!!.x * WIDTH
        }
        top = cat!!.y * WIDTH
        cat_drawable!!.setBounds(
            left - WIDTH / 6 + length, top - WIDTH / 2
                    + OFFSET, left + WIDTH + length, top + WIDTH + OFFSET
        )
        cat_drawable!!.draw(canvas)
        background!!.setBounds(0, 0, SCREEN_WIDTH, OFFSET)
        background!!.draw(canvas)
        holder.unlockCanvasAndPost(canvas)
    }

    var callback: SurfaceHolder.Callback = object : SurfaceHolder.Callback {
        override fun surfaceCreated(holder: SurfaceHolder) {
            redraw()
            startTimer()
        }

        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            WIDTH = width / (COL + 1)
            OFFSET = height - WIDTH * ROW - 2 * WIDTH
            length = WIDTH / 3
            SCREEN_WIDTH = width
        }

        override fun surfaceDestroyed(holder: SurfaceHolder) {
            stopTimer()
        }
    }

    init {
        if (Build.VERSION.SDK_INT < 21) {
            cat_drawable = resources.getDrawable(images[index])
            background = resources.getDrawable(R.drawable.bg)
        } else {
            cat_drawable = resources.getDrawable(images[index], null)
            background = resources.getDrawable(R.drawable.bg, null)
        }
        this.context = context
        initGame()
        holder.addCallback(callback)
        setOnTouchListener(this)
        this.isFocusable = true
        this.isFocusableInTouchMode = true
    }

    private fun startTimer() {
        timer = Timer()
        timerTask = object : TimerTask() {
            override fun run() {
                gifImage()
            }
        }
        timer!!.schedule(timerTask, 50, 65)
    }

    fun stopTimer() {
        timer!!.cancel()
        timer!!.purge()
    }

    private fun gifImage() {
        index++
        if (index > images.size - 1) {
            index = 0
        }
        cat_drawable = if (Build.VERSION.SDK_INT < 21) {
            resources.getDrawable(images[index])
        } else {
            resources.getDrawable(images[index], null)
        }
        redraw()
    }

    private fun getDot(x: Int, y: Int): jdnbdjnfnbc_Point? {
        return matrix[y][x]
    }

    private fun inEdge(dot: jdnbdjnfnbc_Point?): Boolean {
        return if (dot!!.x * dot.y == 0 || dot.x + 1 == COL || dot.y + 1 == ROW
        ) {
            true
        } else false
    }

    private fun moveTo(dot: jdnbdjnfnbc_Point?) {
        dot!!.status = STATUS.STATUS_IN
        getDot(cat!!.x, cat!!.y)!!.status = STATUS.STATUS_OFF
        cat!!.setXY(dot.x, dot.y)
    }

    private fun getDis(one: jdnbdjnfnbc_Point?, dir: Int): Int {
        var distance = 0
        if (inEdge(one)) {
            return 1
        }
        var ori = one
        var next: jdnbdjnfnbc_Point?
        while (true) {
            next = getoBBur(ori, dir)
            if (next!!.status === STATUS.STATUS_ON) {
                return distance * -1
            }
            if (inEdge(next)) {
                distance++
                return distance
            }
            distance++
            ori = next
        }
    }

    private fun getoBBur(dot: jdnbdjnfnbc_Point?, dir: Int): jdnbdjnfnbc_Point? {
        when (dir) {
            1 -> return getDot(dot!!.x - 1, dot.y)
            2 -> return if (dot!!.y % 2 == 0) {
                getDot(dot.x - 1, dot.y - 1)
            } else {
                getDot(dot.x, dot.y - 1)
            }

            3 -> return if (dot!!.y % 2 == 0) {
                getDot(dot.x, dot.y - 1)
            } else {
                getDot(dot.x + 1, dot.y - 1)
            }

            4 -> return getDot(dot!!.x + 1, dot.y)
            5 -> return if (dot!!.y % 2 == 0) {
                getDot(dot.x, dot.y + 1)
            } else {
                getDot(dot.x + 1, dot.y + 1)
            }

            6 -> return if (dot!!.y % 2 == 0) {
                getDot(dot.x - 1, dot.y + 1)
            } else {
                getDot(dot.x, dot.y + 1)
            }
        }
        return null
    }

    private fun move() {
        if (inEdge(cat)) {
            failure()
            return
        }
        val available = Vector<jdnbdjnfnbc_Point?>()
        val direct = Vector<jdnbdjnfnbc_Point?>()
        val hash = HashMap<jdnbdjnfnbc_Point?, Int>()
        for (i in 1..6) {
            val n = getoBBur(cat, i)
            if (n!!.status === STATUS.STATUS_OFF) {
                available.add(n)
                hash[n] = i
                if (getDis(n, i) > 0) {
                    direct.add(n)
                }
            }
        }
        if (available.size == 0) {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Successful clearance")
            dialog.setMessage("You" + (steps + 1) + "Step got The Cat( •̀ ω •́ )y")
            dialog.setCancelable(false)
            dialog.setNegativeButton("play again") { dialog, which ->
                initGame()
                canMove = true
            }
            dialog.setPositiveButton("cancel", null)
            dialog.show()
            canMove = false
        } else if (available.size == 1) {
            moveTo(available[0])
        } else {
            var best: jdnbdjnfnbc_Point? = null
            if (direct.size != 0) {
                var min = 20
                for (i in direct.indices) {
                    if (inEdge(direct[i])) {
                        best = direct[i]
                        break
                    } else {
                        val t = getDis(
                            direct[i],
                            hash[direct[i]]!!
                        )
                        if (t < min) {
                            min = t
                            best = direct[i]
                        }
                    }
                }
            } else {
                var max = 1
                for (i in available.indices) {
                    val k = getDis(
                        available[i],
                        hash[available[i]]!!
                    )
                    if (k < max) {
                        max = k
                        best = available[i]
                    }
                }
            }
            moveTo(best)
        }
        if (inEdge(cat)) {
            failure()
        }
    }

    private fun failure() {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Pass failed")
        dialog.setMessage("You let the nervous cat escape from the mental hospital(ˉ▽ˉ；)...")
        dialog.setCancelable(false)
        dialog.setNegativeButton("play again") { dialog, which ->
            initGame()
            canMove = true
        }
        dialog.setPositiveButton("cancel", null)
        dialog.show()
    }


    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val x: Int
        val y: Int
        if (event.action == MotionEvent.ACTION_UP) {
            if (event.y <= OFFSET) {
                return true
            }
            y = ((event.y - OFFSET) / WIDTH).toInt()
            x = if (y % 2 == 0) {
                if (event.x <= length
                    || event.x >= length + WIDTH * COL
                ) {
                    return true
                }
                ((event.x - length) / WIDTH).toInt()
            } else {
                if (event.x <= length + WIDTH / 2
                    || event.x > length + WIDTH / 2 + WIDTH * COL
                ) {
                    return true
                }
                ((event.x - WIDTH / 2 - length) / WIDTH).toInt()
            }
            if (x + 1 > COL || y + 1 > ROW) {
                return true
            } else if (inEdge(cat) || !canMove) {
                initGame()
                canMove = true
                return true
            } else if (getDot(x, y)!!.status === STATUS.STATUS_OFF) {
                getDot(x, y)!!.status = STATUS.STATUS_ON
                move()
                steps++
            }
        }
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            stopTimer()
        }
        return super.onKeyDown(keyCode, event)
    }

} 