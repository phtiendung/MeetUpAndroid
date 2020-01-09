package com.example.meetup.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.meetup.Adapter.NewsAdapter;
import com.example.meetup.Dao.NewsDB;
import com.example.meetup.Model.News;
import com.example.meetup.NetWorking.APIClient;
import com.example.meetup.R;
import com.example.meetup.databinding.FragmentNewBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class NewsFragment extends Fragment {
    private FragmentNewBinding fragmentNewBinding;
    private NewsAdapter newsAdapter;
    private List<News> data = new ArrayList<>();
    String DATABASE_NAME = "news_database";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentNewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_new, container, false);
        fragmentNewBinding.rcvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        View view = fragmentNewBinding.getRoot();
        getNewsData();
        return view;
    }


    private void getNewsData() {
        APIClient.getInstance().getNews(1, 10).enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                data = response.body();
                Log.e(TAG, "onResponse: " + data);
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Log.e(TAG, "onFailure: fail"+t.getMessage());
            }
        });

    }

}
