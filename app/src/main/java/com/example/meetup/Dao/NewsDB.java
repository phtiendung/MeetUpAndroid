package com.example.meetup.Dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.meetup.Model.News;

@Database(entities = News.class,version = 1)
public abstract class NewsDB extends RoomDatabase {
    private static NewsDB newsDB;
    public static NewsDB getInstance(Context context,String databasename)
    {
        if(newsDB==null)
        {
            newsDB= Room.databaseBuilder(context,NewsDB.class,databasename)
                    .allowMainThreadQueries()
                    .build();
        }
        return newsDB;
    }
    public abstract NewsDao getNewsDao();
}
