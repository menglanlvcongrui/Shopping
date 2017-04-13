package com.example.administrator.shopping.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.adapter.ShoppingcartAdapter;

import java.util.ArrayList;
import java.util.List;


public class ShoppingcartFragment extends Fragment {
    ShoppingcartAdapter shoppingcartAdapter;
    static TextView text_price, text_piece;
    List<String> cartlist;
    CheckBox check_all;
    public static Handler handler = new Handler() {//hander处理总额，然后刷新FragmentUI
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x01:
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
        text_piece=(TextView)view.findViewById(R.id.piece_text);
        cartlist = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            cartlist.add("saaa" + i);
        }
        shoppingcartAdapter = new ShoppingcartAdapter(getActivity(), cartlist);
        check_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Message message = new Message();
                    message.what = 0x01;
                    shoppingcartAdapter.handler.sendMessage(message);
                } else {
                    Message message = new Message();
                    message.what = 0x02;
                    shoppingcartAdapter.handler.sendMessage(message);
                }
            }
        });
        list.setAdapter(shoppingcartAdapter);
        return view;
    }
}
