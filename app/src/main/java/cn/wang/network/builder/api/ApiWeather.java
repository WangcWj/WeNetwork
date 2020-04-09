package cn.wang.network.builder.api;


import java.util.Map;

import cn.wang.network.builder.bean.WeatherBean;
import cn.wenet.networkcomponent.core.WeNetResult;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by WANG on 17/11/23.
 */

public interface ApiWeather {
    /**
     * https://www.apiopen.top/weatherApi?city=%E6%88%90%E9%83%BD
     *
     * @return
     */
    @Headers({BaseAPI.WEATHER_URL_FLAG})
    @GET("simpleWeather/query")
    Observable<WeatherBean> getCityWeather(@QueryMap Map<String, Object> params);

    @Headers({BaseAPI.WEATHER_URL_FLAG})
    @POST("simpleWeather/query")
    @FormUrlEncoded
    WeNetResult<WeatherBean> getCityWeatherByPost(@Field("key") String key);

    @Headers({BaseAPI.WEATHER_URL_FLAG})
    @POST("simpleWeather/query")
    WeNetResult<WeatherBean> getCityWeatherByBody();

    @Headers({BaseAPI.IP_URL_FLAG})
    @POST("back/orgApply/join")
    WeNetResult<String> getHomeData();


}
