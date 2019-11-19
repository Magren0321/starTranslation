package com.example.translate.homepage;

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

public class adapter extends BaseAdapter {


    boolean exist;
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
        TextView tv2 =view.findViewById(R.id.translation);
        TextView tv3 =view.findViewById(R.id.UsPhonetic);
        TextView tv4 =view.findViewById(R.id.UkPhonetic);
        TextView tv5 =view.findViewById(R.id.web1);

        TextView tv6 =view.findViewById(R.id.web_information);

        Button button = view.findViewById(R.id.star_lv);

        //Phonetic 发音
        //Translation 翻译
        //Explain 解释
        //UsPhonetic 英式发音
        //UkPhonetic 美式发音

        StringBuffer webExplains = new StringBuffer();

        if(list.get(position).getTranslate().getExplains()!=null){
            tv1.setText(list.get(position).getTranslate().getExplains().get(0)+"     " + list.get(position).getTranslate().getPhonetic());
        }
        tv2.setText(list.get(position).getTranslate().getTranslations().get(0));
        if (list.get(position).getTranslate().getUkPhonetic()!=null&&list.get(position).getTranslate().getUkPhonetic()!="") {
            tv3.setText("英：/" + list.get(position).getTranslate().getUsPhonetic() + "/");
            tv4.setText("美：/" + list.get(position).getTranslate().getUkPhonetic() + "/");
        }
        if(list.get(position).getTranslate().getWebExplains()!=null){
            for(int i = 0;i<list.get(position).getTranslate().getWebExplains().get(0).getMeans().size();i++){
                webExplains.append(list.get(position).getTranslate().getWebExplains().get(0).getMeans().get(i)+"；");
            }
            tv5.setText(webExplains);
        }else {
            tv6.setVisibility(View.INVISIBLE);
        }

        if(list.get(position).getTranslate().getExplains()!=null){
            if(AppdataBase.getDefault(context).wordDao().loadStar(word,list.get(position).getTranslate().getExplains().get(0))!=null){
                button.setBackgroundResource(R.drawable.star_black);
                exist = true;
            }
        }

        if(list.get(position).getTranslate().getTranslations()!=null){
            if(AppdataBase.getDefault(context).wordDao().loadStar(word,list.get(position).getTranslate().getTranslations().get(0))!=null){
                button.setBackgroundResource(R.drawable.star_black);
                exist = true;
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (exist == true){
                    Toast.makeText(context,"已经存在收藏列表",Toast.LENGTH_SHORT).show();
                }else{
                    result_bean  bean = new result_bean();
                    bean.setSrc(word);
                    if(list.get(position).getTranslate().getExplains()!=null){
                        if(list.get(position).getTranslate().getExplains().get(0)!=null){
                            bean.setDst(list.get(position).getTranslate().getExplains().get(0));

                            AppdataBase.getDefault(context).wordDao().insertWord(bean);
                            Toast.makeText(context,"收藏成功",Toast.LENGTH_SHORT).show();
                            button.setBackgroundResource(R.drawable.star_black);
                            exist =true;

                            return;
                        }
                    }else if(list.get(position).getTranslate().getTranslations()!=null){
                        bean.setDst(list.get(position).getTranslate().getTranslations().get(0));

                        AppdataBase.getDefault(context).wordDao().insertWord(bean);
                        Toast.makeText(context,"收藏成功",Toast.LENGTH_SHORT).show();
                        button.setBackgroundResource(R.drawable.star_black);
                        exist =true;

                        return;
                    }

                }

            }
        });
        return view;
    }
}
