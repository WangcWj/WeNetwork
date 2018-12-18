package cn.wang.network.builder.bean;

import java.util.List;

import cn.example.wang.networkcomponent.base.BaseResultBean;

/**
 * Created by WANG on 2018/7/31.
 */

public class WeatherBean extends BaseResultBean{

    /**
     * code : 200
     * msg : 成功!
     * data : {"yesterday":{"date":"30日星期一","high":"高温 30℃","fx":"无持续风向","low":"低温 23℃","fl":"<![CDATA[<3级]]>","type":"小雨"},"city":"成都","aqi":"53","forecast":[{"date":"31日星期二","high":"高温 29℃","fengli":"<![CDATA[<3级]]>","low":"低温 22℃","fengxiang":"无持续风向","type":"阵雨"},{"date":"1日星期三","high":"高温 28℃","fengli":"<![CDATA[<3级]]>","low":"低温 22℃","fengxiang":"无持续风向","type":"中雨"},{"date":"2日星期四","high":"高温 26℃","fengli":"<![CDATA[<3级]]>","low":"低温 22℃","fengxiang":"无持续风向","type":"中雨"},{"date":"3日星期五","high":"高温 28℃","fengli":"<![CDATA[<3级]]>","low":"低温 23℃","fengxiang":"无持续风向","type":"小雨"},{"date":"4日星期六","high":"高温 31℃","fengli":"<![CDATA[<3级]]>","low":"低温 23℃","fengxiang":"无持续风向","type":"阵雨"}],"ganmao":"天气转凉，空气湿度较大，较易发生感冒，体质较弱的朋友请注意适当防护。","wendu":"29"}
     */

    /**
     * yesterday : {"date":"30日星期一","high":"高温 30℃","fx":"无持续风向","low":"低温 23℃","fl":"<![CDATA[<3级]]>","type":"小雨"}
     * city : 成都
     * aqi : 53
     * forecast : [{"date":"31日星期二","high":"高温 29℃","fengli":"<![CDATA[<3级]]>","low":"低温 22℃","fengxiang":"无持续风向","type":"阵雨"},{"date":"1日星期三","high":"高温 28℃","fengli":"<![CDATA[<3级]]>","low":"低温 22℃","fengxiang":"无持续风向","type":"中雨"},{"date":"2日星期四","high":"高温 26℃","fengli":"<![CDATA[<3级]]>","low":"低温 22℃","fengxiang":"无持续风向","type":"中雨"},{"date":"3日星期五","high":"高温 28℃","fengli":"<![CDATA[<3级]]>","low":"低温 23℃","fengxiang":"无持续风向","type":"小雨"},{"date":"4日星期六","high":"高温 31℃","fengli":"<![CDATA[<3级]]>","low":"低温 23℃","fengxiang":"无持续风向","type":"阵雨"}]
     * ganmao : 天气转凉，空气湿度较大，较易发生感冒，体质较弱的朋友请注意适当防护。
     * wendu : 29
     */

    private YesterdayBean yesterday;
    private String city;
    private String aqi;
    private String ganmao;
    private String wendu;
    private List<ForecastBean> forecast;

    public YesterdayBean getYesterday() {
        return yesterday;
    }

    public void setYesterday(YesterdayBean yesterday) {
        this.yesterday = yesterday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public List<ForecastBean> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastBean> forecast) {
        this.forecast = forecast;
    }

    public static class YesterdayBean {
        /**
         * date : 30日星期一
         * high : 高温 30℃
         * fx : 无持续风向
         * low : 低温 23℃
         * fl : <![CDATA[<3级]]>
         * type : 小雨
         */

        private String date;
        private String high;
        private String fx;
        private String low;
        private String fl;
        private String type;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHigh() {
            return high;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getFx() {
            return fx;
        }

        public void setFx(String fx) {
            this.fx = fx;
        }

        public String getLow() {
            return low;
        }

        public void setLow(String low) {
            this.low = low;
        }

        public String getFl() {
            return fl;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class ForecastBean {
        /**
         * date : 31日星期二
         * high : 高温 29℃
         * fengli : <![CDATA[<3级]]>
         * low : 低温 22℃
         * fengxiang : 无持续风向
         * type : 阵雨
         */

        private String date;
        private String high;
        private String fengli;
        private String low;
        private String fengxiang;
        private String type;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHigh() {
            return high;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getFengli() {
            return fengli;
        }

        public void setFengli(String fengli) {
            this.fengli = fengli;
        }

        public String getLow() {
            return low;
        }

        public void setLow(String low) {
            this.low = low;
        }

        public String getFengxiang() {
            return fengxiang;
        }

        public void setFengxiang(String fengxiang) {
            this.fengxiang = fengxiang;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "date  is : "+date+"     high  is    "+high;
        }
    }
}
