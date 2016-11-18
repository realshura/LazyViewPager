package com.feeling.lazyviewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by chenll on 2016/10/24.
 */

public abstract class BasePageFragment extends Fragment {

    public static final String KEY_PAGE_INDEX = "page_index";

    protected int mPageIndex;
    private boolean isLoaded;

    public void setPageIndex(int pageIndex) {
        mPageIndex = pageIndex;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            mPageIndex = getArguments().getInt(KEY_PAGE_INDEX);
        }

        initArguments(getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        initView(view);

        if (getUserVisibleHint()) {
            tryInitData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        log("setUserVisibleHint", "isVisibleToUser = " + isVisibleToUser);
        if (isAvailable() && isVisibleToUser) {
            tryInitData();
        }

        if (isAvailable()) {
            onVisibleChanged(isVisibleToUser);
        }
    }

    public void onVisibleChanged(boolean visible) {
        loge("onVisibleChanged", "visible = " + visible);
    }

    private void tryInitData() {
        if (isAvailable() && getUserVisibleHint() && !isLoaded) {
            isLoaded = true;
            performInitData();
        }
    }

    private void performInitData() {
        loge("initData", "" + isAvailable());
        initData();
    }

    protected abstract void initArguments(Bundle data);

    protected abstract void initView(View view);

    protected abstract void initData();

    public boolean isAvailable() {
        return isAdded() && !isDetached() && !isRemoving();
    }

    public void log(String method, String msg) {
        Log.i("TEST", mPageIndex + " " + isAvailable() + " --> " + method + " --> " + msg);
    }

    public void loge(String method, String msg) {
        Log.e("TEST", mPageIndex + " " + isAvailable() + " --> " + method + " --> " + msg);
    }
}