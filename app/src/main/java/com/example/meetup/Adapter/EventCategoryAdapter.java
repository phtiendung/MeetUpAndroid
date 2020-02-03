package com.example.meetup.Adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meetup.Model.EventInCateGory;
import com.example.meetup.OnItemClickListener;
import com.example.meetup.R;
import com.example.meetup.databinding.PopulareventItemBinding;

import java.util.List;

public class EventCategoryAdapter extends RecyclerView.Adapter<EventCategoryAdapter.EventCategoryViewHolder> {
    private List<EventInCateGory> data;
    private LayoutInflater inflater;
    private OnItemClickListener  onItemClickListener;
    public EventCategoryAdapter(Context context) {
        this.inflater = inflater;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<EventInCateGory> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PopulareventItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.popularevent_item,parent,false);
        return new EventCategoryViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull EventCategoryViewHolder holder, int position) {
        holder.binding.tvEventName.setText(data.get(position).getName());
        holder.binding.tvDescription.setText(Html.fromHtml(data.get(position).getDescriptionRaw(),Html.FROM_HTML_MODE_COMPACT));
        holder.binding.tvGoingCount.setText(data.get(position).getGoingCount().toString());
        String uri=data.get(position).getPhoto();
        Glide.with(holder.binding.imvEvent).load(uri).into(holder.binding.imvEvent);
        if(onItemClickListener!=null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClickListener(data.get(position).getId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class EventCategoryViewHolder extends RecyclerView.ViewHolder {
        PopulareventItemBinding binding;
        public EventCategoryViewHolder(@NonNull PopulareventItemBinding itemView) {
            super(itemView.getRoot());
            this.binding=itemView;
        }
    }
}
