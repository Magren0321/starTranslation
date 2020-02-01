package com.example.translate.star;



import com.example.translate.Base.BasePresenterImpl;



public class starPresenter extends BasePresenterImpl implements starContract.Presenter {

    private final starContract.View mView;
    public starPresenter(starContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }


}
