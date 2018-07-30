package cn.wang.network.builder.ui;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import cn.wang.network.R;
import cn.wang.network.builder.api.ApiService;
import cn.wang.network.builder.bean.JokeBean;
import cn.wang.network.builder.net.NetControl;
import cn.wang.network.builder.net.base.BaseResultBean;
import cn.wang.network.builder.net.exception.NetException;
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
                        .execute(new NetCallBack<List<JokeBean>, ApiService>() {
                            //返回的data一定为数组的时候这样使用
                            @Override
                            public Observable<BaseResultBean<List<JokeBean>>> getMethod(ApiService api, Map<String, Object> params) {
                                return api.getSingleData(params);
                            }

                            @Override
                            public void onSuccess(List<JokeBean> jokeBeans) {
                               Log.e("WANG","MainActivity.onSuccess."+jokeBeans.toString() );
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
