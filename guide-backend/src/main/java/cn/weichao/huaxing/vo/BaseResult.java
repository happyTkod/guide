package cn.weichao.huaxing.vo;

public class BaseResult<T> {

    private int code;

    private String message;

    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> BaseResult<T> success(T data) {
        BaseResult<T> res = new BaseResult<>();
        res.setCode(BaseResultType.SUCCESS.getCode());
        res.setMessage(BaseResultType.SUCCESS.getMessage());
        res.setData(data);
        return res;
    }

    public static <T> BaseResult<T> fail(T data) {
        BaseResult<T> res = new BaseResult<>();
        res.setCode(BaseResultType.FAIL.getCode());
        res.setMessage(BaseResultType.FAIL.getMessage());
        res.setData(data);
        return res;
    }
}
