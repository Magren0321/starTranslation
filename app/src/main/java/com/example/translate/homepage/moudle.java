package com.example.translate.homepage;


import com.youdao.sdk.app.Language;
import com.youdao.sdk.app.LanguageUtils;
import com.youdao.sdk.ydonlinetranslate.Translator;

import com.youdao.sdk.ydtranslate.TranslateParameters;


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


