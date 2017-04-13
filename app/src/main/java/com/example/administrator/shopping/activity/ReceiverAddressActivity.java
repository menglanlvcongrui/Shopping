package com.example.administrator.shopping.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.adapter.MyOrderPendingAdapter;
import com.example.administrator.shopping.adapter.ReceiverAddressAdapter;
import com.example.administrator.shopping.bean.CircleBean;
import com.example.administrator.shopping.utils.OkHttpUtils;

import java.util.ArrayList;

public class ReceiverAddressActivity extends AppCompatActivity implements View.OnClickListener {
     private ImageView iv_receiver_address_back;

    private RecyclerView rcv;
    private ArrayList<CircleBean.DataBean> list = new ArrayList<>();
    private ReceiverAddressAdapter adapter;
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
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_receiver_address);
        iv_receiver_address_back= (ImageView) findViewById(R.id.iv_receiver_address_back);
        iv_receiver_address_back.setOnClickListener(this);
        rcv = (RecyclerView)findViewById(R.id.rcv);
        initView();
//初始化数据
        initDatas();
//设置适配器
        adapter = new ReceiverAddressAdapter(this, list);
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
//  rcv.setLayoutManager(new GridLayoutManager(getActivity(),2));//效果是网格的,3是3列
        // rcv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,true));//倒序排列
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
