package cn.wang.network;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import cn.wang.network.builder.api.BaseAPI;
import cn.wenet.networkcomponent.control.NetControl;

/**
 * Created by WANG on 18/3/24.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        leakCarry();
        NetControl.init(BaseAPI.BASE_URL,this).successCode(200);
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
