package cn.wenet.networkcomponent.request;

/**
 * Created to :
 *
 * @author WANG
 * @date 2018/12/18
 */

public interface WeNetSuccessCallBack<T> extends WeNetBaseCallBack {

    void onSuccess(T t);
}
