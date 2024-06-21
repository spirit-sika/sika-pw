package cc.sika.handler;

import cc.sika.exception.CaptchaException;
import cc.sika.exception.LoginException;
import cc.sika.exception.SikaRuntimeException;
import cc.sika.vo.Result;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(SikaRuntimeException.class)
    public Result<String> handleLoginException(SikaRuntimeException e) {
        return Result.error(e.getLocalizedMessage());
    }

    @ExceptionHandler(CaptchaException.class)
    public Result<String> handleCaptchaException(CaptchaException e) {
        return Result.error(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getLocalizedMessage());
    }

    @ExceptionHandler(LoginException.class)
    public Result<String> handleLoginException(LoginException e) {
        return Result.error(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getLocalizedMessage());
    }
}
