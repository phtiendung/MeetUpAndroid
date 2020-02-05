package com.example.meetup.NetWorking;


import com.example.meetup.Model.Event;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
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

    @GET("getDetailEvent")
    Call<ApiResultEventDetail> getDetailEvent(@Header("Authorization") String token,
                                              @Query("event_id") int id);

    @GET("listCategories")
    Call<APIStatus> getCategory();

    @GET("listEventsByCategory")
    Call<ApiResultInCategory> getEventCategory(
            @Query("category_id") int categoryId,
            @Query("pageIndex") int pageIndex,
            @Query("pageSize") int pageSize
    );

    @GET("listNearlyEvents")
    Call<ApiResultNearlyEvent> listNearlyEvents(
            //@Header("Authorization") String token,
            @Query("radius") int radius,
            @Query("longitue") String longitue,
            @Query("latitude") String latitude);

    @POST("register")
    Call<APIStatus> register(@Query("name") String name,
                             @Query("email") String email,
                             @Query("password") String password);

    @POST("login")
    @FormUrlEncoded
    Call<APIStatus> login(@Field("email") String email,
                          @Field("password") String password);

    @POST("resetPassword")
    Call<APIStatus> reset(@Query("email") String email);

    @POST("doUpdateEvent")
    Call<APIStatus> doUpdateEvent(@Header("Authorization") String token,
                                  @Query("status") long status,
                                  @Query("event_id") long event_id
    );

    @GET("listMyEvents")
    Call<APIStatus> getEventGoingWent(@Header("Authorization") String token,
                                      @Query("status") long status);

    @POST("doFollowVenue")
    Call<APIStatus> doFolow(@Header("Authorization") String token,
                            @Query("venue_id") long id);
}
