package com.example.administrator.shopping.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.shopping.R;

public class LoginsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins);
        TextView tv_forget_pasword= (TextView) findViewById(R.id.tv_forget_password);
        TextView tv_signin= (TextView) findViewById(R.id.tv_signin);
        tv_forget_pasword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LoginsActivity.this,ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
        tv_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LoginsActivity.this,SigninActivity.class);
                startActivity(intent);
            }
        });

    }
    public void onIvLoginsClick(View v) {
        Intent intent1=new Intent();
        intent1.setClass(LoginsActivity.this,LoginActivity.class);
        startActivity(intent1);
    }
}
