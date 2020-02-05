package com.example.meetup.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.meetup.Adapter.EventAdapter;
import com.example.meetup.Adapter.NearlyEventAdapter;
import com.example.meetup.Fragment.MeFragment;
import com.example.meetup.Model.Event;
import com.example.meetup.Model.EventDetail;
import com.example.meetup.NetWorking.API;
import com.example.meetup.NetWorking.APIClient;
import com.example.meetup.NetWorking.APIStatus;
import com.example.meetup.NetWorking.ApiResultEventDetail;
import com.example.meetup.NetWorking.ApiResultNearlyEvent;
import com.example.meetup.OnItemClickListener;
import com.example.meetup.R;
import com.example.meetup.Service.MyShared;
import com.example.meetup.databinding.ActivityDetailEventBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.meetup.Fragment.LoginFragment.TOKEN;

public class DetailEventActivity extends AppCompatActivity implements OnItemClickListener {
    private ActivityDetailEventBinding binding;
    private EventDetail detail = new EventDetail();
    private EventAdapter adapter;
    private NearlyEventAdapter nearlyEventAdapter;
    private List<Event> eventList = new ArrayList<>();
    private String TOKEN_GET;
    private MyShared shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_event);
        adapter = new EventAdapter(DetailEventActivity.this);
        nearlyEventAdapter=new NearlyEventAdapter(getApplicationContext());
        nearlyEventAdapter.setOnItemClickListener(this);
        shared = new MyShared(this);
        TOKEN_GET = "bearer " + shared.get(TOKEN);
        int id = getIntent().getExtras().getInt("id");
        binding.imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIClient.getInstance().doFolow(TOKEN_GET, detail.getVenue().getId()).enqueue(new Callback<APIStatus>() {
                    @Override
                    public void onResponse(Call<APIStatus> call, Response<APIStatus> response) {
                        if (response.body().getStatus() == 0) {
                            Toast.makeText(getApplicationContext(), "Đã có lỗi xảy ra" + response.body().getError_message(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Đã Follow", Toast.LENGTH_LONG).show();
                            binding.btnFollow.setText("Followed");
                        }
                    }

                    @Override
                    public void onFailure(Call<APIStatus> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Đã có lỗi xảy ra" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        binding.btnGoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkLogin()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng đăng nhập trước", Toast.LENGTH_SHORT).show();
                } else {
                    APIClient.getInstance().doUpdateEvent(TOKEN_GET, 1, id).enqueue(new Callback<APIStatus>() {
                        @Override
                        public void onResponse(Call<APIStatus> call, Response<APIStatus> response) {
                            if (response.body().getStatus() == 1) {
                                Toast.makeText(getApplicationContext(), "Thêm vào sự kiện tham gia", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "" + response.body().getError_message(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<APIStatus> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        binding.btnWent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkLogin()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng đăng nhập trước", Toast.LENGTH_SHORT).show();
                } else {
                    APIClient.getInstance().doUpdateEvent(TOKEN_GET, 2, id).enqueue(new Callback<APIStatus>() {
                        @Override
                        public void onResponse(Call<APIStatus> call, Response<APIStatus> response) {
                            Toast.makeText(getApplicationContext(), "Thêm vào sự kiện đã tham gia", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<APIStatus> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        APIClient.getInstance().getDetailEvent(TOKEN_GET, id).enqueue(new Callback<ApiResultEventDetail>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<ApiResultEventDetail> call, Response<ApiResultEventDetail> response) {

                detail = response.body().getResponse().getEventDetail();
                binding.tvPlace.setText(detail.getVenue().getName());
                binding.tvEventName.setText(detail.getName());
                binding.tvArtist.setText(detail.getArtist());
                binding.tvDescription.setText(Html.fromHtml(detail.getDescriptionHtml(), Html.FROM_HTML_MODE_COMPACT));
                binding.tvName.setText(detail.getVenue().getName());
                binding.tvGenre.setText(detail.getCategory().getSlug());
                binding.tvEventPlace.setText(detail.getVenue().getContactAddress());
                binding.tvArtist.setText(detail.getVenue().getContactPhone());
                Glide.with(binding.imvEvent).load(detail.getPhoto()).into(binding.imvEvent);
                APIClient.getInstance().listNearlyEvents(250, detail.getVenue().getGeoLong(), detail.getVenue().getGeoLat()).enqueue(new Callback<ApiResultNearlyEvent>() {
                    @Override
                    public void onResponse(Call<ApiResultNearlyEvent> call, Response<ApiResultNearlyEvent> response) {
                        if (response.body().getStatus() != 0) {
                            eventList = response.body().getResponse().getEvent();
                            nearlyEventAdapter.setData(eventList);
                            binding.rcvEvent.setAdapter(nearlyEventAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResultNearlyEvent> call, Throwable t) {
                        Log.e("list", t.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Call<ApiResultEventDetail> call, Throwable t) {
                Toast.makeText(DetailEventActivity.this, "Không có kết nối mạng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkLogin() {
        return shared.get(TOKEN).equals("");
    }

    @Override
    public void onItemClickListener(int event_id) {
        Intent intent=new Intent(DetailEventActivity.this, DetailEventActivity.class);
        intent.putExtra("id",event_id);
        startActivity(intent);
    }
}
