# WeNetWork
#### 一 框架简介：

##### OkHttp 3+Retrofit 2+Rxjava 2组合的高性能、超解耦、动态处理、链式请求、全局单例的网络框架。功能如下：   

1. 单例模式，保持一个全局的OkHttp和Retrofit对象。 

2. 真正的链式调用，不基于RxJava的链式模式
3. 单例模式下动态改变BaseUrl和ApiServer，选择性的添加拦截器。
4. 完全动态添加请求参数、Base参数、Headers、RequestBody等。
5. 网络请求的生命周期自动管理。
6. 自定义异常处理，以及Resopnse结果预先处理。
7. 随心所欲的日志系统。让你清楚你的每次请求细节。
8. 每次网络请求都可随时取消，页面销毁自动取消网络请求。

#### 二 基本使用：

##### 2.1 框架初始化

再项目的Application中调用，应尽量避免多进程初始化。

```java
//Application#onCreate()。 
WeNetWork.init(this)
                .addBaseUrl(Control.DEFAULT_BASE_URL_FLAG, BaseAPI.BASE_URL)
                .addBaseUrl(BaseAPI.SINGING_URL_FLAG, BaseAPI.BASE_SINGING_URL)
                .addBaseInterceptor(new BaseLogInterceptor())
                .successCode(200);
```

##### 2.2 定义Api

Api的返回值目前支持三种形式：Call、WeNetObservable、Observable。想要链式调用的话请使用WeNetObservable类型的返回值，这里只详细的介绍后两种返回值的用法。

* WeNetObservable

  ```java
  public interface ApiSong {
      //方式1.
      @GET("getJoke")
      WeNetObservable<SongBean> getPoetry();
      //方式2.
      @GET("getJoke")
      WeNetObservable<NetBaseResultBean<SongBean>> getPoetry();
  }
  ```

  Api接口的返回值为WeNetObservable对象，其泛型可以是Object或者String。一般的以NetBaseResultBean封装基本的接口返回。比如：

  ```java
  {
      "code":200,
      "data":{
  
      },
      "msg":"接口请求成功"
  }
  ```

  用NetBaseResultBean封装，是为了再获取到数据之后对其数据进行一次处理，比如处理code错误码，msg信息展示，data属性的预处理。不过，如果你的项目接口返回数据格式不统一的话，那就使用上述Api中的方法1格式请求数据。

* Observable

  该返回值在正常使用Retrofit时使用。

  ```java
  public interface ApiService {
      @GET("weatherApi")
      Observable<NetBaseResultBean<WeatherBean>> getCityWeather( );
  }
  ```

  改返回值不再详细描述。

框架支持GET、POST请求时动态的绑定参数，所以在定义Api方法的时候，其方法的参数可以是空，也就是可以不用写`@Query()  @Field()`。如果加上参数的话，也没关系。

##### 2.3 发起请求

请求的发起因ApiService定义的方法的返回值不同而不同。

**使用了WeNetObservable：** 

```java
//获取ApiService对象。  
WeNetWork.apiMethod(ApiSong.class)
                //调用需要请求的接口方法。
                .getPoetry()
                //添加参数。
                .addParams("page", "1")
                .addParams("count", "2")
                .addParams("type", "video")
                //开始执行网络。
                .execute(new WeNetworkCallBack<SongBean>(mContext) {
                    @Override
                    public void onSuccess(SongBean songBean) {
                        presenterApi.setSearchData(songBean, true);
                    }

                    @Override
                    public void onError(NetException e) {
                        presenterApi.setSearchData(null, false);
                    }
                });
```

**使用了Observable：**  

```java
//step 1 :先根据ApiService调用接口的方法，获取到Observable对象。
Observable<NetBaseResultBean<WeatherBean>> weather = WeNetWork.getApiServiceInstance(ApiService.class).getCityWeather();
//step 2 :然后发起网络请求。
WeNetWork.request()
         .addParams("city", city)
         .apiMethod(weather)
         .execute(new WeNetworkCallBack<WeatherBean>(mContext) {
                    @Override
                    public void onSuccess(WeatherBean bean) {
                        presenterApi.weatherData(bean, true);
                    }
                    @Override
                    public void onError(NetException e) {
                        presenterApi.weatherData(null, false);
                    }
                });
```



 

#### 三 更新日志：

**20-4-7 更新日志：** 

* 重构网络请求框架，增加自定义Observable，实现完全链式请求。
* 增加网络请求生命周期的自动管理模块，使用的时候不需要在Activity中添加任何属性或者方法。
* 统一管理所有的网络请求，每个请求都会创建一个NetRequest实例。
* 增加Fragment来管理网络请求的生命周期，仿效Glide控件。

**18-12-20 更新日志:** 

- 增加自定义数据返回正确时的code值。
- 完善动态添加Interceptor的功能，该功能针对每个接口，并不是全局。
- 加快初始化过程。

**18-12-18 更新日志:** 

* 底层结构重构，提高了每个类功能的独立性。
* 改掉了动态切换BaseUrl时，BaseUrl复用的问题。将Okhttp和Retrofit单例对象的模式改为Okhttp.Builder跟Retrofit.Builder对象为单例，增强动态配置功能。
* 简化了网络请求的调用方式。   

**18-8-2 更新日志:   ** 

* 删除默认的BaseUrl，ApiServer。改为在初始化的时候设置，如果不设置的话，框架将无法使用。   
* 增加日志的全局设置，需要在初始化的时候选择是否打开日志，以及设置打印日志的TAG。该日志打印包括框架的异常报错和网络请求以及结果的日志打印。包括Intercepter的日志打印。

**18-7-30 更新日志:**    

* 增加请求返回结果为String类型的Convert.Factory；
* 增加异常拦截判断异常类型以及code错误码的自定义判断，当出现异常或者获取不到数据的时候将会Toast提示；
* Retrofit+Rxjava生命周期的管理。页面销毁的时候结束网络请求，也可以根据自己的需求再onStop和onResume的时候去管理网络请求。

**18-7-19 更新日志:**    

* 该版为基础版本，目前支持，GET，POST请求；   
* 动态替换BaseUrl，动态替换ApiServer，动态添加去除日志拦截器。  


​     


