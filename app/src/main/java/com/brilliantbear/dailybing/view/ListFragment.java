package com.brilliantbear.dailybing.view;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brilliantbear.dailybing.R;
import com.brilliantbear.dailybing.adapter.ListAdapter;
import com.brilliantbear.dailybing.bean.ImagesBean;
import com.brilliantbear.dailybing.bean.ResultBean;
import com.brilliantbear.dailybing.presenter.IPresenter;
import com.brilliantbear.dailybing.presenter.PresenterCompl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bear on 2016-6-15.
 */
public class ListFragment extends Fragment implements IListView<ResultBean>, SwipeRefreshLayout.OnRefreshListener, ListAdapter.OnItemClickListener {

    private static final int GET_DATA_COUNT = 7;
    private static final int STATUS_NORMAL = 0;
    private static final int STATUS_REFRESH = 1;
    private static final int STATUS_LOADMORE = 2;

    private int refresStatus = STATUS_NORMAL;
    private Context mContext;
    private View mRootView;
    private RecyclerView listImg;
    private List<ImagesBean> imagesBeanList;
    private ListAdapter mAdapter;
    private IPresenter<ResultBean> mPresenter;
    private SwipeRefreshLayout mRefresh;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragement_list, container, false);
        listImg = (RecyclerView) mRootView.findViewById(R.id.list_img);
        mRefresh = (SwipeRefreshLayout) mRootView.findViewById(R.id.refresh);

        mRefresh.setColorSchemeResources(R.color.colorAccent);
        mRefresh.setOnRefreshListener(this);

        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLayoutManager = new LinearLayoutManager(mContext);
        listImg.setLayoutManager(mLayoutManager);

        imagesBeanList = new ArrayList<>();
        mAdapter = new ListAdapter(mContext, imagesBeanList);
        mAdapter.setOnItemClickListener(this);
        listImg.setAdapter(mAdapter);
        listImg.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        mLayoutManager.findLastVisibleItemPosition() == (imagesBeanList.size() - 1)) {
                    if (refresStatus != STATUS_NORMAL) {
                        return;
                    }
                    mPresenter.getData(imagesBeanList.size(), GET_DATA_COUNT);
                    refresStatus = STATUS_LOADMORE;
                }
            }
        });
        mPresenter = new PresenterCompl(mContext, this);
        onRefresh();
    }

    @Override
    public void showProgress() {
        setRefresh(true);
    }

    @Override
    public void hideProgress() {
        setRefresh(false);
        refresStatus = STATUS_NORMAL;
    }

    @Override
    public void showData(ResultBean data) {
        if (data == null || data.getImages() == null) {
            showSth("无数据");
            return;
        }
        Log.d("TAG", data.toString());
        if (refresStatus == STATUS_REFRESH) {
            imagesBeanList.clear();
        }
        imagesBeanList.addAll(data.getImages());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSth(String sth) {
        Snackbar.make(mRootView, sth, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        mPresenter.getData(0, GET_DATA_COUNT);
        refresStatus = STATUS_REFRESH;
    }


    public void setRefresh(final boolean isShow) {
        mRefresh.post(new Runnable() {
            @Override
            public void run() {
                mRefresh.setRefreshing(isShow);
            }
        });
    }

    @Override
    public void onClick(View v, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("将该图片设置成壁纸?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.setWallpaper(imagesBeanList.get(position).getUrl());
            }
        });
        builder.setNegativeButton("取消", null);
        builder.create().show();
    }
}
