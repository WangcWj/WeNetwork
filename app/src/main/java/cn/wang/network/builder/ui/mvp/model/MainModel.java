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
public class MainModel extends BaseMvpModel {


    private MainPresenterApi presenterApi;


    public MainModel(NetLifecycleControl lifecycleControl, MainPresenterApi presenterApi) {
        super(lifecycleControl);
        this.presenterApi = presenterApi;
    }

    public void getCityWeather(String city) {
        WeNetwork.request(lifecycleControl)
                .addParams("city", city)
                .apiMethod(WeNetwork.getApiServiceInstance(ApiService.class).getCityWeather())
                .execute(new WeNetworkCallBack<WeatherBean>() {
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
        WeNetwork.request(lifecycleControl)
                .addParams("page", "1")
                .addParams("count", "2")
                .addParams("type", "video")
                .apiMethod(WeNetwork.getApiServiceInstance(ApiSong.class).getPoetry())
                .execute(new WeNetworkCallBack<SongBean>() {
                    @Override
                    public void onSuccess(SongBean bean) {
                        presenterApi.setSearchData(bean, true);
                    }

                    @Override
                    public void onError(NetException e) {
                        presenterApi.setSearchData(null, false);
                    }
                });
    }

}
