package com.example.translate.Translate;

import com.example.translate.Base.BasePresenterImpl;

public class translatePresenter extends BasePresenterImpl implements translateContract.Presenter {

    private final translateContract.View mView;
    public translatePresenter(translateContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }
}
