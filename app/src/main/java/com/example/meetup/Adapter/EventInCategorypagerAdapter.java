package com.example.meetup.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.meetup.Fragment.ByDateFragment;
import com.example.meetup.Fragment.ByPopularFragment;

public class EventInCategorypagerAdapter extends FragmentStatePagerAdapter {
    private String[] tabList={"By Popularity","By Date"};
    private ByDateFragment byDateFragment;
    private ByPopularFragment byPopularFragment;
    public EventInCategorypagerAdapter(FragmentManager fm) {
        super(fm);
        byPopularFragment=new ByPopularFragment();
        byDateFragment=new ByDateFragment();
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
        {
            return byPopularFragment;
        }
        else if(position==1)
        {
            return byDateFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabList.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabList[position];
    }
}
