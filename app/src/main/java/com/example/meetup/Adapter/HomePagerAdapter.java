package com.example.meetup.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.meetup.Fragment.NewsFragment;
import com.example.meetup.Fragment.PopularEventFragment;

public class HomePagerAdapter extends FragmentStatePagerAdapter {
    private String[] listTab={"News","Popular"};
    private NewsFragment newsFragment;
    private PopularEventFragment popularEventFragment;

    public HomePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        newsFragment=new NewsFragment();
        popularEventFragment=new PopularEventFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
            return newsFragment;
            case 1:
            return popularEventFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return listTab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
