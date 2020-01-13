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
    private String scheduleOpeninghour;
    @SerializedName("schedule_closinghour")
    @Expose
    private String scheduleClosinghour;
    @SerializedName("schedule_closed")
    @Expose
    private String scheduleClosed;

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

    public String getScheduleOpeninghour() {
        return scheduleOpeninghour;
    }

    public void setScheduleOpeninghour(String scheduleOpeninghour) {
        this.scheduleOpeninghour = scheduleOpeninghour;
    }

    public String getScheduleClosinghour() {
        return scheduleClosinghour;
    }

    public void setScheduleClosinghour(String scheduleClosinghour) {
        this.scheduleClosinghour = scheduleClosinghour;
    }

    public String getScheduleClosed() {
        return scheduleClosed;
    }

    public void setScheduleClosed(String scheduleClosed) {
        this.scheduleClosed = scheduleClosed;
    }
}
