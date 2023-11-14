package com.ashdot.safeount;

import static com.ashdot.safeount.SLOTOTERRAApplication.mPreferences;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;


public class GameGuidePage extends Activity implements ViewPager.OnPageChangeListener {

    private ViewPager vp_guide;
    private GameViewPagerAdapter vp_guide_Adapter;
    private List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide_page);
        initViews();
        initDots();
    }

    public static final String showed_guidpages = "showed_guidpages";

    //初始化
    private void initViews() {
        //LayoutInflater 实例化
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.guide_page_one, null));
        views.add(inflater.inflate(R.layout.guide_page_two, null));
        views.add(inflater.inflate(R.layout.guide_page_three, null));
        //将数据传入适配器
        vp_guide_Adapter = new GameViewPagerAdapter(views, this);
        vp_guide = (ViewPager) findViewById(R.id.viewpager_guide);
        vp_guide.setAdapter(vp_guide_Adapter);
        views.get(views.size() - 1).findViewById(R.id.page3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreferences.edit().putBoolean(showed_guidpages, true).apply();
                Intent intent = new Intent(GameGuidePage.this, SLOTOTERRAGameActivity.class);
                startActivity(intent);
                finish();
            }
        });
        vp_guide.setOnPageChangeListener(this);

    }

    private void initDots() {

    }

    //当页面被滑动时调用
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //当前新的页面被选中时调用
    @Override
    public void onPageSelected(int position) {

    }

    //滑动状态改变时调用
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

//构造适配器
class GameViewPagerAdapter extends PagerAdapter {

    private List<View> views;
    private Context context;

    public GameViewPagerAdapter(List<View> views, Context context) {
        this.views = views;
        this.context = context;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {

        ((ViewPager) container).removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(View container, int position) {

        ((ViewPager) container).addView(views.get(position));
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }
}