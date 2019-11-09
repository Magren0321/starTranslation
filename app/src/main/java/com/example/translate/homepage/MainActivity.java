package com.example.translate.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.translate.Base.adapter;
import com.example.translate.R;
import com.getbase.floatingactionbutton.FloatingActionButton;

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

    presenter presenter;

    String English = "en";
    String Japanese = "ja";
    String Chinese = "zh_CN";
    String tl  = English;
    String q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(MainActivity.this);

        presenter = new presenter(this);

        et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
                    //处理事件
                    q = getSearch();
                    presenter.checkNetwork(getApplicationContext());
                    String word = presenter.getdata(tl,q);
                    adapter adapter = new adapter(getApplicationContext(),word,q);
                    lv.setAdapter(adapter);
                    return true;
                }
                return false;
            }
        });
    }

    @OnClick({R.id.star, R.id.JC, R.id.CJ,R.id.CE,R.id.EC})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.JC:
                tl = Chinese;
                break;
            case R.id.CJ:
                tl = Japanese;
                break;
            case R.id.CE:
                tl = English;
                break;
            case  R.id.EC:
                tl = Chinese;
                break;
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
