package com.example.translate.homepage;

import android.util.Log;

import com.example.translate.Base.baseBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class moudle {

    public String word;
    public Retrofit retrofit;
    public String url = "http://translate.google.cn/";


    public String getdata(String tl,String q) {
       retrofit = new Retrofit.Builder()
                .baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();

        //创建 网络请求接口 的实例
        geturl_interface request = retrofit.create(geturl_interface.class);

        HashMap<String ,String>map =new HashMap<>();
        map.put("client","gtx");
        map.put("dt","t");
        map.put("dj","1");
        map.put("ie","UTF-8");
        map.put("tl",tl);
        map.put("q",q);


        //对 发送请求 进行封装
        Call<baseBean> call = request.getCall(map);

        //发送网络请求(异步)
        call.enqueue(new Callback<baseBean>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<baseBean> call, Response<baseBean> response) {
                // 处理返回的数据结果
                try {
                    word = response.body().sentences.get(0).getTrans();
                    System.out.println(word);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            //请求失败时回调
            @Override
            public void onFailure(Call<baseBean> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
        return word;
    }

}


