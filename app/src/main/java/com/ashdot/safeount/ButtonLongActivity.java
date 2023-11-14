package com.ashdot.safeount;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

/* loaded from: classes.dex */
public class ButtonLongActivity extends Activity {
    private RelativeLayout dj1;
    private LinearLayout dj2;
    private TextView fs;
    private ImageView img;
    private LinearLayout l1;
    private LinearLayout l2;
    private ImageView markImg;
    private Animation myAnimation_Alpha;
    private ImageView p1;
    private ImageView p2;
    private TextView sj;
    private long beginTime = 0;
    private long endTime = 0;
    private long sumTime = 10000;//10s
    private long gameTime = 1000;
    private int mark = 0;
    public int number1 = -1;
    public int number2 = -1;
    private long mExitTime = 0;
    Runnable runnable = new Runnable() { // from class: com.classical.mora.game.diy.ButtonLongActivity.1
        @Override // java.lang.Runnable
        public void run() {
            while (ButtonLongActivity.this.sumTime >= 100) {
                ButtonLongActivity.this.handle.sendEmptyMessage(11);
                ButtonLongActivity.this.sumTime -= 100;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    Runnable game = new Runnable() { // from class: com.classical.mora.game.diy.ButtonLongActivity.2
        @Override // java.lang.Runnable
        public void run() {
            if (ButtonLongActivity.this.sumTime > 100) {
                while (ButtonLongActivity.this.gameTime >= 50) {
                    ButtonLongActivity.this.handle.sendEmptyMessage(12);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ButtonLongActivity.this.gameTime -= 50;
                }
                ButtonLongActivity.this.beginTime = System.currentTimeMillis();
            }
        }
    };
    Runnable r = new Runnable() { // from class: com.classical.mora.game.diy.ButtonLongActivity.3
        @Override // java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(800);
                ButtonLongActivity.this.handle.sendEmptyMessage(13);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    Handler handle = new Handler() { // from class: com.classical.mora.game.diy.ButtonLongActivity.4
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    ButtonLongActivity.this.kaishi();
                    return;
                case 1:
                    ButtonLongActivity.this.img.setBackgroundResource(R.drawable.s1);
                    return;
                case 2:
                    ButtonLongActivity.this.img.setBackgroundResource(R.drawable.s1);
                    return;
                case 3:
                    ButtonLongActivity.this.img.setBackgroundResource(R.drawable.s2);
                    return;
                case 4:
                    ButtonLongActivity.this.img.setBackgroundResource(R.drawable.s3);
                    return;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                default:
                    return;
                case 11:
                    if (ButtonLongActivity.this.sumTime != 0) {
                        ButtonLongActivity.this.sj.setText((ButtonLongActivity.this.sumTime / 1000) + "." + ((ButtonLongActivity.this.sumTime % 1000) / 10) + " s");
                        return;
                    }
                    ButtonLongActivity.this.sj.setText("time over");
                    ButtonLongActivity.this.l1.setVisibility(View.GONE);
                    ButtonLongActivity.this.l2.setVisibility(View.VISIBLE);
                    if (25 <= ButtonLongActivity.this.mark) {
                        ButtonLongActivity.this.img.setBackgroundResource(R.drawable.a);
                    } else if (20 <= ButtonLongActivity.this.mark && ButtonLongActivity.this.mark < 25) {
                        ButtonLongActivity.this.img.setBackgroundResource(R.drawable.b);
                    } else if (15 <= ButtonLongActivity.this.mark && ButtonLongActivity.this.mark < 20) {
                        ButtonLongActivity.this.img.setBackgroundResource(R.drawable.c);
                    } else if (10 <= ButtonLongActivity.this.mark && ButtonLongActivity.this.mark < 15) {
                        ButtonLongActivity.this.img.setBackgroundResource(R.drawable.d);
                    } else if (5 <= ButtonLongActivity.this.mark && ButtonLongActivity.this.mark < 10) {
                        ButtonLongActivity.this.img.setBackgroundResource(R.drawable.e);
                    } else if (ButtonLongActivity.this.mark < 5) {
                        ButtonLongActivity.this.img.setBackgroundResource(R.drawable.f);
                    }
                    ButtonLongActivity.this.dj2.setVisibility(View.VISIBLE);
                    ButtonLongActivity.this.dj1.setVisibility(View.GONE);
                    return;
                case 12:
                    ButtonLongActivity.this.changeImg();
                    return;
                case 13:
                    ButtonLongActivity.this.markImg.setVisibility(View.GONE);
                    return;
            }
        }
    };
    Runnable re = new Runnable() { // from class: com.classical.mora.game.diy.ButtonLongActivity.5
        @Override // java.lang.Runnable
        public void run() {
            for (int i = 4; i >= 0; i--) {
                ButtonLongActivity.this.handle.sendEmptyMessage(i);
                if (i != 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        //UMUtils.init(this);
    }

    public void initView() {
        this.myAnimation_Alpha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        this.p1 = (ImageView) findViewById(R.id.view1);
        this.p2 = (ImageView) findViewById(R.id.view2);
        this.l1 = (LinearLayout) findViewById(R.id.l1);
        this.l2 = (LinearLayout) findViewById(R.id.l2);
        this.dj1 = (RelativeLayout) findViewById(R.id.dj1);
        this.dj2 = (LinearLayout) findViewById(R.id.dj2);
        this.img = (ImageView) findViewById(R.id.img);
        this.markImg = (ImageView) findViewById(R.id.mark);
        this.markImg.setVisibility(View.GONE);
        this.fs = (TextView) findViewById(R.id.js);
        this.sj = (TextView) findViewById(R.id.sj);
        this.l1.setVisibility(View.GONE);
        this.l2.setVisibility(View.VISIBLE);
    }

    public void kaishi() {
        this.l2.setVisibility(View.GONE);
        this.l1.setVisibility(View.VISIBLE);
        this.dj2.setVisibility(View.GONE);
        this.dj1.setVisibility(View.VISIBLE);
        new Thread(this.runnable).start();
        new Thread(this.game).start();
        this.beginTime = System.currentTimeMillis();
    }

    public void next() {
        this.beginTime = System.currentTimeMillis();
        new Thread(this.game).start();
    }

    public void getMark() {
        if (this.endTime - this.beginTime <= 1000) {
            this.mark += 3;
            this.markImg.setVisibility(View.VISIBLE);
            this.markImg.setBackgroundResource(R.drawable.j3);
            this.markImg.startAnimation(this.myAnimation_Alpha);
            new Thread(this.r).start();
        } else if (this.endTime - this.beginTime > 1000 && this.endTime - this.beginTime <= 1500) {
            this.mark += 2;
            this.markImg.setVisibility(View.VISIBLE);
            this.markImg.setBackgroundResource(R.drawable.j2);
            this.markImg.startAnimation(this.myAnimation_Alpha);
            new Thread(this.r).start();
        } else if (this.endTime - this.beginTime >= 1500) {
            this.mark++;
            this.markImg.setVisibility(View.VISIBLE);
            this.markImg.setBackgroundResource(R.drawable.j1);
            this.markImg.startAnimation(this.myAnimation_Alpha);
            new Thread(this.r).start();
        }
        this.fs.setText("scores : " + this.mark);
    }

    public void getJMark() {
        if (this.mark > 0) {
            this.mark--;
            this.markImg.setVisibility(View.VISIBLE);
            this.markImg.setBackgroundResource(R.drawable.jian1);
            this.markImg.startAnimation(this.myAnimation_Alpha);
            new Thread(this.r).start();
        }
        this.fs.setText("scores : " + this.mark);
    }

    public void zuo(View v) {
        if (this.gameTime <= 50) {
            this.endTime = System.currentTimeMillis();
            if (this.number1 == 1) {
                if (this.number2 == 3) {
                    getMark();
                } else {
                    getJMark();
                }
            } else if (this.number1 == 2) {
                if (this.number2 == 1) {
                    getMark();
                } else {
                    getJMark();
                }
            } else if (this.number1 == 3) {
                if (this.number2 == 2) {
                    getMark();
                } else {
                    getJMark();
                }
            }
            this.gameTime = 1000;
            next();
        }
    }

    public void zhong(View v) {
        if (this.gameTime <= 50) {
            this.endTime = System.currentTimeMillis();
            if (this.number1 == this.number2) {
                getMark();
            } else {
                getJMark();
            }
            this.gameTime = 1000;
            next();
        }
    }

    public void you(View v) {
        if (this.gameTime <= 50) {
            this.endTime = System.currentTimeMillis();
            if (this.number2 == 1) {
                if (this.number1 == 3) {
                    getMark();
                } else {
                    getJMark();
                }
            } else if (this.number2 == 2) {
                if (this.number1 == 1) {
                    getMark();
                } else {
                    getJMark();
                }
            } else if (this.number2 == 3) {
                if (this.number1 == 2) {
                    getMark();
                } else {
                    getJMark();
                }
            }
            this.gameTime = 1000;
            next();
        }
    }

    public void restart(View v) {
        this.dj2.setVisibility(View.GONE);
        new Thread(this.re).start();
        this.sumTime = 30000;
        this.mark = 0;
        this.fs.setText("scores : " + this.mark);
    }

    public void changeImg() {//拳头
        getRandomNumber();
        if (this.number1 == 1) {
            this.p1.setBackgroundResource(R.drawable.jd);
        } else if (this.number1 == 2) {
            this.p1.setBackgroundResource(R.drawable.st);
        } else if (this.number1 == 3) {
            this.p1.setBackgroundResource(R.drawable.bu);
        }
        if (this.number2 == 1) {
            this.p2.setBackgroundResource(R.drawable.zjd);
        } else if (this.number2 == 2) {
            this.p2.setBackgroundResource(R.drawable.zst);
        } else if (this.number2 == 3) {
            this.p2.setBackgroundResource(R.drawable.zbu);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        new Thread(this.re).start();
        //UMUtils.onResume(this);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        //UMUtils.onPause(this);
    }

    private void getRandomNumber() {
        Random r = new Random();
        this.number1 = Math.abs(r.nextInt(3) + 1);
        this.number2 = Math.abs(r.nextInt(3) + 1);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        if (System.currentTimeMillis() - this.mExitTime > 2000) {
            Toast.makeText(this, "Press again to exit the program", Toast.LENGTH_SHORT).show();
            this.mExitTime = System.currentTimeMillis();
        } else {
            System.exit(0);
        }
        return true;
    }

    public void jk() {
        this.mExitTime = System.currentTimeMillis();
    }
}