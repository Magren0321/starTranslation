package com.example.translate.Base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.translate.R;

public class adapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    String data;
    String word;

    public adapter(Context context,String data,String word){
        layoutInflater  = LayoutInflater.from(context);
        this.data = data;
        this.word = word;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.item,null);
        TextView tv = view.findViewById(R.id.tv);
        Button bt = view.findViewById(R.id.star_lv);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tv.setText(data);
        return view;
    }
}
