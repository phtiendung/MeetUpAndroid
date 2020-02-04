package com.example.meetup.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.meetup.Fragment.GoingEventFragment;
import com.example.meetup.Fragment.WentEventFragment;

public class MyEventPagerAdapter extends FragmentStatePagerAdapter {
    private String[] listTab={"Going","Went"};
    GoingEventFragment goingEventFragment;
    WentEventFragment wentEventFragment;
    public MyEventPagerAdapter(FragmentManager fm) {
        super(fm);
        goingEventFragment=new GoingEventFragment();
        wentEventFragment=new WentEventFragment();
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
        {
            return goingEventFragment;
        }
        else if (position==1)
        {
            return wentEventFragment;
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
