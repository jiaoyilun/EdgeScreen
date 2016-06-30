package com.fisher.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fisher.R;
import com.fisher.po.RouteInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/6/16/.
 */
public class TrackInfoAdapter extends BaseAdapter {
    private List<RouteInfo> routeInfoList;
    private Context context;

    public TrackInfoAdapter(List<RouteInfo> routeInfoList, Context context) {
        this.routeInfoList = routeInfoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return routeInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return routeInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_track_plus, null);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_context = (TextView) convertView.findViewById(R.id.tv_context);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RouteInfo routeInfo = routeInfoList.get(position);
        holder.tv_context.setText(routeInfo.getContext());
        holder.tv_time.setText(routeInfo.getTime());

        return convertView;
    }

    private class ViewHolder {
        private TextView tv_time;
        private TextView tv_context;
    }
}