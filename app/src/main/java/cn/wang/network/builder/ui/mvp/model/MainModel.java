package cn.wang.network.builder.ui.mvp.model;


import android.content.Context;

import cn.wang.network.builder.api.ApiService;
import cn.wang.network.builder.api.ApiSong;
import cn.wang.network.builder.bean.SongBean;
import cn.wang.network.builder.bean.WeatherBean;
import cn.wang.network.builder.ui.mvp.presenter.MainPresenterApi;
import cn.wenet.networkcomponent.base.NetBaseResultBean;
import cn.wenet.networkcomponent.core.WeNetwork;
import cn.wenet.networkcomponent.base.NetLifecycleControl;
import cn.wenet.networkcomponent.exception.NetException;
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

    public void getCityWeather(String city) {
        Observable<NetBaseResultBean<WeatherBean>> weather = WeNetwork.getApiServiceInstance(ApiService.class).getCityWeather();
        WeNetwork.request()
                .addParams("city", city)
                .apiMethod(weather)
                .execute(new WeNetworkCallBack<WeatherBean>(mContext) {
                    @Override
                    public void onSuccess(WeatherBean bean) {
                        presenterApi.weatherData(bean, true);
                    }
                    @Override
                    public void onError(NetException e) {
                        presenterApi.weatherData(null, false);
                    }
                });
    }

    public void getSearchData() {
        WeNetwork.apiMethod(ApiSong.class)
                .getPoetry()
                .addParams("page", "1")
                .addParams("count", "2")
                .addParams("type", "video")
                .execute(new WeNetworkCallBack<SongBean>(mContext) {
                    @Override
                    public void onSuccess(SongBean songBean) {
                        presenterApi.setSearchData(songBean, true);
                    }

                    @Override
                    public void onError(NetException e) {
                        presenterApi.setSearchData(null, false);
                    }
                });

    }

}
