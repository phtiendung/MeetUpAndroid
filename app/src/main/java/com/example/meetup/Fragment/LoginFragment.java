package com.example.meetup.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.meetup.NetWorking.APIClient;
import com.example.meetup.NetWorking.APIStatus;
import com.example.meetup.R;
import com.example.meetup.Service.MyShared;
import com.example.meetup.databinding.FragmentMypageLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    private FragmentMypageLoginBinding binding;
    private MeFragment meFragment;
    private MyShared myShared;
    public static final String TOKEN="TOKEN";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_mypage_login,container,false);
        meFragment=(MeFragment)getParentFragment();
        myShared=new MyShared(getContext());
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meFragment=(MeFragment)getParentFragment();
                APIClient.getInstance().login(binding.edtEmail.getText().toString(),binding.edtPassword.getText().toString()).enqueue(new Callback<APIStatus>() {
                    @Override
                    public void onResponse(Call<APIStatus> call, Response<APIStatus> response) {
                        if(response.body().getStatus()!=0)
                        {
                            Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            String token=response.body().getResponse().getToken();
                            myShared.put(TOKEN,token);
                            meFragment.showFragment(meFragment.getMyEventFragment());
                        }
                        else
                        {
                            Toast.makeText(getContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<APIStatus> call, Throwable t) {
                        Toast.makeText(getContext(), "Lỗi hệ thống", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        binding.btnFogot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meFragment=(MeFragment)getParentFragment();
                meFragment.showFragment(meFragment.getResetPassFragment());
            }
        });
        binding.imbtBackTosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meFragment=(MeFragment)getParentFragment();
                meFragment.showFragment(meFragment.getSignupFragment());
            }
        });
        return binding.getRoot();
    }
}
