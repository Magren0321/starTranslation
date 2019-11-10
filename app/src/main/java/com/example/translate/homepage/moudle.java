package com.example.translate.homepage;

import com.example.translate.Base.MD5Utils;
import com.example.translate.Base.baseBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class moudle {

    public String word;
    public Retrofit retrofit;
    public String url = "http://api.fanyi.baidu.com/api/trans/vip/";

    public String appid = "20191110000355560";
    public String password = "Vd9VgkvLAAUapc4kstsJ";
    String from = "auto";
    public String sign;

    public Call<baseBean> getdata(String q,String to) {
       retrofit = new Retrofit.Builder()
                .baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();

        //创建 网络请求接口 的实例
        geturl_interface request = retrofit.create(geturl_interface.class);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());

        String str = appid+q+salt+password;
        sign = MD5Utils.stringToMD5(str);

        //对 发送请求 进行封装
        Call<baseBean> call = request.getCall(q,from,to,appid,salt,sign);

        return call;

    }

}


