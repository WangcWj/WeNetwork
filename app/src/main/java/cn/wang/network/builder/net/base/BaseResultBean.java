package cn.wang.network.builder.net.base;

/**
 * Created by WANG on 17/11/23.
 */

public class BaseResultBean<T> {
    private int code;
    private String msg;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getRet() {
        return code;
    }

    public void setRet(int ret) {
        this.code = ret;
    }

    @Override
    public String toString() {
        return "code  is " + code +"msg  is "+data.toString();
    }
}
