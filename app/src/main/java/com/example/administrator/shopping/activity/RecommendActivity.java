package com.example.administrator.shopping.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.fragment.RecommendHighFragment;
import com.example.administrator.shopping.fragment.RecommendLowFragment;
import com.example.administrator.shopping.utils.GetstatusBarHeight;

public class RecommendActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private RadioGroup radioGroup;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //是通知栏的颜色和头部颜色保持一致
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_recommend);
        GetstatusBarHeight barHeight=new GetstatusBarHeight(this,RecommendActivity.this);
        linearLayout=(LinearLayout)findViewById(R.id.content);
        radioGroup= (RadioGroup)findViewById(R.id.rg);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.content, new RecommendLowFragment());
        ft.commit();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ft = fm.beginTransaction();
                switch(checkedId){
                    case R.id.button:
                        ft.replace(R.id.content, new RecommendLowFragment());
                        break;
                    case R.id.button2:
                        ft.replace(R.id.content, new RecommendHighFragment());
                        break;
                    default:
                        break;
                }
                ft.commit();
            }
        });
        //返回碎片中
       ImageView iv_recommend_more_back= (ImageView) findViewById(R.id.iv_recommend_more_back);
        iv_recommend_more_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
