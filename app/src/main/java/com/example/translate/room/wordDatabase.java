package com.example.translate.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(entities = {wordBean.class}, version = 1, exportSchema = false)

public abstract class wordDatabase extends RoomDatabase {

    public static wordDatabase getDefault(Context context) {
        return buildDatabase(context);
    }

    private static wordDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), wordDatabase.class, "StarWord.db")
                .allowMainThreadQueries()
                .build();
    }

    public abstract wordDao getWordDao();
}
