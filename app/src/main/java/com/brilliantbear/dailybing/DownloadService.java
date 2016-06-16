package com.brilliantbear.dailybing;

import android.app.IntentService;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadService extends IntentService {

    public static final String TAG = "DownloadService";

    public DownloadService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String url = intent.getStringExtra("url");
        if (TextUtils.isEmpty(url)) {
            Log.e("TAG", "url is null");
            return;
        }

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        InputStream inputStream = null;
        try {
            Response response = okHttpClient.newCall(request).execute();
            inputStream = response.body().byteStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputStream == null)
            return;

        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        if (bitmap == null) {
            return;
        }
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        try {
            wallpaperManager.setBitmap(bitmap);
            Log.d("TAG", "设置壁纸成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
