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
import com.example.meetup.Model.Event;
import com.example.meetup.OnItemClickListener;
import com.example.meetup.R;
import com.example.meetup.databinding.PopulareventItemBinding;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<Event> data;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public EventAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(List<Event> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PopulareventItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.popularevent_item, parent, false);
        return new EventViewHolder(itemBinding);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
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

    public class EventViewHolder extends RecyclerView.ViewHolder {
        private PopulareventItemBinding binding;

        public EventViewHolder(@NonNull PopulareventItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
