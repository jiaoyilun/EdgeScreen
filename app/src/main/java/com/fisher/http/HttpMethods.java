package com.fisher.http;

import com.fisher.po.TrackData;
import com.fisher.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/6/30/.
 */
public class HttpMethods {
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;

    private TrackService trackService;

    private HttpMethods() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder().client(httpClientBuilder.addInterceptor(logging).build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constants.URL_KD).build();

        trackService = retrofit.create(TrackService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }


/*    public void getTrackData1(Subscriber<TrackData> subscriber, String com, String nu) {
        Observable observable = trackService.getTranckData(Constants.API_KD, "0", "1", "desc", com, nu).map(new HttpResultFunc<TrackData>());
        toSubscribe(observable, subscriber);
    }*/

    public void getTrackData(Subscriber<TrackData> subscriber, String com, String nu) {
        Observable observable = trackService.getTranckData("72430", "98a158351e834a85868be7faa1e1b19e", nu, com,"desc")
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }


    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s);
    }

    private class HttpResultFunc implements Func1<TrackData, TrackData> {

        @Override
        public TrackData call(TrackData trackData) {
            if (!trackData.getErrcode().equals("0000")) {
                throw new ApiException(trackData.getErrcode());
            }
            return trackData;
        }
    }
}
