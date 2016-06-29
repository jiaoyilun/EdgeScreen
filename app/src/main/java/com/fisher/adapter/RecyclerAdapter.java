package com.fisher.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fisher.R;
import com.fisher.po.RouteInfo;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecVH> implements OnLongClickListener {
    private List<RouteInfo> routeInfoList = new ArrayList<RouteInfo>();
    private OnRecyclerViewItemLongClickListener mOnItemLongClickListener = null;

    public RecyclerAdapter(List<RouteInfo> routeInfoList) {
        this.routeInfoList = routeInfoList;
    }

    public void setOnItemLongClickListener(OnRecyclerViewItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    //创造ViewHolder
    @Override
    public RecVH onCreateViewHolder(ViewGroup parent, int viewType) {
        //把item的Layout转化成View传给ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        view.setOnLongClickListener(this);
        return new RecVH(view);
    }

    //    将数据放入相应的位置
    @Override
    public void onBindViewHolder(RecVH holder, int position) {
        holder.iv_logo.setImageResource(R.drawable.ic_fab_star);
        holder.tv_nu.setText(routeInfoList.get(position).getLocation());
        holder.tv_context.setText(routeInfoList.get(position).getContext());
        holder.tv_time.setText(routeInfoList.get(position).getTime());

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return routeInfoList.size();
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemLongClickListener != null) {
            mOnItemLongClickListener.onItemLongClick(v, (String) v.getTag());
            return false;
        }
        return false;
    }

    //ViewHolder绑定控件
    public class RecVH extends RecyclerView.ViewHolder {
        ImageView iv_logo;
        TextView tv_nu;
        TextView tv_time;
        TextView tv_context;

        public RecVH(View itemView) {
            super(itemView);
            iv_logo = (ImageView) itemView.findViewById(R.id.iv_logo);
            tv_nu = (TextView) itemView.findViewById(R.id.tv_nu);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_context = (TextView) itemView.findViewById(R.id.tv_context);
        }
    }

    public interface OnRecyclerViewItemLongClickListener {
        void onItemLongClick(View view, String data);
    }

}