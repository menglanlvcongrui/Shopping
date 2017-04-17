package com.example.administrator.shopping.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.shopping.R;

import java.util.List;

import kankan.wheel.widget.adapters.AbstractWheelTextAdapter;

/**
 *
 *
 */
public class AddressTextAdapter extends AbstractWheelTextAdapter {
    List<String> mDatas;
    Context mContext;

    public AddressTextAdapter(Context context, List<String> datas, int currentIndex, int maxSize , int minSize) {
        super(context, R.layout.item_address,NO_RESOURCE,currentIndex,maxSize,minSize);
        mContext = context;
        mDatas=datas;
        setItemTextResource(R.id.tempValue);
        this.setTextColor(0x323232);
    }

    @Override
    protected CharSequence getItemText(int index) {
        return mDatas.get(index);
    }

    @Override
    public int getItemsCount() {
        return mDatas.size();
    }

    @Override
    public View getItem(int index, View convertView, ViewGroup parent) {


        View view = super.getItem(index,convertView,parent);

        return view;
    }

    public String getName(int index){
        return mDatas.get(index);
    }




}
