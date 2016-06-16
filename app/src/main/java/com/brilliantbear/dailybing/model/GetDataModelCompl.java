package com.brilliantbear.dailybing.model;

import com.brilliantbear.dailybing.bean.ResultBean;
import com.brilliantbear.dailybing.net.Net;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Bear on 2016-6-15.
 */
public class GetDataModelCompl implements IGetDataModel {

    private OnGetDataListener<ResultBean> listener;
    private final Net mNet;

    public GetDataModelCompl(OnGetDataListener<ResultBean> listener) {
        this.listener = listener;
        mNet = Net.getInstance();
    }

    @Override
    public void getData(int idx, int n) {
        mNet.getData(idx, n, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    listener.onFailure("error");
                    return;
                }
                String result = null;
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                    listener.onFailure("error");
                    return;
                }
                Gson gson = new Gson();
                ResultBean resultBean = gson.fromJson(result, ResultBean.class);
                listener.onSuccess(resultBean);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onFailure("error:" + t.toString());
            }
        });
    }
}
