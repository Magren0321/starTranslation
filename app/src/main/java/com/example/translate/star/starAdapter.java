package com.example.translate.star;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.translate.Base.result_bean;
import com.example.translate.Base.AppdataBase;
import com.example.translate.Base.TranslateData;
import com.example.translate.R;

import java.util.List;

public class starAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    List<result_bean> list;
    Context context;

    public starAdapter(List<result_bean>list,Context context){
        this.context = context;
        this.list =list;
        layoutInflater  = LayoutInflater.from(context);
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
        View view = layoutInflater.inflate(R.layout.itemstar,null);
        TextView src = view.findViewById(R.id.src);
        TextView dst = view.findViewById(R.id.dst);
        Button starbt = view.findViewById(R.id.starbt);
        src.setText(list.get(position).getSrc());
        dst.setText(list.get(position).getDst());
        starbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppdataBase.getDefault(context).wordDao().loadStar(list.get(position).getSrc(),list.get(position).getDst())!=null){
                    AppdataBase.getDefault(context).wordDao().deleteWord(list.get(position));
                    starbt.setBackgroundResource(R.drawable.star_white);
                    Toast.makeText(context,"取消收藏",Toast.LENGTH_SHORT).show();
                }else {
                    AppdataBase.getDefault(context).wordDao().insertWord(list.get(position));
                    starbt.setBackgroundResource(R.drawable.star_black);
                    Toast.makeText(context,"收藏成功",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}