package com.example.meetup.NetWorking;


import com.example.meetup.Model.Event;

import java.util.List;
import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("listNews")
    Call<APIStatus> getNews(
            @Query("pageIndex") int pageIndex,
            @Query("pageSize") int pageSize
    );
    @GET("listPopularEvents")
    Call<APIStatus> getPopularEvent(
            @Query("pageIndex") int pageIndex,
            @Query("pageSize") int pageSize
    );
}
