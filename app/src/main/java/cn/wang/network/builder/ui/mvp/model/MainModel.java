package cn.wang.network.builder.ui.mvp.model;


import cn.wang.network.builder.api.ApiService;
import cn.wang.network.builder.api.ApiSong;
import cn.wang.network.builder.bean.SongBean;
import cn.wang.network.builder.bean.WeatherBean;
import cn.wang.network.builder.ui.mvp.presenter.MainPresenterApi;
import cn.wenet.networkcomponent.WeNetwork;
import cn.wenet.networkcomponent.base.NetLifecycleControl;
import cn.wenet.networkcomponent.exception.NetException;
import cn.wenet.networkcomponent.request.WeNetworkCallBack;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/8/28
 */
public class MainModel extends BaseMvpModel implements WeNetworkCallBack<Object> {

    NetLifecycleControl lifecycleControl;
    MainPresenterApi presenterApi;


    public MainModel(NetLifecycleControl lifecycleControl, MainPresenterApi presenterApi) {
        this.lifecycleControl = lifecycleControl;
        this.presenterApi = presenterApi;
    }

    public void getCityWeather(String city) {
        WeNetwork.request(lifecycleControl)
                .addParams("city", city)
                .apiMethod(WeNetwork.getApiServiceInastance(ApiService.class).getCityWeather())
                .execute(this);
    }

    public void getSearchData() {
        WeNetwork.request(lifecycleControl)
                .addParams("page", "1")
                .addParams("count", "2")
                .addParams("type", "video")
                .apiMethod(WeNetwork.getApiServiceInastance(ApiSong.class).getPoetry())
                .execute(this);
    }

    @Override
    public void onSuccess(Object bean) {
        if (bean instanceof WeatherBean) {
            presenterApi.weatherData((WeatherBean) bean, true);
        } else if (bean instanceof SongBean) {
            presenterApi.setSearchData((SongBean) bean, true);
        }
    }

    @Override
    public void onError(NetException e) {
        presenterApi.weatherData(null, false);
        presenterApi.setSearchData(null, false);
    }
}
