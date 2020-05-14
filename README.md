# `WeNetWork`
#### 一 框架简介：

一个`OkHttp + Retrofit +Rxjava `组合的高性能、超解耦、动态处理、链式请求、单例模式的网络框架。封装框架的唯一目的就是好用、适用。

##### 支持功能如下：   

1. 单例模式，保持一个全局的`OkHttp`和`Retrofit`对象。 
2. 真正的链式调用，不基于`RxJava`的链式模式
3. 单例模式下动态改变`BaseUrl`和`ApiServer`，选择性的添加拦截器。
4. 完全动态添加请求参数、Base参数、`Headers`、`RequestBody`等。
5. 网络请求的生命周期自动管理。
6. 自定义异常处理，以及`Resopnse`结果预先处理（主要是`Json`的类型转换问题）。
7. 随心所欲的日志系统。让你清楚你的每次请求细节。
8. 每次网络请求都可随时取消，页面销毁自动取消网络请求。

##### 1.1  单例模式：

`WeNetWork`采用的是单例模式，也就是`OkHttp`和`Retrofit`对象基本上只会创建一次（动态添加拦截器的话会重新创建一次）。单例模式的目的也是方便维护，和解决网络请求发起时的对象创建过程，但是全局单例会持续占用一点内存，也是一种空间换时间的做法。另外单例模式可以让网络的发起变得简单化，很适合链式请求。单例模式带来的问题还是很多的：

1. 怎么更换`BaseUrl`？

   `Retrofit`框架创建的时候，是必须需要设置`baseUrl`的，但是在实际的应用中会有多个`baseUrl`，这就给单例模式的`Retrofit`带来了一个小问题。该问题最后的解决方法是采用`OkHttp`的拦截器动态替换默认的`BaseUrl`。难点在于替换`baseUrl`的实际和如何替换并重新构建`HttpUrl`。

2. 怎么把基础参数或者`Header`添加到具体的请求中？

   再项目中，或多或少的总会有一些默认的参数需要添加到请求连接中，如果是单域名的话那还好，但是多域名的话可能不同的域名对应的基础参数就不一样。同样也是采用的拦截器解决的这个问题，难点在于拦截器中拦截到`Url`之后怎么判断该链接需要拼接那些基础参数，且基础参数需要拼接到哪里（`RequestBody`，`Header`，`FromBody`）等。

3. 怎么添加删除自定义拦截器？

   对该需求没有很好的方法去解决，因为`OkHttp`中的拦截器是不支持动态的修改的，是一个不可修改的集合，只能重新的创建`OkHttp`对象，同样的`Retrofit`也要重新的创建。

##### 1.2  链式请求：

链式请求带来的好处就是将请求简单化，你只需要根据制定好的规范，下一步、下一步的添加上特定的数值就可以了。`Retrofit+OkHttp`带来的问题是，定义的`ApiService`的方法的返回值是确定的（`Call`、`Observable`）。`Observable`当然很容易使用链式调用了，但是这样的链式并不是完全自定义的，因为你要跟随`RxJava`的逻辑来进行下一步，而这些设置操作完全是可以封装在框架里面的。我想要的是这样的：

```java
//ApiSong是定义的ApiService，getPoetry()是ApiService中定义的接口方法。
WeNetWork.apiMethod(ApiSong.class)
                .getPoetry()
                .bindLife(mContext)
                .addParams("page", "1")
                .addParams("count", "2")
                .addParams("type", "video")
                .execute(new WeNetworkCallBack<SongBean>() {});
```

每一个关节都能实现自定义，并做出特殊的处理，如果是`RxJava`的话是无法更改链式方法的返回值，以及处理一些逻辑。所以，要想实现自定义的链式调用，需要对`ApiService`定义的方法的返回值入手，要让支持的类型不仅限于`Call、Observable`。`Retrofit`采用了适配器模式去选择特定的`CallAdapter`去处理`ApiService`中定义的接口方法的返回值。那么我们完全可以仿照着去自定义一个`CallAdapter`，`WeNetWork`框架中自定义的是`WeNetResult`j接口。这个前提要知道`Retrofit`的动态代理逻辑。

```java
public interface ApiSong {
    @GET("getJoke")
    WeNetResult<SongBean> getPoetry();
}
```

##### 1.3 网络请求的生命周期管理

为什么要控制网络请求的生命周期？就是为了应用少Bug。网络请求的发起一般都是异步，在请求期间，是无法预知数据到来之后界面是怎么样的。如果数据回来之后，界面被销毁了就有可能出现空指针问题，轻一点的话会引起内存泄漏问题。这种现象在`Activity`、`Fragment`、`Dialog`等中都会出现，所以界面中每个网络请求的发起，都要可控。在用 `Retrofit+OkHttp+RxJava2`这种组合的网络框架时，每个网络请求对应着一个`Disposable`对象，通过`Disposable`对象就可以取消网络请求。所以一般的都会在`BaseActivity`、`BaseFragment`、`BaseDialog`中去处理这个`Disposable`，这样无形中会给网络框架的使用增加一些学习的成本，框架的移植也是一个问题。

所以要让网络框架自行管理请求的生命周期，同时也要提供方法让外界手动控制。`WeNetWork`管理生命周期的方式，效仿的是`Glide`框架，`Glide`本身也是网络请求，但是我们使用的时候对它的生命周期控制毫无感知。可能生命周期的控制没有`Glide`严谨，这个需要一个长期和复杂的使用场景去验证。

#### 二 基本使用：

##### 2.1 框架初始化

再项目的`Application`的`onCreate()`方法中调用，要在主进程中调用，防止初始化多次。

```java
//这里支持很多种参数的设置，接口超时时间、Log打印、拦截器、BaseParams等。
WeNetWork.init(this)
                .addBaseUrl(BaseAPI.BASE_URL_FLAG, BaseAPI.BASE_URL)
                .addBaseUrl(BaseAPI.WEATHER_URL_FLAG, BaseAPI.WEATHER_BASE_URL)
                .addBaseUrl(BaseAPI.IP_URL_FLAG, BaseAPI.IP_BASE_URL)
                .addBaseInterceptor(new BaseLogInterceptor())
                .successCode(200);

//参考如下设置。
public class BaseAPI {
    public final static String BASE_URL = "https://******/";
    public final static String BASE_URL_FLAG = Control.DEFAULT_BASE_URL_FLAG;

    public final static String WEATHER_BASE_URL = "http://******/";
    public final static String WEATHER_URL_FLAG = Control.GLOBAL_HEADER + ":weather";

    public final static String IP_URL_FLAG = Control.GLOBAL_HEADER + ":ip";
    public final static String IP_BASE_URL = "http://********/";
}

```

`addBaseUrl()` 方法支持两个参数：第一个参数是`baseUrl`的唯一标记，再动态替换`basrUrl`的时候用到；第二个参数，就是项目中需要用到的`baseUrl`。一般的要给框架设置一个基础的`baseUrl`，也就是项目中大部分接口都能用的，该`baseUrl`使用的标记是固定的，必须使用`Control.DEFAULT_BASE_URL_FLAG`，要不然无法知道当前需要更换`baseUrl`，当然这个逻辑还能优化。

##### 2.2 定义`ApiService`

`ApiService`中接口的返回值目前支持三种形式：`Call`、`WeNetResult`、`Observable`。想要链式调用的话请使用`WeNetResult`类型的返回值，这里只详细的介绍后两种返回值的用法。

* `WeNetResult`的使用：

  ```java
  public interface ApiSong {
      //方式1.
      @GET("getJoke")
      WeNetResult<SongBean> getPoetry();
      //方式2.
      @GET("getJoke")
      WeNetResult<NetBaseResultBean<SongBean>> getPoetry();
  }
  ```

  `Api`接口的返回值为`WeNetResult`对象，其泛型可以是`Object`或者`String`。当接口的返回类型是下面的格式的话，可以使用`NetBaseResultBean`来封装对象。比如：

  ```java
  {
      "code":200,
      "data":{
  
      },
      "msg":"接口请求成功"
  }
  ```

  这样做的好处是，再获取到数据之后对其数据进行一次预处理，比如处理`code`错误码，`msg`信息展示，`data`数据判断等。不过，如果你的项目接口返回数据格式不统一的话，那就只能再回调方法中处理，回调方法后面有讲。

* `Observable`

  该返回值在正常使用`Retrofit`时使用。

  ```java
  public interface ApiService {
      @GET("weatherApi")
      Observable<NetBaseResultBean<WeatherBean>> getCityWeather( );
  }
  ```

  改返回值不再详细描述。

`WeNetWork`框架支持`GET`请求参数注解是`@Query() ,QueryMap()`时动态的绑定参数，也就是在定义`Api`方法的时候，方法的参数是空的。如果使用`@Query(),QueryMap() `添加参数的话，也没问题，但是对`GET`请求方式中的其他参数注解就不适用了。

`WeNetWork`框架对`Post`请求时，参数动态加载的限制是其参数注解为`@Field()，@FieldMap()，@Body()`，也就是你定义的`Api`方法的参数可以是空的。

```java
  public interface ApiWeather {
      
    @GET("simpleWeather/query")
    Observable<WeatherBean> getCityWeather(@QueryMap Map<String, Object> params);

    @Headers({BaseAPI.WEATHER_URL_FLAG})
    @POST("simpleWeather/query")
    @FormUrlEncoded
    WeNetResult<WeatherBean> getCityWeatherByPost();
  }
```

参数为空，不代表不需要参数，而是将参数的添加放在了链式调用中。

##### 2.3 发起请求

请求的发起因`ApiService`定义的方法的返回值不同而不同。链式请求的话，要使用`WeNetResult`类型的返回值。

**2.3.1 使用`WeNetResult`：** 

```java
//获取ApiService对象。
WeNetWork.apiMethod(ApiWeather.class)
                //调用设置的Api方法。
                .getCityWeatherByPost()
                //添加参数。
                .addParams("key","******")
                .addParams("city", "洛阳")
                //绑定生命周期，自动管理。
                .bindLife(mContext)
                //结果的回调。 
                .execute(new WeNetworkCallBack<WeatherBean>() {
                    @Override
                    public void onSuccess(WeatherBean songBean) {
                       
                    }

                    @Override
                    public void onError(NetException e) {
                       
                    }
                });
```

**2.3.2 使用`Observable`：**  

```java
//step 1 :先根据ApiService调用接口的方法，获取到Observable对象。
Map<String, Object> params = new HashMap<>();
        params.put("city", city);
        params.put("key", "a1ae58f53edaf0518c72f41adc3987a9");
        Observable<WeatherBean> cityWeather = WeNetWork.getApiServiceInstance(ApiWeather.class).getCityWeather(params);
//step 2 :然后发起网络请求。
WeNetWork.request(cityWeather)
                //绑定生命周期。
                .bindLife(mContext)
                .execute(new WeNetworkCallBack<WeatherBean>() {
                    @Override
                    public void onSuccess(WeatherBean bean) {
                        
                    }
                    @Override
                    public void onError(NetException e) {
                     
                    }
                });
```



单例对象有： Control+BaseControl。mLifeManager、retryWhen、OkHttp、Retrofit、mBaseParams、mBaseUrls、

