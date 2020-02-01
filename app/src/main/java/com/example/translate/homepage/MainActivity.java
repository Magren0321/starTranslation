package com.example.translate.homepage;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.translate.Base.BaseActivity;

import com.example.translate.R;
import com.example.translate.Translate.translateFragment;

import com.example.translate.adapter.F_Adapter;
import com.example.translate.room.wordDatabase;
import com.example.translate.star.starFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<hompage_contract.Presenter> implements hompage_contract.View {

    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.ll_main_tab_1)
    LinearLayout tab_1;
    @BindView(R.id.ll_main_tab_2)
    LinearLayout tab_2;
    @BindView(R.id.img_main_tab_1)
    ImageView img_1;
    @BindView(R.id.img_main_tab_2)
    ImageView img_2;
    @BindView(R.id.tv_main_tab_1)
    TextView tv_1;
    @BindView(R.id.tv_main_tab_2)
    TextView tv_2;

    F_Adapter madapter;

    List<Fragment> list = new ArrayList<>();

    translateFragment translateFragment = new translateFragment();
    starFragment starFragment = new starFragment();

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

        list.add(translateFragment);
        list.add( starFragment);
        madapter = new F_Adapter(getSupportFragmentManager(),list);
        mViewPager.setAdapter(madapter);
        setToolbarTitle("翻译");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {



    }

    @Override
    protected void initEvent() {
       mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @SuppressLint("ResourceAsColor")
           @Override
           public void onPageSelected(int position) {
               switch (position){
                   case 0:
                       img_1.setBackgroundResource(R.drawable.home_click);
                       tv_1.setTextColor(R.color.colorClick);
                       img_2.setBackgroundResource(R.drawable.star);
                       tv_2.setTextColor(R.color.black_semi_transparent);
                       mtoolbar.setTitle("翻译");
                       break;
                   case 1:
                       img_2.setBackgroundResource(R.drawable.star_click);
                       tv_2.setTextColor(R.color.colorClick);
                       img_1.setBackgroundResource(R.drawable.home);
                       tv_1.setTextColor(R.color.black_semi_transparent);
                       mtoolbar.setTitle("收藏");
                       starFragment.showRefresh(wordDatabase.getDefault(getApplicationContext()).getWordDao().getAll());
                       break;
               }
           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });
    }

    @SuppressLint("ResourceAsColor")
    @OnClick({R.id.ll_main_tab_1,R.id.ll_main_tab_2})
    public void onViewClicked(View view){
        switch(view.getId()){
            case R.id.ll_main_tab_1:
                mViewPager.setCurrentItem(0);
                img_1.setBackgroundResource(R.drawable.home_click);
                tv_1.setTextColor(R.color.colorClick);
                img_2.setBackgroundResource(R.drawable.star);
                tv_2.setTextColor(R.color.black_semi_transparent);
                mtoolbar.setTitle("翻译");
                break;
            case R.id.ll_main_tab_2:
                mViewPager.setCurrentItem(1);
                img_2.setBackgroundResource(R.drawable.star_click);
                tv_2.setTextColor(R.color.colorClick);
                img_1.setBackgroundResource(R.drawable.home);
                tv_1.setTextColor(R.color.black_semi_transparent);
                mtoolbar.setTitle("收藏");
                starFragment.showRefresh(wordDatabase.getDefault(getApplicationContext()).getWordDao().getAll());
                break;
        }
    }


}
