package cn.edu.nju.exception;

import cn.edu.nju.bean.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hongchuanwang
 */
@ControllerAdvice
public class CustomExceptionHandler {
    private final static int SERVICE_EXCEPTION_ERR_CODE = 600;

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response<String> customExceptionHandler(HttpServletRequest req, Exception e) {
        return new Response<>(SERVICE_EXCEPTION_ERR_CODE, e.getMessage());
    }

}
