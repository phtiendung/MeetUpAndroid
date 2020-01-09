package com.example.meetup.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetup.Model.News;
import com.example.meetup.R;
import com.example.meetup.databinding.NewsItemBinding;


import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    List<News> data;

    public NewsAdapter(List<News> newsList) {
        this.data = newsList;
    }

    public void setData(List<News> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsItemBinding newsItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_item, parent, false);
        return new NewsViewHolder(newsItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.binding.tvTitle.setText(data.get(position).getTitle());
//        holder.binding.tvAuthor.setText(data.get(position).getAuthor());
//        holder.binding.tvDescription.setText(data.get(position).getDescription());
//        holder.binding.tvFeed.setText(data.get(position).getFeed());
//        holder.binding.tvPubdate.setText(data.get(position).getPublishDate());
//        holder.binding.
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private NewsItemBinding binding;

        public NewsViewHolder(@NonNull NewsItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

    }

}
