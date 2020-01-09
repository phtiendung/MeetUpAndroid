package com.example.meetup.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenuePopular {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("schedule_openinghour")
    @Expose
    private Object scheduleOpeninghour;
    @SerializedName("schedule_closinghour")
    @Expose
    private Object scheduleClosinghour;
    @SerializedName("schedule_closed")
    @Expose
    private Object scheduleClosed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getScheduleOpeninghour() {
        return scheduleOpeninghour;
    }

    public void setScheduleOpeninghour(Object scheduleOpeninghour) {
        this.scheduleOpeninghour = scheduleOpeninghour;
    }

    public Object getScheduleClosinghour() {
        return scheduleClosinghour;
    }

    public void setScheduleClosinghour(Object scheduleClosinghour) {
        this.scheduleClosinghour = scheduleClosinghour;
    }

    public Object getScheduleClosed() {
        return scheduleClosed;
    }

    public void setScheduleClosed(Object scheduleClosed) {
        this.scheduleClosed = scheduleClosed;
    }
}
