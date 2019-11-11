package com.example.translate.homepage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.translate.Base.AppdataBase;
import com.example.translate.Base.result_bean;
import com.example.translate.R;

import java.util.List;

public class adapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    List<String> list;
    String word;
    Context context;

    public adapter(Context context,List<String>list,String word){
        this.context = context;
        layoutInflater  = LayoutInflater.from(context);
        this.list = list;
        this.word = word;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.item,null);
        TextView tv = view.findViewById(R.id.tv);
        Button bt = view.findViewById(R.id.star_lv);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result_bean bean = new result_bean();
                bean.setSrc(word);
                bean.setDst(list.get(0));
                AppdataBase.getDefault(context).wordDao().insertWord(bean);
                Toast.makeText(context,"收藏成功",Toast.LENGTH_SHORT).show();
            }
        });
        String data = list.get(position);
        tv.setText(data);
        return view;
    }
}
