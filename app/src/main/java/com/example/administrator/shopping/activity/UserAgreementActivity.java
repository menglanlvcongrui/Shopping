package com.example.administrator.shopping.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.shopping.R;

public class UserAgreementActivity extends AppCompatActivity implements View.OnClickListener {
     private ImageView iv_user_agreement_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_agreement);
        iv_user_agreement_back= (ImageView) findViewById(R.id.iv_user_agreement_back);
        iv_user_agreement_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      finish();
    }
}
