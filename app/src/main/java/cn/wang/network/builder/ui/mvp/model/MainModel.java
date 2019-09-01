package cn.wang.network.builder.ui.mvp.model;

import java.util.List;

import cn.wang.network.builder.api.ApiService;
import cn.wang.network.builder.api.ApiSong;
import cn.wang.network.builder.bean.SongBean;
import cn.wang.network.builder.bean.WeatherBean;
import cn.wang.network.builder.ui.mvp.presenter.MainPresenterApi;
import cn.wenet.networkcomponent.WeNetwork;
import cn.wenet.networkcomponent.base.NetBaseResultBean;
import cn.wenet.networkcomponent.base.NetLifecycleControl;
import cn.wenet.networkcomponent.exception.NetException;
import cn.wenet.networkcomponent.request.WeNetworkCallBack;
import io.reactivex.Observable;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/8/28
 */
public class MainModel extends BaseMvpModel {

    NetLifecycleControl lifecycleControl;
    MainPresenterApi presenterApi;


    public MainModel(NetLifecycleControl lifecycleControl, MainPresenterApi presenterApi) {
        this.lifecycleControl = lifecycleControl;
        this.presenterApi = presenterApi;
    }

    public void getCityWeather(String city) {
        Observable<NetBaseResultBean<WeatherBean>> cityWeather = WeNetwork.getApiService(ApiService.class).getCityWeather(WeNetwork.getParams());
        WeNetwork.request(lifecycleControl)
                .addParams("city", city)
                .apiMethod(cityWeather)
                .execute(new WeNetworkCallBack<WeatherBean>() {
                    @Override
                    public void onSuccess(WeatherBean weatherBean) {
                        presenterApi.weatherData(weatherBean,true);
                    }

                    @Override
                    public void onError(NetException e) {
                        presenterApi.weatherData(null,false);
                    }
                });
    }

    public void getSearchData() {
        WeNetwork.request(lifecycleControl)
                .addParams("page", "1")
                .addParams("count", "2")
                .addParams("type", "video")
                .apiMethod(WeNetwork.getApiService(ApiSong.class).getPoetry(WeNetwork.getParams()))
                .execute(new WeNetworkCallBack<SongBean>() {
                    @Override
                    public void onSuccess(SongBean bean) {
                        presenterApi.setSearchData(bean,true);
                    }

                    @Override
                    public void onError(NetException e) {
                        presenterApi.setSearchData(null,false);
                    }
                });
    }
}
