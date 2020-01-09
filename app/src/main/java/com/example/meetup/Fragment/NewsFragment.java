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
import com.example.meetup.NetWorking.APIStatus;
import com.example.meetup.R;
import com.example.meetup.databinding.FragmentNewBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment implements Callback<APIStatus> {
    private FragmentNewBinding fragmentNewBinding;
    private NewsAdapter newsAdapter;
    private List<News> data = new ArrayList<>();
    String DATABASE_NAME = "news_database";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentNewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_new, container, false);
        fragmentNewBinding.rcvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter=new NewsAdapter(getContext());
        APIClient.getInstance().getNews(1,10).enqueue(new Callback<APIStatus>() {
            @Override
            public void onResponse(Call<APIStatus> call, Response<APIStatus> response) {
                Log.e("TAG","oki");
            }

            @Override
            public void onFailure(Call<APIStatus> call, Throwable t) {
                Log.e("TAG","Fail"+t.getMessage());
            }
        });
        addData();
        View view = fragmentNewBinding.getRoot();
        return view;
    }
    public void addData()
    {
        if(NewsDB.getInstance(getContext(),DATABASE_NAME).getNewsDao().getNewsAll().isEmpty())
        {
            APIClient.getInstance().getNews(1,10).enqueue(this);
        }
        else
        {
            data=NewsDB.getInstance(getContext(),DATABASE_NAME).getNewsDao().getNewsAll();
            newsAdapter.setData(data);
            fragmentNewBinding.rcvNews.setAdapter(newsAdapter);
            newsAdapter.setData(data);
            fragmentNewBinding.rcvNews.setAdapter(newsAdapter);

        }
    }

    @Override
    public void onResponse(Call<APIStatus> call, Response<APIStatus> response) {
        data=response.body().getResponse().getNews();
        NewsDB.getInstance(getContext(),DATABASE_NAME).getNewsDao().insertnews(data);
    }

    @Override
    public void onFailure(Call<APIStatus> call, Throwable t) {

    }
}
