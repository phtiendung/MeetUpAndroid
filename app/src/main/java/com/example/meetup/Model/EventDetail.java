package com.example.meetup.Model;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventDetail {
    @PrimaryKey
    @ColumnInfo(name="id")
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("status")
    @Expose
    @ColumnInfo(name="status")
    private Integer status;
    @SerializedName("link")
    @Expose
    @ColumnInfo(name="link")
    private String link;
    @SerializedName("photo")
    @Expose
    @ColumnInfo(name="photo")
    private String photo;
    @SerializedName("name")
    @Expose
    @ColumnInfo(name="name")
    private String name;
    @SerializedName("description_raw")
    @Expose
    @ColumnInfo(name="description_raw")
    private String descriptionRaw;
    @SerializedName("description_html")
    @Expose
    @ColumnInfo(name="description_html")
    private String descriptionHtml;
    @SerializedName("artist")
    @Expose
    @ColumnInfo(name="artist")
    private String artist;
    @SerializedName("schedule_permanent")
    @Expose
    @ColumnInfo(name="schedule_permanent")
    private String schedulePermanent;
    @SerializedName("schedule_date_warning")
    @Expose
    @ColumnInfo(name="schedule_date_warning")
    private String scheduleDateWarning;
    @SerializedName("schedule_time_alert")
    @Expose
    @ColumnInfo(name="schedule_time_alert")
    private String scheduleTimeAlert;
    @SerializedName("schedule_start_date")
    @Expose
    @ColumnInfo(name="schedule_start_date")
    private String scheduleStartDate;
    @SerializedName("schedule_start_time")
    @Expose
    @ColumnInfo(name="schedule_end_date")
    private String scheduleStartTime;
    @SerializedName("schedule_end_date")
    @Expose
    @ColumnInfo(name="schedule_end_date")
    private String scheduleEndDate;
    @SerializedName("schedule_end_time")
    @Expose
    @ColumnInfo(name="schedule_end_time")
    private String scheduleEndTime;
    @SerializedName("schedule_one_day_event")
    @Expose
    @ColumnInfo(name="schedule_one_day_event")
    private String scheduleOneDayEvent;
    @SerializedName("schedule_extra")
    @Expose
    @ColumnInfo(name="schedule_extra")
    private String scheduleExtra;
    @SerializedName("going_count")
    @Expose
    @ColumnInfo(name="going_count")
    private Integer goingCount;
    @SerializedName("went_count")
    @Expose
    @ColumnInfo(name="went_count")
    private Integer wentCount;
    @SerializedName("venue")
    @Expose
    @ColumnInfo(name="venue")
    private Venue venue;
    @SerializedName("category")
    @Expose
    @ColumnInfo(name="category")
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSchedulePermanent() {
        return schedulePermanent;
    }

    public void setSchedulePermanent(String schedulePermanent) {
        this.schedulePermanent = schedulePermanent;
    }

    public String getScheduleDateWarning() {
        return scheduleDateWarning;
    }

    public void setScheduleDateWarning(String scheduleDateWarning) {
        this.scheduleDateWarning = scheduleDateWarning;
    }

    public String getScheduleTimeAlert() {
        return scheduleTimeAlert;
    }

    public void setScheduleTimeAlert(String scheduleTimeAlert) {
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

    public String getScheduleOneDayEvent() {
        return scheduleOneDayEvent;
    }

    public void setScheduleOneDayEvent(String scheduleOneDayEvent) {
        this.scheduleOneDayEvent = scheduleOneDayEvent;
    }

    public String getScheduleExtra() {
        return scheduleExtra;
    }

    public void setScheduleExtra(String scheduleExtra) {
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

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }}
