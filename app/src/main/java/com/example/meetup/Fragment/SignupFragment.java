package com.example.meetup.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.room.Dao;

import com.example.meetup.NetWorking.APIClient;
import com.example.meetup.NetWorking.APIStatus;
import com.example.meetup.R;
import com.example.meetup.databinding.FragmentMypageSignupBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupFragment extends Fragment {
    FragmentMypageSignupBinding binding;
    MeFragment meFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage_signup,container,false);

        View view=binding.getRoot();
        binding.tvAlreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meFragment=(MeFragment)getParentFragment();
                meFragment.showFragment(meFragment.getLoginFragment());
            }
        });
        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkInput())
                APIClient.getInstance().register(binding.edtFullName.getText().toString()
                        ,binding.edtEmail.getText().toString()
                        ,binding.edtPassword.getText().toString())
                        .enqueue(new Callback<APIStatus>() {
                            @Override
                            public void onResponse(Call<APIStatus> call, Response<APIStatus> response) {
                                if(response.body().getStatus()!=0)
                                {
                                    Toast.makeText(getContext(),"Đăng kí thành công, hãy đăng nhập lại",Toast.LENGTH_SHORT).show();
                                    meFragment=(MeFragment)getParentFragment();
                                    meFragment.showFragment(meFragment.getLoginFragment());
                                }
                                else
                                {
                                    Toast.makeText(getContext(),"Đăng kí thất bại, tài khoản đã tồn tại",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<APIStatus> call, Throwable t) {
                                Toast.makeText(getContext(),"Đăng kí thất bại",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        return view;
    }
    private boolean checkInput()
    {
        if(!checkEmail(binding.edtEmail.getText()))
        {
            Toast.makeText(getContext(),"Email không hợp lệ",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(binding.edtPassword.getText().length()<6)
        {
            Toast.makeText(getContext(),"Mật khẩu phải từ 6 kí tự trở lên",Toast.LENGTH_SHORT).show();
            return false;
        }
        return !Empty();
    }
    private boolean checkEmail(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean Empty()
    {
        return binding.edtFullName.getText().length() == 0 || binding.edtEmail
                .getText().length() == 0 || binding.edtPassword.getText().length() == 0;
    }
}
