package cc.sika.exception;

public class CaptchaException extends SikaRuntimeException {

    public static final String NON_KEY = "非法的验证码键";
    public static final String ERROR_CODE = "验证码错误";
    public static final String EXPIRED_CODE = "验证码已过期";

    public CaptchaException() {
        super(ERROR_CODE);
    }

    public CaptchaException(String message) {
        super(message);
    }
}
