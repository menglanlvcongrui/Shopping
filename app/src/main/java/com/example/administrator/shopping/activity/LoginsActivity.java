package com.example.administrator.shopping.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.utils.GetstatusBarHeight;

public class LoginsActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_logins);
        GetstatusBarHeight barHeight=new GetstatusBarHeight(this,LoginsActivity.this);
        TextView tv_forget_pasword= (TextView) findViewById(R.id.tv_forget_password);
        TextView tv_signin= (TextView) findViewById(R.id.tv_signin);
        ImageView iv_logins= (ImageView) findViewById(R.id.iv_loginss);
        iv_logins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent();
                intent1.setClass(LoginsActivity.this,LoginActivity.class);
                startActivity(intent1);
            }
        });
        tv_forget_pasword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent();
                intent2.setClass(LoginsActivity.this,ForgetPasswordActivity.class);
                startActivity(intent2);
            }
        });
        tv_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent();
                intent3.setClass(LoginsActivity.this,SigninActivity.class);
                startActivity(intent3);
            }
        });


    }
    /*public void onIvLoginsClick(View v) {
        Intent intent1=new Intent();
        intent1.setClass(LoginsActivity.this,LoginActivity.class);
        startActivity(intent1);
    }*/
}
