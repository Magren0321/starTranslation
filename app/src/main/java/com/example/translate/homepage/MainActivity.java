package com.example.translate.homepage;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Entity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.translate.Base.TranslateData;
import com.example.translate.R;
import com.example.translate.star.Star;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.youdao.sdk.app.YouDaoApplication;
import com.youdao.sdk.ydonlinetranslate.Translator;
import com.youdao.sdk.ydtranslate.Translate;
import com.youdao.sdk.ydtranslate.TranslateErrorCode;
import com.youdao.sdk.ydtranslate.TranslateListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements viewinterface{

    //显示翻译结果
    @BindView(R.id.lv)
    ListView lv;
    //搜索框
    @BindView(R.id.et)
    EditText et;
    //收藏界面
    @BindView(R.id.star)
    Button star;
    @BindView(R.id.spinner_left)
    Spinner spinner_left;
    @BindView(R.id.spinner_right)
    Spinner spinner_right;

    presenter presenter;

    String English = "英文";
    String Japanese = "日文";
    String Chinese = "中文";
    String to  = Chinese;
    String q;
    String from = Chinese;


    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        YouDaoApplication.init(this,"33c36c0f6d8b3fe7");

        ButterKnife.bind(MainActivity.this);

        presenter = new presenter(this);

        Select();

        et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
                    //处理事件

                    q = getSearch();
                    presenter.checkParameter(getApplicationContext());
                    getData(q,from,to);
                    et.setText("");
                    return true;
                }
                return false;
            }
        });



    }

    public void Select(){
        spinner_left.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                switch (pos){
                    case 0:
                        from = Chinese;
                        break;
                    case 1:
                        from = English;
                        break;
                    case 2:
                        from = Japanese;
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        spinner_right.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                switch (pos){
                    case 0:
                        to = Chinese;
                        break;
                    case 1:
                        to = English;
                        break;
                    case 2:
                        to = Japanese;
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    public void getData(String word,String f_word,String t_word){
        Translator translator = presenter.getdata(word,f_word,t_word);
        List<TranslateData>list = new ArrayList<>();
        translator.lookup(q, "requestId", new TranslateListener() {
            @Override
            public void onError(TranslateErrorCode translateErrorCode, String s) {

            }
            @Override
            public void onResult(final Translate result, String input, String requestId) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        TranslateData td = new TranslateData(
                                System.currentTimeMillis(), result);

                        list.add(td);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println(td.getTranslate().getLe());
                                adapter adapter = new adapter(getApplicationContext(),list,q);
                                lv.setAdapter(adapter);
                            }
                        });

                    }
                });
            }
            @Override
            public void onResult(List<Translate> list, List<String> list1, List<TranslateErrorCode> list2, String s) {

            }
        });
    }

    @OnClick({R.id.star})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.star:
                Intent i = new Intent(getApplicationContext(), Star.class);
                startActivity(i);
        }
    }




    @Override
    public String getSearch() {
        return et.getText().toString();
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(getApplicationContext(),"网络异常",Toast.LENGTH_LONG).show();
    }



}
