package cn.wenet.networkcomponent.urlparse;

import okhttp3.HttpUrl;

/**
 * <p>
 *     URL的组成： http://10.1.192.66:8080/zentao/index.php?m=task&f=view&task=4640。
 *     protocol协议,http或者https://域名地址+端口/服务器的路径？参数。
 *
 * </p>
 *
 * @author WANG
 */
public class WeUrlParse {


    /**
     * <p>
     * 新的Url为： https://github.com/。
     * 原始的url为： https://www.apiopen.top/weatherApi?city=杭州。
     *
     * 替换之后为：https://github.com/weatherApi?city=杭州。
     *
     * </p>
     *
     * @param newUrl
     * @param oriUrl
     * @return
     */
   public HttpUrl parseUrl(HttpUrl newUrl,HttpUrl oriUrl){
       if(null == newUrl){
           return oriUrl;
       }
       HttpUrl.Builder builder = oriUrl.newBuilder();
       
       HttpUrl build = builder.scheme(newUrl.scheme())
               .port(newUrl.port())
               .host(newUrl.host())
               .build();
       return build;
   }


}
