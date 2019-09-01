package cn.wang.network;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import cn.wang.network.builder.api.BaseAPI;
import cn.wenet.networkcomponent.WeNetwork;
import cn.wenet.networkcomponent.control.Control;

/**
 * Created by WANG on 18/3/24.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        leakCarry();
        WeNetwork.init(this)
                //这个是默认的的BaseUrl
                .addBaseUrl(Control.DEFAULT_BASE_URL_FLAG, BaseAPI.BASE_URL)
                .addBaseUrl(BaseAPI.SINGING_URL_FLAG, BaseAPI.BASE_SINGING_URL)
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
