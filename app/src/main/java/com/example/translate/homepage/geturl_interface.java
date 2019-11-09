package com.example.translate.homepage;

import com.example.translate.Base.baseBean;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;


public interface geturl_interface {

    @GET("translate_a/single")
    Call<baseBean> getCall(@QueryMap  Map<String, String> params);

}
