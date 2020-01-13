package com.example.meetup.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.meetup.Model.Event;
import com.example.meetup.Model.News;

import java.util.List;

@Dao
public interface EventDao  {
    @Query("SELECT*FROM table_event ORDER BY event_going_count DESC")
    List<Event> getEventsAll();
    @Insert
    void insertEvent(List<Event> news);
    @Update
    void updateEvent(List<Event> news);
    @Delete
    void deleteEvent(Event event);
    @Query("DELETE FROM table_event")
    void deleteEventAll();
}
