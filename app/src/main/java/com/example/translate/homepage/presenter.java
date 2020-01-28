package com.example.translate.homepage;



import com.example.translate.Base.BasePresenterImpl;


public class presenter extends BasePresenterImpl implements hompage_contract.Presenter{


    private final hompage_contract.View mView;
    public presenter(hompage_contract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }



}
