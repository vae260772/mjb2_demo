package com.perfbrainstorming.happyguessing

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.perfbrainstorming.happyguessing.R
import me.majiajie.pagerbottomtabstrip.PageNavigationView
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem
import me.majiajie.pagerbottomtabstrip.item.NormalItemView
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener

class MainBottomTabActivity : AppCompatActivity() {
    private val mFragments = ArrayList<Fragment>()
    var number: Int = 8
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.brain_games)
        //初始化Fragment
        initFragment()
        //初始化底部Button
        initBottomTab()
    }

    /**
     * 初始化四个导航页面
     */
    fun initFragment() {
        mFragments!!.add(FirstFragment())
        mFragments!!.add(SecondFragment())
        mFragments!!.add(ThirdFragment())
        //默认选中第一个
        val transaction = supportFragmentManager.beginTransaction()
        transaction!!.add(R.id.frameLayout, mFragments[0])
        transaction.commitAllowingStateLoss()
    }

    lateinit var pager_bottom_tab: PageNavigationView


    fun initBottomTab() {
        pager_bottom_tab = findViewById<PageNavigationView>(R.id.bottom_navigation_bar)
        //这里要特别注意，pager_bottom_tab.custom()这句话就是选择自己需要的样式
        val navigationController = pager_bottom_tab.custom()
            .addItem(newItem(R.mipmap.tab_brain_unselected, R.mipmap.tab_brain_selected, "sharp turns"))
            .addItem(
                newItem(
                    R.mipmap.tab_favorite_unselected,
                    R.mipmap.tab_favorite_selected,
                    "collection"
                )
            )
            .addItem(newItem(R.mipmap.tab_more_unselected, R.mipmap.tab_more_selected, "more"))
            .build()
        //设置消息数
        //navigationController.setMessageNumber(1, number)
        //设置显示小圆点
        //navigationController.setHasMessage(0, true)
        //底部按钮的点击事件监听
        navigationController.addTabItemSelectedListener(object : OnTabItemSelectedListener {
            override fun onSelected(index: Int, old: Int) {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, mFragments[index])
                transaction.commitAllowingStateLoss()
//                if (index == 0) {
//                    navigationController.setHasMessage(0, false)
//                } else if (index == 1) {
//                    navigationController.setMessageNumber(1, --number)
//                }
            }

            override fun onRepeat(index: Int) {}
        })
    }

    //创建一个Item
    private fun newItem(drawable: Int, checkedDrawable: Int, text: String): BaseTabItem {
        val normalItemView = NormalItemView(this)
        normalItemView.initialize(drawable, checkedDrawable, text)
        normalItemView.setTextDefaultColor(Color.GRAY)
        normalItemView.setTextCheckedColor(Color.BLUE)
        //   normalItemView.sette
        return normalItemView
    }
}