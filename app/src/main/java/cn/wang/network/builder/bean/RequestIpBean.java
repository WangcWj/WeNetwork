package cn.wang.network.builder.bean;

/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/3/6
 */
public class RequestIpBean {

    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "RequestIpBean{" +
                "ip='" + ip + '\'' +
                '}';
    }
}
