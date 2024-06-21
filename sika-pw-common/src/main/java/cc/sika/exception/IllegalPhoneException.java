package cc.sika.exception;

public class IllegalPhoneException extends SikaRuntimeException{
    public IllegalPhoneException(String message) {
        super(message);
    }

    public static final String PHONE_EXIST = "手机号码已被注册";
}
