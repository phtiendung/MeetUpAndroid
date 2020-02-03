package com.example.meetup.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.meetup.R;
import com.example.meetup.databinding.FragmentMeBinding;

public class MeFragment extends Fragment {
    FragmentMeBinding fragmentMeBinding;
     SignupFragment signupFragment=new SignupFragment();
     LoginFragment loginFragment=new LoginFragment();
     ResetPassFragment resetPassFragment=new ResetPassFragment();
    public SignupFragment getSignupFragment() {
        return signupFragment;
    }
    public LoginFragment getLoginFragment() { return loginFragment; }

    public ResetPassFragment getResetPassFragment() { return resetPassFragment; }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMeBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_me,container,false);
        addFragment();
        showFragment(resetPassFragment);
        View view=fragmentMeBinding.getRoot();
        return view;
    }
    public void addFragment()
    {
        FragmentTransaction transaction=getChildFragmentManager().beginTransaction();
        transaction.add(R.id.ly_frame,signupFragment);
        transaction.add(R.id.ly_frame,loginFragment);
        transaction.add(R.id.ly_frame,resetPassFragment);
        transaction.commit();
    }
    public void showFragment(Fragment fmShow) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.hide(signupFragment);
        transaction.hide(loginFragment);
        transaction.hide(resetPassFragment);
        transaction.show(fmShow);
        transaction.commit();
    }

}
