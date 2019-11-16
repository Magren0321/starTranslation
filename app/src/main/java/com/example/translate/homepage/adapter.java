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
import com.example.translate.Base.TranslateData;
import com.example.translate.Base.result_bean;
import com.example.translate.R;

import java.util.List;

public class adapter extends BaseAdapter {


    LayoutInflater layoutInflater;
    List<TranslateData> list;
    String word;
    Context context;

    public adapter(Context context,List<TranslateData>list,String word){
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
        TextView tv1 =view.findViewById(R.id.explain);
        TextView tv2 = view.findViewById(R.id.DeepLink);
        TextView tv3 = view.findViewById(R.id.DictDeepLink);
        TextView tv4 = view.findViewById(R.id.UsPhonetic);
        TextView tv5 = view.findViewById(R.id.UKPhonetic);

       tv2.setText(list.get(position).getTranslate().getDeeplink());
       tv1.setText(list.get(position).getTranslate().getExplains().get(0));
       tv3.setText(list.get(position).getTranslate().getDictDeeplink());
       tv4.setText(list.get(position).getTranslate().getUsPhonetic());
       tv5.setText(list.get(position).getTranslate().getUkPhonetic());

        return view;
    }
}
