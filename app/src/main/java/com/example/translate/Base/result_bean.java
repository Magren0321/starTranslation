package com.example.translate.Base;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "starWord")
public class result_bean{

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "src")
    String src;
    @ColumnInfo(name = "dst")
    String dst;

    public String getDst(){
        return dst;
    }
    public String getSrc(){
        return src;
    }
    public void setSrc(String src){
        this.src = src;
    }
    public void setDst(String dst){
        this.dst = dst;
    }
}