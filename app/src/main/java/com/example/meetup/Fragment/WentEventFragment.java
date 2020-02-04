package com.example.meetup.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.meetup.Adapter.EventAdapter;
import com.example.meetup.Model.Event;
import com.example.meetup.NetWorking.APIClient;
import com.example.meetup.NetWorking.APIStatus;
import com.example.meetup.R;
import com.example.meetup.Service.MyShared;
import com.example.meetup.databinding.FragmentMypageGoingeventBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.meetup.Fragment.LoginFragment.TOKEN;

public class WentEventFragment extends Fragment {
    FragmentMypageGoingeventBinding binding;
    private String TOKEN_GET;
    private MyShared shared;
    private EventAdapter adapter;
    private List<Event> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_mypage_goingevent,container,false);
        adapter = new EventAdapter(getContext());
        shared = new MyShared(getContext());
        TOKEN_GET = "bearer " + shared.get(TOKEN);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rcvGoing.setLayoutManager(linearLayoutManager);
        APIClient.getInstance().getEventGoingWent(TOKEN_GET, 2).enqueue(new Callback<APIStatus>() {
            @Override
            public void onResponse(Call<APIStatus> call, Response<APIStatus> response) {
                if (response.body().getStatus() == 2) {
                    list = response.body().getResponse().getEvents();
                    if (list.size() != 0) {
                        adapter.setData(list);
                        binding.rcvGoing.setAdapter(adapter);
                    } else {
                        Toast.makeText(getContext(), "Bạn chưa chọn sự kiện đã tham gia", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<APIStatus> call, Throwable t) {

            }
        });
        return binding.getRoot();
    }
}
