package cn.wang.download;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/4/15
 */
public interface ProgressListener {

    void progress(long count,long read,boolean isFinish);

}
