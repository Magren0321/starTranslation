package com.example.translate.Base;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface wordDao {
    @Query("SELECT * FROM starWord")
    List<result_bean> getALL();

    @Insert
    void insertWord(result_bean bean);

    @Delete
    void deleteWOrd(result_bean bean);

}
