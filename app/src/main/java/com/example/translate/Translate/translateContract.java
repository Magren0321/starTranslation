package com.example.translate.Translate;

import com.example.translate.Base.BasePresenter;
import com.example.translate.Base.BaseView;
import com.example.translate.Base.TranslationBean;

import java.util.List;

public interface translateContract {

    interface View extends BaseView<Presenter> {
        void showResult(List<TranslationBean> list);
        void showConnection();
    }

    interface Presenter extends BasePresenter {
        void netConnection(String q,String from,String to,String salt,String sign,String curtime);
    }
}
