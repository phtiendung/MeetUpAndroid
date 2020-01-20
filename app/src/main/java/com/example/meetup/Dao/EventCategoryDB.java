package com.example.meetup.Dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.meetup.Model.EventInCateGory;

@Database(entities = EventInCateGory.class,version = 1)
public abstract class EventCategoryDB extends RoomDatabase {
    private static EventCategoryDB eventCategoryDB;
    public static EventCategoryDB getInstance(Context context, String databasename)
    {
        if(eventCategoryDB==null)
        {
            eventCategoryDB= Room.databaseBuilder(context,EventCategoryDB.class,databasename)
                    .allowMainThreadQueries()
                    .build();
        }
        return eventCategoryDB;
    }
    public abstract EventCategoryDao getEventCategoryDao();
}
