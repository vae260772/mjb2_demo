package com.abcdgame.game.a

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abcdgame.game.R


class AActivity : AppCompatActivity() {

    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
    }
}