package com.example.administrator.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.activity.EditAddressActivity;
import com.example.administrator.shopping.bean.CircleBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-04-12.
 */

/*public class ReceiverAddressAdapter {
}*/

public class ReceiverAddressAdapter extends RecyclerView.Adapter<ReceiverAddressAdapter.MyHolder> {
    private Context mcontext;
    private ArrayList<CircleBean.DataBean> list;
    private LayoutInflater inflater;

    public ReceiverAddressAdapter(Context context, ArrayList<CircleBean.DataBean> list) {
        this.mcontext = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.item_receiver_address, null);
        parent.addView(inflate);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

       /* holder.tv1.setText(list.get(position).getTitle());
        holder.tv2.setText(list.get(position).getContent());*/
/* List<String> imgs = list.get(position).getImgs();
        //通过Picasso框架下载图片并显示
        for (int i = 0; i < imgs.size(); i++) {
            Picasso.with(mcontext)
                    .load(imgs.get(i))
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.ivs[i]);
        }*/
        /*Picasso.with(mcontext)
                .load(list.get(position).getImg())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.iv);*/
        holder.edit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, EditAddressActivity.class);
                mcontext.startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });
        holder.check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                   holder.text.setTextColor(0xffe66f2e);
                } else {
                    holder.text.setTextColor(0xff323232);
                }
            }
        });
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.check_box.isChecked()) {
                    holder.check_box.setChecked(false);
                    holder.text.setTextColor(0xff323232);
                } else {
                    holder.check_box.setChecked(true);
                    holder.text.setTextColor(0xffe66f2e);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {


        TextView text;
        TextView tv1;
        TextView tv2;
        ImageView iv;
        TextView edit_address;
        TextView delete;
        CheckBox check_box;

        public MyHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.tv_mo_ren);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
            tv2 = (TextView) itemView.findViewById(R.id.tv2);
            check_box = (CheckBox) itemView.findViewById(R.id.check_box);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            edit_address = (TextView) itemView.findViewById(R.id.edit_address);
            delete = (TextView) itemView.findViewById(R.id.delete);
        }
    }

    public void remove(int position) {
        Log.i("xinxi", list.size() + "  " + position);
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }
}