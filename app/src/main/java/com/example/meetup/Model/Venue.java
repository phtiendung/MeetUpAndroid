package com.example.meetup.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Venue {
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
    @SerializedName("permanent")
    @Expose
    private Object permanent;
    @SerializedName("contact_fee")
    @Expose
    private Object contactFee;
    @SerializedName("contact_phone")
    @Expose
    private String contactPhone;
    @SerializedName("contact_fax")
    @Expose
    private Object contactFax;
    @SerializedName("contact_web")
    @Expose
    private Object contactWeb;
    @SerializedName("contact_web_lang")
    @Expose
    private Object contactWebLang;
    @SerializedName("contact_address")
    @Expose
    private String contactAddress;
    @SerializedName("contact_access")
    @Expose
    private Object contactAccess;
    @SerializedName("contact_discount")
    @Expose
    private Object contactDiscount;
    @SerializedName("contact_discount_details")
    @Expose
    private Object contactDiscountDetails;
    @SerializedName("geo_area")
    @Expose
    private String geoArea;
    @SerializedName("geo_long")
    @Expose
    private String geoLong;
    @SerializedName("geo_lat")
    @Expose
    private String geoLat;
    @SerializedName("schedule_openinghour")
    @Expose
    private Object scheduleOpeninghour;
    @SerializedName("schedule_closinghour")
    @Expose
    private Object scheduleClosinghour;
    @SerializedName("schedule_breakstart")
    @Expose
    private Object scheduleBreakstart;
    @SerializedName("schedule_breakend")
    @Expose
    private Object scheduleBreakend;
    @SerializedName("schedule_openingdetails")
    @Expose
    private Object scheduleOpeningdetails;
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

    public Object getPermanent() {
        return permanent;
    }

    public void setPermanent(Object permanent) {
        this.permanent = permanent;
    }

    public Object getContactFee() {
        return contactFee;
    }

    public void setContactFee(Object contactFee) {
        this.contactFee = contactFee;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Object getContactFax() {
        return contactFax;
    }

    public void setContactFax(Object contactFax) {
        this.contactFax = contactFax;
    }

    public Object getContactWeb() {
        return contactWeb;
    }

    public void setContactWeb(Object contactWeb) {
        this.contactWeb = contactWeb;
    }

    public Object getContactWebLang() {
        return contactWebLang;
    }

    public void setContactWebLang(Object contactWebLang) {
        this.contactWebLang = contactWebLang;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public Object getContactAccess() {
        return contactAccess;
    }

    public void setContactAccess(Object contactAccess) {
        this.contactAccess = contactAccess;
    }

    public Object getContactDiscount() {
        return contactDiscount;
    }

    public void setContactDiscount(Object contactDiscount) {
        this.contactDiscount = contactDiscount;
    }

    public Object getContactDiscountDetails() {
        return contactDiscountDetails;
    }

    public void setContactDiscountDetails(Object contactDiscountDetails) {
        this.contactDiscountDetails = contactDiscountDetails;
    }

    public String getGeoArea() {
        return geoArea;
    }

    public void setGeoArea(String geoArea) {
        this.geoArea = geoArea;
    }

    public String getGeoLong() {
        return geoLong;
    }

    public void setGeoLong(String geoLong) {
        this.geoLong = geoLong;
    }

    public String getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(String geoLat) {
        this.geoLat = geoLat;
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

    public Object getScheduleBreakstart() {
        return scheduleBreakstart;
    }

    public void setScheduleBreakstart(Object scheduleBreakstart) {
        this.scheduleBreakstart = scheduleBreakstart;
    }

    public Object getScheduleBreakend() {
        return scheduleBreakend;
    }

    public void setScheduleBreakend(Object scheduleBreakend) {
        this.scheduleBreakend = scheduleBreakend;
    }

    public Object getScheduleOpeningdetails() {
        return scheduleOpeningdetails;
    }

    public void setScheduleOpeningdetails(Object scheduleOpeningdetails) {
        this.scheduleOpeningdetails = scheduleOpeningdetails;
    }

    public Object getScheduleClosed() {
        return scheduleClosed;
    }

    public void setScheduleClosed(Object scheduleClosed) {
        this.scheduleClosed = scheduleClosed;
    }
}
