package com.example.meetup.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.meetup.Adapter.EventAdapter;
import com.example.meetup.Model.Event;
import com.example.meetup.Model.EventDetail;
import com.example.meetup.NetWorking.APIClient;
import com.example.meetup.NetWorking.APIStatus;
import com.example.meetup.NetWorking.ApiResultEventDetail;
import com.example.meetup.R;
import com.example.meetup.databinding.ActivityDetailEventBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEventActivity extends AppCompatActivity {
    ActivityDetailEventBinding binding;
    EventDetail detail=new EventDetail();
    EventAdapter adapter;
    List<Event> eventList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_detail_event);
        adapter=new EventAdapter(DetailEventActivity.this);
        binding.imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        int id=getIntent().getExtras().getInt("id");
        APIClient.getInstance().getDetailEvent(id).enqueue(new Callback<ApiResultEventDetail>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<ApiResultEventDetail> call, Response<ApiResultEventDetail> response) {
                detail=response.body().getResponse().getEventDetail();
                binding.tvPlace.setText(detail.getVenue().getName());
                binding.tvEventName.setText(detail.getName());
                binding.tvArtist.setText(detail.getArtist());
                binding.tvDescription.setText(Html.fromHtml(detail.getDescriptionHtml(),Html.FROM_HTML_MODE_COMPACT));
                binding.tvName.setText(detail.getVenue().getName());
                binding.tvGenre.setText(detail.getCategory().getSlug());
                binding.tvEventPlace.setText(detail.getVenue().getContactAddress());
                Glide.with(binding.imvEvent).load(detail.getPhoto()).into(binding.imvEvent);
            }

            @Override
            public void onFailure(Call<ApiResultEventDetail> call, Throwable t) {
                Toast.makeText(DetailEventActivity.this,"Không có kết nối mạng",Toast.LENGTH_SHORT).show();
            }
        });
        APIClient.getInstance().getPopularEvent(1,10).enqueue(new Callback<APIStatus>() {
            @Override
            public void onResponse(Call<APIStatus> call, Response<APIStatus> response) {
                eventList=response.body().getResponse().getEvents();
                adapter.setData(eventList);
                binding.rcvEvent.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<APIStatus> call, Throwable t) {

            }
        });
    }
}
