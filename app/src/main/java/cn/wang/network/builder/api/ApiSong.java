package cn.wang.network.builder.api;



import cn.wang.network.builder.bean.SongBean;
import cn.wenet.networkcomponent.core.WeNetObservable;
import retrofit2.http.GET;
import retrofit2.http.Headers;


/**
 * Created by WANG on 17/11/23.
 */

public interface ApiSong {
    //getJoke?page=1&count=2&type=video

    @Headers({BaseAPI.SINGING_URL_FLAG})
    @GET("getJoke")
    WeNetObservable<SongBean> getPoetry();


}
