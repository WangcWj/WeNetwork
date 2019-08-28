package cn.wang.network.builder.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import java.util.Map;

import cn.wang.network.builder.bean.SongBean;
import cn.wenet.networkcomponent.WeNetwork;
import cn.wenet.networkcomponent.exception.NetException;
import cn.wenet.networkcomponent.intercepter.NetInterceptorFactory;
import cn.wenet.networkcomponent.request.NetRequest;
import cn.wang.network.R;
import cn.wang.network.builder.api.ApiSong;
import cn.wang.network.builder.api.BaseAPI;
import io.reactivex.Observable;

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
        WeNetwork.request(this)
                .baseUrl(BaseAPI.BASE_SINGING_URL)
                .addParams("name", "忆江南")
                .addInterceptor(NetInterceptorFactory.logInterceptor());
    }

    @Override
    protected void initListener() {

    }
}
