package cn.weichao.huaxing.vo;

public enum BaseResultType {

    SUCCESS(0, "请求成功"),
    FAIL(1, "请求失败");

    BaseResultType(int code, String message) {
        this.code = code;
        this.message = message;
    }
    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
