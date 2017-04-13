package com.example.administrator.shopping.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.fragment.ShoppingcartFragment;

import java.util.List;

/**
 * Created by GX on 2017/4/13 0013.
 */

public class ShoppingcartAdapter extends BaseAdapter implements View.OnClickListener {
    LayoutInflater infater = null;
    Context context;
    List<String> cartlist;
    boolean[] isselect;//表示是否选中数组
    int[] moneylist;//表示价格数组
    int[] numberlist;//表示数量数组
    int total = 0, count = 0;//总价,件数
    int[] finish_isVisible;//是否显示完成页面
    int[] edit_isVisible;//是否显示编辑页面
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x01://表示全选
                    for (int i = 0; i < cartlist.size(); i++) {
                        isselect[i] = true;
                        notifyDataSetChanged();
                    }
                    break;
                case 0x02://表示全不选
                    for (int i = 0; i < cartlist.size(); i++) {
                        isselect[i] = false;
                        notifyDataSetChanged();
                    }
                    break;
                case 0x03://完成界面，进入编辑模式
                    for (int i = 0; i < cartlist.size(); i++) {
                        numberlist[i] = i;
                        isselect[i] = false;
                        finish_isVisible[i]=View.VISIBLE;
                        edit_isVisible[i]=View.GONE;
                    }
                    notifyDataSetChanged();
                    break;
                case 0x04://编辑界面，进入完成界面（操作完成）
                    for (int i = 0; i < cartlist.size(); i++) {
                        numberlist[i] = i;
                        isselect[i] = false;
                        finish_isVisible[i]=View.GONE;
                        edit_isVisible[i]=View.VISIBLE;
                    }
                    notifyDataSetChanged();
                    break;
            }
        }
    };

    public ShoppingcartAdapter(Context context, List<String> cartlist) {
        this.context = context;
        isselect = new boolean[cartlist.size()];
        moneylist = new int[cartlist.size()];
        numberlist = new int[cartlist.size()];
        finish_isVisible=new int[cartlist.size()];
        edit_isVisible=new int[cartlist.size()];
        for (int i = 0; i < cartlist.size(); i++) {
            numberlist[i] = i;
            isselect[i] = false;
            finish_isVisible[i]=View.GONE;
            edit_isVisible[i]=View.VISIBLE;
        }
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
            view = infater.inflate(R.layout.item_shoppingcart, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertview;
            holder = (ViewHolder) convertview.getTag();
        }
        holder.numbers.setText(numberlist[position] + "");
        holder.check_cart.setChecked(isselect[position]);
        holder.liner_edit.setVisibility(edit_isVisible[position]);
        holder.liner_finish.setVisibility(finish_isVisible[position]);
        holder.reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = 0;
                count = 0;
                for (int i = 0; i < cartlist.size(); i++) {
                    if (numberlist[i] > 0) {
                        if (position == i) {
                            numberlist[position]--;
                            notifyDataSetChanged();
                        }
                    }
                    if (isselect[i]) {
                        total = total + 30 * numberlist[i];
                        count+=numberlist[i];
                    }
                }
                Message message =ShoppingcartFragment.handler.obtainMessage();
                message.what = 0x01;
                Bundle bundle = new Bundle();
                bundle.putString("total", String.valueOf(total));
                bundle.putString("count", String.valueOf(count));
                message.setData(bundle);
                ShoppingcartFragment.handler.sendMessage(message);
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = 0;
                count = 0;
                for (int i = 0; i < cartlist.size(); i++) {
                    if (position == i) {
                        numberlist[position]++;
                        notifyDataSetChanged();
                    }
                    if (isselect[i]) {
                        count+=numberlist[i];
                        total = total + 30 * numberlist[i];
                    }
                }
                Message message = ShoppingcartFragment.handler.obtainMessage();
                message.what = 0x01;
                Bundle bundle = new Bundle();
                bundle.putString("total", String.valueOf(total));
                bundle.putString("count", String.valueOf(count));
                message.setData(bundle);
                ShoppingcartFragment.handler.sendMessage(message);

            }
        });
        holder.check_cart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                total = 0;
                count = 0;
                for (int i = 0; i < cartlist.size(); i++) {
                    if (position == i) {
                        isselect[position] = isChecked;
                    }
                    if (isselect[i]) {
                        count+=numberlist[i];
                        total = total + 30 * numberlist[i];
                    }

                }
                Message message =ShoppingcartFragment.handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString("total", String.valueOf(total));
                bundle.putString("count", String.valueOf(count));
                message.setData(bundle);
                message.what = 0x01;
                ShoppingcartFragment.handler.sendMessage(message);

            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    class ViewHolder {
        LinearLayout dot_selector,liner_edit,liner_finish;
        CheckBox check_cart;
        TextView numbers;
        TextView money;
        ImageView reduce, add;

        public ViewHolder(View view) {
            this.dot_selector = (LinearLayout) view.findViewById(R.id.dot_selector);
            this.check_cart = (CheckBox) view.findViewById(R.id.check_cart);
            this.numbers = (TextView) view.findViewById(R.id.one);
            this.reduce = (ImageView) view.findViewById(R.id.reduce);
            this.add = (ImageView) view.findViewById(R.id.plus);
            this.money = (TextView) view.findViewById(R.id.money);
            this.liner_finish=(LinearLayout)view.findViewById(R.id.liner_finish);
            this.liner_edit=(LinearLayout)view.findViewById(R.id.liner_edit);
        }
    }

}