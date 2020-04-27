package cn.wang.network.builder.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.wang.network.R;
import cn.wang.network.builder.api.ApiSong;
import cn.wang.network.builder.bean.SongBean;
import cn.wenet.networkcomponent.core.WeNetWork;
import cn.wenet.networkcomponent.core.WeNetworkCallBack;
import cn.wenet.networkcomponent.debug.exception.NetException;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends BaseFragment {

    public static FirstFragment newInstance() {
        Bundle args = new Bundle();
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView mText;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView() {
        mText = (TextView) findViewById(R.id.json_text);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    @Override
    protected void pageLoadDataOnce() {
        getData();
    }

    private void getData() {
        WeNetWork.apiMethod(ApiSong.class)
                .getPoetry()
                .bindLife(this)
                .addParams("page", "1")
                .addParams("count", "2")
                .addParams("type", "video")
                .execute(new WeNetworkCallBack<SongBean>() {
                    @Override
                    public void onSuccess(SongBean songBean) {
                        List<SongBean.ResultBean> result = songBean.getResult();
                        if (null != result && result.size() > 0) {
                            SongBean.ResultBean resultBean = result.get(0);
                            String thumbnail = resultBean.getText();
                            mText.setText(thumbnail);
                        }
                    }

                    @Override
                    public void onError(NetException e) {
                        Toast.makeText(mContext, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    protected void initListener() {

    }
}
