package cn.wang.network.builder.bean;

import java.util.List;

/**
 * Created to :
 *
 * @author WANG
 * @date 2018/12/19
 */

public class SongBean {


    /**
     * code : 200
     * message : 成功!
     * result : [{"sid":"29740184","text":"就是听不懂卖家的普通话","type":"video","thumbnail":"http://wimg.spriteapp.cn/picture/2019/0830/b2167d0e-cafa-11e9-bbcb-1866daea6abd_wpd.jpg","video":"http://wvideo.spriteapp.cn/video/2019/0830/b2167d0e-cafa-11e9-bbcb-1866daea6abd_wpd.mp4","images":null,"up":"309","down":"9","forward":"7","comment":"8","uid":"20746671","name":"橙子爱人_","header":"http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6da86e61_mini.jpg","top_comments_content":"我简单翻译一下：你与果，哎就似里安脏枣码阔以 。枣码的就似安脏载四过枣落里   明白了吗？","top_comments_voiceuri":"","top_comments_uid":"23129320","top_comments_name":"慕容","top_comments_header":"http://wimg.spriteapp.cn/profile/large/2019/07/04/5d1d74c986d56_mini.jpg","passtime":"2019-08-31 23:01:11"},{"sid":"29739411","text":"澳大利亚袋鼠半夜公路上打架，别打了去睡觉吧","type":"video","thumbnail":"http://wimg.spriteapp.cn/picture/2019/0830/d6794ef4-cac5-11e9-819c-1866daeb0df1_wpd.jpg","video":"http://wvideo.spriteapp.cn/video/2019/0830/d6794ef4-cac5-11e9-819c-1866daeb0df1_wpd.mp4","images":null,"up":"429","down":"8","forward":"11","comment":"36","uid":"20746886","name":"流年絮语","header":"http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6dba8287_mini.jpg","top_comments_content":"不是为了女人就是为了钱","top_comments_voiceuri":"","top_comments_uid":"15789662","top_comments_name":"熟读并背诵全文","top_comments_header":"http://wimg.spriteapp.cn/profile/large/2019/03/21/5c9393ac81262_mini.jpg","passtime":"2019-08-31 22:51:02"}]
     */

    private int code;
    private String message;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * sid : 29740184
         * text : 就是听不懂卖家的普通话
         * type : video
         * thumbnail : http://wimg.spriteapp.cn/picture/2019/0830/b2167d0e-cafa-11e9-bbcb-1866daea6abd_wpd.jpg
         * video : http://wvideo.spriteapp.cn/video/2019/0830/b2167d0e-cafa-11e9-bbcb-1866daea6abd_wpd.mp4
         * images : null
         * up : 309
         * down : 9
         * forward : 7
         * comment : 8
         * uid : 20746671
         * name : 橙子爱人_
         * header : http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6da86e61_mini.jpg
         * top_comments_content : 我简单翻译一下：你与果，哎就似里安脏枣码阔以 。枣码的就似安脏载四过枣落里   明白了吗？
         * top_comments_voiceuri :
         * top_comments_uid : 23129320
         * top_comments_name : 慕容
         * top_comments_header : http://wimg.spriteapp.cn/profile/large/2019/07/04/5d1d74c986d56_mini.jpg
         * passtime : 2019-08-31 23:01:11
         */

        private String sid;
        private String text;
        private String type;
        private String thumbnail;
        private String video;
        private Object images;
        private String up;
        private String down;
        private String forward;
        private String comment;
        private String uid;
        private String name;
        private String header;
        private String top_comments_content;
        private String top_comments_voiceuri;
        private String top_comments_uid;
        private String top_comments_name;
        private String top_comments_header;
        private String passtime;

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public Object getImages() {
            return images;
        }

        public void setImages(Object images) {
            this.images = images;
        }

        public String getUp() {
            return up;
        }

        public void setUp(String up) {
            this.up = up;
        }

        public String getDown() {
            return down;
        }

        public void setDown(String down) {
            this.down = down;
        }

        public String getForward() {
            return forward;
        }

        public void setForward(String forward) {
            this.forward = forward;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public String getTop_comments_content() {
            return top_comments_content;
        }

        public void setTop_comments_content(String top_comments_content) {
            this.top_comments_content = top_comments_content;
        }

        public String getTop_comments_voiceuri() {
            return top_comments_voiceuri;
        }

        public void setTop_comments_voiceuri(String top_comments_voiceuri) {
            this.top_comments_voiceuri = top_comments_voiceuri;
        }

        public String getTop_comments_uid() {
            return top_comments_uid;
        }

        public void setTop_comments_uid(String top_comments_uid) {
            this.top_comments_uid = top_comments_uid;
        }

        public String getTop_comments_name() {
            return top_comments_name;
        }

        public void setTop_comments_name(String top_comments_name) {
            this.top_comments_name = top_comments_name;
        }

        public String getTop_comments_header() {
            return top_comments_header;
        }

        public void setTop_comments_header(String top_comments_header) {
            this.top_comments_header = top_comments_header;
        }

        public String getPasstime() {
            return passtime;
        }

        public void setPasstime(String passtime) {
            this.passtime = passtime;
        }
    }

}
