package com.example.administrator.shopping.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import com.example.administrator.shopping.R;
import com.example.administrator.shopping.adapter.RecommendMoreAdapter;
import com.example.administrator.shopping.bean.CircleBean;
import com.example.administrator.shopping.utils.OkHttpUtils;

import java.util.ArrayList;

public class RecommendActivity extends AppCompatActivity {
    private RecyclerView rcv;
    private ArrayList<CircleBean.DataBean> list = new ArrayList<>();
    private RecommendMoreAdapter adapter;

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
        rcv = (RecyclerView) findViewById(R.id.rcv01);
        //返回碎片中
       ImageView iv_recommend_more_back= (ImageView) findViewById(R.id.iv_recommend_more_back);
        iv_recommend_more_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
//初始化数据
        initDatas();
//设置适配器
        // adapter = new MyOrderAllAdapter(getContext(),list);
        adapter = new RecommendMoreAdapter(this, list);
        rcv.setAdapter(adapter);
    }

    private void initDatas() {
//使用异步获取字符串
        OkHttpUtils.getInstance().getStringAsync(this, "http://a.wowozhe.com/home/m?target=android&v=291&act=discovery_menu&json=", CircleBean.class, new OkHttpUtils.DataCallback<CircleBean>() {
//当数据下载完成后自动回到此方法
            //该方法中的所有代码运行在主线程中

            @Override
            public void getData(CircleBean fenLeiBean) {
                list.addAll(fenLeiBean.getData());
                adapter.notifyDataSetChanged();
            }
        });
    }

    //对RecyclerView进行设置
    private void initView() {
        rcv.setLayoutManager(new LinearLayoutManager(this));
        // rcv.setLayoutManager(new LinearLayoutManager(getContext()));
//  rcv.setLayoutManager(new GridLayoutManager(getActivity(),2));//效果是网格的,3是3列
        // rcv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,true));//倒序排列
    }
}
