package cn.wang.network.builder.api;


import java.util.List;
import java.util.Map;

import cn.wenet.networkcomponent.base.BaseResultBean;
import cn.wang.network.builder.bean.JokeBean;
import cn.wang.network.builder.bean.WeatherBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by WANG on 17/11/23.
 */

public interface ApiService {
    //http://v5.pc.duomi.com/search-ajaxsearch-searchall?kw=%E8%96%9B%E4%B9%8B%E8%B0%A6&pi=1&pz=20
    //返回的data一定为数组的时候这样使用
    @GET("meituApi")
    Observable<BaseResultBean<List<JokeBean>>> getSingleData(@QueryMap Map<String, Object> params);

    @GET("meituApi")
    Observable<String> getSingleDataForString(@QueryMap Map<String, Object> params);

    //https://www.apiopen.top/weatherApi?city=%E6%88%90%E9%83%BD
    @GET("weatherApi")
    Observable<BaseResultBean<WeatherBean>> getCityWeather(@QueryMap Map<String, Object> params);




}
