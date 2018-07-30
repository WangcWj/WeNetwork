# NetComponent
#### Retrofit+Rxjava组合的高性能网络框架,代码解耦,各个功能独立维护,网络请求中只维护一个Retrofit和OkhttpClient的实例.功能如下:   
#### 1.链式调用.   
#### 2.动态改变BaseUrl和ApiServer.   
#### 3.动态添加拦截器.(动态的添加你想要添加的然后拦截器).   
#### 4.自定义异常处理让你的Resonse都在你的意料之中.   
#### Demo如下:
```
 NetControl.request(MainActivity.this)
                        .addParams("page", "2")
                        .execute(new NetCallBack<JokeBean, ApiService>() {
                            @Override
                            public Observable<BaseResultBean<JokeBean>> getMethod(ApiService api, Map<String, Object> params){
                                return api.getSingleData(params);
                            }
                            @Override
                            public void onSuccess(JokeBean json) {
                                Log.e("WANG", "MainActivity.onNext." + json.toString());
                               // jsonText.setText(json);
                            }
                            @Override
                            public void onError(NetException e) {

                            }
                        });

```

### V1.0.0     
18-7-19:   
日志:改版为基础版本,目前支持,GET,POST请求,动态替换BaseUrl,动态替换ApiServer,动态添加去除日志拦截器.   
18-7-30:   
日志:增加请求返回结果为String类型的Convert.Factory,增加异常拦截判断异常类型以及code错误码的自定义判断.


