package cn.wang.network.builder.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cn.wang.network.R;
import cn.wang.network.builder.api.ApiSong;
import cn.wang.network.builder.bean.SongBean;
import cn.wenet.networkcomponent.core.WeNetWork;
import cn.wenet.networkcomponent.core.WeNetworkCallBack;
import cn.wenet.networkcomponent.debug.exception.NetException;

/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/4/13
 */
public class NetDialog extends Dialog {

    public NetDialog(@NonNull Context context) {
        super(context);
    }

    public NetDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected NetDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }





    public static class Builder{

        private NetDialog netDialog;
        private Context context;
        private TextView jsonText;

        public Builder(Context context) {
            this.context = context;
        }

        public NetDialog create(){
            netDialog = new NetDialog(context);
            Window window = netDialog.getWindow();
            if(null != window){
                window.setContentView(R.layout.dialog_net);
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                window.findViewById(R.id.dialog_request).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getData();
                    }
                });
                jsonText = window.findViewById(R.id.dialog_json);
            }
            return netDialog;
        }

        private void getData() {
            WeNetWork.apiMethod(ApiSong.class)
                    .getPoetry()
                    .bindLife(netDialog)
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
                                jsonText.setText(thumbnail);
                            }
                        }

                        @Override
                        public void onError(NetException e) {
                            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }


    }


}
