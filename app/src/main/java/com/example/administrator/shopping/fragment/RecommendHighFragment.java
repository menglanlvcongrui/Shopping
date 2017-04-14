package com.example.administrator.shopping.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.adapter.RecommendHighAdapter;
import com.example.administrator.shopping.adapter.RecommendLowAdapter;
import com.example.administrator.shopping.bean.CircleBean;
import com.example.administrator.shopping.utils.OkHttpUtils;

import java.util.ArrayList;


/*public class RecommendHighFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend_high, container, false);
    }
}*/
public class  RecommendHighFragment extends Fragment {

    private RecyclerView rcv;
    private ArrayList<CircleBean.DataBean> list = new ArrayList<>();
    private RecommendHighAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend_high, container, false);
    }
    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv = (RecyclerView)view.findViewById(R.id.rcv);
        initView();
//初始化数据
        initDatas();
//设置适配器
        adapter = new RecommendHighAdapter(getContext(),list);
        rcv.setAdapter(adapter);
    }
    private void initDatas() {
//使用异步获取字符串
        OkHttpUtils.getInstance().getStringAsync(getActivity(),"http://a.wowozhe.com/home/m?target=android&v=291&act=discovery_menu&json=",CircleBean.class,new OkHttpUtils.DataCallback<CircleBean>() {
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

        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
//  rcv.setLayoutManager(new GridLayoutManager(getActivity(),2));//效果是网格的,3是3列
        // rcv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,true));//倒序排列
    }

}
