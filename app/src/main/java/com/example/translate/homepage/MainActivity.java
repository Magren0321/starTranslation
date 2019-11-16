package com.example.translate.homepage;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.translate.Base.TranslateData;
import com.example.translate.Base.baseBean;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    //日中
    @BindView(R.id.JC)
    FloatingActionButton jc;
    //中日
    @BindView(R.id.CJ)
    FloatingActionButton cj;
    //中英
    @BindView(R.id.CE)
    FloatingActionButton ce;
    //英中
    @BindView(R.id.EC)
    FloatingActionButton ec;
    @BindView(R.id.switch_l)
    FloatingActionsMenu menu;

    presenter presenter;

    String English = "en";
    String Japanese = "ja";
    String Chinese = "zh-CHS";
    String to  = English;
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


        et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
                    //处理事件

                    q = getSearch();
                    presenter.checkParameter(getApplicationContext());
                    getData();

                    et.setText("");
                    return true;
                }
                return false;
            }
        });
    }

    public void getData(){
        Translator translator = presenter.getdata(q,from,to);
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

    @OnClick({R.id.star, R.id.JC, R.id.CJ,R.id.CE,R.id.EC})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.JC:
                to = Chinese;
                from = Japanese;
                Toast.makeText(getApplicationContext(),"日——>中",Toast.LENGTH_SHORT).show();
                break;
            case R.id.CJ:
                to = Japanese;
                from = Chinese;
                Toast.makeText(getApplicationContext(),"中——>日",Toast.LENGTH_SHORT).show();
                break;
            case R.id.CE:
                from = Chinese;
                to = English;
                Toast.makeText(getApplicationContext(),"中——>英",Toast.LENGTH_SHORT).show();
                break;
            case  R.id.EC:
                to = Chinese;
                from = English;
                Toast.makeText(getApplicationContext(),"英——>中",Toast.LENGTH_SHORT).show();
                break;
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
