package com.example.administrator.shopping.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.utils.GetstatusBarHeight;

public class UserAgreementActivity extends AppCompatActivity implements View.OnClickListener {
     private ImageView iv_user_agreement_back;
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
        setContentView(R.layout.activity_user_agreement);
        GetstatusBarHeight barHeight = new GetstatusBarHeight(this, UserAgreementActivity.this);
        iv_user_agreement_back= (ImageView) findViewById(R.id.iv_user_agreement_back);
        iv_user_agreement_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      finish();
    }
}
