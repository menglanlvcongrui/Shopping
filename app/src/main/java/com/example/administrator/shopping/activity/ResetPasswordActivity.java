package com.example.administrator.shopping.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.utils.GetstatusBarHeight;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView rl_reset_password_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //是通知栏的颜色和头部颜色保持一致
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_reset_password);
        GetstatusBarHeight  barHeight=new GetstatusBarHeight(this,ResetPasswordActivity.this);
        rl_reset_password_back= (ImageView)findViewById(R.id.iv_reset_password_back);
        rl_reset_password_back.setOnClickListener(this);
        TextView textView= (TextView) findViewById(R.id.tv_forget_passwordss);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ResetPasswordActivity.this,ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
