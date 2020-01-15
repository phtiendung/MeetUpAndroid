package com.example.meetup.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.meetup.Model.Category;
import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT*FROM table_category")
    List<Category> getCategorysAll();
    @Insert
    void insertCategory(List<Category> categories);
    @Update
    void updateCategory(List<Category> categories);
    @Delete
    void deleteCategory(Category category);
    @Query("DELETE FROM table_category")
    void deleteCategorytAll();
}
