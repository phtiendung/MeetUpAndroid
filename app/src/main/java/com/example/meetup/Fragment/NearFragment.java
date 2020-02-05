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

import com.example.meetup.Adapter.NearlyEventAdapter;
import com.example.meetup.Model.Event;
import com.example.meetup.NetWorking.APIClient;
import com.example.meetup.NetWorking.ApiResultEventDetail;
import com.example.meetup.NetWorking.ApiResultNearlyEvent;
import com.example.meetup.OnEventClickListener;
import com.example.meetup.R;
import com.example.meetup.Service.MyShared;
import com.example.meetup.databinding.FragmentNearBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.meetup.Fragment.LoginFragment.TOKEN;

public class NearFragment extends Fragment implements OnMapReadyCallback, OnEventClickListener {
    private FragmentNearBinding fragmentNearBinding;
    private String TOKEN_GET;
    private NearlyEventAdapter adapter;
    private MyShared myShared;
    private List<Event> eventList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentNearBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_near, container, false);
        myShared = new MyShared(getContext());
        adapter = new NearlyEventAdapter(getContext());
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this::onMapReady);
        APIClient.getInstance().listNearlyEvents(100, "-122.317334", "47.671528").enqueue(new Callback<ApiResultNearlyEvent>() {
            @Override
            public void onResponse(Call<ApiResultNearlyEvent> call, Response<ApiResultNearlyEvent> response) {
                if (response.body().getStatus() != 0) {
                    //Log.e("map",""+response.body().getResponse().getEvent().size());
                    eventList = response.body().getResponse().getEvent();
                    adapter.setData(eventList);
                    fragmentNearBinding.rcvNearevent.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ApiResultNearlyEvent> call, Throwable t) {

            }
        });
        View view = fragmentNearBinding.getRoot();
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(47.671528, -122.317334);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));
        googleMap.addMarker(new MarkerOptions()
                .title("sydney")
                .snippet("okoki")
                .position(sydney));
    }

    @Override
    public void oneventclick(Event event) {
        APIClient.getInstance().getDetailEvent(TOKEN_GET, event.getId()).enqueue(new Callback<ApiResultEventDetail>() {
            @Override
            public void onResponse(Call<ApiResultEventDetail> call, Response<ApiResultEventDetail> response) {
                double Long = Double.parseDouble(response.body().getResponse().getEventDetail().getVenue().getGeoLong());
                double Lat = Double.parseDouble(response.body().getResponse().getEventDetail().getVenue().getGeoLat());
                LatLng lng = new LatLng(Lat, Long);
            }

            @Override
            public void onFailure(Call<ApiResultEventDetail> call, Throwable t) {

            }
        });
    }
}
