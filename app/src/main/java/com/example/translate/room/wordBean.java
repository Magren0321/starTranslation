package com.example.translate.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Starword")
public class wordBean {
    @PrimaryKey(autoGenerate = true)
    int i;
    @ColumnInfo(name = "query")
    String query;
    @ColumnInfo(name = "to")
    String to;
    @ColumnInfo(name = "translation")
    String translation;
    @ColumnInfo(name = "webExplain")
    String webExplain;
    @ColumnInfo(name = "usPhonetic")
    String usPhonetic;
    @ColumnInfo(name = "ukPhonetic")
    String ukPhonetic;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getUkPhonetic() {
        return ukPhonetic;
    }

    public String getUsPhonetic() {
        return usPhonetic;
    }

    public void setUkPhonetic(String ukPhonetic) {
        this.ukPhonetic = ukPhonetic;
    }

    public void setUsPhonetic(String usPhonetic) {
        this.usPhonetic = usPhonetic;
    }

    public void setWebExplain(String webExplain) {
        this.webExplain = webExplain;
    }

    public String getWebExplain() {
        return webExplain;
    }
}
