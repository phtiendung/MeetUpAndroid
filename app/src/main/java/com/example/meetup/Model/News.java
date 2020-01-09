package com.example.meetup.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = "table_news")
public class News {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @ColumnInfo(name="news_feed")
    @SerializedName("feed")
    @Expose
    private String feed;
    @ColumnInfo(name="new_title")
    @SerializedName("title")
    @Expose
    private String title;
    @ColumnInfo(name="news_thumb_img")
    @SerializedName("thumb_img")
    @Expose
    private String thumbImg;
    @ColumnInfo(name="news_detail_url")
    @SerializedName("detail_url")
    @Expose
    private String detailUrl;
    @ColumnInfo(name="news_description")
    @SerializedName("description")
    @Expose
    private String description;
    @ColumnInfo(name="news_author")
    @SerializedName("author")
    @Expose
    private String author;
    @ColumnInfo(name="news_publish_date")
    @SerializedName("publish_date")
    @Expose
    private String publishDate;
    @ColumnInfo(name="news_created_at")
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @ColumnInfo(name="news_updated_at")
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
