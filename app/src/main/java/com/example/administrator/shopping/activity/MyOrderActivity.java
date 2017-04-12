package com.example.administrator.shopping.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.adapter.MyOrderAdapter;
import com.example.administrator.shopping.fragment.MyOrderAllFragment;
import com.example.administrator.shopping.fragment.MyOrderObligationFragment;
import com.example.administrator.shopping.fragment.MyOrderPendingFragment;
import com.example.administrator.shopping.fragment.MyOrderWaitGainFragment;

import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends AppCompatActivity implements View.OnClickListener {
   private ImageView iv_my_order_back;
    private TabLayout tabs;
    private ViewPager viewPager;
    private List<String> mTitle = new ArrayList<String>();
    private List<Fragment> mFragment = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_order);
        iv_my_order_back= (ImageView) findViewById(R.id.iv_my_order_back);
        iv_my_order_back.setOnClickListener(this);
        initView();
        MyOrderAdapter adapter = new MyOrderAdapter(getSupportFragmentManager(), mTitle, mFragment);
        viewPager.setAdapter(adapter);
        //为TabLayout设置ViewPager
        tabs.setupWithViewPager(viewPager);
        // 使用ViewPager的适配器
        tabs.setTabsFromPagerAdapter(adapter);
        //tabs.setSelectedTabIndicatorColor(Color.BLUE);//下划线的颜色
        tabs.setSelectedTabIndicatorHeight(2);//下划线的高度
        //tabs.setTabTextColors(Color.BLACK, Color.BLUE);//不选时是红色，选中时是蓝色
    }
    // 初始化V
    private void initView() {
        tabs = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mTitle.add("全部");
        mTitle.add("待付款");
        mTitle.add("待发货");
        mTitle.add("待收货");
        mFragment.add(new MyOrderAllFragment());
        mFragment.add(new MyOrderObligationFragment());
        mFragment.add(new MyOrderPendingFragment());
        mFragment.add(new MyOrderWaitGainFragment());
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
