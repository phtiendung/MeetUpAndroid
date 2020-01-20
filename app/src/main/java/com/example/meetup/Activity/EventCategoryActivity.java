package com.example.meetup.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.meetup.Adapter.EventCategoryAdapter;
import com.example.meetup.Dao.EventCategoryDB;
import com.example.meetup.EndlessRecyclerViewScrollListener;
import com.example.meetup.Model.Category;
import com.example.meetup.Model.Event;
import com.example.meetup.Model.EventInCateGory;
import com.example.meetup.NetWorking.APIClient;
import com.example.meetup.NetWorking.ApiResultInCategory;
import com.example.meetup.R;
import com.example.meetup.databinding.ActivityEventCategoryBinding;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventCategoryActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private ActivityEventCategoryBinding binding;
    private List<EventInCateGory> data = new ArrayList<>();
    private EventCategoryAdapter adapter;
    private String DATABASE_NAME = "eventcategory_database";
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;
    private int pageIndex = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_category);
        Category category = (Category) getIntent().getSerializableExtra("category");
        binding.tvCategotyName.setText(category.getName());
        binding.srlEventCategory.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        binding.rcvCategory.setLayoutManager(linearLayoutManager);
        adapter=new EventCategoryAdapter(getApplicationContext());
        //EventCategoryDB.getInstance(getApplicationContext(),DATABASE_NAME).getEventCategoryDao().deleteEventAll();
        endlessRecyclerViewScrollListener= new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                APIClient.getInstance().getEventCategory(category.getId(),page,10).enqueue(new Callback<ApiResultInCategory>() {
                    @Override
                    public void onResponse(Call<ApiResultInCategory> call, Response<ApiResultInCategory> response) {
                        List<EventInCateGory> eventList=response.body().getResponse().getEvents();
                        data.addAll(eventList);
                        adapter.notifyItemRangeInserted(page*10,10);
                    }
                    @Override
                    public void onFailure(Call<ApiResultInCategory> call, Throwable t) {
                    }
                });
            }
        };
        binding.btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.rcvCategory.addOnScrollListener(endlessRecyclerViewScrollListener);
        addData(category.getId());
    }
    public void addData(int id)
    {
        if(EventCategoryDB.getInstance(getApplicationContext(),DATABASE_NAME).getEventCategoryDao().getEventsAll().isEmpty())
        {
          APIClient.getInstance().getEventCategory(id,1,10).enqueue(new Callback<ApiResultInCategory>() {
              @Override
              public void onResponse(Call<ApiResultInCategory> call, Response<ApiResultInCategory> response) {
                  data=response.body().getResponse().getEvents();
                  adapter.setData(data);
                  binding.rcvCategory.setAdapter(adapter);
                  Log.e("oki",""+data.size());
              }

              @Override
              public void onFailure(Call<ApiResultInCategory> call, Throwable t) {
                Log.e("oki",t.getMessage());
              }
          });
        }
        else
        {
            data=EventCategoryDB.getInstance(getApplicationContext(),DATABASE_NAME).getEventCategoryDao().getEventsAll();
            adapter.setData(data);
            binding.rcvCategory.setAdapter(adapter);
        }
    }
    @Override
    public void onRefresh() {
        pageIndex++;
        Category category = (Category) getIntent().getSerializableExtra("category");
        APIClient.getInstance().getEventCategory(category.getId(),pageIndex,10).enqueue(new Callback<ApiResultInCategory>() {
            @Override
            public void onResponse(Call<ApiResultInCategory> call, Response<ApiResultInCategory> response) {
                data=response.body().getResponse().getEvents();
                EventCategoryDB.getInstance(getApplicationContext(),DATABASE_NAME).getEventCategoryDao().insertEvent(data);
                adapter.setData(data);
                binding.rcvCategory.setAdapter(adapter);
                binding.srlEventCategory.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ApiResultInCategory> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Kiểm tra lại kết nối mạng", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
