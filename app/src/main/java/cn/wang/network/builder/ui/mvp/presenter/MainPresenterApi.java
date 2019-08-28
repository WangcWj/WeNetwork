package cn.wang.network.builder.ui.mvp.presenter;

import cn.wang.network.builder.bean.WeatherBean;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/8/28
 */
public interface MainPresenterApi {

    void weatherData(WeatherBean bean,boolean success);

}
