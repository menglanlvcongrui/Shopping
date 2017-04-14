package com.example.administrator.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public   class ReceiverAddressAdapter extends RecyclerView.Adapter<ReceiverAddressAdapter.MyHolder> {
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
    public void onBindViewHolder(MyHolder holder, int position) {

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
                Intent intent=new Intent(mcontext, EditAddressActivity.class);
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView tv1;
        TextView tv2;
        ImageView iv;
        TextView edit_address;
        public MyHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
            tv2 = (TextView) itemView.findViewById(R.id.tv2);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            edit_address=(TextView)itemView.findViewById(R.id.edit_address);
        }
    }
}