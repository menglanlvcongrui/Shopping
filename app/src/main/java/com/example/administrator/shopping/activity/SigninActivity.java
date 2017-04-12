package com.example.administrator.shopping.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.shopping.R;

public class SigninActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        TextView tv_logins= (TextView) findViewById(R.id.tv_logins);
        ImageView iv_sinnin=(ImageView) findViewById(R.id.iv_sinnin);
         iv_sinnin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent1=new Intent();
                 intent1.setClass(SigninActivity.this,LoginsActivity.class);
                 startActivity(intent1);
             }
         });
        tv_logins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(SigninActivity.this,LoginsActivity.class);
                startActivity(intent);
            }
        });
    }
}
