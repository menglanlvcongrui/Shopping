package com.example.administrator.shopping.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.activity.ClearCacheActivity;
import com.example.administrator.shopping.activity.EscActivity;
import com.example.administrator.shopping.activity.LoginActivity;
import com.example.administrator.shopping.activity.LoginsActivity;
import com.example.administrator.shopping.activity.MyOrderActivity;
import com.example.administrator.shopping.activity.ReceiverAddressActivity;
import com.example.administrator.shopping.activity.ResetPasswordActivity;
import com.example.administrator.shopping.activity.UserAgreementActivity;

public class PersonalcenterFragment extends Fragment implements View.OnClickListener{
    private RelativeLayout my_order;
    private RelativeLayout goods_address;
    private RelativeLayout reset_password;
    private RelativeLayout user_agreement;
    private RelativeLayout clear_cache;
    private RelativeLayout esc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personalcenter, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         my_order= (RelativeLayout) view.findViewById(R.id.my_order);
         goods_address= (RelativeLayout) view.findViewById(R.id.goods_address);
         reset_password= (RelativeLayout) view.findViewById(R.id.reset_password);
         user_agreement= (RelativeLayout) view.findViewById(R.id.user_agreement);
         clear_cache= (RelativeLayout) view.findViewById(R.id.clear_cache);
         esc= (RelativeLayout) view.findViewById(R.id.esc);
         my_order.setOnClickListener(this);
         goods_address.setOnClickListener(this);
         reset_password.setOnClickListener(this);
         user_agreement.setOnClickListener(this);
         clear_cache.setOnClickListener(this);
         esc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch(v.getId()){
            case R.id.my_order:
                intent.setClass(getActivity(), MyOrderActivity.class);
                break;
            case R.id.goods_address:
                intent.setClass(getActivity(), ReceiverAddressActivity.class);
                break;
            case R.id.reset_password:
                intent.setClass(getActivity(), ResetPasswordActivity.class);
                break;
            case R.id.user_agreement:
                intent.setClass(getActivity(), UserAgreementActivity.class);
                break;
            case R.id.clear_cache:
                intent.setClass(getActivity(), ClearCacheActivity.class);
                break;
            case R.id.esc:
                intent.setClass(getActivity(), LoginActivity.class);
                break;
        }
        startActivity(intent);

    }
}
