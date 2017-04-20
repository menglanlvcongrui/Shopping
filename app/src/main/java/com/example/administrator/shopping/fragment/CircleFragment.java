package com.example.administrator.shopping.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.activity.CostumeCircleActivity;
import com.example.administrator.shopping.utils.GetstatusBarHeight;


public class CircleFragment extends Fragment {
    /*private RecyclerView rcv;
    private ArrayList<CircleBean.DataBean> list = new ArrayList<>();
    private CircleAdapter adapter;
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_circle, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GetstatusBarHeight barHeight = new GetstatusBarHeight(getActivity(), view);
       /* rcv = (RecyclerView)view.findViewById(R.id.rcv);
        initView();
//初始化数据
        initDatas();
//设置适配器
        adapter = new CircleAdapter(getContext(),list);
        rcv.setAdapter(adapter);*/
  //  }
  /* = private void initDatas() {
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
    }*/




    //同购圈页面写死
     LinearLayout ll1 = (LinearLayout) view.findViewById(R.id.ll1);
        LinearLayout ll2 = (LinearLayout) view.findViewById(R.id.ll2);
        LinearLayout ll3 = (LinearLayout) view.findViewById(R.id.ll3);
        LinearLayout ll4 = (LinearLayout) view.findViewById(R.id.ll4);
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), CostumeCircleActivity.class);
                startActivity(intent);
            }
        });
        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent();
                intent1.setClass(getContext(), CostumeCircleActivity.class);
                startActivity(intent1);
            }
        });
        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2=new Intent();
                intent2.setClass(getContext(), CostumeCircleActivity.class);
                startActivity(intent2);
            }
        });
        ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent3=new Intent();
                intent3.setClass(getContext(), CostumeCircleActivity.class);
                startActivity(intent3);
            }
        });

}
}
