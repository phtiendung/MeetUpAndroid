package com.example.meetup.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.meetup.Model.News;

import java.util.List;

@Dao
public interface NewsDao {
    @Query("SELECT*FROM TABLE_NEWS")
    List<News> getNewsAll();
    @Insert
    void insertnews(List<News> news);
    @Update
    void updateNews(News news);
    @Delete
    void deleteNews(News news);
}
