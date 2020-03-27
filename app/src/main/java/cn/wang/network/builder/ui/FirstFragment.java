package cn.wang.network.builder.ui;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.View;

import cn.wenet.networkcomponent.core.WeNetwork;
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
     findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             getData();
         }
     });
    }

    @Override
    protected void pageLoadDataOnce() {
       getData();
    }

    private void getData(){
        //getJoke?page=1&count=2&type=video

    }

    @Override
    protected void initListener() {

    }
}
