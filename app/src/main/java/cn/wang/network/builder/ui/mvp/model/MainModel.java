package cn.wang.network.builder.ui.mvp.model;


import android.content.Context;

import java.util.Map;

import cn.wang.network.builder.NewRequest;
import cn.wang.network.builder.api.ApiWeather;
import cn.wang.network.builder.api.ApiSong;
import cn.wang.network.builder.bean.SongBean;
import cn.wang.network.builder.bean.WeatherBean;
import cn.wang.network.builder.ui.mvp.presenter.MainPresenterApi;
import cn.wenet.networkcomponent.core.WeNetWork;
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
        Map<String, Object> baseParams = WeNetWork.getBaseParams();
        baseParams.put("city", city);
        baseParams.put("key", "a1ae58f53edaf0518c72f41adc3987a9");
        Observable<WeatherBean> cityWeather = WeNetWork.getApiServiceInstance(ApiWeather.class).getCityWeather(baseParams);
        WeNetWork.request(cityWeather)
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
        WeNetWork.apiMethod(ApiSong.class)
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

    public void getDataByPost() {
        WeNetWork.apiMethod(ApiWeather.class)
                .getCityWeatherByPost()
                .addParams("city", "洛阳")
                .addParams("key", "a1ae58f53edaf0518c72f41adc3987a9")
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

    public void getDataBybody() {
        WeNetWork.apiMethod(ApiWeather.class)
                .getCityWeatherByPost()
                .asBody()
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
