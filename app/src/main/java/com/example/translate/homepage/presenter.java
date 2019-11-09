package com.example.translate.homepage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.translate.Base.baseBean;

public class presenter {

    private moudle moudle;
    private viewinterface viewinterface;


    public presenter(viewinterface viewinterface){
        moudle = new moudle();
        this.viewinterface = viewinterface;
    }

    public String getdata(String tl,String q){
       String  getWord =  moudle.getdata(tl,q);

       return getWord;
    }


    private boolean checkParameter(Context context) {
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
