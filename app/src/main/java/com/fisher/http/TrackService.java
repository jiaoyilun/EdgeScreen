package com.fisher.http;

import com.fisher.po.TrackData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/6/30/.
 */
public interface TrackService {

    @GET("api")
    Observable<HttpResult<TrackData>> getTranckData(@Query("id") String id, @Query("show") String show, @Query("muti") String muti, @Query("order") String order, @Query("com") String com, @Query("nu") String nu);


}
