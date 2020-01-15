package com.example.meetup.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.meetup.Adapter.CategoryAdapter;
import com.example.meetup.Dao.CategoryDB;
import com.example.meetup.Model.Category;
import com.example.meetup.NetWorking.APIClient;
import com.example.meetup.NetWorking.APIStatus;
import com.example.meetup.R;
import com.example.meetup.databinding.FragmentBrowserBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowserFragment extends Fragment {
    private CategoryAdapter adapter;
    private List<Category> list=new ArrayList<>();
    private FragmentBrowserBinding fragmentBrowserBinding;
    private String DATABASE_NAME="category_database";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentBrowserBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_browser,container,false);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        fragmentBrowserBinding.rcvCategory.setLayoutManager(linearLayoutManager);
        View view=fragmentBrowserBinding.getRoot();
        adapter=new CategoryAdapter(getContext());
        //CategoryDB.getInstance(getContext(),DATABASE_NAME).getCategoryDao().deleteCategorytAll();
        addData();
        return  view;
    }
    private void addData()
    {
        if(CategoryDB.getInstance(getContext(),DATABASE_NAME).getCategoryDao().getCategorysAll().isEmpty())
        {
            APIClient.getInstance().getCatagory().enqueue(new Callback<APIStatus>() {
                @Override
                public void onResponse(Call<APIStatus> call, Response<APIStatus> response) {
                   List<Category> categories=response.body().getResponse().getCategories();
                   CategoryDB.getInstance(getContext(),DATABASE_NAME).getCategoryDao().insertCategory(categories);
                }

                @Override
                public void onFailure(Call<APIStatus> call, Throwable t) {

                }
            });
        }
        else
        {
            list=CategoryDB.getInstance(getContext(),DATABASE_NAME).getCategoryDao().getCategorysAll();
            adapter.setData(list);
            fragmentBrowserBinding.rcvCategory.setAdapter(adapter);
        }
    }
}
