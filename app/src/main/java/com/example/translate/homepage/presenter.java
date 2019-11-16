package com.example.translate.homepage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.translate.Base.TranslateData;
import com.example.translate.Base.baseBean;
import com.youdao.sdk.ydonlinetranslate.Translator;

import java.util.List;

import retrofit2.Call;

public class presenter {

    private moudle moudle;
    private viewinterface viewinterface;


    public presenter(viewinterface viewinterface){
        moudle = new moudle();
        this.viewinterface = viewinterface;
    }

    public Translator getdata(String q, String from, String to){

        return  moudle.getdata(q,from,to);
    }


    public boolean checkParameter(Context context) {
        if (!checkNetwork(context)) {
            viewinterface.showNetworkError();
            return false;
        }
        return true;
    }


    public boolean checkNetwork(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);

        if (manager == null) {
            return false;
        }

        NetworkInfo networkinfo = manager.getActiveNetworkInfo();

        if (networkinfo == null || !networkinfo.isAvailable()) {
            return false;
        }

        return true;
    }
}
