package cn.wang.network.builder.api;


import java.util.Map;

import cn.wang.network.builder.bean.SongBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;


/**
 * Created by WANG on 17/11/23.
 */

public interface ApiSong {
    //getJoke?page=1&count=2&type=video

    @Headers({BaseAPI.SINGING_URL_FLAG})
    @GET("getJoke")
    Observable<SongBean> getPoetry(@QueryMap Map<String, Object> params);


}
