package cn.wang.network.builder.ui.mvp.model;


import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import cn.wang.network.builder.api.ApiWeather;
import cn.wang.network.builder.api.ApiSong;
import cn.wang.network.builder.api.NewRequest;
import cn.wang.network.builder.bean.IpInfoBean;
import cn.wang.network.builder.bean.SongBean;
import cn.wang.network.builder.bean.WeatherBean;
import cn.wang.network.builder.ui.BaseNetCallback;
import cn.wang.network.builder.ui.mvp.presenter.MainPresenterApi;
import cn.wenet.networkcomponent.core.WeNetWork;
import cn.wenet.networkcomponent.debug.exception.NetException;
import cn.wenet.networkcomponent.core.WeNetworkCallBack;
import io.reactivex.Observable;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/8/28
 */
public class MainModel extends BaseMvpModel {


    private MainPresenterApi presenterApi;


    public MainModel(Context lifecycleControl, MainPresenterApi presenterApi) {
        super(lifecycleControl);
        this.presenterApi = presenterApi;
    }

    public void getIsp(){
        WeNetWork.apiMethod(ApiWeather.class)
                .getIpInfo()
                .addParams("ip", "10.133.3.144")
                .addParams("json", "true")
                .showProgress(true)
                .bindLife(mContext)
                .execute(new BaseNetCallback<IpInfoBean>() {
                    @Override
                    public void onSuccess(IpInfoBean ipInfoBean) {
                        super.onSuccess(ipInfoBean);

                    }
                });
    }


    public void getCityWeather(String city) {

    }

    public void getIP() {
        WeNetWork.apiMethod(ApiWeather.class)
                .getIpInfo()
                .bindLife(mContext)
                .execute(new BaseNetCallback<SongBean>() {
                    @Override
                    public void onSuccess(SongBean songBean) {

                    }

                    @Override
                    public void onError(NetException e) {

                    }
                });

    }

    public void getDataByPost() {

    }

    public void getDataBybody() {


    }
}
