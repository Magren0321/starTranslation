package com.example.translate.star;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.translate.Base.BaseFragment;
import com.example.translate.R;
import com.example.translate.adapter.starWordAdapter;
import com.example.translate.room.wordBean;
import com.example.translate.room.wordDatabase;

import java.util.List;


import butterknife.BindView;

public class starFragment extends BaseFragment<starContract.Presenter> implements starContract.View{

    @BindView(R.id.lv_star)
    ListView lv_star;
    @BindView(R.id.SR)
    SwipeRefreshLayout sf;

    List<wordBean> list;
    starWordAdapter starWordAdapter;

    @Override
    protected starContract.Presenter createPresenter() {
        return new starPresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {
        list = wordDatabase.getDefault(getContext()).getWordDao().getAll();

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_star;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        starWordAdapter = new starWordAdapter(getContext(),list);
        lv_star.setAdapter(starWordAdapter);
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initEvent() {
        sf.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list = wordDatabase.getDefault(getContext()).getWordDao().getAll();
                        starWordAdapter = new starWordAdapter(getContext(),list);
                        lv_star.setAdapter(starWordAdapter);
                        sf.setRefreshing(false);
                    }
                }, 1200);

            }
        });



    }

    @Override
    public void showRefresh(List<wordBean>list) {
        starWordAdapter = new starWordAdapter(getContext(),list);
        lv_star.setAdapter(starWordAdapter);
    }
}
