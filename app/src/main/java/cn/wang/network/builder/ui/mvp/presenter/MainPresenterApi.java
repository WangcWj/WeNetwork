package cn.wang.network.builder.ui.mvp.presenter;

import java.util.List;

import cn.wang.network.builder.bean.SongBean;
import cn.wang.network.builder.bean.WeatherBean;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/8/28
 */
public interface MainPresenterApi {

    void weatherData(WeatherBean bean,boolean success);

    void setSearchData(SongBean bean, boolean success);

    void getDataByPost();
    void getDataByBody();

    void getIp();

}
