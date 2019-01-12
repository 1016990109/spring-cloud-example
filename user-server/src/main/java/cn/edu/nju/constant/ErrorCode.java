package cn.edu.nju.constant;

import cn.edu.nju.exception.CustomException;
import lombok.Getter;

/**
 * @author hongchuanwang
 */
@Getter
public enum ErrorCode {
    /**
     * 用户相关错误码
     */
    USER_NOT_EXIST(-1, "用户不存在"),
    PASSWORD_ERROR(-2, "密码错误");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CustomException generateCustomException(ErrorCode errorCode) {
        return new CustomException(errorCode.code, errorCode.message);
    }
}
