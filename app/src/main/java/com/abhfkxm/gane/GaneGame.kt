package com.abhfkxm.gane

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Arrays

class GaneGame : AppCompatActivity() {
    private var gameActive = true
    private var activePlayer = 0
    private val gameState = IntArray(9) { 2 } // 2 represents Null
    private val winPositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun playerTap(view: View) {
        val img = view as ImageView
        val tappedImage = img.tag.toString().toInt()

        if (!gameActive) {
            gameReset(view)
            counter = 0
        }

        if (gameState[tappedImage] == 2) {
            counter++
            if (counter == 9) gameActive = false
            gameState[tappedImage] = activePlayer

            val status = findViewById<TextView>(R.id.status)
            val nextPlayer = if (activePlayer == 0) 1 else 0
            val playerSymbol = if (activePlayer == 0) "X" else "O"
            status.text = "$playerSymbol's Turn - Tap to play"

            val resourceId = if (activePlayer == 0) R.drawable.x else R.drawable.o
            img.setImageResource(resourceId)
            activePlayer = nextPlayer

            img.translationY = -1000f
            img.animate().translationYBy(1000f).duration = 300
        }

        var flag = 0
        if (counter > 4) {
            for (winPosition in winPositions) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2
                ) {
                    flag = 1
                    val winnerStr = if (gameState[winPosition[0]] == 0) "X has won" else "O has won"
                    gameActive = false
                    val status = findViewById<TextView>(R.id.status)
                    status.text = winnerStr
                }
            }
            if (counter == 9 && flag == 0) {
                val status = findViewById<TextView>(R.id.status)
                status.text = "Match Draw"
            }
        }
    }

    fun gameReset(view: View?) {
        gameActive = true
        activePlayer = 0
        Arrays.fill(gameState, 2)

        val imageIds = arrayOf(
            R.id.imageView0, R.id.imageView1, R.id.imageView2,
            R.id.imageView3, R.id.imageView4, R.id.imageView5,
            R.id.imageView6, R.id.imageView7, R.id.imageView8
        )

        for (imageId in imageIds) {
            (findViewById<View>(imageId) as ImageView).setImageResource(0)
        }

        val status = findViewById<TextView>(R.id.status)
        status.text = "X's Turn - Tap to play"
    }

    companion object {
        var counter = 0
    }
}
 