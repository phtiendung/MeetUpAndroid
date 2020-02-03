package com.example.meetup.NetWorking;

import com.example.meetup.Model.Event;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponeNearlyEvent {
    @SerializedName("events")
    private List<Event> event;

    public List<Event> getEvent() {
        return event;
    }
}
