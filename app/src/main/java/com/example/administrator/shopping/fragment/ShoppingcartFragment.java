package com.example.administrator.shopping.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.adapter.ShoppingcartAdapter;

import java.util.ArrayList;
import java.util.List;


public class ShoppingcartFragment extends Fragment {
    ShoppingcartAdapter shoppingcartAdapter;//购物车适配器
    static TextView text_price, text_piece;//价格、总价
    LinearLayout linear, linear_1;//需要隐藏的布局
    List<String> cartlist;
    TextView is_edit, payment;//编辑/完成  结算/编辑
    static CheckBox check_all;//全选按钮
    boolean isEdit = false;//编辑/完成标记
   static boolean isAll=false;//是否全选
    public static Handler handler = new Handler() {//hander处理总额，然后刷新FragmentUI
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x01:
                    text_price.setText(msg.getData().getString("total"));
                    text_piece.setText(msg.getData().getString("count"));

                    break;
                case 0x02:
                    int j = 0;
                    for (int i = 0; i < 6; i++)
                        if (!msg.getData().getBooleanArray("isselect")[i]) {
                            j++;
                        }
                    if (j==0){
                        check_all.setChecked(true);
                        isAll=true;
                    }else {
                        check_all.setChecked(false);
                        isAll=false;
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
        ListView list = (ListView) view.findViewById(R.id.shoppingcart_list);
        text_price = (TextView) view.findViewById(R.id.textView);
        check_all = (CheckBox) view.findViewById(R.id.check_all);
        text_piece = (TextView) view.findViewById(R.id.piece_text);
        is_edit = (TextView) view.findViewById(R.id.is_edit);
        payment = (TextView) view.findViewById(R.id.payment);
        linear = (LinearLayout) view.findViewById(R.id.linear);
        linear_1 = (LinearLayout) view.findViewById(R.id.liear_1);
        cartlist = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            cartlist.add("saaa" + i);
        }
        shoppingcartAdapter = new ShoppingcartAdapter(getActivity(), cartlist);
        list.setAdapter(shoppingcartAdapter);

        check_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAll) {

                    isAll=false; Message message = new Message();
                    message.what = 0x02;
                    shoppingcartAdapter.handler.sendMessage(message);
                } else {
                    Message message = new Message();
                    message.what = 0x01;
                    shoppingcartAdapter.handler.sendMessage(message);
                    isAll=true;
                }
        }
        });
        is_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEdit) {
                    is_edit.setText("完成");
                    payment.setText("删除");
                    linear.setVisibility(View.INVISIBLE);
                    linear_1.setVisibility(View.INVISIBLE);
                    Message message = new Message();
                    message.what = 0x03;
                    shoppingcartAdapter.handler.sendMessage(message);
                    isEdit = true;
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
            }
        });
        return view;
    }
}
