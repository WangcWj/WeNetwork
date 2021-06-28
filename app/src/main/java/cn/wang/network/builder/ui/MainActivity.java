package cn.wang.network.builder.ui;

import androidx.lifecycle.LifecycleObserver;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.wang.network.builder.bean.SongBean;
import cn.wang.network.builder.ui.mvp.presenter.BaseMvpPresenter;
import cn.wang.network.builder.ui.mvp.view.BaseMvpView;
import cn.wang.network.R;
import cn.wang.network.builder.bean.WeatherBean;


public class MainActivity extends BaseActivity implements BaseMvpView {

    private TextView jsonText;

    private ImageView imageView;
    private BaseMvpPresenter mPresenter;
    private Map<String, String> mMaps = new HashMap<>();

    public static boolean isShow = false;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        jsonText = findViewById(R.id.jsonText);
        jsonText.setText("第一个的哈哈看理解");




        findViewById(R.id.uselog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.nouselog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewPagerActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.body).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void setData(WeatherBean bean) {
        WeatherBean.ResultBean result = bean.getResult();
        if (result == null) {
            jsonText.setText("result 为空");
            return;
        }
        WeatherBean.ResultBean.RealtimeBean realtime = result.getRealtime();
        if (null == realtime) {
            jsonText.setText("realtime 为空");
            return;
        }
        String beanCity = realtime.getDirect();
        jsonText.setText(beanCity);
    }

    @Override
    public void setSearchData(SongBean beans) {
        if (null != beans) {
            List<SongBean.ResultBean> result = beans.getResult();
            if (null != result && result.size() > 0) {
                SongBean.ResultBean resultBean = result.get(0);
                String thumbnail = resultBean.getText();
                jsonText.setText(thumbnail);
            }
        }
    }
}
