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

public class NearFragment extends Fragment {
    FragmentNearBinding fragmentNearBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentNearBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_near,container,false);
        View view=fragmentNearBinding.getRoot();
        return  view;
    }
}
