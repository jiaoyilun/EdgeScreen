package com.fisher.extra;

import android.content.ComponentName;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.fisher.R;
import com.fisher.po.RouteInfo;
import com.fisher.po.TrackData;
import com.fisher.provider.RouteInfoProvider;
import com.fisher.utils.NetUtil;
import com.samsung.android.sdk.look.cocktailbar.SlookCocktailManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/16/.
 */
public class RouteInfoAdapterFactory implements RemoteViewsService.RemoteViewsFactory {
    static final String TAG = "AdapterFactory";

    private Context context;

    private List<RouteInfo> routeInfoList = new ArrayList<>();

    public RouteInfoAdapterFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
//        loadLocalData();
//        loadRemoteData();
    }

    @Override
    public void onDataSetChanged() {
        loadRemoteDataWithoutThread();
        Log.d(TAG,"load?  "+ routeInfoList.size());
        Log.d(TAG, "onDataSetChanged");
//                notifyReady();
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return (routeInfoList.isEmpty()) ? 0 : routeInfoList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Log.d(TAG,"load?  "+ routeInfoList.size());
        Log.d(TAG, "getViewAt....");

        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.item_track_plus);
      /*   Bundle extras = new Bundle();
        Intent fillInIntent = new Intent();
       if ((position % 2) == 0) {
            PendingIntent pIntent = PendingIntent.getActivity(context, 0, new Intent(Intent.ACTION_DIAL), PendingIntent.FLAG_UPDATE_CURRENT);
            extras.putParcelable(Constants.EXTRA_CONTENT_INTENT, pIntent);
            fillInIntent.putExtras(extras);
            contentView.setOnClickFillInIntent(R.id.track_plus_item_layout, fillInIntent);
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setType("vnd.android-dir/mms-sms");
            PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            extras.putParcelable(Constants.EXTRA_CONTENT_INTENT, pIntent);
            fillInIntent.putExtras(extras);
            contentView.setOnClickFillInIntent(R.id.track_plus_item_layout, fillInIntent);
        }*/
        try {
            RouteInfo routeInfo = routeInfoList.get(position);
            contentView.setTextViewText(R.id.tv_context, routeInfo.getContent());
            contentView.setTextViewText(R.id.tv_time, routeInfo.getTime());
        } catch (IndexOutOfBoundsException e) {

        }
        return contentView;
    }


    private void notifyReady() {
        Log.d(TAG, "notifyReady....");
        SlookCocktailManager mgr = SlookCocktailManager.getInstance(context);
        int[] cocktailIds = mgr.getCocktailIds(new ComponentName(context, RouteInfoProvider.class));
        for (int i = 0; i < cocktailIds.length; i++) {
            mgr.notifyCocktailViewDataChanged(cocktailIds[i], R.id.lv_routeInfoList);
        }
    }

    private void loadLocalData() {
        TrackData data = NetUtil.getTestData();
        routeInfoList = data.getData();
    }

    private void loadRemoteData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                TrackData data = NetUtil.loadRouteInfo("zhaijisong","3944490863");
                routeInfoList = data.getData();
            }
        }).start();
    }

    private void loadRemoteDataWithoutThread(){
                TrackData data = NetUtil.loadRouteInfo("zhaijisong","3944490863");
                routeInfoList = data.getData();
    }

    @Override
    public RemoteViews getLoadingView() {
        Log.d(TAG, "getLoadingView");
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
