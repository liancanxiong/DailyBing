package com.brilliantbear.dailybing.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Bear on 2016-6-14.
 */
public interface NetService {

    @GET("HPImageArchive.aspx")
    Call<ResponseBody> getData(@Query("format") String format, @Query("idx") int idx, @Query("n") int n, @Query("mkt") String mkt);
}
