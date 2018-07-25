package cn.wang.network.builder.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import cn.wang.network.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourFragment extends BaseFragment {


    public static FourFragment newInstance() {
        Bundle args = new Bundle();
        FourFragment fragment = new FourFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_four;
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
