package cn.wang.network.builder.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.List;
import java.util.Map;

import cn.example.wang.networkcomponent.NetControl;
import cn.example.wang.networkcomponent.base.BaseResultBean;
import cn.example.wang.networkcomponent.exception.NetException;
import cn.example.wang.networkcomponent.request.NetCallBack;
import cn.wang.network.R;
import cn.wang.network.builder.api.ApiService;
import cn.wang.network.builder.bean.JokeBean;
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

    }

    @Override
    protected void pageLoadDataOnce() {
        NetControl.request(this)
                .addParams("page", "2")
                .execute(new NetCallBack<List<JokeBean>, ApiService>() {
                    //返回的data一定为数组的时候这样使用
                    @Override
                    public Observable<BaseResultBean<List<JokeBean>>> getMethod(ApiService api, Map<String, Object> params) {
                        return api.getSingleData(params);
                    }

                    @Override
                    public void onSuccess(List<JokeBean> json) {

                    }

                    @Override
                    public void onError(NetException e) {
                        Log.e("WANG","MainActivity.onError.Code   "+e.getCode()+"    Message     "+e.getMessage()+"       Throwable    " + e.getThrowable() );
                    }
                });
    }

    @Override
    protected void initListener() {

    }
}
