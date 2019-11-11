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

    @Query("SELECT * FROM starWord WHERE src = :src AND dst = :dst")
    result_bean loadStar(String src,String dst);

    @Insert
    void insertWord(result_bean bean);

    @Delete
    void deleteWord(result_bean bean);


}
