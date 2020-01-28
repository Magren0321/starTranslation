package com.example.translate.Base;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//@Database(entities = {result_bean.class},version = 1,exportSchema = false)
//public abstract class AppdataBase extends RoomDatabase {
//
//    public static AppdataBase getDefault(Context context) {
//        return buildDatabase(context);
//    }
//
//    private static AppdataBase buildDatabase(Context context) {
//        return Room.databaseBuilder(context.getApplicationContext(), AppdataBase.class, "starWord.db")
//                .allowMainThreadQueries()
//                .build();
//    }
//
//    public abstract wordDao wordDao();
//
//
//}