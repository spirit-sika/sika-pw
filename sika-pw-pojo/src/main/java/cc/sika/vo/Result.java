package cc.sika.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Result<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    /* ******************** 响应成功消息 ******************** */
    public static <T> Result<T> success(Integer code, String message, T data) {
        return new Result<T>(code, message, data);
    }

    public static <T> Result<T> success(T data) {
        return success(200, "success", data);
    }

    public static <T> Result<T> success(String message, T data) {
        return success(200, message, data);
    }

    /**
     * 将data域内容填充为成功提示消息
     * @param message 成功提示消息
     */
    public static Result<String> successMessage(String message) {
        return success(message, null);
    }

    /* ******************** 响应错误消息 ******************** */
    public static <T> Result<T> error(Integer code, String message, T data) {
        return new Result<T>(code, message, data);
    }

    public static <T> Result<T> error(T data) {
        if (data instanceof String) {
            return error(500, (String) data, null);
        }
        return error(500, "error", data);
    }

    public static <T> Result<T> error(Integer code, T data) {
        if (data instanceof String) {
            return error(code, (String) data, null);
        }
        return error(code, "error", data);
    }

}
