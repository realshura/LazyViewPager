package com.feeling.lazyviewpager;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Created by chenll on 2016/11/18.
 */

public class NewsFragment extends BasePageFragment {

    public static final String KEY_INDEX = "item_index";

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.textView_title)
    TextView mTitleText;

    @BindView(R.id.view_content)
    View mContentView;

    private int mPageIndex;

    public static NewsFragment newInstance(int index) {
        NewsFragment fragment = new NewsFragment();
        Bundle data = new Bundle();
        data.putInt(KEY_INDEX, index);
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_news, container, false);
    }

    @Override
    protected void initArguments(Bundle data) {
        if (data != null) {
            mPageIndex = data.getInt(KEY_INDEX);
        }
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isAvailable()) {
                    return;
                }
                showContent();
                mTitleText.setText("新闻 " + mPageIndex);
                mProgressBar.setVisibility(View.GONE);
            }
        }, 350);
    }

    @Override
    public void onVisibleChanged(boolean visible) {

    }

    private void showContent() {
        mContentView.setVisibility(View.VISIBLE);
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setDuration(350);
        mContentView.startAnimation(animation);
    }

}
