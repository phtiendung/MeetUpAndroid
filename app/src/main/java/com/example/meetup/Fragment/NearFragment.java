package com.example.meetup.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.meetup.R;
import com.example.meetup.databinding.FragmentNearBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class NearFragment extends Fragment implements OnMapReadyCallback {
    FragmentNearBinding fragmentNearBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentNearBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_near,container,false);
        View view=fragmentNearBinding.getRoot();
        SupportMapFragment mapFragment=(SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this::onMapReady);
        return  view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney=new LatLng(35.690511,139.754681);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,18));
        googleMap.addMarker(new MarkerOptions()
        .title("sydney")
        .snippet("okoki")
        .position(sydney));
    }
}
