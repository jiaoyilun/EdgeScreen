package com.fisher.provider;

import android.content.Context;
import android.widget.RemoteViews;

import com.fisher.R;
import com.samsung.android.sdk.look.cocktailbar.SlookCocktailManager;
import com.samsung.android.sdk.look.cocktailbar.SlookCocktailProvider;

/**
 * Created by Administrator on 2016/6/16/.
 */
public class RouteInfoFeedProvider extends SlookCocktailProvider {

    @Override
    public void onUpdate(Context context, SlookCocktailManager cocktailManager, int[] cocktailIds) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.track_feed);

        rv.setTextViewText(R.id.text, "客户已签收");

        for (int i = 0; i < cocktailIds.length; i++) {
            cocktailManager.updateCocktail(cocktailIds[i], rv);
        }
    }
}
