package com.example.administrator.shopping.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.utils.GetstatusBarHeight;

public class GoodsDetailActivity extends AppCompatActivity {

    private FrameLayout payment;

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

        setContentView(R.layout.activity_goods_detail);
        GetstatusBarHeight barHeight=new GetstatusBarHeight(this,GoodsDetailActivity.this);
        payment=(FrameLayout)findViewById(R.id.payment);
payment.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(GoodsDetailActivity.this,MainActivity.class);
        //用Bundle携带数据
        Bundle bundle=new Bundle();
        //传递name参数为tinyphp
        bundle.putString("name", "shopping");
        intent.putExtras(bundle);
        startActivity(intent);
    }
});
    }
}
