package com.example.translate.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.translate.Base.TranslationBean;
import com.example.translate.R;
import com.example.translate.Utils.ToastUtils;
import com.example.translate.room.wordBean;
import com.example.translate.room.wordDatabase;


import java.util.List;

public class wordAdapter extends BaseAdapter {

    private List<TranslationBean> mList;
    private Context context;
    private LayoutInflater layoutInflater;

    private String to;


    public wordAdapter(List<TranslationBean> mList, Context context,String to){
        this.mList = mList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.to = to;
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
            viewHolder.translation = view.findViewById(R.id.translation);
            viewHolder.UkPhonetic =view.findViewById(R.id.UkPhonetic);
            viewHolder.UsPhonetic =view.findViewById(R.id.UsPhonetic);
            viewHolder.web1 = view.findViewById(R.id.web1);
            viewHolder.web_information = view.findViewById(R.id.web_information);
            viewHolder.star_lv = view.findViewById(R.id.star_lv);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        TranslationBean bean = mList.get(i);

        if(bean.getTranslation()!=null){
            viewHolder.translation.setText(bean.getTranslation().get(0));
        }
        if(bean.getBasic()!=null){
            viewHolder.web_information.setVisibility(View.VISIBLE);
            int n = bean.getBasic().getExplains().size();
            StringBuilder stringBuilder = new StringBuilder();
            for(int l = 0 ; l< n;l++){
                stringBuilder.append(bean.getBasic().getExplains().get(l)+" ;\n");
            }
            viewHolder.web1.setText(stringBuilder);
            viewHolder.UkPhonetic.setText(bean.getBasic().getUkPhonetic());
            viewHolder.UsPhonetic.setText(bean.getBasic().getUsPhonetic());
        }

        viewHolder.star_lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = bean.getQuery();
                String translation = bean.getTranslation().get(0);
                if(wordDatabase.getDefault(context).getWordDao().loadWordByQuery(query,translation).size()!=0){
                    ToastUtils.shortToast(context,"已存在收藏列表");
                }else {
                    wordBean wordBean = new wordBean();
                    wordBean.setQuery(query);
                    wordBean.setTo(to);
                    wordBean.setTranslation(translation);
                    wordDatabase.getDefault(context).getWordDao().insertWord(wordBean);
                    ToastUtils.shortToast(context,"收藏成功");
                }
            }
        });



        return view;
    }
}

class ViewHolder{
    public TextView translation;
    public TextView UsPhonetic;
    public TextView UkPhonetic;
    public TextView web1;
    public TextView web_information;
    public Button star_lv;

}