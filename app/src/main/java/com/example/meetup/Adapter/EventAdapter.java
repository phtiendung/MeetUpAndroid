package com.example.meetup.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meetup.Model.Event;
import com.example.meetup.R;
import com.example.meetup.databinding.PopulareventItemBinding;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    List<Event> data;
    LayoutInflater inflater;

    public EventAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(List<Event> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PopulareventItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.popularevent_item, parent, false);
        return new EventViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.binding.tvEventName.setText(data.get(position).getName());
        holder.binding.tvDescription.setText(data.get(position).getDescriptionRaw());
        holder.binding.tvGoingCount.setText(data.get(position).getGoingCount().toString());
        String uri=data.get(position).getPhoto();
        Glide.with(holder.binding.imvEvent).load(uri).into(holder.binding.imvEvent);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        private PopulareventItemBinding binding;

        public EventViewHolder(@NonNull PopulareventItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
