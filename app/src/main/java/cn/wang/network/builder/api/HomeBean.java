package cn.wang.network.builder.api;

/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/4/9
 */
public class HomeBean {

    private String name;
    private String value;

    public HomeBean(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
