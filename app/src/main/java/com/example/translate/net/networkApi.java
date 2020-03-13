package com.example.translate.net;

import com.example.translate.Base.TranslationBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface networkApi {
    @GET("api?")
    Observable<TranslationBean> translateYouDao(
            @Query("q") String q,
            @Query("from") String from,
            @Query("to") String to,
            @Query("appKey") String appKey, //应用ID
            @Query("salt") String salt,  //UUID
            @Query("sign") String sign,  //应用ID+input+salt+curtime+应用密钥 。 input= q前10个字符+q长度+q后10个字符（q的长度>=20） 或input = 字符串
            @Query("signType") String signType, //签名类型
            @Query("curtime") String curtime //时间戳
    );
}
