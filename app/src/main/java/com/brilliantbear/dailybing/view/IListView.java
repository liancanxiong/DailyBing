package com.brilliantbear.dailybing.view;

/**
 * Created by Bear on 2016-6-15.
 */
public interface IListView<T> {
    void showProgress();

    void hideProgress();

    void showData(T data);

    void showSth(String sth);
}
