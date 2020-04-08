package cn.wang.network.builder.bean;



import java.util.List;


/**
 * Created by WANG on 2018/7/31.
 */

public class WeatherBean {


    /**
     * reason : 查询成功
     * result : {"city":"苏州","realtime":{"temperature":"4","humidity":"82","info":"阴","wid":"02","direct":"西北风","power":"3级","aqi":"80"},"future":[{"date":"2019-02-22","temperature":"1/7℃","weather":"小雨转多云","wid":{"day":"07","night":"01"},"direct":"北风转西北风"},{"date":"2019-02-23","temperature":"2/11℃","weather":"多云转阴","wid":{"day":"01","night":"02"},"direct":"北风转东北风"},{"date":"2019-02-24","temperature":"6/12℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"东北风转北风"},{"date":"2019-02-25","temperature":"5/12℃","weather":"小雨转多云","wid":{"day":"07","night":"01"},"direct":"东北风"},{"date":"2019-02-26","temperature":"5/11℃","weather":"多云转小雨","wid":{"day":"01","night":"07"},"direct":"东北风"}]}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * city : 苏州
         * realtime : {"temperature":"4","humidity":"82","info":"阴","wid":"02","direct":"西北风","power":"3级","aqi":"80"}
         * future : [{"date":"2019-02-22","temperature":"1/7℃","weather":"小雨转多云","wid":{"day":"07","night":"01"},"direct":"北风转西北风"},{"date":"2019-02-23","temperature":"2/11℃","weather":"多云转阴","wid":{"day":"01","night":"02"},"direct":"北风转东北风"},{"date":"2019-02-24","temperature":"6/12℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"东北风转北风"},{"date":"2019-02-25","temperature":"5/12℃","weather":"小雨转多云","wid":{"day":"07","night":"01"},"direct":"东北风"},{"date":"2019-02-26","temperature":"5/11℃","weather":"多云转小雨","wid":{"day":"01","night":"07"},"direct":"东北风"}]
         */

        private String city;
        private RealtimeBean realtime;
        private List<FutureBean> future;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public RealtimeBean getRealtime() {
            return realtime;
        }

        public void setRealtime(RealtimeBean realtime) {
            this.realtime = realtime;
        }

        public List<FutureBean> getFuture() {
            return future;
        }

        public void setFuture(List<FutureBean> future) {
            this.future = future;
        }

        public static class RealtimeBean {
            /**
             * temperature : 4
             * humidity : 82
             * info : 阴
             * wid : 02
             * direct : 西北风
             * power : 3级
             * aqi : 80
             */

            private String temperature;
            private String humidity;
            private String info;
            private String wid;
            private String direct;
            private String power;
            private String aqi;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getWid() {
                return wid;
            }

            public void setWid(String wid) {
                this.wid = wid;
            }

            public String getDirect() {
                return direct;
            }

            public void setDirect(String direct) {
                this.direct = direct;
            }

            public String getPower() {
                return power;
            }

            public void setPower(String power) {
                this.power = power;
            }

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }
        }

        public static class FutureBean {
            /**
             * date : 2019-02-22
             * temperature : 1/7℃
             * weather : 小雨转多云
             * wid : {"day":"07","night":"01"}
             * direct : 北风转西北风
             */

            private String date;
            private String temperature;
            private String weather;
            private WidBean wid;
            private String direct;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WidBean getWid() {
                return wid;
            }

            public void setWid(WidBean wid) {
                this.wid = wid;
            }

            public String getDirect() {
                return direct;
            }

            public void setDirect(String direct) {
                this.direct = direct;
            }

            public static class WidBean {
                /**
                 * day : 07
                 * night : 01
                 */

                private String day;
                private String night;

                public String getDay() {
                    return day;
                }

                public void setDay(String day) {
                    this.day = day;
                }

                public String getNight() {
                    return night;
                }

                public void setNight(String night) {
                    this.night = night;
                }
            }
        }
    }
}
