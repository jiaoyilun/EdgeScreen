package com.fisher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fisher.R;
import com.fisher.po.RouteInfo;

import java.util.List;

public class TimeLineAdapter extends BaseAdapter {

    private Context context;
    private List<RouteInfo> routeInfoList;
    private LayoutInflater inflater;

    public TimeLineAdapter(Context context, List<RouteInfo> routeInfoList) {
        super();
        this.context = context;
        this.routeInfoList = routeInfoList;
    }

    @Override
    public int getCount() {

        return routeInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_timeline, null);
            viewHolder = new ViewHolder();

           /* if (0 == position) {
                convertView.findViewById(R.id.view_1).setVisibility(View.GONE);
            }
            if (position == routeInfoList.size() - 1) {
                convertView.findViewById(R.id.view_2).setVisibility(View.GONE);
            }*/


            viewHolder.context = (TextView) convertView.findViewById(R.id.context);
            viewHolder.time = (TextView) convertView.findViewById(R.id.show_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String titleStr = routeInfoList.get(position).getContent();
        viewHolder.context.setText(titleStr);
        viewHolder.time.setText(routeInfoList.get(position).getTime());
        return convertView;
    }

    static class ViewHolder {
        public TextView time;
        public TextView context;
    }
}
