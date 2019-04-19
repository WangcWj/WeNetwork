package cn.wang.download.response;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/4/19
 */
public class WeLoaderResponse implements Callback ,DownloadProgressListener{

    private volatile long mCurrentPoint = 0L;


    public long getBreakPoint() {
        return mCurrentPoint;
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

    }

    @Override
    public void progress(long count, long read, boolean isFinish) {
        mCurrentPoint+=read;
    }
}
