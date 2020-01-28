package com.example.translate.homepage;


import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.translate.Base.BaseActivity;

import com.example.translate.R;
import com.example.translate.Translate.translateFragment;

import com.example.translate.adapter.F_Adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<hompage_contract.Presenter> implements hompage_contract.View {

    @BindView(R.id.vp)
    ViewPager mViewPager;

    F_Adapter madapter;



    @Override
    protected hompage_contract.Presenter createPresenter() {
        return new presenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.viewpager_layout;
    }

    @Override
    protected void prepareData() {

    }

    @Override
    protected void initView() {

        List<Fragment> list = new ArrayList<>();
        list.add(new translateFragment());
        madapter = new F_Adapter(getSupportFragmentManager(),list);
        mViewPager.setAdapter(madapter);
        mViewPager.setCurrentItem(0);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {



    }

    @Override
    protected void initEvent() {

    }


}
