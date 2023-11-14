package com.ashdot.safeount;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseGameActivity extends AppCompatActivity implements View.OnClickListener {
    public Context context;

    public String intent_str1 = "intent_str1";
    public String intent_str2 = "intent_str2";
    public String intent_str3 = "intent_str3";
    public String intent_str4 = "intent_str4";

    public SharedPreferences sharedPreferences;

    @Override
    public void onClick(View v) {

    }




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        sharedPreferences = getSharedPreferences("com.ashdot.safeount", MODE_PRIVATE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
