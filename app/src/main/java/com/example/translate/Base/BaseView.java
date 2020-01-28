package com.example.translate.Base;

public interface BaseView<P> {

    void setPresenter(P presenter);

    void showToast(CharSequence msg);

    void showToast(int msgId);

    void showLoadingDialog(CharSequence msg);

    void hideLoadingDialog();


}