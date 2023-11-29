package com.abcdk.puuhdjncluk

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Random

class puuhdjncluk_GameActivity : AppCompatActivity() {
    private lateinit var txtMoney: TextView
    private lateinit var txtBet: TextView
    private var isRecyclerView1StopScrolling = true
    private var isRecyclerView2StopScrolling = true
    private var isRecyclerView3StopScrolling = true
    private val TAG = "MyDebug"
    private var MyAsyncTask: AsyncTask<Void, Void, Void>? = null
    private val offset = -60
    private val bonusMoney = 500

    private fun giveMoney() {
        val betAmount = txtBet.text.toString().toInt()
        val rand = Random()
        val randomMoney = rand.nextInt(betAmount * 2 - 1 + 1)
        val moneyAfter = txtMoney.text.toString().toInt()
        txtMoney.text = (moneyAfter + randomMoney).toString()
        if (betAmount <= randomMoney) {
            Toast.makeText(applicationContext, "You win ${randomMoney - betAmount} \uD83D\uDE03", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "You lost ${betAmount - randomMoney} \uD83D\uDE12", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_puuhdjncluk)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        txtMoney = findViewById(R.id.txtMoney)
        txtBet = findViewById(R.id.txtBet)

        val items = ArrayList<puuhdjncluk_Item>()
        items.add(puuhdjncluk_Item(resources.getDrawable(R.drawable.ico_1)))
        items.add(puuhdjncluk_Item(resources.getDrawable(R.drawable.ico_2)))
        items.add(puuhdjncluk_Item(resources.getDrawable(R.drawable.ico_3)))
        items.add(puuhdjncluk_Item(resources.getDrawable(R.drawable.ico_4)))
        items.add(puuhdjncluk_Item(resources.getDrawable(R.drawable.ico_5)))
        items.add(puuhdjncluk_Item(resources.getDrawable(R.drawable.ico_6)))
        items.add(puuhdjncluk_Item(resources.getDrawable(R.drawable.ico_7)))
        items.add(puuhdjncluk_Item(resources.getDrawable(R.drawable.ico_8)))

        val recyclerView1 = findViewById<RecyclerView>(R.id.recyclerView1)
        recyclerView1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = puuhdjncluk_ItemsRecyclerAdapter(this, items)
        recyclerView1.adapter = adapter
        recyclerView1.layoutManager?.scrollToPosition(Int.MAX_VALUE / 2 + 150)

        val recyclerView2 = findViewById<RecyclerView>(R.id.recyclerView2)
        recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter2 = puuhdjncluk_ItemsRecyclerAdapter(this, items)
        recyclerView2.adapter = adapter2
        recyclerView2.layoutManager?.scrollToPosition(Int.MAX_VALUE / 2 + 50)

        val recyclerView3 = findViewById<RecyclerView>(R.id.recyclerView3)
        recyclerView3.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter3 = puuhdjncluk_ItemsRecyclerAdapter(this, items)
        recyclerView3.adapter = adapter3
        recyclerView3.layoutManager?.scrollToPosition(Int.MAX_VALUE / 2 - 100)

        val btnSpin = findViewById<ImageButton>(R.id.btnSpin)
        btnSpin.setOnClickListener {
            if (!(isRecyclerView1StopScrolling && isRecyclerView2StopScrolling && isRecyclerView3StopScrolling)) {
                if (recyclerView1.scrollState == RecyclerView.SCROLL_STATE_IDLE &&
                    recyclerView2.scrollState == RecyclerView.SCROLL_STATE_IDLE &&
                    recyclerView3.scrollState == RecyclerView.SCROLL_STATE_IDLE
                ) {

                } else {
                    Toast.makeText(applicationContext, "Please wait till slots stop scrolling", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            val money = txtMoney.text.toString().toInt()
            val betAmount = txtBet.text.toString().toInt()
            if (money >= betAmount) {
                txtMoney.text = (money - betAmount).toString()
            } else {
                Toast.makeText(applicationContext, "Not enough money", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            isRecyclerView1StopScrolling = false
            isRecyclerView2StopScrolling = false
            isRecyclerView3StopScrolling = false

            val randomInt = Random().nextInt(Int.MAX_VALUE / 2 + 100) - (Int.MAX_VALUE / 2 - 100) + (Int.MAX_VALUE / 2 - 100)
            val randomInt2 = Random().nextInt(Int.MAX_VALUE / 2 + 100) - (Int.MAX_VALUE / 2 - 100) + (Int.MAX_VALUE / 2 - 100)
            val randomInt3 = Random().nextInt(Int.MAX_VALUE / 2 + 100) - (Int.MAX_VALUE / 2 - 100) + (Int.MAX_VALUE / 2 - 100)
            recyclerView1.smoothScrollToPosition(randomInt)
            recyclerView2.smoothScrollToPosition(randomInt2)
            recyclerView3.smoothScrollToPosition(randomInt3)

            recyclerView1.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        isRecyclerView1StopScrolling = true
                        val llr = recyclerView1.layoutManager as LinearLayoutManager
                        llr.scrollToPositionWithOffset(
                            (recyclerView1.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition(),
                            offset
                        )
                    }
                }
            })
            recyclerView2.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        isRecyclerView2StopScrolling = true
                        val llr2 = recyclerView2.layoutManager as LinearLayoutManager
                        llr2.scrollToPositionWithOffset(
                            (recyclerView2.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition(),
                            offset
                        )
                    }
                }
            })
            recyclerView3.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        isRecyclerView3StopScrolling = true
                        val llr3 = recyclerView3.layoutManager as LinearLayoutManager
                        llr3.scrollToPositionWithOffset(
                            (recyclerView3.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition(),
                            offset
                        )
                    }
                }
            })

            MyAsyncTask = object : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg voids: Void): Void? {
                    while (!isCancelled) {
                        if (isRecyclerView1StopScrolling && isRecyclerView2StopScrolling && isRecyclerView3StopScrolling) {
                            break
                        }
                    }
                    return null
                }

                override fun onPostExecute(unused: Void?) {
                    super.onPostExecute(unused)
                    giveMoney()
                    val moneyAfter = txtMoney.text.toString().toInt()
                    if (moneyAfter < 10) {
                        val customDialogLose = puuhdjncluk_CustomDialogLose(this@puuhdjncluk_GameActivity)
                        customDialogLose.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        customDialogLose.setClicklistener(object :
                            puuhdjncluk_CustomDialogClass.ClickListener {
                            override fun onClick(view: View) {
                                txtMoney.text = "250"
                                customDialogLose.dismiss()
                            }
                        })
                        customDialogLose.show()
                    }
                }
            }.execute()
        }

        val btnBetPlus = findViewById<ImageButton>(R.id.btnBetPlus)
        btnBetPlus.setOnClickListener {
            val g = txtBet.text.toString().toInt()
            txtBet.text = (g + 10).toString()
        }

        val btnBetMinus = findViewById<ImageButton>(R.id.btnBetMinus)
        btnBetMinus.setOnClickListener {
            val g = txtBet.text.toString().toInt()
            if (g > 10) {
                txtBet.text = (g - 10).toString()
            } else {
                Toast.makeText(applicationContext, "Mimimum bet is 10", Toast.LENGTH_SHORT).show()
            }
        }

        val btnGoBack = findViewById<ImageButton>(R.id.btnGoBack)
        btnGoBack.setOnClickListener { finish() }

        val btnBonus = findViewById<ImageButton>(R.id.btnBonus)
        btnBonus.setOnClickListener {
            val customDialogClass = puuhdjncluk_CustomDialogClass(this@puuhdjncluk_GameActivity)
            customDialogClass.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            customDialogClass.setClicklistener(object :
                puuhdjncluk_CustomDialogClass.ClickListener {
                override fun onClick(view: View) {
                    val moneyAfter = txtMoney.text.toString().toInt()
                    txtMoney.text = (moneyAfter + bonusMoney).toString()
                    customDialogClass.dismiss()
                }
            })
            customDialogClass.show()
        }
    }

    override fun onStop() {
        super.onStop()
        MyAsyncTask?.cancel(true)
    }
}
 