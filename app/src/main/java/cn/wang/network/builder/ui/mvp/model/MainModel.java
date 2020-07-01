package cn.wang.network.builder.ui.mvp.model;


import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import cn.wang.network.builder.api.ApiWeather;
import cn.wang.network.builder.api.ApiSong;
import cn.wang.network.builder.api.NewRequest;
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

    public void getCityWeather(String city) {
        WeNetWork.apiMethod(ApiWeather.class)
                .getCityWeather()
                .addParams("city", city)
                .addParams("key", "a1ae58f53edaf0518c72f41adc3987a9")
                .execute(new BaseNetCallback<WeatherBean>() {
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
                .bindLife(mContext)
                .addParams("page", "1")
                .addParams("count", "2")
                .addParams("type", "video")
                .execute(new BaseNetCallback<SongBean>() {
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
                .getCityWeatherByPost("a1ae58f53edaf0518c72f41adc3987a9")
                .addParams("city", "洛阳")
                .bindLife(mContext)
                .execute(new BaseNetCallback<WeatherBean>() {
                    @Override
                    public void onSuccess(WeatherBean songBean) {
                        presenterApi.weatherData(songBean, true);
                    }

                    @Override
                    public void onError(NetException e) {
                        presenterApi.weatherData(null, false);
                    }
                });
    }

    public void getDataBybody() {

        WeNetWork.apiMethod(ApiWeather.class)
                .getHomeData()
                .asBody()
                .bindLife(mContext)
                .bodyToJson(new NewRequest(0,"v1"))
                .execute(new BaseNetCallback<SongBean>() {
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
