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
import androidx.fragment.app.FragmentTransaction;

import com.example.meetup.R;
import com.example.meetup.databinding.FragmentHomeBinding;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

public class HomeFragment extends Fragment {
    FragmentHomeBinding fragmentHomeBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View view = fragmentHomeBinding.getRoot();
        NewsFragment newsFragment=new NewsFragment();
        loadFragment(newsFragment);
        NavigationTabStrip navigationTabStrip=fragmentHomeBinding.tabStrip;
        navigationTabStrip.setTitles("News","Popular");
        navigationTabStrip.setTabIndex(0, true);
        navigationTabStrip.setTitleSize(35);
        navigationTabStrip.setStripWeight(6);
        navigationTabStrip.setStripFactor(2);
        navigationTabStrip.setStripType(NavigationTabStrip.StripType.LINE);
        navigationTabStrip.setStripGravity(NavigationTabStrip.StripGravity.BOTTOM);
        navigationTabStrip.setCornersRadius(3);
        navigationTabStrip.setAnimationDuration(300);
        navigationTabStrip.setInactiveColor(Color.DKGRAY);
        navigationTabStrip.setActiveColor(R.color.colorAccent);
        navigationTabStrip.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {
                if(index==0)
                {
                    NewsFragment newsFragment=new NewsFragment();
                    loadFragment(newsFragment);
                    Log.e("TAG","A");
                }
                else
                {
                    PopularFragment popularFragment=new PopularFragment();
                    loadFragment(popularFragment);
                    Log.e("TAG","B");
                }
            }

            @Override
            public void onEndTabSelected(String title, int index) {

            }
        });
        return view;
    }
    private void loadFragment(Fragment fragment)
    {
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.home_frame,fragment);
        transaction.commit();
    }
}
