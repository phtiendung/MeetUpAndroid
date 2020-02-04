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
import com.example.meetup.databinding.FragmentMypageMylisteventBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import  com.example.meetup.R;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment=new HomeFragment();
    NearFragment nearFragment=new NearFragment();
    BrowserFragment browserFragment=new BrowserFragment();
    MeFragment meFragment=new MeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding activityHomeBinding= DataBindingUtil.setContentView(this,R.layout.activity_home);
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.navigation_view);
//        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.frame_container,homeFragment);
//        transaction.add(R.id.frame_container,nearFragment);
//        transaction.add(R.id.frame_container,browserFragment);
//        transaction.add(R.id.frame_container,meFragment);
//        transaction.commit();
//        add();
//        show(homeFragment);
        loadFragment(homeFragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId())
                {
                    case R.id.navigation_home:
//                        add();
//                        show(homeFragment);
                        fragment=new HomeFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_near:
//                        add();
//                        show(nearFragment);
                        fragment=new NearFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_browser:
//                        add();
//                        show(browserFragment);
                        fragment=new BrowserFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_mypage:
//                        add();
//                        show(meFragment);
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
    private void add()
    {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.hide(homeFragment);
        transaction.hide(nearFragment);
        transaction.hide(browserFragment);
        transaction.hide(meFragment);
        transaction.commit();
    }
    private void show(Fragment fragment)
    {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }
}
