package com.example.administrator.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.shopping.R;

import java.util.List;

/**
 * Created by GX on 2017/4/13 0013.
 */

public class PaymentcartAdapter extends BaseAdapter implements View.OnClickListener {
    LayoutInflater infater = null;
    Context context;
    List<String> cartlist;

    public PaymentcartAdapter(Context context, List<String> cartlist) {
        this.context = context;
        infater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.cartlist = cartlist;

    }


    @Override
    public int getCount() {

        return cartlist.size();
    }

    @Override
    public Object getItem(int position) {
        return cartlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHolder holder = null;

    @Override
    public View getView(final int position, View convertview, ViewGroup arg2) {
        View view = null;
        if (convertview == null || convertview.getTag() == null) {
            view = infater.inflate(R.layout.item_shopping_payment, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertview;
            holder = (ViewHolder) convertview.getTag();
        }


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    class ViewHolder {
        LinearLayout liner_edit, liner_finish;//编辑页面布局/完成页面布局
        CheckBox check_cart;//选择
        TextView numbers, number;//数量
        ImageView reduce, add, finish_reduce, finish_add;//1.编辑界面增加/减少按钮  2.完成界面增加/减少按钮

        public ViewHolder(View view) {
//            this.check_cart = (CheckBox) view.findViewById(R.id.check_cart);
//            this.numbers = (TextView) view.findViewById(R.id.one);
//            this.number = (TextView) view.findViewById(R.id.numbers);
//            this.reduce = (ImageView) view.findViewById(R.id.reduce);
//            this.add = (ImageView) view.findViewById(R.id.plus);
//            this.liner_finish = (LinearLayout) view.findViewById(R.id.liner_finish);
//            this.liner_edit = (LinearLayout) view.findViewById(R.id.liner_edit);
//            this.finish_reduce = (ImageView) view.findViewById(R.id.finish_reduce);
//            this.finish_add = (ImageView) view.findViewById(R.id.finish_add);
        }
    }

}