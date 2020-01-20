package com.example.meetup.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.meetup.Model.Event;
import com.example.meetup.Model.EventInCateGory;

import java.util.List;
@Dao
public interface EventCategoryDao {
    @Query("SELECT*FROM category_event")
    List<EventInCateGory> getEventsAll();
    @Insert
    void insertEvent(List<EventInCateGory> news);
    @Update
    void updateEvent(List<EventInCateGory> news);
    @Delete
    void deleteEvent(EventInCateGory event);
    @Query("DELETE FROM category_event")
    void deleteEventAll();

}
