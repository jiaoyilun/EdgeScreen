package com.fisher.subscribers;

/**
 * Created by Administrator on 2016/6/30/.
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}
