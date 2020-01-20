package com.example.meetup.Fragment;

import android.graphics.Color;
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
import com.example.meetup.Adapter.HomePagerAdapter;
import com.example.meetup.R;
import com.example.meetup.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    FragmentHomeBinding fragmentHomeBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View view = fragmentHomeBinding.getRoot();
        fragmentHomeBinding.vpViewPager.setAdapter(new HomePagerAdapter(getChildFragmentManager()));
        fragmentHomeBinding.tlHome.setupWithViewPager(fragmentHomeBinding.vpViewPager);
        return view;
    }
}
