package com.example.translate.homepage;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.translate.Base.baseBean;
import com.example.translate.R;
import com.example.translate.star.Star;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

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
    String Japanese = "jp";
    String Chinese = "zh";
    String tl  = English;
    String q;
    String word;

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
                    presenter.checkParameter(getApplicationContext());
                    Call<baseBean>call = presenter.getdata(q,tl);
                    setData(call);
                    et.setText("");
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
                Toast.makeText(getApplicationContext(),"日——>中",Toast.LENGTH_SHORT).show();
                break;
            case R.id.CJ:
                tl = Japanese;
                Toast.makeText(getApplicationContext(),"中——>日",Toast.LENGTH_SHORT).show();
                break;
            case R.id.CE:
                tl = English;
                Toast.makeText(getApplicationContext(),"中——>英",Toast.LENGTH_SHORT).show();
                break;
            case  R.id.EC:
                tl = Chinese;
                Toast.makeText(getApplicationContext(),"英——>中",Toast.LENGTH_SHORT).show();
                break;
            case R.id.star:
                Intent i = new Intent(getApplicationContext(), Star.class);
                startActivity(i);
        }
    }

    public void setData(Call<baseBean>call){
        //发送网络请求(异步)
        call.enqueue(new Callback<baseBean>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<baseBean> call, Response<baseBean> response) {
                // 处理返回的数据
                word = response.body().trans_result.get(0).getDst();
                List<String>data =new ArrayList<>();
                data.add(word);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter adapter = new adapter(getApplicationContext(),data,q);
                        lv.setAdapter(adapter);
                    }
                });
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<baseBean> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });

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
