package com.example.translate.Translate;

import android.annotation.SuppressLint;

import com.example.translate.Base.BasePresenterImpl;
import com.example.translate.Base.TranslationBean;
import com.example.translate.net.netWork;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.translate.Base.Constants.appID;
import static com.example.translate.Base.Constants.signType;

public class translatePresenter extends BasePresenterImpl implements translateContract.Presenter {

    private final translateContract.View mView;
    public translatePresenter(translateContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }

    @SuppressLint("CheckResult")
    public void netConnection(String q,String from,String to,String salt,String sign,String curtime){
        netWork.getInstance().getDataService()
                .translateYouDao(q,from,to,appID,salt,sign,signType,curtime)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TranslationBean>() {
                    @Override
                    public void accept(TranslationBean translationBean) throws Exception {
                        List<TranslationBean> list_word = new ArrayList<>();
                        list_word.add(translationBean);
                        mView.showResult(list_word);
                    }
                });
    }


}
