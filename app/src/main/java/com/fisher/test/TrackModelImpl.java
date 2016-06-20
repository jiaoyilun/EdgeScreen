package com.fisher.test;

import com.fisher.po.TrackData;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/6/20/.
 */
public class TrackModelImpl implements TrackModel {

    private TrackOnListener mTrackOnListener;

    public TrackModelImpl(TrackOnListener mTrackOnListener) {
        this.mTrackOnListener = mTrackOnListener;
    }

    @Override
    public void getTranckData(String com, String nu) {
        ApiManager.getTranckData(com, nu).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<TrackData>() {
            @Override
            public void call(TrackData trackData) {
                mTrackOnListener.onSuccess(trackData);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mTrackOnListener.onFailure(throwable);
            }
        });

    }

    /**
     * 回调接口
     */
    public interface TrackOnListener {
        void onSuccess(TrackData data);

        void onFailure(Throwable e);
    }


}
