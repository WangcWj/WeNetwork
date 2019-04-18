package cn.wang.download;

import android.text.TextUtils;

import java.io.File;

import okhttp3.Request;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/4/15
 */
public class WeRequest {

    final String TAG = "WeRequest : ";
    private String url;
    private String mFilePath;
    private File mTargetFile;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getmFilePath() {
        return mFilePath;
    }

    public WeRequest setFilePath(String mFilePath) {
        if (TextUtils.isEmpty(mFilePath)) {
            throw new NullPointerException(TAG + "mFilePath is null !");
        }
        this.mFilePath = mFilePath;
        File file = new File(mFilePath);
        if (file.exists()) {
            file.delete();
        }
        mTargetFile = file;
        return this;
    }

    public void setTargetFile(File mTargetFile) {
        if (null == mTargetFile) {
            throw new NullPointerException(TAG + "File is null !");
        }
        this.mTargetFile = mTargetFile;
        if (mTargetFile.exists()) {
            mTargetFile.delete();
        }
    }

    public Request createRequest(long startPoints){
       return new Request.Builder()
                .addHeader("RANGE", "bytes=" + startPoints + "-")
                .url(url)
                .build();
    }
}
