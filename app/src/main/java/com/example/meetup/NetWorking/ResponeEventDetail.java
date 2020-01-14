package com.example.meetup.NetWorking;

import com.example.meetup.Model.EventDetail;
import com.google.gson.annotations.SerializedName;


public class ResponeEventDetail {
    @SerializedName("events")
    private EventDetail eventDetail;

    public EventDetail getEventDetail() {
        return eventDetail;
    }
}
