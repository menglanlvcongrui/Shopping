package com.example.administrator.shopping.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.adapter.PaymentcartAdapter;
import com.example.administrator.shopping.utils.GetstatusBarHeight;

import java.util.ArrayList;
import java.util.List;

public class ShoppingcartPaymentActivity extends AppCompatActivity implements View.OnClickListener {
    ListView payment_list;
    List<String> list;
    PaymentcartAdapter paymentcartAdapter;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //是通知栏的颜色和头部颜色保持一致
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_shoppingcart_payment);
        GetstatusBarHeight barHeight=new GetstatusBarHeight(this,ShoppingcartPaymentActivity.this);
        payment_list = (ListView) findViewById(R.id.payment_list);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(i + "");
        }

        View header = View.inflate(this, R.layout.recipient_info,
                null);
        // 给listview添加头布局
        payment_list.addHeaderView(header);
        paymentcartAdapter = new PaymentcartAdapter(this, list);
        payment_list.setAdapter(paymentcartAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
