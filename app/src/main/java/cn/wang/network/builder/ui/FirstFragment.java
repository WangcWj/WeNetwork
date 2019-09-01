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
import cn.wenet.networkcomponent.request.WeNetworkCallBack;
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
        //getJoke?page=1&count=2&type=video
        WeNetwork.request(this)
                .addParams("page", "1")
                .addParams("count", "2")
                .addParams("type", "video")
                .apiMethod(WeNetwork.getApiService(ApiSong.class).getPoetry(WeNetwork.getParams()))
                .execute(new WeNetworkCallBack<SongBean>() {
                    @Override
                    public void onSuccess(SongBean bean) {

                    }

                    @Override
                    public void onError(NetException e) {

                    }
                });
    }

    @Override
    protected void initListener() {

    }
}
