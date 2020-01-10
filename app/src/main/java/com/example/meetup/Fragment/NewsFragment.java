package com.example.meetup.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.meetup.Adapter.NewsAdapter;
import com.example.meetup.Dao.NewsDB;
import com.example.meetup.EndlessRecyclerViewScrollListener;
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

public class NewsFragment extends Fragment implements Callback<APIStatus>, SwipeRefreshLayout.OnRefreshListener {
    private FragmentNewBinding fragmentNewBinding;
    private NewsAdapter newsAdapter;
    private List<News> data = new ArrayList<>();
    String DATABASE_NAME = "news_database";
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;
    private int pageIndex=2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentNewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_new, container, false);
        fragmentNewBinding.srlNews.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        fragmentNewBinding.rcvNews.setLayoutManager(linearLayoutManager);
        newsAdapter=new NewsAdapter(getContext());
        //NewsDB.getInstance(getContext(),DATABASE_NAME).getNewsDao().deleteAll();
        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                APIClient.getInstance().getNews(page,10).enqueue(new Callback<APIStatus>() {
                    @Override
                    public void onResponse(Call<APIStatus> call, Response<APIStatus> response) {
                        List<News> newsList=response.body().getResponse().getNews();
                        data.addAll(newsList);
                        newsAdapter.notifyItemRangeInserted(page*10,10);
                    }

                    @Override
                    public void onFailure(Call<APIStatus> call, Throwable t) {

                    }
                });
            }
        };
        fragmentNewBinding.rcvNews.addOnScrollListener(endlessRecyclerViewScrollListener);
        addData();
        View view = fragmentNewBinding.getRoot();
        return view;
    }
    public void addData()
    {
        if(NewsDB.getInstance(getContext(),DATABASE_NAME).getNewsDao().getNewsAll().isEmpty())
        {
            APIClient.getInstance().getNews(0,10).enqueue(this);
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

    @Override
    public void onRefresh() {
        pageIndex++;
        APIClient.getInstance().getNews(pageIndex,10).enqueue(new Callback<APIStatus>() {
            @Override
            public void onResponse(Call<APIStatus> call, Response<APIStatus> response) {
                data=response.body().getResponse().getNews();
                NewsDB.getInstance(getContext(),DATABASE_NAME).getNewsDao().updateNews(data);
                newsAdapter.setData(data);
                fragmentNewBinding.rcvNews.setAdapter(newsAdapter);
                fragmentNewBinding.srlNews.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<APIStatus> call, Throwable t) {
                Toast.makeText(getContext(),"Kiểm tra lại kết nối mạng",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
