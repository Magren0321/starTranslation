package com.example.translate;

import android.app.Application;
import android.content.Context;

import com.example.translate.Utils.ToastUtils;

public class starTranslation  extends Application {

    private static Context appContext;
    private static long exitTime = 0;

    /**
     * 获取Application的Context
     *
     * @return 全局Context
     */
    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    /**
     * 退出APP
     */
    public static void exitApp() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtils.shortToast(getAppContext(), appContext.getString(R.string.text_press_again));
            exitTime = System.currentTimeMillis();
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

}


