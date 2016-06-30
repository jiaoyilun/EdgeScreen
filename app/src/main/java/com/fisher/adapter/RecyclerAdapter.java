package com.fisher.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fisher.R;
import com.fisher.po.TrackData;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecVH> implements OnLongClickListener, View.OnClickListener {
    private List<TrackData> trackDataList = new ArrayList<TrackData>();
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerAdapter(List<TrackData> trackDataList) {
        this.trackDataList = trackDataList;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    //创造ViewHolder
    @Override
    public RecVH onCreateViewHolder(ViewGroup parent, int viewType) {
        //把item的Layout转化成View传给ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        view.setOnLongClickListener(this);
        view.setOnClickListener(this);
        return new RecVH(view);
    }

    //    将数据放入相应的位置
    @Override
    public void onBindViewHolder(RecVH holder, int position) {
        holder.iv_logo.setImageResource(R.drawable.ic_fab_star);
        holder.tv_nu.setText(trackDataList.get(position).getNu());
        holder.tv_context.setText(trackDataList.get(position).getContext());
        holder.tv_time.setText(trackDataList.get(position).getTime());

        Object[] arr = new Object[2];
        arr[0] = position;
        arr[1] = trackDataList.get(position);
        holder.itemView.setTag(arr);
    }

    @Override
    public int getItemCount() {
        return trackDataList.size();
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemLongClickListener != null) {
            mOnItemLongClickListener.onItemLongClick(v, v.getTag());
            return false;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, v.getTag());
        }
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

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, Object obj);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Object obj);
    }

}