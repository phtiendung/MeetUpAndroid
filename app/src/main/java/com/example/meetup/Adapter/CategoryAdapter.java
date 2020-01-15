package com.example.meetup.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetup.Model.Category;
import com.example.meetup.R;
import com.example.meetup.databinding.ItemCategoryBinding;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    List<Category> data;
    LayoutInflater inflater;
    public CategoryAdapter(Context context) {
        this.inflater=LayoutInflater.from(context);
    }

    public void setData(List<Category> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_category,parent,false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.binding.tvCategotyName.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ItemCategoryBinding binding;
        public CategoryViewHolder(@NonNull ItemCategoryBinding itemView) {
            super(itemView.getRoot());
            this.binding=itemView;
        }
    }
}
