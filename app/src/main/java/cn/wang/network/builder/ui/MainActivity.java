package cn.wang.network.builder.ui;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Map;

import cn.wang.network.R;
import cn.wang.network.builder.api.ApiService;
import cn.wang.network.builder.bean.JokeBean;
import cn.wang.network.builder.net.NetControl;
import cn.wang.network.builder.net.request.NetCallBack;
import io.reactivex.Observable;

public class MainActivity extends BaseActivity{

   private TextView jsonText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        jsonText = findViewById(R.id.jsonText);
        findViewById(R.id.nouselog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetControl.request(MainActivity.this)
                        .addParams("page", "2")
                        .needPrintLog(true)
                        .execute(new NetCallBack<JokeBean, ApiService>() {
                            @Override
                            public Observable<JokeBean> getMethod(ApiService api, Map<String, Object> params) {
                                return api.getSingleData(params);
                            }

                            @Override
                            public void onSuccess(JokeBean json) {
                                Log.e("WANG", "MainActivity.onNext." + json.toString());
                               // jsonText.setText(json);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("WANG","MainActivity.onError."+e );
                            }
                        });
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        findViewById(R.id.uselog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerActivity.start(thisActivity());
            }
        });
    }


}
