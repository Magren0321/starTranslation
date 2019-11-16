package com.example.translate.homepage;

import android.os.Handler;
import android.util.Log;

import com.example.translate.Base.MD5Utils;
import com.example.translate.Base.TranslateData;
import com.example.translate.Base.baseBean;
import com.youdao.sdk.app.Language;
import com.youdao.sdk.app.LanguageUtils;
import com.youdao.sdk.ydonlinetranslate.Translator;
import com.youdao.sdk.ydtranslate.Translate;
import com.youdao.sdk.ydtranslate.TranslateErrorCode;
import com.youdao.sdk.ydtranslate.TranslateListener;
import com.youdao.sdk.ydtranslate.TranslateParameters;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class moudle {

    Translator translator;


    public Translator getdata(String q, String from, String to) {


        Language langFrom = LanguageUtils.getLangByName(from);
        Language langTo = LanguageUtils.getLangByName(to);

        TranslateParameters tps = new TranslateParameters.Builder()
                .source(q).from(langFrom).to(langTo).build();

        translator = Translator.getInstance(tps);


        return  translator;
    }

}


