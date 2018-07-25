package cn.wang.network.builder.bean;

import java.util.List;

/**
 * Created by WANG on 17/11/23.
 */

public class JokeBean {

    /**
     * code : 200
     * msg : 成功!
     * data : [{"createdAt":"2017-09-27T08:54:19.73Z","publishedAt":"2017-10-09T13:07:56.458Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjxu5qqdjoj20qo0xc0wk.jpg"},{"createdAt":"2017-09-29T09:01:07.894Z","publishedAt":"2017-09-29T11:21:16.116Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fk05lf9f4cj20u011h423.jpg"},{"createdAt":"2017-09-22T08:57:53.998Z","publishedAt":"2017-09-26T12:12:07.813Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjs25xfq48j20u00mhtb6.jpg"},{"createdAt":"2017-09-21T08:43:35.381Z","publishedAt":"2017-09-21T13:27:15.675Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjqw4n86lhj20u00u01kx.jpg"},{"createdAt":"2017-09-20T08:18:40.702Z","publishedAt":"2017-09-20T13:17:38.709Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjppsiclufj20u011igo5.jpg"},{"createdAt":"2017-09-18T07:58:47.204Z","publishedAt":"2017-09-19T12:07:31.405Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjndz4dh39j20u00u0ada.jpg"},{"createdAt":"2017-09-12T07:48:47.73Z","publishedAt":"2017-09-14T16:36:51.63Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjgfyxgwgnj20u00gvgmt.jpg"},{"createdAt":"2017-09-11T07:50:13.510Z","publishedAt":"2017-09-12T08:15:08.26Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjfae1hjslj20u00tyq4x.jpg"},{"createdAt":"2017-09-07T13:21:27.937Z","publishedAt":"2017-09-07T13:25:26.977Z","type":"美图","url":"http://ww1.sinaimg.cn/large/610dc034ly1fjaxhky81vj20u00u0ta1.jpg"},{"createdAt":"2017-08-25T08:46:26.461Z","publishedAt":"2017-09-06T12:18:11.687Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fivohbbwlqj20u011idmx.jpg"},{"createdAt":"2017-09-04T08:44:51.44Z","publishedAt":"2017-09-05T11:29:05.240Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fj78mpyvubj20u011idjg.jpg"},{"createdAt":"2017-09-01T11:11:24.81Z","publishedAt":"2017-09-01T12:55:52.582Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fj3w0emfcbj20u011iabm.jpg"},{"createdAt":"2017-08-31T08:17:38.117Z","publishedAt":"2017-08-31T08:22:07.982Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fj2ld81qvoj20u00xm0y0.jpg"},{"createdAt":"2017-08-28T08:10:21.141Z","publishedAt":"2017-08-29T12:19:18.634Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fiz4ar9pq8j20u010xtbk.jpg"},{"createdAt":"2017-08-24T08:47:28.949Z","publishedAt":"2017-08-24T12:43:10.124Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fiuiw5givwj20u011h79a.jpg"},{"createdAt":"2017-08-23T08:22:38.611Z","publishedAt":"2017-08-23T12:12:15.166Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fitcjyruajj20u011h412.jpg"},{"createdAt":"2017-08-22T08:38:13.732Z","publishedAt":"2017-08-22T12:02:15.769Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fis7dvesn6j20u00u0jt4.jpg"},{"createdAt":"2017-08-21T08:30:18.487Z","publishedAt":"2017-08-21T11:38:57.363Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fir1jbpod5j20ip0newh3.jpg"},{"createdAt":"2017-08-16T07:42:54.135Z","publishedAt":"2017-08-17T11:36:42.967Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fil82i7zsmj20u011hwja.jpg"},{"createdAt":"2017-08-15T07:53:01.962Z","publishedAt":"2017-08-15T13:32:36.998Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fik2q1k3noj20u00u07wh.jpg"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createdAt : 2017-09-27T08:54:19.73Z
         * publishedAt : 2017-10-09T13:07:56.458Z
         * type : 美图
         * url : https://ws1.sinaimg.cn/large/610dc034ly1fjxu5qqdjoj20qo0xc0wk.jpg
         */

        private String createdAt;
        private String publishedAt;
        private String type;
        private String url;

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
