package com.example.meetup.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventDetail {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description_raw")
    @Expose
    private String descriptionRaw;
    @SerializedName("description_html")
    @Expose
    private String descriptionHtml;
    @SerializedName("artist")
    @Expose
    private Object artist;
    @SerializedName("schedule_permanent")
    @Expose
    private Object schedulePermanent;
    @SerializedName("schedule_date_warning")
    @Expose
    private Object scheduleDateWarning;
    @SerializedName("schedule_time_alert")
    @Expose
    private Object scheduleTimeAlert;
    @SerializedName("schedule_start_date")
    @Expose
    private String scheduleStartDate;
    @SerializedName("schedule_start_time")
    @Expose
    private String scheduleStartTime;
    @SerializedName("schedule_end_date")
    @Expose
    private String scheduleEndDate;
    @SerializedName("schedule_end_time")
    @Expose
    private String scheduleEndTime;
    @SerializedName("schedule_one_day_event")
    @Expose
    private Object scheduleOneDayEvent;
    @SerializedName("schedule_extra")
    @Expose
    private Object scheduleExtra;
    @SerializedName("going_count")
    @Expose
    private Integer goingCount;
    @SerializedName("went_count")
    @Expose
    private Integer wentCount;
    @SerializedName("venue")
    @Expose
    private VenuePopular venue;
    @SerializedName("category")
    @Expose
    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionRaw() {
        return descriptionRaw;
    }

    public void setDescriptionRaw(String descriptionRaw) {
        this.descriptionRaw = descriptionRaw;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    public Object getArtist() {
        return artist;
    }

    public void setArtist(Object artist) {
        this.artist = artist;
    }

    public Object getSchedulePermanent() {
        return schedulePermanent;
    }

    public void setSchedulePermanent(Object schedulePermanent) {
        this.schedulePermanent = schedulePermanent;
    }

    public Object getScheduleDateWarning() {
        return scheduleDateWarning;
    }

    public void setScheduleDateWarning(Object scheduleDateWarning) {
        this.scheduleDateWarning = scheduleDateWarning;
    }

    public Object getScheduleTimeAlert() {
        return scheduleTimeAlert;
    }

    public void setScheduleTimeAlert(Object scheduleTimeAlert) {
        this.scheduleTimeAlert = scheduleTimeAlert;
    }

    public String getScheduleStartDate() {
        return scheduleStartDate;
    }

    public void setScheduleStartDate(String scheduleStartDate) {
        this.scheduleStartDate = scheduleStartDate;
    }

    public String getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(String scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public String getScheduleEndDate() {
        return scheduleEndDate;
    }

    public void setScheduleEndDate(String scheduleEndDate) {
        this.scheduleEndDate = scheduleEndDate;
    }

    public String getScheduleEndTime() {
        return scheduleEndTime;
    }

    public void setScheduleEndTime(String scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }

    public Object getScheduleOneDayEvent() {
        return scheduleOneDayEvent;
    }

    public void setScheduleOneDayEvent(Object scheduleOneDayEvent) {
        this.scheduleOneDayEvent = scheduleOneDayEvent;
    }

    public Object getScheduleExtra() {
        return scheduleExtra;
    }

    public void setScheduleExtra(Object scheduleExtra) {
        this.scheduleExtra = scheduleExtra;
    }

    public Integer getGoingCount() {
        return goingCount;
    }

    public void setGoingCount(Integer goingCount) {
        this.goingCount = goingCount;
    }

    public Integer getWentCount() {
        return wentCount;
    }

    public void setWentCount(Integer wentCount) {
        this.wentCount = wentCount;
    }

    public VenuePopular getVenue() {
        return venue;
    }

    public void setVenue(VenuePopular venue) {
        this.venue = venue;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }}
