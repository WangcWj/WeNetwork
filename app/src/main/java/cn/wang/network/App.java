package cn.wang.network;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import cn.wang.network.builder.api.BaseAPI;
import cn.wenet.networkcomponent.core.WeNetWork;
import cn.wenet.networkcomponent.core.Control;
import cn.wenet.networkcomponent.okhttp.intercepter.BaseLogInterceptor;

/**
 * Created by WANG on 18/3/24.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        leakCarry();
        WeNetWork.init(this)
                .addBaseUrl(Control.DEFAULT_BASE_URL_FLAG, BaseAPI.BASE_URL)
                .addBaseUrl(BaseAPI.WEATHER_URL_FLAG, BaseAPI.WEATHER_BASE_URL)
                .addBaseInterceptor(new BaseLogInterceptor())
                .successCode(200);
    }

    private void leakCarry() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
