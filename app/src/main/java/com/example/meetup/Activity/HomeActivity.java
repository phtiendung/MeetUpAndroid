package com.example.meetup.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.meetup.Fragment.BrowserFragment;
import com.example.meetup.Fragment.HomeFragment;
import com.example.meetup.Fragment.MeFragment;
import com.example.meetup.Fragment.NearFragment;
import com.example.meetup.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import  com.example.meetup.R;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding activityHomeBinding= DataBindingUtil.setContentView(this,R.layout.activity_home);
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.navigation_view);
        HomeFragment fragment=new HomeFragment();
        loadFragment(fragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId())
                {
                    case R.id.navigation_home:
                        fragment=new HomeFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_near:
                        fragment=new NearFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_browser:
                        fragment=new BrowserFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_mypage:
                        fragment=new MeFragment();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
    }
    private void loadFragment(Fragment fragment)
    {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        transaction.commit();
    }
}
