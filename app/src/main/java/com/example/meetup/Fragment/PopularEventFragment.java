package com.example.meetup.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.meetup.Adapter.EventAdapter;
import com.example.meetup.Dao.EventDB;
import com.example.meetup.Dao.NewsDB;
import com.example.meetup.EndlessRecyclerViewScrollListener;
import com.example.meetup.Model.Event;
import com.example.meetup.NetWorking.APIClient;
import com.example.meetup.NetWorking.APIStatus;
import com.example.meetup.R;
import com.example.meetup.databinding.FragmentPopularBinding;
import com.example.meetup.databinding.PopulareventItemBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularEventFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, Callback<APIStatus> {
    private FragmentPopularBinding binding;
    private EventAdapter adapter;
    private List<Event> data=new ArrayList<>();
    String DATABASE_NAME="event_database";
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;
    private int pageIndex=2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         binding= DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_popular,container,false);
         binding.srlPopular.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        //EventDB.getInstance(getContext(),DATABASE_NAME).getEventDao().deleteEventAll();
        endlessRecyclerViewScrollListener =new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                APIClient.getInstance().getPopularEvent(page,10).enqueue(new Callback<APIStatus>() {
                    @Override
                    public void onResponse(Call<APIStatus> call, Response<APIStatus> response) {
                        List<Event> events=response.body().getResponse().getEvents();
                        data.addAll(events);
                        adapter.notifyItemRangeInserted(page*10,10);
                    }

                    @Override
                    public void onFailure(Call<APIStatus> call, Throwable t) {

                    }
                });
            }
        };
        binding.rcvPopular.addOnScrollListener(endlessRecyclerViewScrollListener);
        addData();
        View view=binding.getRoot();
        return view;
    }

    private void addData() {
        if(EventDB.getInstance(getContext(),DATABASE_NAME).getEventDao().getEventsAll().isEmpty())
        {
            APIClient.getInstance().getPopularEvent(0,10).enqueue(this);
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

    }

    @Override
    public void onResponse(Call<APIStatus> call, Response<APIStatus> response) {
        data=response.body().getResponse().getEvents();
        EventDB.getInstance(getContext(),DATABASE_NAME).getEventDao().updateEvent(data);
    }

    @Override
    public void onFailure(Call<APIStatus> call, Throwable t) {

    }
}
