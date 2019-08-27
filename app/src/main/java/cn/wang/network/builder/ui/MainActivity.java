package cn.wang.network.builder.ui;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import cn.wenet.networkcomponent.WeNetwork;
import cn.wenet.networkcomponent.base.NetBaseResultBean;
import cn.wenet.networkcomponent.exception.NetException;
import cn.wenet.networkcomponent.request.NetCallBack;
import cn.wenet.networkcomponent.request.NetRequest;
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
    protected void init() {

        jsonText = findViewById(R.id.jsonText);

        jsonText.setText("第一个的哈哈看理解");
        //单例切换BaseUrl会影响到其他的BaseUrl
        findViewById(R.id.nouselog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonText.setText("");
                WeNetwork.request(MainActivity.this)
                        .addParams("city", "杭州")
                        .execute(new NetCallBack<WeatherBean>() {
                            @Override
                            public Observable<NetBaseResultBean<WeatherBean>> getMethod(NetRequest request, Map<String, Object> params) {
                                return request.getApiService(ApiService.class).getCityWeather(params);
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

        findViewById(R.id.uselog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerActivity.start(thisActivity());
            }
        });
    }




}
