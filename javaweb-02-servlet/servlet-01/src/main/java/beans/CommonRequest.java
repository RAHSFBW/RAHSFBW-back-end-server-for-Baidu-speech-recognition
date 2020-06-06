package beans;

import java.util.HashMap;

/**
 * 基本请求体封装类
 */
public class CommonRequest {

    private String requestCode;
    private HashMap<String, String> requestParam;

    public String getRequestCode() {
        return requestCode;
    }
    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }
    public HashMap<String, String> getRequestParam() {
        return requestParam;
    }
    public void setRequestParam(HashMap<String, String> requestParam) {
        this.requestParam = requestParam;
    }

}
