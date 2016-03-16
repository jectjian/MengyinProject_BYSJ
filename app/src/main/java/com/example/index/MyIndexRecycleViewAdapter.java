package com.example.index;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mengyin.R;
import com.example.utils.CircleImageView;

/**
 * Created by jectjian on 2016/3/14.
 * Email : jectjian@126.com
 */
public class MyIndexRecycleViewAdapter extends RecyclerView.Adapter<MyIndexRecycleViewAdapter.ViewHolder>{

    private Context context;
    private int sign;
    private View view;

    //构造方法用来传递-上下文&数据源标识&数据源
    public MyIndexRecycleViewAdapter(Context context,int sign) {
        this.context = context;
        this.sign = sign;
    }

    //封装ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView index_circleImageView_iv;
        public TextView index_circleImageView_tv,index_recommend_zt_cardview_tv;
        public ImageView index_recommend_zt_cardview_iv;
        public ViewHolder(View itemView) {
            super(itemView);
            switch (sign){
                case 1://萌主item布局控件
                    index_circleImageView_iv = (CircleImageView) itemView.findViewById(R.id.index_circleImageView_iv);
                    index_circleImageView_tv = (TextView) itemView.findViewById(R.id.index_circleImageView_tv);
                    break;
                case 2://专题item布局控件
                    index_recommend_zt_cardview_iv = (ImageView) itemView.findViewById(R.id.index_recommend_zt_cardview_iv);
                    index_recommend_zt_cardview_tv = (TextView) itemView.findViewById(R.id.index_recommend_zt_cardview_tv);
                    break;
                case 3:
                    break;
            }
        }
    }

    //引入布局
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (sign){
            case 1://萌主item布局
                view = LayoutInflater.from(context).inflate(R.layout.index_recycleview_adapter_mz_item,parent,false);
                break;
            case 2://专题item布局
                view = LayoutInflater.from(context).inflate(R.layout.index_recycleview_adapter_zt_item,parent,false);
                break;
        }

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //为控件赋值
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (sign){
            case 1://萌主item布局控件
                holder.index_circleImageView_tv.setText("小旭音乐");
                break;
            case 2://专题item布局控件
                holder.index_recommend_zt_cardview_tv.setText("古来吉安");
                break;
        }
    }

    //数据源条目总数
    @Override
    public int getItemCount() {
        return 10;
    }
}
