package cn.wang.network.builder.api;



import cn.wenet.networkcomponent.base.NetBaseResultBean;
import cn.wang.network.builder.bean.WeatherBean;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by WANG on 17/11/23.
 */

public interface ApiService {
    //https://www.apiopen.top/weatherApi?city=%E6%88%90%E9%83%BD
    @GET("weatherApi")
    Observable<NetBaseResultBean<WeatherBean>> getCityWeather();




}
