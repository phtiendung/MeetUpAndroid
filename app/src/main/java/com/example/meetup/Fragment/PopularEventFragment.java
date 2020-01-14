package com.example.meetup.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.meetup.Activity.DetailEventActivity;
import com.example.meetup.Adapter.EventAdapter;
import com.example.meetup.Dao.EventDB;
import com.example.meetup.Dao.NewsDB;
import com.example.meetup.EndlessRecyclerViewScrollListener;
import com.example.meetup.Model.Event;
import com.example.meetup.NetWorking.API;
import com.example.meetup.NetWorking.APIClient;
import com.example.meetup.NetWorking.APIStatus;
import com.example.meetup.OnItemClickListener;
import com.example.meetup.R;
import com.example.meetup.Service.MyShared;
import com.example.meetup.databinding.FragmentPopularBinding;
import com.example.meetup.databinding.PopulareventItemBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularEventFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, Callback<APIStatus>,OnItemClickListener {
    private FragmentPopularBinding binding;
    private EventAdapter adapter;
    private List<Event> data = new ArrayList<>();
    private String DATABASE_NAME = "event_database";
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;
    private int pageIndex = 2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_popular,container,false);
        binding.srlPopular.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        binding.rcvPopular.setLayoutManager(linearLayoutManager);
        adapter=new EventAdapter(getContext());
        adapter.setOnItemClickListener(this);
        //EventDB.getInstance(getContext(),DATABASE_NAME).getEventDao().deleteEventAll();
        endlessRecyclerViewScrollListener=new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                APIClient.getInstance().getPopularEvent(page,10).enqueue(new Callback<APIStatus>() {
                    @Override
                    public void onResponse(Call<APIStatus> call, Response<APIStatus> response) {
                        List<Event> eventList=response.body().getResponse().getEvents();
                        data.addAll(eventList);
                        adapter.notifyItemRangeInserted(page*10,10);
                    }

                    @Override
                    public void onFailure(Call<APIStatus> call, Throwable t) {
                        Toast.makeText(getContext(),"Kiểm tra lại kết nối mạng",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        binding.rcvPopular.addOnScrollListener(endlessRecyclerViewScrollListener);
        View view=binding.getRoot();
        insertData();
        return view;
    }
    private void insertData()
    {
        if(EventDB.getInstance(getContext(),DATABASE_NAME).getEventDao().getEventsAll().isEmpty())
        {
            APIClient.getInstance().getPopularEvent(1,10).enqueue(this);
        }
        else
        {
            data=EventDB.getInstance(getContext(),DATABASE_NAME).getEventDao().getEventsAll();
            adapter.setData(data);
            binding.rcvPopular.setAdapter(adapter);
        }
    }
    @Override
    public void onRefresh() {
        pageIndex++;
        APIClient.getInstance().getPopularEvent(pageIndex,10).enqueue(new Callback<APIStatus>() {
            @Override
            public void onResponse(Call<APIStatus> call, Response<APIStatus> response) {
                data=response.body().getResponse().getEvents();
                adapter.setData(data);
                binding.rcvPopular.setAdapter(adapter);
                binding.srlPopular.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<APIStatus> call, Throwable t) {
                Toast.makeText(getContext(),"Kiểm tra lại kết nối mạng",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResponse(Call<APIStatus> call, Response<APIStatus> response) {
        data=response.body().getResponse().getEvents();
        EventDB.getInstance(getContext(),DATABASE_NAME).getEventDao().insertEvent(data);
    }

    @Override
    public void onFailure(Call<APIStatus> call, Throwable t) {
        Toast.makeText(getContext(),"Kiểm tra lại kết nối mạng",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClickListener(int event_id) {
        Intent intent=new Intent(getActivity(), DetailEventActivity.class);
        intent.putExtra("id",event_id);
        startActivity(intent);
    }
}
