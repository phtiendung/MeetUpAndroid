package com.example.meetup.Model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = "table_event")
public class Event {
    @PrimaryKey
    @ColumnInfo(name="event_id")
    @SerializedName("id")
    @Expose
    private Integer id;
    @ColumnInfo(name="event_status")
    @SerializedName("status")
    @Expose
    private Integer status;
    @ColumnInfo(name="event_photo")
    @SerializedName("photo")
    @Expose
    private String photo;
    @ColumnInfo(name="event_name")
    @SerializedName("name")
    @Expose
    private String name;
    @ColumnInfo(name="event_description_raw")
    @SerializedName("description_raw")
    @Expose
    private String descriptionRaw;
    @ColumnInfo(name="event_description_html")
    @SerializedName("description_html")
    @Expose
    private String descriptionHtml;
    @ColumnInfo(name="event_schedule_permanent")
    @SerializedName("schedule_permanent")
    @Expose
    private String schedulePermanent;
    @ColumnInfo(name="event_schedule_date_warning")
    @SerializedName("schedule_date_warning")
    @Expose
    private String scheduleDateWarning;
    @ColumnInfo(name="event_schedule_time_alert")
    @SerializedName("schedule_time_alert")
    @Expose
    private String scheduleTimeAlert;
    @ColumnInfo(name="event_schedule_start_date")
    @SerializedName("schedule_start_date")
    @Expose
    private String scheduleStartDate;
    @ColumnInfo(name="event_schedule_start_time")
    @SerializedName("schedule_start_time")
    @Expose
    private String scheduleStartTime;
    @ColumnInfo(name="event_schedule_end_date")
    @SerializedName("schedule_end_date")
    @Expose
    private String scheduleEndDate;
    @ColumnInfo(name="event_schedule_end_time")
    @SerializedName("schedule_end_time")
    @Expose
    private String scheduleEndTime;
    @ColumnInfo(name="event_schedule_one_day_event")
    @SerializedName("schedule_one_day_event")
    @Expose
    private String scheduleOneDayEvent;
    @ColumnInfo(name="event_schedule_extra")
    @SerializedName("schedule_extra")
    @Expose
    private String scheduleExtra;
    @ColumnInfo(name="event_going_count")
    @SerializedName("going_count")
    @Expose
    private Integer goingCount;
    @ColumnInfo(name="event_went_count")
    @SerializedName("went_count")
    @Expose
    private Integer wentCount;

    @SerializedName("venue")
    @Embedded
    private VenuePopular venue;

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

    public VenuePopular getVenue() {
        return venue;
    }

    public void setVenue(VenuePopular venue) {
        this.venue = venue;
    }
}
