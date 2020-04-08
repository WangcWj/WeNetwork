package cn.wang.network.builder.api;


import java.util.Map;

import cn.wang.network.builder.bean.WeatherBean;
import cn.wenet.networkcomponent.core.WeNetResult;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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
    @POST("simpleWeather/query")
    @FormUrlEncoded
    Observable<WeatherBean> getCityWeather(@FieldMap Map<String, Object> params);

    @Headers({BaseAPI.WEATHER_URL_FLAG})
    @POST("simpleWeather/query")
    WeNetResult<WeatherBean> getCityWeatherByPost();


}
