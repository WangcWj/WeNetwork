package cn.wang.network.builder.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import cn.wang.network.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends BaseFragment {

    public static FirstFragment newInstance() {
        Bundle args = new Bundle();
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void pageLoadDataOnce() {
        super.pageLoadDataOnce();
    }

    @Override
    protected void initListener() {

    }
}
