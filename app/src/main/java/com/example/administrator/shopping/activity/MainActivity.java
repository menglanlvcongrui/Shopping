package com.example.administrator.shopping.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.fragment.CircleFragment;
import com.example.administrator.shopping.fragment.HomeFragment;
import com.example.administrator.shopping.fragment.PersonalcenterFragment;
import com.example.administrator.shopping.fragment.ShoppingcartFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rgTabs;
    private List<Fragment> fragmentList;
    private int currentIndex = 0;
    private int previousIndex = 0;

    //  public TextView tv;
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
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_main);
        rgTabs = (RadioGroup) findViewById(R.id.rg_tabs);
        // tv= (TextView) findViewById(R.id.tv);
        //初始化数据源
        initData();
        // setTranslucentStatus();
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            showFragmentByPosition(currentIndex, previousIndex);
            ((RadioButton) rgTabs.getChildAt(currentIndex)).setChecked(true);
//        监听的时候动态切换
            rgTabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton rb = (RadioButton) group.findViewById(checkedId);
                    currentIndex = group.indexOfChild(rb);
                    showFragmentByPosition(currentIndex, previousIndex);
                    previousIndex = currentIndex;
                }
            });
        } else {
            //点击购物车图标，跳转到购物车碎片
            currentIndex = 2;
            previousIndex = 2;
            showFragmentByPosition(currentIndex, previousIndex);
            ((RadioButton) rgTabs.getChildAt(currentIndex)).setChecked(true);
            rgTabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton rb = (RadioButton) group.findViewById(checkedId);
                    currentIndex = group.indexOfChild(rb);
                    showFragmentByPosition(currentIndex, previousIndex);
                    previousIndex = currentIndex;
                }
            });
        }
    }

    /**
     * 动态显示需要显示的碎片,隐藏不需要现实的碎片
     */
    public void showFragmentByPosition(int currentIndex, int previousIndex) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fshow = fragmentList.get(currentIndex);
        Fragment fHide = fragmentList.get(previousIndex);
        //判断显示的碎片是否添加
        if (!fshow.isAdded()) {
            ft.add(R.id.main_container, fshow);
        }
        if (currentIndex == previousIndex) {
            ft.show(fshow).commit();
            return;
        }
        //不一样的情况(显示该显示的,隐藏该隐藏的)
        ft.show(fshow).hide(fHide).commit();
    }

    private void initData() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new CircleFragment());
        fragmentList.add(new ShoppingcartFragment());
        fragmentList.add(new PersonalcenterFragment());
    }

    private void setTranslucentStatus() {
        // 5.0以上系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 4.4系统状态栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}
