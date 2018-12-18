package cn.example.wang.networkcomponent.base;

import io.reactivex.disposables.Disposable;

/**
 *
 * @author WANG
 * @date 2018/7/19
 */

public interface NetLifecycleControl {

   void addDisposable(Disposable disposable);

}
