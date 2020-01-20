package com.example.meetup.NetWorking;

import com.example.meetup.Model.EventInCateGory;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResponeIncategory {
    @SerializedName("events")
    private List<EventInCateGory> events;

    public List<EventInCateGory> getEvents() {
        return events;
    }
}
