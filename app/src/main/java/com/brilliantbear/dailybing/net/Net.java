package com.brilliantbear.dailybing.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by Bear on 2016-6-14.
 */
public class Net {

    public static final String BASE_URL = "http://www.bing.com/";
    private static Net mNet;
    private final NetService mService;

    private Net() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        mService = retrofit.create(NetService.class);
    }

    public static Net getInstance() {
        if (mNet == null) {
            synchronized (Net.class) {
                if (mNet == null) {
                    mNet = new Net();
                }
            }
        }
        return mNet;
    }


    public void getData(int idx, int n, Callback<ResponseBody> callback) {
        Call<ResponseBody> call = mService.getData("js", idx, n, "zh-CN");
        call.enqueue(callback);
    }
}
