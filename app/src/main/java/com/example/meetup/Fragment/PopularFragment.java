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
import com.example.meetup.databinding.FragmentPopularBinding;

public class PopularFragment extends Fragment {
    FragmentPopularBinding fragmentPopularBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentPopularBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_popular,container,false);
        View view=fragmentPopularBinding.getRoot();
        return view;
    }
}
