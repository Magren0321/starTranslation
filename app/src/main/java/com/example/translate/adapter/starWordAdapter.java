package com.example.translate.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.translate.R;
import com.example.translate.Utils.ToastUtils;

import com.example.translate.homepage.MainActivity;
import com.example.translate.room.wordBean;
import com.example.translate.room.wordDatabase;

import java.util.List;


public class starWordAdapter extends BaseAdapter {



    private List<wordBean>list;
    private Context context;
    private LayoutInflater layoutInflater;

    String q;
    String to;


    public starWordAdapter(Context context, List<wordBean> list){
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
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
            view = layoutInflater.inflate(R.layout.itemstar,null);
            viewHolder.starbt = view.findViewById(R.id.starbt);
            viewHolder.starQuery = view.findViewById(R.id.starQuery);
            viewHolder.starTranslation = view.findViewById(R.id.starTranslation);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        wordBean wordBean  = list.get(i);
        q = wordBean.getQuery();
        to = wordBean.getTo();

        viewHolder.starQuery.setText(wordBean.getQuery());
        viewHolder.starTranslation.setText(wordBean.getTranslation());

        viewHolder.starbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordDatabase.getDefault(context).getWordDao().loadWordByQuery(wordBean.getQuery(),wordBean.getTranslation()).size()==0){
                    wordDatabase.getDefault(context).getWordDao().insertWord(wordBean);
                    viewHolder.starbt.setBackgroundResource(R.drawable.star_click);
                    ToastUtils.shortToast(context,"收藏成功");
                }else {
                    wordDatabase.getDefault(context).getWordDao().delete(wordBean);
                    viewHolder.starbt.setBackgroundResource(R.drawable.star);
                    ToastUtils.shortToast(context,"取消收藏");
                }
            }
        });

        viewHolder.starQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(list.get(i));
            }
        });

        return view;
    }


    class ViewHolder{
        public TextView starQuery;
        public TextView starTranslation;
        public Button starbt;
    }

    public void showDialog(wordBean wordBean){
        View view = LayoutInflater.from(context).inflate(R.layout.item,null);
        final AlertDialog dialog = new AlertDialog.Builder(context).setView(view).create();
        TextView translation = view.findViewById(R.id.translation);
        TextView UkPhonetic =view.findViewById(R.id.UkPhonetic);
        TextView UsPhonetic =view.findViewById(R.id.UsPhonetic);
        TextView web1 = view.findViewById(R.id.web1);
        TextView web_information = view.findViewById(R.id.web_information);
        if(wordBean.getWebExplain()!=null){
            web_information.setVisibility(View.VISIBLE);
            web1.setText(wordBean.getWebExplain());
        }
        translation.setText(wordBean.getTranslation());
        UkPhonetic.setText(wordBean.getUkPhonetic());
        UsPhonetic.setText(wordBean.getUsPhonetic());
        dialog.show();

    }



}

