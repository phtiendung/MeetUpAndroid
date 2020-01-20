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
import com.example.meetup.databinding.FragmentBydateBinding;

public class ByDateFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBydateBinding binding= DataBindingUtil.inflate(inflater, R.layout.fragment_bydate,container,false);
        View view=binding.getRoot();
        return view;
    }
}
