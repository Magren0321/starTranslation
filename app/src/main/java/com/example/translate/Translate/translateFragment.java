package com.example.translate.Translate;


import android.annotation.SuppressLint;
import android.os.Bundle;


import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.translate.Base.BaseFragment;

import com.example.translate.Base.TranslationBean;
import com.example.translate.R;
import com.example.translate.Utils.ToastUtils;
import com.example.translate.adapter.wordAdapter;
import com.example.translate.net.netWork;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.translate.Base.Constants.appID;
import static com.example.translate.Base.Constants.appKey;

import static com.example.translate.Utils.Digest.getDigest;
import static com.example.translate.Utils.Digest.truncate;

public class translateFragment extends BaseFragment<translateContract.Presenter> implements translateContract.View {

    @BindView(R.id.spinner_left)
    NiceSpinner spinner_left;
    @BindView(R.id.spinner_right)
    NiceSpinner spinner_right;
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.arrow)
    ImageView arrow;

    String Chinese = "zh-CHS";
    String English = "en";
    String Japanese = "ja";

    String q;
    String from = "auto";
    String to = Chinese;
    String salt;
    String curtime;
    String sign;


    String[] position_list = {Chinese,Japanese,English};
    int position_right = 0;
    int position_left = 0;

    List<String> list;


    wordAdapter wordAdapter;

    @Override
    protected translateContract.Presenter createPresenter() {
        return new translatePresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.translation;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {


    }

    @Override
    protected void initView(View rootView) {

        list = new LinkedList<>(Arrays.asList("中文", "日文", "英文"));
        spinner_left.attachDataSource(list);
        spinner_right.attachDataSource(list);

    }

    @Override
    protected void initEvent() {
        spinner_right.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        to = position_list[0];
                        position_right = 0;
                        break;
                    case 1:
                        to = position_list[1];
                        position_right = 1;
                        break;
                    case 2:
                        to = position_list[2];
                        position_right = 2;
                        break;
                }
            }
        });

        spinner_left.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        from = position_list[0];
                        position_left = 0;
                        break;
                    case 1:
                        from = position_list[1];
                        position_left = 1;
                        break;
                    case 2:
                        from = position_list[2];
                        position_left = 2;
                        break;
                }
            }
        });

    }



    @OnClick({R.id.bt,R.id.arrow})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.bt:
                q = et.getText().toString();
                if(q.length()!=0){
                    salt = String.valueOf(System.currentTimeMillis());
                    curtime = String.valueOf(System.currentTimeMillis() / 1000);
                    String signStr = appID+truncate(q)+salt+curtime+appKey;
                    sign = getDigest(signStr);
                    mPresenter.netConnection(q,from,to,salt,sign,curtime);
                }
                break;
            case R.id.arrow:
                int i;
                spinner_left.setSelectedIndex(position_right);
                from = position_list[position_right];
                spinner_right.setSelectedIndex(position_left);
                to = position_list[position_left];
                i = position_left;
                position_left = position_right;
                position_right = i;
                break;
        }
    }


    @Override
    public void showResult(List<TranslationBean> list) {
        wordAdapter = new wordAdapter(list,getContext(),to);
        lv.setAdapter(wordAdapter);
    }

    @Override
    public void showConnection() {
        ToastUtils.shortToast(getContext(),"网络错误");
    }
}
