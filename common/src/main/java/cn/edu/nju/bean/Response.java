package cn.edu.nju.bean;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Response<T> {
    private static final int OK = 0;
    /**
     * 数据
     */
    private T res;
    /**
     * 状态码
     */
    private int code;

    public Response(T res) {
        this(OK, res);
    }

    public Response(HttpStatus status, T res) {
        this.res = res;
        this.code = status.value();
    }

    public Response(int errCode, T res) {
        this.res = res;
        this.code = errCode;
    }
}
