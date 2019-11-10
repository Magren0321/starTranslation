package com.example.translate.homepage;

import com.example.translate.Base.baseBean;


import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;



public interface geturl_interface {

    @GET("translate?")
    Call<baseBean> getCall(@Query("q")String q,
                           @Query("from")String from,
                           @Query("to")String to,
                           @Query("appid")String appid,
                           @Query("salt")String salt,
                           @Query("sign")String sign);

}
