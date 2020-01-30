package com.example.translate.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface wordDao {

    /**
     * 查询所有
     *
     * @return
     */
    @Query("SELECT * FROM Starword")
    List<wordBean> getAll();

    /**
     * 根据指定字段查询
     *
     * @return
     */
    @Query("SELECT * FROM Starword WHERE query = :query AND translation = :translation")
    List<wordBean> loadWordByQuery(String query,String translation);

    /**
     * 数据库添加数据
     *
     * @param wordBean
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWord(wordBean wordBean);

    /**
     * 删除数据
     *
     * @param wordBean
     */
    @Delete()
    void delete(wordBean wordBean);

}
