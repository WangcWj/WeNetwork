package cn.wang.network.builder.api;

/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/4/8
 */
public class NewRequest {
    private int orgId ;
    private String  username;

    public NewRequest(int orgId, String username) {
        this.orgId = orgId;
        this.username = username;
    }
}
