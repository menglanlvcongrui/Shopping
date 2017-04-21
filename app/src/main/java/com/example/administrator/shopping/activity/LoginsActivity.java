package com.example.administrator.shopping.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
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
        ImageView iv_loginss= (ImageView) findViewById(R.id.iv_loginss);
      TextView tv_logins=(TextView)findViewById(R.id.tv_logins);
        tv_logins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent();
                intent4.setClass(LoginsActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent4);
            }
        });

        iv_loginss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent();
               //startActivity的时候传递FLAG_ACTIVITY_CLEAR_TASK这个标志,那么这个标志将会清除之前所有已经打开的activity,只剩下跳转后的当前页.然后将会变成另外一个空栈的root,然后其他的Activitys就都被关闭了
                intent1.setClass(LoginsActivity.this,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
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
                intent3.setClass(LoginsActivity.this,SigninActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent3);
            }
        });


    }
    /*public void onIvLoginsClick(View v) {
        Intent intent1=new Intent();
        intent1.setClass(LoginsActivity.this,LoginActivity.class);
        startActivity(intent1);
    }*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            //TODO something
//            Intent startMain = new Intent(Intent.ACTION_MAIN);
//            startMain.addCategory(Intent.CATEGORY_HOME);
//            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(startMain);
            Intent intent1=new Intent();
            intent1.setClass(LoginsActivity.this,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
            return true;
        }
        return super.onKeyDown(keyCode, event);
        // return  false;
    }
}
