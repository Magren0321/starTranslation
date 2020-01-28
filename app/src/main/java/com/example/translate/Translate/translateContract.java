package com.example.translate.Translate;

import com.example.translate.Base.BasePresenter;
import com.example.translate.Base.BaseView;

public interface translateContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}
