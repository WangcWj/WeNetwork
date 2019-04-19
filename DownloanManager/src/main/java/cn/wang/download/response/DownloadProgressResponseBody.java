package cn.wang.download.response;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/4/15
 */
public class DownloadProgressResponseBody extends ResponseBody {
    private ResponseBody responseBody;
    private DownloadProgressListener downloadProgressListener;

    public DownloadProgressResponseBody(ResponseBody responseBody, DownloadProgressListener downloadProgressListener) {
        this.responseBody = responseBody;
        this.downloadProgressListener = downloadProgressListener;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        return Okio.buffer(createSource(responseBody.source()));
    }

    private Source createSource(Source source) {
        return new ForwardingSource(source) {
            long read;
            long currentRead;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                read = super.read(sink, byteCount);
                currentRead += read != -1 ? read : 0;
                if (null != downloadProgressListener) {
                    downloadProgressListener.progress(byteCount, currentRead, currentRead == byteCount);
                }
                return read;
            }
        };
    }
}
