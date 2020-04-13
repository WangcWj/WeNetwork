package cn.wang.network.builder.ui;


import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import cn.wang.network.R;
import cn.wang.network.builder.ui.dialog.NetDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends BaseFragment {

    public static SecondFragment newInstance() {
        Bundle args = new Bundle();
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initView() {
       findViewById(R.id.dialog_btn).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               new NetDialog.Builder(getActivity()).create().show();
           }
       });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void pageOnResume() {
        super.pageOnResume();
    }

}
