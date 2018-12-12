package cn.edu.nju.exception;

import lombok.AllArgsConstructor;

/**
 * @author hongchuanwang
 */
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = -4353988281256823851L;

    private Integer code;

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
