package com.example.translate.star;

import android.content.Context;

import com.example.translate.Base.BasePresenter;
import com.example.translate.Base.BaseView;
import com.example.translate.room.wordBean;

import java.util.List;

public interface starContract {


    interface View extends BaseView<starContract.Presenter> {
        void showRefresh(List<wordBean> list);
    }

    interface Presenter extends BasePresenter {

    }

}
