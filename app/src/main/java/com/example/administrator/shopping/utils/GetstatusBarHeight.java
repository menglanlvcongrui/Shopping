package com.example.administrator.shopping.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.administrator.shopping.R;

import java.lang.reflect.Field;


/**
 * Created by GX on 2017/4/20 0020.
 */

public class GetstatusBarHeight {
    private final Context context;
    private RelativeLayout relative;
    public GetstatusBarHeight(Context context,View view){
        this.context=context;
        relative=(RelativeLayout)view.findViewById(R.id.relative);
        relative.setMinimumHeight(getStatusBarHeight());
    }
    public GetstatusBarHeight(Context context,Activity view){
        this.context=context;
        relative=(RelativeLayout)view.findViewById(R.id.relative);
        relative.setMinimumHeight(getStatusBarHeight());
    }
    public int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelOffset(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }
}
