package com.fisher.test;

import com.fisher.po.TrackData;
import com.fisher.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 接口
 * Created by Hal on 15/4/26.
 */
public class ApiManager {

    private static final String ENDPOINT = "http://api.kuaidi100.com";


    private static final Retrofit sRetrofit = new Retrofit.Builder().baseUrl(ENDPOINT).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .build();

    private static final ApiManagerService apiManager = sRetrofit.create(ApiManagerService.class);


    public static Observable<TrackData> getTranckData(String com, String nu) {

        Map<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("id", Constants.API_KD);
        fieldMap.put("show", "0");
        fieldMap.put("muti", "1");
        fieldMap.put("order", "desc");

        return apiManager.getTranckData(fieldMap, com, nu);
    }


    /**
     * 服务接口http://api.kuaidi100.com/api?id=f5b7db2ef15a853b&com=%s&nu=%s&show=0&muti=1&order=desc
     */
    private interface ApiManagerService {

        @GET("/api")
        Observable<TrackData> getTranckData(@FieldMap Map<String, String> fieldMap, @Query("com") String com, @Query("nu") String nu);

    }
}