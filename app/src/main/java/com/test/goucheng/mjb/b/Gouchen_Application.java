package com.test.goucheng.mjb.b;

import android.app.Application;
import android.content.Context;

public class Gouchen_Application extends Application {
    public static Context myAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        myAppContext = this;

    }
}
