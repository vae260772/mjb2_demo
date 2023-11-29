package com.perfbrainstorming.happyguessing

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.perfbrainstorming.happyguessing.R

class QuestionActivity : AppCompatActivity(), View.OnClickListener {
    var TAG = "QuestionActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.brain_games_content_item)
        //     var data = intent.getSerializableExtra("type") as List<TypeItemBean1>
        var type = intent.getIntExtra("data", 0)

        //Log.d(TAG, "type===" + type)


        val collect = findViewById<ImageView>(R.id.collect)
        collect.setOnClickListener(this)

        val tv1 = findViewById<TextView>(R.id.tv1)
        tv1.setOnClickListener(this)
        val tv2 = findViewById<TextView>(R.id.tv2)
        tv2.setOnClickListener(this)
        val bt1 = findViewById<Button>(R.id.bt1)
        bt1.setOnClickListener(this)
        val bt2 = findViewById<Button>(R.id.bt2)
        bt2.setOnClickListener(this)
        val bt3 = findViewById<Button>(R.id.bt3)
        bt3.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.collect) {
        }
        if (v.id == R.id.tv1) {
        }
        if (v.id == R.id.tv2) {
        }
        if (v.id == R.id.bt1) {
        }
        if (v.id == R.id.bt2) {
        }


        if (v.id == R.id.bt3) {
        }
    }

}