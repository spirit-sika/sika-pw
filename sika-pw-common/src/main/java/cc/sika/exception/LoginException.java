package cc.sika.exception;

public class LoginException extends SikaRuntimeException{

    public static final String ERROR_LOGIN = "登录失败";
    public static final String MISMATCH = "手机号码或密码不正确";
    public static final String ILLEGAL = "非法输入";
    public static final String PHONE_NO_EXIST = "非法输入";
    public static final String CURRENT_USER = "CURRENT_USER";
    public static final Long FIFTEEN_DAYS = 60 * 60 * 24 * 15L;
    public static final Long THIRTY_DAYS = 60 * 60 * 24 * 30L;

    public LoginException() {
        super(ERROR_LOGIN);
    }

    public LoginException(String message) {
        super(message);
    }
}
