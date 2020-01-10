package com.example.meetup.Dao;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.meetup.Model.Event;

@Database(entities = Event.class,version = 1)
public abstract class EventDB extends RoomDatabase {
    private static EventDB eventDB;
    public static EventDB getInstance(Context context, String databasename)
    {
        if(eventDB==null)
        {
            eventDB= Room.databaseBuilder(context,EventDB.class,databasename)
                    .allowMainThreadQueries()
                    .build();
        }
        return eventDB;
    }
    public abstract EventDao getEventDao();
}
