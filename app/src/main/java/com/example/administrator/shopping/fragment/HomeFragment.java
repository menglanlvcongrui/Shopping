package com.example.administrator.shopping.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.activity.GoodsDetailActivity;
import com.example.administrator.shopping.activity.HomeTheLatestGoodsActivity;
import com.example.administrator.shopping.activity.HomeTheLatestLookActivity;
import com.example.administrator.shopping.activity.RecommendActivity;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements View.OnClickListener{

    int[] imgs = {
            R.mipmap.circle_skirt,
            R.mipmap.circle_details_picture,
            R.mipmap.circle_display_picture,
            R.mipmap.details_picture,
    };
    private LinearLayout containBottom;
    private Runnable r;
    private boolean flag = false;
    ViewPager pager;
    ArrayList<ImageView> arraylist = new ArrayList<ImageView>();
    android.os.Handler handler = new android.os.Handler();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pager = (ViewPager)view.findViewById(R.id.pager);
        containBottom=(LinearLayout)view.findViewById(R.id.bottom_container);
        TextView tv_recommend_more= (TextView) view.findViewById(R.id.tv_recommend_more);
        TextView tv_the_latest_goods_more= (TextView) view.findViewById(R.id.tv_the_latest_goods_more);
        TextView  tv_the_latest_look_more= (TextView) view.findViewById(R.id.tv_the_latest_look_more);
        LinearLayout ll_goods_detail_one= (LinearLayout) view.findViewById(R.id.ll_goods_detail_one);
        LinearLayout ll_goods_detail_tow= (LinearLayout) view.findViewById(R.id.ll_goods_detail_tow);
        LinearLayout ll_goods_detail_three= (LinearLayout) view.findViewById(R.id.ll_goods_detail_three);
        LinearLayout ll_goods_detail_four= (LinearLayout) view.findViewById(R.id.ll_goods_detail_four);
        LinearLayout ll_goods_detail_five= (LinearLayout) view.findViewById(R.id.ll_goods_detail_five);
        LinearLayout ll_goods_detail_six= (LinearLayout) view.findViewById(R.id.ll_goods_detail_six);
        LinearLayout ll_goods_detail_seven= (LinearLayout) view.findViewById(R.id.ll_goods_detail_seven);
        LinearLayout ll_goods_detail_eight= (LinearLayout) view.findViewById(R.id.ll_goods_detail_eight);
        LinearLayout ll_goods_detail_nine= (LinearLayout) view.findViewById(R.id.ll_goods_detail_nine);
        LinearLayout ll_goods_detail_ten= (LinearLayout) view.findViewById(R.id.ll_goods_detail_ten);
        LinearLayout ll_goods_detail_eleven= (LinearLayout) view.findViewById(R.id.ll_goods_detail_eleven);
        LinearLayout ll_goods_detail_twenve= (LinearLayout) view.findViewById(R.id.ll_goods_detail_twenve);
        tv_recommend_more.setOnClickListener(this);
        tv_the_latest_goods_more.setOnClickListener(this);
        tv_the_latest_look_more.setOnClickListener(this);
        ll_goods_detail_one.setOnClickListener(this);
        ll_goods_detail_tow.setOnClickListener(this);
        ll_goods_detail_three.setOnClickListener(this);
        ll_goods_detail_four.setOnClickListener(this);
        ll_goods_detail_five.setOnClickListener(this);
        ll_goods_detail_six.setOnClickListener(this);
        ll_goods_detail_seven.setOnClickListener(this);
        ll_goods_detail_eight.setOnClickListener(this);
        ll_goods_detail_nine.setOnClickListener(this);
        ll_goods_detail_ten.setOnClickListener(this);
        ll_goods_detail_eleven.setOnClickListener(this);
        ll_goods_detail_twenve.setOnClickListener(this);

// initView();
        initData();
        initXiaoYuanDian();
        pager.setAdapter(new MyPagerAdapter());

//默认选中指定条目，即设置支持ViewPager初始显示时既可以向右滑动
        pager.setCurrentItem(200);

//让它自己动起来，实现广告播放
        r = new Runnable() {
            @Override
            public void run() {
                pager.setCurrentItem(pager.getCurrentItem() + 1);
                handler.postDelayed(this, 3000);//控制播放时间间隔3s
            }
        };
        handler.postDelayed(r, 3000);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //对小圆点进行设置
            @Override
            public void onPageSelected(int arg0) {
                for (int i = 0; i < arraylist.size(); i++) {
// 可以实现小圆点的切换
                    // 通过container容器和下标position,找到指定的ImageView,修改他的图片就可以了
                    ImageView iv = (ImageView) containBottom.getChildAt(i);
                    if(i == arg0%arraylist.size()){
                        iv.setImageResource(R.mipmap.dot_selected); //选中的图片
                    }else {
//其他ImageView都是默认的小圆点
                        iv.setImageResource(R.mipmap.dot);
                    }
                }
            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //强制停止图片的自动切换，也就是停止handler的调用
            @Override
            public void onPageScrollStateChanged(int arg0) {
                switch (arg0) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
//强制停止图片的自动切换，也就是停止handler的调用
                        handler.removeCallbacks(r);
                        flag = true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (flag) {
                            handler.postDelayed(r, 3000);
                            flag = false;
                        }
                        break;
                }
            }
        });
    }

    private void initData() {
        for (int i = 0; i < imgs.length; i++) {
            ImageView iv1 = new ImageView(getActivity());
            iv1.setImageResource(imgs[i]);
            iv1.setScaleType(ImageView.ScaleType.FIT_XY);
            arraylist.add(iv1);
        }
    }
    private void initXiaoYuanDian() {
        for (int i = 0; i < imgs.length; i++) {
            ImageView iv2 = new ImageView(getActivity());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            iv2.setLayoutParams(lp);
//设置图片源 (默认没有选中的图片,初始化时候第一张图片对应的小圆点是details_like_click_icon)
            if(i == 0){
//构建第一个ImageView的时候选用dot_0的图片
                iv2.setImageResource(R.mipmap.dot_selected);
            }else {
//构建后续ImageView使用details_like_icon的图片
                lp.setMargins(15,0,0,0);
                iv2.setImageResource(R.mipmap.dot);
            }
//添加到容器中
            containBottom.addView(iv2);
        }
    }
//推荐，最新商品，最近浏览的展开
    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch(v.getId()){
            case R.id.tv_recommend_more:
                intent.setClass(getActivity(), RecommendActivity.class);
                break;
            case R.id.tv_the_latest_goods_more:
                //intent.setClass(getActivity(), HomeTheLatestGoodsActivity.class);
                intent.setClass(getActivity(), RecommendActivity.class);
                break;
            case R.id.tv_the_latest_look_more:
               // intent.setClass(getActivity(), HomeTheLatestLookActivity.class);
                intent.setClass(getActivity(), RecommendActivity.class);
                break;
            case R.id.ll_goods_detail_one:
                intent.setClass(getActivity(), GoodsDetailActivity.class);
                break;
            case R.id.ll_goods_detail_tow:
                intent.setClass(getActivity(), GoodsDetailActivity.class);
                break;
            case R.id.ll_goods_detail_three:
                intent.setClass(getActivity(), GoodsDetailActivity.class);
                break;
            case R.id.ll_goods_detail_four:
                intent.setClass(getActivity(), GoodsDetailActivity.class);
                break;
            case R.id.ll_goods_detail_five:
                intent.setClass(getActivity(), GoodsDetailActivity.class);
                break;
            case R.id.ll_goods_detail_six:
                intent.setClass(getActivity(), GoodsDetailActivity.class);
                break;
            case R.id.ll_goods_detail_seven:
                intent.setClass(getActivity(), GoodsDetailActivity.class);
                break;
            case R.id.ll_goods_detail_eight:
                intent.setClass(getActivity(), GoodsDetailActivity.class);
                break;
            case R.id.ll_goods_detail_nine:
                intent.setClass(getActivity(), GoodsDetailActivity.class);
                break;
            case R.id.ll_goods_detail_ten:
                intent.setClass(getActivity(), GoodsDetailActivity.class);
                break;
            case R.id.ll_goods_detail_eleven:
                intent.setClass(getActivity(), GoodsDetailActivity.class);
                break;
            case R.id.ll_goods_detail_twenve:
                intent.setClass(getActivity(), GoodsDetailActivity.class);
                break;
        }
        startActivity(intent);
    }
//viewpager的适配器
    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {

            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }

        //对图片进行监听
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            View v = arraylist.get(position %arraylist.size());
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "图片被监听了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
