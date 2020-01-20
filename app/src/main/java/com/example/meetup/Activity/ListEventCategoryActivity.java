package com.example.meetup.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;
import android.os.Bundle;

import com.example.meetup.Fragment.ByDateFragment;
import com.example.meetup.Fragment.ByPopularFragment;
import com.example.meetup.Fragment.ListEventCategoryFragment;
import com.example.meetup.Model.Category;
import com.example.meetup.R;
import com.example.meetup.databinding.ActivityListEventCategoryBinding;
import com.example.meetup.databinding.FragmentListeventCategoryBinding;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

public class ListEventCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityListEventCategoryBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_list_event_category);
        Category category = (Category) getIntent().getSerializableExtra("category");
        Fragment fragment=new ByPopularFragment();
        loadFragment(fragment);
        Bundle bundle=new Bundle();
        bundle.putSerializable("category",category);
        fragment.setArguments(bundle);
        NavigationTabStrip navigationTabStrip = binding.tabStrip;
        navigationTabStrip.setTitles("By Popularity","By Date");
        navigationTabStrip.setTabIndex(0, true);
        navigationTabStrip.setTitleSize(35);
        navigationTabStrip.setStripColor(R.color.colorPrimaryDark);
        navigationTabStrip.setStripWeight(6);
        navigationTabStrip.setStripFactor(2);
        navigationTabStrip.setStripType(NavigationTabStrip.StripType.LINE);
        navigationTabStrip.setStripGravity(NavigationTabStrip.StripGravity.BOTTOM);
        navigationTabStrip.setCornersRadius(3);
        navigationTabStrip.setAnimationDuration(300);
        navigationTabStrip.setInactiveColor(Color.DKGRAY);
        navigationTabStrip.setActiveColor(R.color.colorPrimary);
        navigationTabStrip.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {
                Fragment fragment;
                Bundle bundle=new Bundle();
                if (index == 0) {
                    fragment=new ByPopularFragment();
                    loadFragment(fragment);
                    bundle.putSerializable("category",category);
                    fragment.setArguments(bundle);
                } else {
                    fragment=new ByDateFragment();
                    loadFragment(fragment);
                    bundle.putSerializable("category",category);
                    fragment.setArguments(bundle);
                }
            }

            @Override
            public void onEndTabSelected(String title, int index) {

            }
        });
    }
    private void loadFragment(Fragment fragment)
    {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_container1,fragment);
        transaction.commit();
    }
}
