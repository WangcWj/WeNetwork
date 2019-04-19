package cn.wang.download.response;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/4/15
 */
public interface DownloadProgressListener {

    void progress(long count,long read,boolean isFinish);

}
