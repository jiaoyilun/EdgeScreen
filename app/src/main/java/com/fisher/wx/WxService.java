package com.fisher.wx;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jiaoy on 2016/7/1/.
 */
public interface WxService {

    @GET("txapi/weixin/wxhot")
    Observable<WeixinEntity> getData(@Header("apiKey") String apiKey, @Query("num") int num);

}
