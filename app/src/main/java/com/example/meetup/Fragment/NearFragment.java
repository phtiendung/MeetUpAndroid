package com.example.meetup.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.meetup.Adapter.NearlyEventAdapter;
import com.example.meetup.Model.Event;
import com.example.meetup.Model.EventDetail;
import com.example.meetup.NetWorking.APIClient;
import com.example.meetup.NetWorking.ApiResultEventDetail;
import com.example.meetup.NetWorking.ApiResultNearlyEvent;
import com.example.meetup.R;
import com.example.meetup.Service.MyShared;
import com.example.meetup.databinding.FragmentNearBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.meetup.Fragment.LoginFragment.TOKEN;

public class NearFragment extends Fragment {
    private FragmentNearBinding fragmentNearBinding;
    private String TOKEN_GET;
    private NearlyEventAdapter adapter;
    private MyShared myShared;
    private List<Event> eventList = new ArrayList<>();
    private Context context;
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location mLocation;
    private SupportMapFragment mapFragment;
    private double Long, Lat;
    private final String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private List<EventDetail> detailList;
    private SnapHelper snapHelper;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentNearBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_near, container, false);
        context = container.getContext();
        myShared = new MyShared(getContext());
        TOKEN_GET = myShared.get(TOKEN);
        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(fragmentNearBinding.rcvNearevent);
        adapter = new NearlyEventAdapter(getContext());
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        if (getLocationPermission()) {
            getCurrentLocation();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(PERMISSIONS, 0);
            }
        }
        View view = fragmentNearBinding.getRoot();
        fragmentNearBinding.rcvNearevent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                int position = getCurrentItem();
                EventDetail detail = detailList.get(position);
                moveCamera(detail);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        return view;
    }

    private void getCurrentLocation() {
        if (getLocationPermission()) {
            Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
            locationTask.addOnSuccessListener(location -> {
                if (location != null) {
                    mLocation = location;
                    mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(googleMap -> {
                        mMap = googleMap;
                        mMap.setOnMarkerClickListener(listener);
                        Lat = mLocation.getLatitude();
                        Long = mLocation.getLongitude();
                        String la = String.valueOf(Lat);
                        String lg = String.valueOf(Long);
                        LatLng lng = new LatLng(Lat, Long);
                        MarkerOptions markerOptions = new MarkerOptions().position(lng).title("Vị trí của bạn");
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lng, 15));
                        googleMap.addMarker(markerOptions);
                        APIClient.getInstance().listNearlyEvents(100, lg, la).enqueue(new Callback<ApiResultNearlyEvent>() {
                            @Override
                            public void onResponse(Call<ApiResultNearlyEvent> call, Response<ApiResultNearlyEvent> response) {
                                if (response.body() != null) {
                                    if (response.body().getStatus() != 0) {
                                        //Log.e("map",""+response.body().getResponse().getEvent().size());
                                        eventList = response.body().getResponse().getEvent();
                                        adapter.setData(eventList);
                                        fragmentNearBinding.rcvNearevent.setAdapter(adapter);
                                        addMarker(eventList);
                                    }
                                } else {
                                    Toast.makeText(getContext(), "Không có sự kiện nào gần bạn", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ApiResultNearlyEvent> call, Throwable t) {

                            }
                        });
                    });
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (getLocationPermission()) {
            getCurrentLocation();
        } else {
            Toast.makeText(getContext(), "Cấp quyền để sử dụng chức năng ", Toast.LENGTH_LONG).show();
        }
    }

    private boolean getLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String s : PERMISSIONS) {
                int check = context.checkSelfPermission(s);
                if (check != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth()
                , vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth()
                , vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    private void addMarker(List<Event> events) {
        detailList = new ArrayList<>();
        BitmapDescriptor icon = bitmapDescriptorFromVector(context, R.drawable.ic_location_on_black_24dp);
        for (Event i : events) {
            APIClient.getInstance().getDetailEvent(TOKEN_GET, i.getId()).enqueue(new Callback<ApiResultEventDetail>() {
                @Override
                public void onResponse(Call<ApiResultEventDetail> call, Response<ApiResultEventDetail> response) {
                    EventDetail detail = response.body().getResponse().getEventDetail();
                    LatLng lng = new LatLng(Double.valueOf(detail.getVenue().getGeoLat()), Double.valueOf(detail.getVenue().getGeoLong()));
                    MarkerOptions markerOptions = new MarkerOptions().position(lng).title(detail.getName()).snippet("Khoảng cách đến bạn: "+convertDistanceToString(dinstance(lng.latitude,lng.longitude)[0]));
                    mMap.addMarker(markerOptions).setIcon(icon);
                    detailList.add(detail);
                }

                @Override
                public void onFailure(Call<ApiResultEventDetail> call, Throwable t) {

                }
            });
        }
    }

    private int getCurrentItem() {
        return ((LinearLayoutManager) fragmentNearBinding.rcvNearevent.getLayoutManager())
                .findFirstVisibleItemPosition();
    }

    private void moveCamera(EventDetail eventDetail) {
        LatLng lng = new LatLng(Double.valueOf(eventDetail.getVenue().getGeoLat()), Double.valueOf(eventDetail.getVenue().getGeoLong()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lng, 15));
    }

    GoogleMap.OnMarkerClickListener listener = marker -> {
        moveToItem(marker);
        Log.e("distance",marker.getSnippet());
        return false;
    };

    private void moveToItem(Marker marker) {
        for (int i = 0; i < detailList.size(); i++) {
            if (marker.getTitle().equals(detailList.get(i).getName())) {
                fragmentNearBinding.rcvNearevent.scrollToPosition(i);
            }
        }

    }

    private float[] dinstance(double lat, double log) {
        float[] results = new float[1];
        Location.distanceBetween(mLocation.getLatitude(),mLocation.getLongitude(), lat, log,results);
        return results;
    }
    private String convertDistanceToString(float distance) {
        if (distance < 1000) {
            return (int) distance + " m";
        } else {
            return (Math.round(distance * 10) / 10) / 1000 + " Km";
        }
    }
}
