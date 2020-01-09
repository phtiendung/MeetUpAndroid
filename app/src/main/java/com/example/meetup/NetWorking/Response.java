package com.example.meetup.NetWorking;

import com.example.meetup.Model.Category;
import com.example.meetup.Model.Event;
import com.example.meetup.Model.News;
import com.google.gson.annotations.SerializedName;


import java.util.List;

public class Response {
    @SerializedName("news")
    private List<News> news;

    @SerializedName("events")
    private List<Event> events;

    @SerializedName("token")
    private String token;

    @SerializedName("categories")
    private List<Category> categories;


    public List<News> getNews() {
        return news;
    }
//    public EventDetail getEventDetail() {
//        return eventDetail;
//    }

    public List<Event> getEvents() {
        return events;
    }

    public String getToken() {
        return token;
    }

    public List<Category> getCategories() {
        return categories;
    }

}
