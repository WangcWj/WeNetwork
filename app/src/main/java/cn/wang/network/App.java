package cn.wang.network;

import android.app.Application;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

import cn.wang.network.builder.net.NetControl;
/**
 * Created by WANG on 18/3/24.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("WANG","App.onCreate." );
        leakCarry();
        NetControl.getInstance().init();
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
