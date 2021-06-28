package cn.wang.network.builder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/12/15
 */
public class MeService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("cc.wang","MeService.onCreate.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
