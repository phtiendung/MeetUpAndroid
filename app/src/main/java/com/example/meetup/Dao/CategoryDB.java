package com.example.meetup.Dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.meetup.Model.Category;

@Database(entities = Category.class,version = 1)
public abstract class CategoryDB extends RoomDatabase {
    private static CategoryDB categoryDB;
    public static CategoryDB getInstance(Context context, String databasename)
    {
        if(categoryDB==null)
        {
            categoryDB= Room.databaseBuilder(context,CategoryDB.class,databasename)
                    .allowMainThreadQueries()
                    .build();
        }
        return categoryDB;
    }
    public abstract CategoryDao getCategoryDao();
}
