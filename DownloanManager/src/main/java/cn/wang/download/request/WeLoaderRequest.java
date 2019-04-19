package cn.wang.download.request;
import java.io.File;

import okhttp3.Request;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/4/15
 */
public class WeLoaderRequest {

    final String TAG = "WeLoaderRequest : ";
    private String url;
    private File mTargetFile;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public File getTargetFile() {
        return mTargetFile;
    }

    public WeLoaderRequest setFilePath(String mFilePath) {
        mTargetFile = new File(mFilePath);
        checkoutFile();
        return this;
    }

    public void setTargetFile(File mTargetFile) {
        this.mTargetFile = mTargetFile;
        checkoutFile();
    }

    public Request createRequest(long startPoints) {
        Request request = new Request.Builder()
                .addHeader("RANGE", "bytes=" + startPoints + "-")
                .url(url)
                .build();
        return request;
    }

    private void checkoutFile() {
        if (mTargetFile.exists()) {
            mTargetFile.delete();
        }
    }
}
