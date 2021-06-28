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
                .addBaseUrl(BaseAPI.BASE_FLAG, BaseAPI.BASE_URL)
                .addBaseUrl(BaseAPI.GAODE_FLAG, BaseAPI.BASE_GAODE_URL)
                .addBaseInterceptor(new BaseLogInterceptor())
                .successCode(200);
    }

    private void leakCarry() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
