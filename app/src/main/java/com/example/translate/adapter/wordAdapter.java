package com.example.translate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.translate.Base.TranslationBean;
import com.example.translate.R;

import java.util.List;

public class wordAdapter extends BaseAdapter {

    private List<TranslationBean> mList;
    private Context context;
    private LayoutInflater layoutInflater;

    public wordAdapter(List<TranslationBean> mList, Context context){
        this.mList = mList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.item,null);
            viewHolder.explain = view.findViewById(R.id.explain);
            viewHolder.translation = view.findViewById(R.id.translation);
            viewHolder.UkPhonetic =view.findViewById(R.id.UkPhonetic);
            viewHolder.UsPhonetic =view.findViewById(R.id.UsPhonetic);
            viewHolder.web1 = view.findViewById(R.id.web1);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        TranslationBean bean = mList.get(i);

        if(bean.getTranslation()!=null){
            viewHolder.translation.setText(bean.getTranslation().get(0));
        }
        if(bean.getBasic()!=null){
            viewHolder.web1.setText(bean.getBasic().getExplains().get(0));
            viewHolder.UkPhonetic.setText(bean.getBasic().getUkSpeech());
            viewHolder.UsPhonetic.setText(bean.getBasic().getUsSpeech());
        }



        return view;
    }
}

class ViewHolder{
    public TextView explain;
    public TextView translation;
    public TextView UsPhonetic;
    public TextView UkPhonetic;
    public TextView web1;
    public Button star_lv;
}