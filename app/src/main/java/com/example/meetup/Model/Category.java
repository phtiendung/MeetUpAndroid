package com.example.meetup.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = "table_category")
public class Category {
    @PrimaryKey
    @ColumnInfo(name = "category_id")
    @SerializedName("id")
    @Expose
    private Integer id;
    @ColumnInfo(name = "category_name")
    @SerializedName("name")
    @Expose
    private String name;
    @ColumnInfo(name = "category_slug")
    @SerializedName("slug")
    @Expose
    private String slug;
    @ColumnInfo(name = "category_parent_id")
    @SerializedName("parent_id")
    @Expose
    private String parentId;

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}