package com.example.translate.net;

import com.example.translate.Base.Constants;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class netWork {
    private static networkApi sContactsApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

    private static class ApiClientHolder {
        public static final netWork INSTANCE = new netWork();
    }

    public static netWork getInstance() {
        return ApiClientHolder.INSTANCE;
    }

    public networkApi getDataService() {
        if (sContactsApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            sContactsApi = retrofit.create(networkApi.class);
        }
        return sContactsApi;
    }

}
