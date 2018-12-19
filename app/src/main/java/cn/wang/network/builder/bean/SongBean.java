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
     * result : [{"title":"古风二首 二","content":"锄禾日当午，汗滴禾下土。|谁知盘中餐，粒粒皆辛苦。","authors":"李绅"}]
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
         * title : 古风二首 二
         * content : 锄禾日当午，汗滴禾下土。|谁知盘中餐，粒粒皆辛苦。
         * authors : 李绅
         */

        private String title;
        private String content;
        private String authors;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAuthors() {
            return authors;
        }

        public void setAuthors(String authors) {
            this.authors = authors;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", authors='" + authors + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SongBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
