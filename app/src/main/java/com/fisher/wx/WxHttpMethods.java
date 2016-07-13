package com.fisher.wx;

import android.util.Log;

import com.fisher.http.ApiException;

import java.util.List;
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
public class WxHttpMethods {
    private static final String TAG = "WxHttpMethods";
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;

    private WxService wxService;

    private WxHttpMethods() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder().client(httpClientBuilder.addInterceptor(logging).build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://apis.baidu.com/").build();

        wxService = retrofit.create(WxService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final WxHttpMethods INSTANCE = new WxHttpMethods();
    }

    //获取单例
    public static WxHttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public void getData(Subscriber<List<Article>> subscriber, int num) {
        Observable observable = wxService.getData("fd73da6d921fb9717059f653caa6da92",1)
                .map(new WxResultFunc());
        toSubscribe(observable, subscriber);
    }


    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s);
    }

    public class WxResultFunc implements Func1 {
        public Object call(Object o) {
            WeixinEntity tWeixinEntity = (WeixinEntity)o;
            Log.d(TAG,o.getClass().getCanonicalName());
            Log.d(TAG,tWeixinEntity.getCode()+"");
            if (tWeixinEntity.getCode() != 200) {
                throw new ApiException(tWeixinEntity.getCode());
            }
            return tWeixinEntity.getArticleList();
        }

    }

}
