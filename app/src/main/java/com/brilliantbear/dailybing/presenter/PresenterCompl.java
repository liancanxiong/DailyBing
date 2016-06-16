package com.brilliantbear.dailybing.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.brilliantbear.dailybing.DownloadService;
import com.brilliantbear.dailybing.bean.ResultBean;
import com.brilliantbear.dailybing.model.GetDataModelCompl;
import com.brilliantbear.dailybing.model.IGetDataModel;
import com.brilliantbear.dailybing.model.OnGetDataListener;
import com.brilliantbear.dailybing.utils.NetUtils;
import com.brilliantbear.dailybing.view.IListView;

/**
 * Created by Bear on 2016-6-15.
 */
public class PresenterCompl implements IPresenter<ResultBean>, OnGetDataListener<ResultBean> {

    private Context context;
    private IListView<ResultBean> view;
    private final IGetDataModel dataModel;

    public PresenterCompl(Context context, IListView<ResultBean> view) {
        this.context = context;
        this.view = view;
        dataModel = new GetDataModelCompl(this);

    }


    @Override
    public void getData(int idx, int n) {
        view.showProgress();
        dataModel.getData(idx, n);
    }

    @Override
    public void setWallpaper(final String url) {
        int networkType = NetUtils.getNetworkType(context);
        if (networkType != NetUtils.NET_TYPE_WIFI) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("当前网络不是Wi-Fi网络，确认下载该图片并设置为壁纸？");
            builder.setNegativeButton("取消", null);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    view.showSth("正在下载壁纸");
                    Intent intent = new Intent(context, DownloadService.class);
                    intent.putExtra("url", url);
                    context.startService(intent);
                }
            });
            builder.create().show();
        } else {
            view.showSth("正在下载壁纸");
            Intent intent = new Intent(context, DownloadService.class);
            intent.putExtra("url", url);
            context.startService(intent);
        }
    }

    @Override
    public ResultBean getCache() {
        return null;
    }

    @Override
    public void onSuccess(ResultBean data) {
        view.hideProgress();
        view.showData(data);
    }

    @Override
    public void onFailure(String sth) {
        view.hideProgress();
        Log.e("TAG", "onFailure:" + sth);
    }
}
