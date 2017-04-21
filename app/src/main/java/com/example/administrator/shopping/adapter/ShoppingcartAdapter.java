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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.bean.CartBean;
import com.example.administrator.shopping.fragment.ShoppingcartFragment;

import java.util.List;

/**
 * Created by GX on 2017/4/13 0013.
 */

public class ShoppingcartAdapter extends BaseAdapter implements View.OnClickListener {
    LayoutInflater infater = null;
    Context context;
    //    List<String> cartlist;
    List<CartBean> cartlist;
    boolean[] isselect;//表示是否选中数组
    int total = 0, count = 0;//总价,件数
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x01://表示全选
                    count = 0;
                    total = 0;
                    for (int i = 0; i < cartlist.size(); i++) {
//                        isselect[i] = true;
                        cartlist.get(i).setIsselect(true);
                        if (cartlist.get(i).isselect()) {
                            count += cartlist.get(i).getNumberlist();
                            total = total + cartlist.get(i).getMoneylist() * cartlist.get(i).getNumberlist();
                        }
                    }
                    Message message1 = ShoppingcartFragment.handler.obtainMessage();
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("total", String.valueOf(total));
                    bundle1.putString("count", String.valueOf(count));
                    message1.setData(bundle1);
                    message1.what = 0x01;
                    ShoppingcartFragment.handler.sendMessage(message1);
                    notifyDataSetChanged();
                    break;
                case 0x02://表示全不选
                    for (int i = 0; i < cartlist.size(); i++) {
//                        isselect[i] = false;
                        cartlist.get(i).setIsselect(false);
                    }
                    notifyDataSetChanged();
                    break;
                case 0x03://完成界面，进入编辑模式
                    for (int i = 0; i < cartlist.size(); i++) {
                        cartlist.get(i).setFinish_isVisible(View.VISIBLE);
                        cartlist.get(i).setEdit_isVisible(View.GONE);
//                        finish_isVisible[i] = View.VISIBLE;
//                        edit_isVisible[i] = View.GONE;
                    }
                    for (int i = 0; i < cartlist.size(); i++) {
//                        finishlist = numberlist;
                        cartlist.get(i).setFinishlist(cartlist.get(i).getNumberlist());
                    }

                    notifyDataSetInvalidated();
                    break;
                case 0x04://编辑界面，进入完成界面（操作完成）
                    for (int i = 0; i < cartlist.size(); i++) {
                        cartlist.get(i).setFinish_isVisible(View.GONE);
                        cartlist.get(i).setEdit_isVisible(View.VISIBLE);
                    }
                    for (int i = 0; i < cartlist.size(); i++) {
//                         numberlist= finishlist;
                        cartlist.get(i).setNumberlist(cartlist.get(i).getFinishlist());
                    }
                    notifyDataSetInvalidated();
                    break;
                case 0x05://删除item（购物车）
                    for (int i = 0; i < cartlist.size(); i++) {
                        cartlist.get(i).setFinish_isVisible(View.GONE);
                        cartlist.get(i).setEdit_isVisible(View.VISIBLE);
                    }
                    for (int i = 0; i < cartlist.size(); i++) {
//                         numberlist= finishlist;
                        cartlist.get(i).setNumberlist(cartlist.get(i).getFinishlist());
                    }
                    notifyDataSetInvalidated();
                    break;
                case 0x06://item 点击事件处理
                    if (cartlist.get(Integer.valueOf(msg.obj.toString())).isselect()) {
                        cartlist.get(Integer.valueOf(msg.obj.toString())).setIsselect(false);
                    } else {
                        cartlist.get(Integer.valueOf(msg.obj.toString())).setIsselect(true);
                    }
                    /**
                     * 价格/件数增加
                     * */
                    total = 0;
                    count = 0;
                    for (int i = 0; i < cartlist.size(); i++) {
                        if (cartlist.get(i).isselect()) {
                            count += cartlist.get(i).getNumberlist();
                            total = total + cartlist.get(i).getMoneylist() * cartlist.get(i).getNumberlist();
                        }
                        isselect[i] = cartlist.get(i).isselect();
                    }

                    Message message = ShoppingcartFragment.handler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putString("total", String.valueOf(total));
                    bundle.putString("count", String.valueOf(count));
                    bundle.putBooleanArray("isselect", isselect);
                    message.setData(bundle);
                    message.what = 0x02;
                    ShoppingcartFragment.handler.sendMessage(message);
                    notifyDataSetChanged();
                    break;
            }
        }
    };

    public List<CartBean> getCartlist() {
        return cartlist;
    }

    public void setList(List<CartBean> cartlist) {
        isselect = new boolean[cartlist.size()];
        this.cartlist = cartlist;
    }

    public ShoppingcartAdapter(Context context, List<CartBean> cartlist) {
        this.context = context;
        this.cartlist = cartlist;
        isselect = new boolean[cartlist.size()];
        infater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        CartBean cartBean = cartlist.get(position);
        if (cartBean.getEdit_isVisible() == View.GONE) {
            holder.liner_edit.setVisibility(View.GONE);
        } else {
            holder.liner_edit.setVisibility(View.VISIBLE);
        }
        if (cartBean.getFinish_isVisible() == View.GONE) {
            holder.liner_finish.setVisibility(View.GONE);
        } else {
            holder.liner_finish.setVisibility(View.VISIBLE);
        }
        holder.numbers.setText(cartBean.getNumberlist() + "");
        holder.number.setText(cartBean.getFinishlist() + "");
        holder.check_cart.setChecked(cartBean.isselect());
        holder.finish_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < cartlist.size(); i++) {
                    if (cartlist.get(i).getFinishlist() > 0) {
                        if (position == i) {
//                            finishlist[position]--;
                            cartlist.get(i).setFinishlist(cartlist.get(i).getFinishlist() - 1);
                            notifyDataSetChanged();
                        }
                    }
                }
            }
        });
        holder.finish_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < cartlist.size(); i++) {
                    if (position == i) {
                        cartlist.get(i).setFinishlist(cartlist.get(i).getFinishlist() + 1);
//                        finishlist[position]++;
                        notifyDataSetChanged();
                    }
                }

            }
        });


        holder.reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = 0;
                count = 0;
                for (int i = 0; i < cartlist.size(); i++) {
                    if (cartlist.get(i).getNumberlist() > 0) {
                        if (position == i) {
//                            numberlist[position]--;
                            cartlist.get(i).setNumberlist(cartlist.get(i).getNumberlist() - 1);
                            notifyDataSetChanged();
                        }
                    }
                    if (cartlist.get(i).isselect()) {
                        total = total + cartlist.get(i).getMoneylist() * cartlist.get(i).getNumberlist();
                        count += cartlist.get(i).getNumberlist();
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
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = 0;
                count = 0;
                for (int i = 0; i < cartlist.size(); i++) {
                    if (position == i) {
//                        numberlist[position]++;
                        cartlist.get(i).setNumberlist(cartlist.get(i).getNumberlist() + 1);
                        notifyDataSetChanged();
                    }
                    if (cartlist.get(i).isselect()) {
                        count += cartlist.get(i).getNumberlist();
                        total = total + cartlist.get(i).getMoneylist() * cartlist.get(i).getNumberlist();
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
                if (!buttonView.isPressed()) return;//设置点击才会执行，否则不执行
                total = 0;
                count = 0;
                for (int i = 0; i < cartlist.size(); i++) {
                    if (position == i) {
                        cartlist.get(position).setIsselect(isChecked);
//                        isselect[position] = isChecked;
                    }
                    if (cartlist.get(i).isselect()) {
                        count += cartlist.get(i).getNumberlist();
                        total = total + cartlist.get(i).getMoneylist() * cartlist.get(i).getNumberlist();
                    }
                    isselect[i] = cartlist.get(i).isselect();
                }

                Message message = ShoppingcartFragment.handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString("total", String.valueOf(total));
                bundle.putString("count", String.valueOf(count));
                bundle.putBooleanArray("isselect", isselect);
                message.setData(bundle);
                message.what = 0x02;
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
        LinearLayout liner_edit, liner_finish;//编辑页面布局/完成页面布局
        CheckBox check_cart;//选择
        TextView numbers, number;//数量
        LinearLayout reduce, add;//1.编辑界面增加/减少按钮
        LinearLayout finish_reduce, finish_add;//2.完成界面增加/减少按钮
        LinearLayout itempayment;

        public ViewHolder(View view) {
            this.check_cart = (CheckBox) view.findViewById(R.id.check_cart);
            this.numbers = (TextView) view.findViewById(R.id.one);
            this.number = (TextView) view.findViewById(R.id.numbers);
            this.reduce = (LinearLayout) view.findViewById(R.id.reduce);
            this.add = (LinearLayout) view.findViewById(R.id.plus);
            this.liner_finish = (LinearLayout) view.findViewById(R.id.liner_finish);
            this.liner_edit = (LinearLayout) view.findViewById(R.id.liner_edit);
            this.finish_reduce = (LinearLayout) view.findViewById(R.id.finish_reduce);
            this.finish_add = (LinearLayout) view.findViewById(R.id.finish_add);
            this.itempayment = (LinearLayout) view.findViewById(R.id.itempayment);
        }
    }

}