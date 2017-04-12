package com.example.administrator.shopping.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.shopping.R;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ImageView iv_forget_password_backss= (ImageView) findViewById(R.id.iv_forget_password_backss);
        iv_forget_password_backss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ForgetPasswordActivity.this,LoginsActivity.class);
                startActivity(intent);
            }
        });
    }

}
