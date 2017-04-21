package com.example.administrator.shopping.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.activity.ShoppingcartPaymentActivity;
import com.example.administrator.shopping.adapter.ShoppingcartAdapter;
import com.example.administrator.shopping.bean.CartBean;
import com.example.administrator.shopping.utils.GetstatusBarHeight;

import java.util.ArrayList;
import java.util.List;


public class ShoppingcartFragment extends Fragment implements View.OnClickListener ,AdapterView.OnItemClickListener{
    ShoppingcartAdapter shoppingcartAdapter;//购物车适配器
    static TextView text_price, text_piece;//价格、总价
    LinearLayout linear, linear_1;//需要隐藏的布局
    static List<CartBean> cartlist;//购物车数组类
//    List<String> cartlist;//购物车数组类
    TextView is_edit, payment;//编辑/完成  结算/编辑
    static CheckBox check_all;//全选按钮
    boolean isEdit = false;//编辑/完成标记
    static boolean isAll = false;//是否全选
    ListView list;//购物车列表
    private LinearLayout check_linear;//全选点击事件
    public static Handler handler = new Handler() {//hander处理总额，然后刷新FragmentUI
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x01://每次点击增加/减少件数/价格
                    text_price.setText(msg.getData().getString("total"));
                    text_piece.setText(msg.getData().getString("count"));
                    break;
                case 0x02://全选/非全选刷新 每次点击增加件数/价格
                    int j = 0;
                    for (int i = 0; i < cartlist.size(); i++)
                        if (!msg.getData().getBooleanArray("isselect")[i]) {
                            j++;
                        }
                    if (j == 0) {
                        check_all.setChecked(true);
                        isAll = true;
                    } else {
                        check_all.setChecked(false);
                        isAll = false;
                    }
                    text_price.setText(msg.getData().getString("total"));
                    text_piece.setText(msg.getData().getString("count"));
                    break;
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shoppingcart, container, false);
        initview(view);
        initdata();
        shoppingcartAdapter = new ShoppingcartAdapter(getActivity(), cartlist);
        GetstatusBarHeight barHeight = new GetstatusBarHeight(getActivity(), view);
        list.setAdapter(shoppingcartAdapter);
        check_all.setOnClickListener(this);
        is_edit.setOnClickListener(this);
        payment.setOnClickListener(this);
        check_linear.setOnClickListener(this);
        return view;
    }

    void initdata() {
        cartlist = new ArrayList<>();
        CartBean cartBean1 = new CartBean();
        CartBean cartBean2 = new CartBean();
        CartBean cartBean3 = new CartBean();
        CartBean cartBean4 = new CartBean();
        CartBean cartBean5 = new CartBean();
        CartBean cartBean6 = new CartBean();
            cartBean1.setEdit_isVisible(View.VISIBLE);
            cartBean1.setMoneylist(30);
            cartBean1.setIsselect(false);
            cartBean1.setNumberlist(0);
            cartBean1.setFinishlist(0);
            cartBean1.setFinish_isVisible(View.GONE);

        cartBean2.setEdit_isVisible(View.VISIBLE);
        cartBean2.setMoneylist(30);
        cartBean2.setIsselect(false);
        cartBean2.setNumberlist(1);
        cartBean2.setFinishlist(1);
        cartBean2.setFinish_isVisible(View.GONE);

        cartBean3.setEdit_isVisible(View.VISIBLE);
        cartBean3.setMoneylist(30);
        cartBean3.setIsselect(false);
        cartBean3.setNumberlist(2);
        cartBean3.setFinishlist(2);
        cartBean3.setFinish_isVisible(View.GONE);

        cartBean4.setEdit_isVisible(View.VISIBLE);
        cartBean4.setMoneylist(30);
        cartBean4.setIsselect(false);
        cartBean4.setNumberlist(3);
        cartBean4.setFinishlist(3);
        cartBean4.setFinish_isVisible(View.GONE);

        cartBean5.setEdit_isVisible(View.VISIBLE);
        cartBean5.setMoneylist(30);
        cartBean5.setIsselect(false);
        cartBean5.setNumberlist(4);
        cartBean5.setFinishlist(4);
        cartBean5.setFinish_isVisible(View.GONE);

        cartBean6.setEdit_isVisible(View.VISIBLE);
        cartBean6.setMoneylist(30);
        cartBean6.setIsselect(false);
        cartBean6.setNumberlist(5);
        cartBean6.setFinishlist(5);
        cartBean6.setFinish_isVisible(View.GONE);
        for (int i=0;i<6;i++){
//            cartlist.add(i+"");
        }
        cartlist.add(cartBean1);
        cartlist.add(cartBean2);
        cartlist.add(cartBean3);
        cartlist.add(cartBean4);
        cartlist.add(cartBean5);
        cartlist.add(cartBean6);
    }

    void initview(View view) {
        list = (ListView) view.findViewById(R.id.shoppingcart_list);
        text_price = (TextView) view.findViewById(R.id.textView);
        check_all = (CheckBox) view.findViewById(R.id.check_all);
        text_piece = (TextView) view.findViewById(R.id.piece_text);
        is_edit = (TextView) view.findViewById(R.id.is_edit);
        payment = (TextView) view.findViewById(R.id.payment);
        linear = (LinearLayout) view.findViewById(R.id.linear);
        linear_1 = (LinearLayout) view.findViewById(R.id.liear_1);
        check_linear=(LinearLayout)view.findViewById(R.id.check_linear);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Message message = new Message();
        message.obj=i;
        message.what = 0x06;
        shoppingcartAdapter.handler.sendMessage(message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_all:
                if (isAll) {
                    isAll = false;
                    Message message = new Message();
                    message.what = 0x02;
                    shoppingcartAdapter.handler.sendMessage(message);
                    cartlist=shoppingcartAdapter.getCartlist();
                    text_price.setText("0");
                    text_piece.setText("0");
                } else {
                    Message message = new Message();
                    message.what = 0x01;
                    shoppingcartAdapter.handler.sendMessage(message);
                    isAll = true;
                }
                break;
            case R.id.is_edit:
                if (!isEdit) {
                    is_edit.setText("完成");
                    payment.setText("删除");
                    linear.setVisibility(View.INVISIBLE);
                    linear_1.setVisibility(View.INVISIBLE);
                    Message message = new Message();
                    message.what = 0x03;
                    shoppingcartAdapter.handler.sendMessage(message);
                    isEdit = true;
                    text_price.setText("0");
                    text_piece.setText("0");
                } else {
                    is_edit.setText("编辑");
                    payment.setText("去结算");
                    linear.setVisibility(View.VISIBLE);
                    linear_1.setVisibility(View.VISIBLE);
                    Message message = new Message();
                    message.what = 0x04;
                    shoppingcartAdapter.handler.sendMessage(message);
                    isEdit = false;

                }
                break;
            case R.id.payment:
                if (payment.getText().equals("删除")) {
                    is_edit.setText("编辑");
                    payment.setText("去结算");
                    linear.setVisibility(View.VISIBLE);
                    linear_1.setVisibility(View.VISIBLE);
                    Message message = new Message();
                    message.what = 0x05;
                    shoppingcartAdapter.handler.sendMessage(message);
                    deleteMsg(shoppingcartAdapter.getCartlist());
                    isEdit = false;
                    return;
                }
                if (payment.getText().equals("去结算")){
                    Intent intent =new Intent(getActivity(), ShoppingcartPaymentActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.check_linear:
                if (isAll) {
                    isAll = false;
                    text_price.setText("0");
                    text_piece.setText("0");
                    check_all.setChecked(false);
                    Message message = new Message();
                    message.what = 0x02;
                    shoppingcartAdapter.handler.sendMessage(message);
                } else {
                    Message message = new Message();
                    message.what = 0x01;
                    shoppingcartAdapter.handler.sendMessage(message);
                    isAll = true;
                    check_all.setChecked(true);
                }
                break;
        }
    }
    public void deleteMsg(List<CartBean> cartlist){
        List<CartBean> deleteList = new ArrayList<>();
        for (CartBean cartBean : cartlist) {
            // (1-已读 0-未读)
            if (cartBean.isselect()) {
                deleteList.add(cartBean);
            }
        }
        this.cartlist.removeAll(deleteList);
        shoppingcartAdapter.setList(this.cartlist);
        shoppingcartAdapter.notifyDataSetChanged();
    }
}
