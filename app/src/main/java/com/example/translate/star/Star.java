package com.example.translate.star;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import com.example.translate.Base.AppdataBase;
import com.example.translate.R;
import com.example.translate.Base.result_bean;
import com.example.translate.homepage.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Star extends AppCompatActivity {

    @BindView(R.id.SR)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.lv_star)
    ListView lv;

    List<result_bean>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star);

        ButterKnife.bind(Star.this);

        getData();
        initSR();

        starAdapter adapter = new starAdapter(list,getApplicationContext());
        lv.setAdapter(adapter);

    }

    public void getData(){
        list = AppdataBase.getDefault(getApplicationContext()).wordDao().getALL();
    }
    public void initSR(){
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                        starAdapter adapter = new starAdapter(list,getApplicationContext());
                        lv.setAdapter(adapter);
                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1200);

            }
        });
    }
}
