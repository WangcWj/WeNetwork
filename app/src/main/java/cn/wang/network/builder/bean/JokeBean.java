package cn.wang.network.builder.bean;

import java.util.List;

/**
 * Created by WANG on 17/11/23.
 */

public class JokeBean {


    /**
     * shidu : 23%
     * pm25 : 43.0
     * pm10 : 80.0
     * quality : 良
     * wendu : 5
     * ganmao : 极少数敏感人群应减少户外活动
     * yesterday : {"date":"17日星期一","sunrise":"07:29","high":"高温 8.0℃","low":"低温 -5.0℃","sunset":"16:51","aqi":54,"fx":"西南风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"}
     * forecast : [{"date":"18日星期二","sunrise":"07:30","high":"高温 8.0℃","low":"低温 -4.0℃","sunset":"16:51","aqi":67,"fx":"北风","fl":"3-4级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"19日星期三","sunrise":"07:30","high":"高温 8.0℃","low":"低温 -5.0℃","sunset":"16:51","aqi":73,"fx":"北风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"20日星期四","sunrise":"07:31","high":"高温 5.0℃","low":"低温 -4.0℃","sunset":"16:52","aqi":127,"fx":"东南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"21日星期五","sunrise":"07:32","high":"高温 9.0℃","low":"低温 -3.0℃","sunset":"16:52","aqi":114,"fx":"西北风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"22日星期六","sunrise":"07:32","high":"高温 7.0℃","low":"低温 -4.0℃","sunset":"16:53","aqi":59,"fx":"北风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}]
     */

    private String shidu;
    private double pm25;
    private double pm10;
    private String quality;
    private String wendu;
    private String ganmao;
    private YesterdayBean yesterday;
    private List<ForecastBean> forecast;

    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }

    public double getPm25() {
        return pm25;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public double getPm10() {
        return pm10;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public YesterdayBean getYesterday() {
        return yesterday;
    }

    public void setYesterday(YesterdayBean yesterday) {
        this.yesterday = yesterday;
    }

    public List<ForecastBean> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastBean> forecast) {
        this.forecast = forecast;
    }

    public static class YesterdayBean {
        /**
         * date : 17日星期一
         * sunrise : 07:29
         * high : 高温 8.0℃
         * low : 低温 -5.0℃
         * sunset : 16:51
         * aqi : 54.0
         * fx : 西南风
         * fl : <3级
         * type : 晴
         * notice : 愿你拥有比阳光明媚的心情
         */

        private String date;
        private String sunrise;
        private String high;
        private String low;
        private String sunset;
        private double aqi;
        private String fx;
        private String fl;
        private String type;
        private String notice;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getHigh() {
            return high;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getLow() {
            return low;
        }

        public void setLow(String low) {
            this.low = low;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public double getAqi() {
            return aqi;
        }

        public void setAqi(double aqi) {
            this.aqi = aqi;
        }

        public String getFx() {
            return fx;
        }

        public void setFx(String fx) {
            this.fx = fx;
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

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }
    }

    public static class ForecastBean {
        /**
         * date : 18日星期二
         * sunrise : 07:30
         * high : 高温 8.0℃
         * low : 低温 -4.0℃
         * sunset : 16:51
         * aqi : 67.0
         * fx : 北风
         * fl : 3-4级
         * type : 晴
         * notice : 愿你拥有比阳光明媚的心情
         */

        private String date;
        private String sunrise;
        private String high;
        private String low;
        private String sunset;
        private double aqi;
        private String fx;
        private String fl;
        private String type;
        private String notice;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getHigh() {
            return high;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getLow() {
            return low;
        }

        public void setLow(String low) {
            this.low = low;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public double getAqi() {
            return aqi;
        }

        public void setAqi(double aqi) {
            this.aqi = aqi;
        }

        public String getFx() {
            return fx;
        }

        public void setFx(String fx) {
            this.fx = fx;
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

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }
    }
}
