package cn.wang.network.builder.ui;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import cn.example.wang.networkcomponent.NetControl;
import cn.example.wang.networkcomponent.base.BaseResultBean;
import cn.example.wang.networkcomponent.exception.NetException;
import cn.example.wang.networkcomponent.request.NetCallBack;
import cn.wang.network.R;
import cn.wang.network.builder.api.ApiService;
import cn.wang.network.builder.bean.WeatherBean;
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
                        .addParams("city", "杭州")
                        .needPrintLog(true)
                        .execute(new NetCallBack<WeatherBean, ApiService>() {
                            //返回的data一定为数组的时候这样使用

                            @Override
                            public Observable<BaseResultBean<WeatherBean>> getMethod(ApiService api, Map<String, Object> params) {
                                return api.getCityWeather(params);
                            }

                            @Override
                            public void onSuccess(WeatherBean weatherBean) {
                                List<WeatherBean.ForecastBean> forecast = weatherBean.getForecast();
                                jsonText.setText(forecast.toString());
                            }

                            @Override
                            public void onError(NetException e) {
                                Log.e("WANG","MainActivity.onError.Code   "+e.getCode()+"    Message     "+e.getMessage()+"       Throwable    " + e.getThrowable() );
                            }
                        });
            }
        });
    }

    @Override
    protected void initData(){

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
