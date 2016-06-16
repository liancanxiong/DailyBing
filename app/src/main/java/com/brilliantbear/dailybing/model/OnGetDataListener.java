package com.brilliantbear.dailybing.model;

/**
 * Created by Bear on 2016-6-12.
 */
public interface OnGetDataListener<T> {
    void onSuccess(T data);

    void onFailure(String sth);
}
