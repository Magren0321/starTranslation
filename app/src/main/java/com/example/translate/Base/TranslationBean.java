package com.example.translate.Base;



import com.google.gson.annotations.SerializedName;

import java.util.List;


public class TranslationBean {

    private int errorCode; //错误返回码
    private String query;     //源语言
    private List<String> translation; //翻译结果
    private basicEntity basic;
    private List<WebEntity> web;
    private String tSpeakUrl;

    public class basicEntity {
        @SerializedName("us-phonetic")
        private String usPhonetic;  //英式音标
        @SerializedName("uk-speech")
        private String ukSpeech;    //美式发音
        @SerializedName("us-speech")
        private String usSpeech;  //英式发音
        private String phonetic;   //默认音标
        @SerializedName("uk-phonetic")
        private String ukPhonetic;   //美式英标

        private List<String> explains;  //基本释义


        public List<String> getExplains() {
            return explains;
        }

        public void setExplains(List<String> explains) {
            this.explains = explains;
        }

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }

        public String getUkPhonetic() {
            return ukPhonetic;
        }

        public void setUkPhonetic(String ukPhonetic) {
            this.ukPhonetic = ukPhonetic;
        }

        public String getUsPhonetic() {
            return usPhonetic;
        }

        public void setUsPhonetic(String usPhonetic) {
            this.usPhonetic = usPhonetic;
        }

        public String getUkSpeech() {
            return ukSpeech;
        }

        public void setUkSpeech(String ukSpeech) {
            this.ukSpeech = ukSpeech;
        }

        public String getUsSpeech() {
            return usSpeech;
        }

        public void setUsSpeech(String usSpeech) {
            this.usSpeech = usSpeech;
        }

    }

    public class WebEntity {
        private String key;
        private List<String> value;

        public void setKey(String key) {
            this.key = key;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public List<String> getValue() {
            return value;
        }
    }




    public void setQuery(String query) {
        this.query = query;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public void setWeb(List<WebEntity> web) {
        this.web = web;
    }

    public String getQuery() {
        return query;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public List<WebEntity> getWeb() {
        return web;
    }

    public basicEntity getBasic() {
        return basic;
    }

    public void setBasic(basicEntity basic) {
        this.basic = basic;
    }

    public void settSpeakUrl(String tSpeakUrl){ this.tSpeakUrl = tSpeakUrl;}

    public String gettSpeakUrl(){ return tSpeakUrl; }



}




