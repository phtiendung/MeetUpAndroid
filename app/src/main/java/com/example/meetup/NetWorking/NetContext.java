package com.example.meetup.NetWorking;


import android.util.Log;


import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetContext {

    public static NetContext instance;
    private String TAG = "NetContext";

    public static NetContext getInstance() {
        return instance;
    }

    private Retrofit retrofit;
    public String BASE_URL = "";

    public String getBASE_URL() {
        return BASE_URL;
    }

    private NetContext(String url) {
        this.BASE_URL = url;
        try {
            OkHttpClient client = new OkHttpClient
                    .Builder()
                    .build();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } catch (Exception e) {
            Log.d(TAG, "Exception: " + e.toString());
        }
    }

    public <T> T create(Class<T> classz) {
        return retrofit.create(classz);
    }
}
