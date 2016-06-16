package com.brilliantbear.dailybing.presenter;

/**
 * Created by Bear on 2016-6-15.
 */
public interface IPresenter<T> {
    void getData(int idx, int n);

    void setWallpaper(String url);

    T getCache();
}
