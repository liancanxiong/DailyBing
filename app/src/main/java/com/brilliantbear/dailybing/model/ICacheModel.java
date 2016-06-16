package com.brilliantbear.dailybing.model;

/**
 * Created by Bear on 2016-6-15.
 */
public interface ICacheModel<T> {
    void setCache(T cache);

    T getCache();
}
