package com.example.translate.star;

import com.example.translate.Base.BasePresenter;
import com.example.translate.Base.BaseView;

public interface starContract {


    interface View extends BaseView<starContract.Presenter> {
    }

    interface Presenter extends BasePresenter {
    }

}
