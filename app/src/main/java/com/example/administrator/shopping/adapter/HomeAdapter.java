package com.example.administrator.shopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.bean.HomeBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-04-10.
 */

public   class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyHolder> {
    private Context mcontext;
    private ArrayList<HomeBean.DataBean> list;
    private LayoutInflater inflater;

    public HomeAdapter(Context context, ArrayList<HomeBean.DataBean> list) {
        this.mcontext = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.item_home, null);
        parent.addView(inflate);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

/* List<String> imgs = list.get(position).getImgs();
        //通过Picasso框架下载图片并显示
        for (int i = 0; i < imgs.size(); i++) {
            Picasso.with(mcontext)
                    .load(imgs.get(i))
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.ivs[i]);
        }*/
       /* Picasso.with(mcontext)
                .load(list.get(position).getImg())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.iv1);
        Picasso.with(mcontext)
                .load(list.get(position).getImg())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.iv2);
        Picasso.with(mcontext)
                .load(list.get(position).getImg())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.iv3);
        Picasso.with(mcontext)
                .load(list.get(position).getImg())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.iv4);*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        ImageView iv1;
        ImageView iv2;
        ImageView iv3;
        ImageView iv4;

        public MyHolder(View itemView) {
            super(itemView);
            iv1 = (ImageView) itemView.findViewById(R.id.iv1);
            iv2 = (ImageView) itemView.findViewById(R.id.iv2);
            iv3 = (ImageView) itemView.findViewById(R.id.iv3);
            iv4 = (ImageView) itemView.findViewById(R.id.iv4);
        }
    }
}