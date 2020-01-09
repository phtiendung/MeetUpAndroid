package com.example.meetup.NetWorking;

import com.example.meetup.Model.News;

import java.util.List;
import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("listNews")
    Call<List<News>> getNews(
            @Query("pageIndex") int pageIndex,
            @Query("pageSize") int pageSize
    );
}
