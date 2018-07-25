package cn.wang.network.builder.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import cn.wang.network.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends BaseFragment {

    public static ThreeFragment newInstance() {
        Bundle args = new Bundle();
        ThreeFragment fragment = new ThreeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_three;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void pageOnResume() {
        super.pageOnResume();
    }

}
