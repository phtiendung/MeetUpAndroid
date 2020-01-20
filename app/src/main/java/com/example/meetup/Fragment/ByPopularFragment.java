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

import com.example.meetup.Adapter.EventCategoryAdapter;
import com.example.meetup.Dao.EventCategoryDB;
import com.example.meetup.EndlessRecyclerViewScrollListener;
import com.example.meetup.Model.Category;
import com.example.meetup.Model.EventInCateGory;
import com.example.meetup.NetWorking.APIClient;
import com.example.meetup.NetWorking.ApiResultInCategory;
import com.example.meetup.R;
import com.example.meetup.databinding.ActivityEventCategoryBinding;
import com.example.meetup.databinding.FragmentBypopularityBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ByPopularFragment extends Fragment {
    private FragmentBypopularityBinding binding;
    private List<EventInCateGory> data = new ArrayList<>();
    private EventCategoryAdapter adapter;
    private String DATABASE_NAME = "bypopular_database";
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;
    private int pageIndex = 2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBypopularityBinding binding= DataBindingUtil.inflate(inflater, R.layout.fragment_bypopularity,container,false);
        Category category= (Category) getArguments().getSerializable("category");
        Toast.makeText(getActivity(), ""+category.getId(),Toast.LENGTH_SHORT).show();
        int id=category.getId();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        binding.rcvByPopular.setLayoutManager(linearLayoutManager);
        adapter=new EventCategoryAdapter(getContext());
        //EventCategoryDB.getInstance(getContext(),DATABASE_NAME).getEventCategoryDao().deleteEventAll();
        endlessRecyclerViewScrollListener=new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                APIClient.getInstance().getEventCategory(id,page,10).enqueue(new Callback<ApiResultInCategory>() {
                    @Override
                    public void onResponse(Call<ApiResultInCategory> call, Response<ApiResultInCategory> response) {
                        List<EventInCateGory> list=response.body().getResponse().getEvents();
                        data.addAll(list);
                        adapter.notifyItemRangeInserted(page*10,10);
                    }

                    @Override
                    public void onFailure(Call<ApiResultInCategory> call, Throwable t) {

                    }
                });
            }
        };
        View view=binding.getRoot();
        addData(id);
        return view;
    }
    private void addData(int id)
    {
        if(EventCategoryDB.getInstance(getContext(),DATABASE_NAME).getEventCategoryDao().getEventsAll().isEmpty())
        {
            APIClient.getInstance().getEventCategory(id,1,10).enqueue(new Callback<ApiResultInCategory>() {
                @Override
                public void onResponse(Call<ApiResultInCategory> call, Response<ApiResultInCategory> response) {
                    if(response.body().getResponse().getEvents()!=null) {
                        data = response.body().getResponse().getEvents();
                        EventCategoryDB.getInstance(getContext(), DATABASE_NAME).getEventCategoryDao().insertEvent(data);
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"không có sự kiện nào",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResultInCategory> call, Throwable t) {

                }
            });
        }
        else
        {
            data=EventCategoryDB.getInstance(getContext(),DATABASE_NAME).getEventCategoryDao().getEventsAll();
            adapter.setData(data);
            binding.rcvByPopular.setAdapter(adapter);
        }
    }
}
