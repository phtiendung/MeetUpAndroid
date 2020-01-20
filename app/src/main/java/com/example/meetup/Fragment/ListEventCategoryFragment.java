package com.example.meetup.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.meetup.Adapter.EventInCategorypagerAdapter;
import com.example.meetup.Adapter.HomePagerAdapter;
import com.example.meetup.Model.Category;
import com.example.meetup.R;
import com.example.meetup.databinding.FragmentListeventCategoryBinding;

public class ListEventCategoryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentListeventCategoryBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listevent_category, container, false);
        View view = binding.getRoot();
        binding.vpViewPager.setAdapter(new EventInCategorypagerAdapter(getChildFragmentManager()));
        binding.tlHome.setupWithViewPager(binding.vpViewPager);
        Category category= (Category) getArguments().getSerializable("category");
        binding.tvCategotyName.setText(category.getName());
        binding.btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return view;
    }
}

