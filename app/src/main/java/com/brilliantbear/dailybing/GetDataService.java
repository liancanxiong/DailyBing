package com.brilliantbear.dailybing;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.brilliantbear.dailybing.bean.ResultBean;
import com.brilliantbear.dailybing.net.Net;
import com.brilliantbear.dailybing.utils.NetUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDataService extends IntentService {

    public static final String TAG = "GetDataService";
    private final Context context;

    public GetDataService() {
        super(TAG);
        context = this;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("TAG", TAG);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        boolean onlyWifi = sp.getBoolean("key_wifi", false);
        int networkType = NetUtils.getNetworkType(context);
        Log.d("TAG", "networkType:" + networkType);
        if (onlyWifi && networkType != NetUtils.NET_TYPE_WIFI) {
            return;
        }

        Net.getInstance().getData(0, 1, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                String result = null;
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                Gson gson = new Gson();
                ResultBean resultBean = gson.fromJson(result, ResultBean.class);
                String url = resultBean.getImages().get(0).getUrl();
                Intent intent = new Intent(context, DownloadService.class);
                intent.putExtra("url", url);
                context.startService(intent);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
