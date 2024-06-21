package cc.sika.exception;

public class RegisterException extends SikaRuntimeException{
    public RegisterException(String message) {
        super(message);
    }

    public static final String REGISTER_ERROR = "注册失败!";
}
