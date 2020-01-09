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
import com.example.meetup.databinding.FragmentMeBinding;

public class MeFragment extends Fragment {
    FragmentMeBinding fragmentMeBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMeBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_me,container,false);
        View view=fragmentMeBinding.getRoot();
        return  view;
    }
}
