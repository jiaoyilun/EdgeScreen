package com.fisher.extra;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Administrator on 2016/6/16/.
 */
public class RouteInfoAdapterService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RouteInfoAdapterFactory(this.getApplicationContext());
    }
}
