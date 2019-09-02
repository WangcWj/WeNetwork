package cn.wenet.networkcomponent.exception;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.io.IOException;
import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import cn.wenet.networkcomponent.base.NetBaseParam;
import retrofit2.HttpException;

/**
 * Created by WANG on 2018/7/25.
 * Josn的基本返回格式一定要跟后台约定好,这里只是大家比较熟悉的返回类型.
 * 这里可以去的判断code根据code的返回值去判断错误类型
 */

public class NetException {

    private int mCode;
    private int status;
    private String mMessage;
    private Throwable e;

    public int getCode() {
        return mCode;
    }

    public Throwable getThrowable() {
        return e;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public NetException(Throwable e) {
        this.e = e;
    }

    public NetException(int code, String message) {
        this.mCode = code;
        this.mMessage = message;
    }

    public NetException(int code, int status, String message) {
        this.status = status;
        this.mCode = code;
        this.mMessage = message;
    }

    public boolean success() {
        if (NetBaseParam.SUCCESS_CODE == mCode || NetBaseParam.SUCCESS_CODE == status) {
            return true;
        } else {
            return false;
        }
    }

    public String switchError(Throwable e) {
        String errorMessage;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            try {
                errorMessage = httpException.response().errorBody().string();
            } catch (IOException e1) {
                e1.printStackTrace();
                errorMessage = e1.getMessage();
            }
        } else if (e instanceof SocketTimeoutException) {
            errorMessage = "网络连接超时！";
        } else if (e instanceof ConnectException) {
            errorMessage = "网络连接异常！";
        } else if (e instanceof ConnectTimeoutException) {
            errorMessage = "网络连接超时！";
        } else if (e instanceof UnknownHostException) {
            errorMessage = "网络连接异常！";
        } else if (e instanceof NullPointerException) {
            errorMessage = "空指针异常!";
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            errorMessage = "证书验证失败!";
        } else if (e instanceof ClassCastException) {
            errorMessage = "类型转换错误!";
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof JsonSyntaxException
                || e instanceof JsonSerializer
                || e instanceof NotSerializableException
                || e instanceof ParseException) {
            errorMessage = "解析错误!";
        } else if (e instanceof IllegalStateException) {
            errorMessage = e.getMessage();
        } else {
            errorMessage = "未知错误!";
        }
        return errorMessage;
    }

}
