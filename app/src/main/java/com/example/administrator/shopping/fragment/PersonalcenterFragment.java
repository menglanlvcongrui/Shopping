package com.example.administrator.shopping.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.shopping.Dialog.CleanDialog;
import com.example.administrator.shopping.Dialog.ExitDialog;
import com.example.administrator.shopping.R;
import com.example.administrator.shopping.activity.LoginActivity;
import com.example.administrator.shopping.activity.MyOrderActivity;
import com.example.administrator.shopping.activity.ReceiverAddressActivity;
import com.example.administrator.shopping.activity.ResetPasswordActivity;
import com.example.administrator.shopping.activity.UserAgreementActivity;

public class PersonalcenterFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout my_order;
    private RelativeLayout  goods_address;
    private RelativeLayout  reset_password;
    private RelativeLayout  user_agreement;
    private RelativeLayout  clear_cache;
    private RelativeLayout  esc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personalcenter, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        my_order = (RelativeLayout) view.findViewById(R.id.my_order);
        goods_address = (RelativeLayout ) view.findViewById(R.id.goods_address);
        reset_password = (RelativeLayout ) view.findViewById(R.id.reset_password);
        user_agreement = (RelativeLayout ) view.findViewById(R.id.user_agreement);
        clear_cache = (RelativeLayout) view.findViewById(R.id.clear_cache);
        esc = (RelativeLayout ) view.findViewById(R.id.esc);
        my_order.setOnClickListener(this);
        goods_address.setOnClickListener(this);
        reset_password.setOnClickListener(this);
        user_agreement.setOnClickListener(this);
        clear_cache.setOnClickListener(this);
        esc.setOnClickListener(this);
    }

    CleanDialog cleanDialog;
    ExitDialog exitDialog;
    Intent intent=null;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_order:
                intent=new Intent(getActivity(), MyOrderActivity.class);
                break;
            case R.id.goods_address:
                intent=new Intent(getActivity(), ReceiverAddressActivity.class);
                break;
            case R.id.reset_password:
                intent=new Intent(getActivity(), ResetPasswordActivity.class);
                break;
            case R.id.user_agreement:
                intent=new Intent(getActivity(), UserAgreementActivity.class);
                break;
            case R.id.clear_cache:
                startCleanDialog();
                break;
            case R.id.esc:

                startExitDialog();
                break;

        }
        if (intent != null) {
            startActivity(intent);
            intent=null;
        }

    }
    //清除缓存弹出的dialog
    public void startCleanDialog() {

        cleanDialog = new CleanDialog(getActivity());//cleanDialog.setTitle("选择分享应用");
        cleanDialog.setYesOnclickListener("清除", new CleanDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                cleanDialog.dismiss();
            }
        });

        cleanDialog.setNoOnclickListener("取消", new CleanDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                cleanDialog.dismiss();
            }
        });
        cleanDialog.show();
    }
    //退出账户弹出的dialog
    public void startExitDialog(){
        exitDialog = new ExitDialog(getActivity());

        exitDialog.setYesOnclickListener("确定", new ExitDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                exitDialog.dismiss();
                Intent intentexit=new Intent();
                intentexit.setClass(getContext(), LoginActivity.class);
                startActivity(intentexit);
            }
        });

        exitDialog.setNoOnclickListener("取消", new ExitDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                exitDialog.dismiss();
            }
        });
        exitDialog.show();
    }
}
