package com.fisher.provider;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.fisher.R;
import com.fisher.common.Constants;
import com.fisher.extra.RouteInfoAdapterService;
import com.samsung.android.sdk.look.cocktailbar.SlookCocktailManager;
import com.samsung.android.sdk.look.cocktailbar.SlookCocktailProvider;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/6/16/.
 */
public class RouteInfoProvider extends SlookCocktailProvider {
    private static final String TAG = "RouteInfoProvider";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        Log.d(TAG,intent.getAction());
        if (intent.getAction().equals(Constants.ACTION_CLICK_REFRESH)) {
            Log.d(TAG, "ACTION_CLICK_REFRESH");
            Toast.makeText(context, "加载中...", Toast.LENGTH_SHORT).show();
        }
        super.onReceive(context, intent);
        //        if (intent.getAction() == Constants.COCKTAIL_LIST_ADAPTER_CLICK_ACTION) {
        //            PendingIntent p = intent.getParcelableExtra(Constants.EXTRA_CONTENT_INTENT);
        //            if (p != null) {
        //                try {
        //                    p.send();
        //                } catch (PendingIntent.CanceledException e) {
        //                    e.printStackTrace();
        //                }
        //            }
        //        }
    }

    @Override
    public void onUpdate(Context context, SlookCocktailManager cocktailManager, int[] cocktailIds) {
        Intent intent = new Intent(context, RouteInfoAdapterService.class);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.track_plus);

        views.setViewVisibility(R.id.tv_com, View.VISIBLE);
        views.setTextViewText(R.id.tv_com, "顺丰速递");
        views.setTextViewText(R.id.tv_nu, "3944490863");
        views.setTextViewText(R.id.tv_tel, "aaaa");
        views.setTextViewText(R.id.tv_url, "www.baidu.com");


        views.setRemoteAdapter(R.id.lv_routeInfoList, intent);
        views.setEmptyView(R.id.lv_routeInfoList, R.id.tv_emptylist);

        /**
         Intent itemClickIntent = new Intent(context, RouteInfoProvider.class);
         itemClickIntent.setAction(Constants.COCKTAIL_LIST_ADAPTER_CLICK_ACTION);
         PendingIntent itemClickPendingIntent = PendingIntent.getBroadcast(context, 1, itemClickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
         views.setPendingIntentTemplate(R.id.lv_routeInfoList, itemClickPendingIntent);
         **/

        //设置帮助区域视图
        RemoteViews view_help = this.setHelpAreaView(context);

        Log.d(TAG, cocktailIds.length + "");
        for (int i = 0; i < cocktailIds.length; i++) {
            cocktailManager.updateCocktail(cocktailIds[i], views, view_help);
        }
    }


    private RemoteViews setHelpAreaView(Context context) {
        String tx_update = String.format(context.getString(R.string.text_updated), getCurrentTime());
        RemoteViews view_help = new RemoteViews(context.getPackageName(), R.layout.help_layout);
        view_help.setTextViewText(R.id.tv_update, tx_update);

        Intent logoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.URL_POWERED));
        PendingIntent logoClickPendingIntent = PendingIntent.getActivity(context, 0, logoIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        view_help.setOnClickPendingIntent(R.id.iv_logo, logoClickPendingIntent);

        Intent refreshIntent = new Intent().setAction(Constants.ACTION_CLICK_REFRESH);
        PendingIntent refreshPendingIntent = PendingIntent.getBroadcast(context, 0, refreshIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        view_help.setOnClickPendingIntent(R.id.iv_refresh, refreshPendingIntent);

        return view_help;
    }

    private String getCurrentTime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("MM/dd HH:mm");
        String time = sDateFormat.format(new java.util.Date());
        return time;
    }
}
