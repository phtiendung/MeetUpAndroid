package com.example.meetup.NetWorking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static API api;

    public static API getInstance(){
        if (api == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://meetup.rikkei.org/api/v0/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(API.class);
        }
        return api;
    }


}
