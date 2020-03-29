package com.example.translate.Base;


import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenterImpl implements BasePresenter {
    protected CompositeDisposable mSubscriptions;

    @Override
    public void onStart() {
        if (mSubscriptions == null) {
            mSubscriptions = new CompositeDisposable();
        }
    }

    @Override
    public void onDestroy() {
        if (mSubscriptions != null) {
            mSubscriptions.dispose();
            mSubscriptions.clear();
            mSubscriptions = null;
        }
    }
}