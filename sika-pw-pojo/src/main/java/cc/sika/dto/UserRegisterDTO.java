package cc.sika.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "用户昵称不能为空")
    private String nickname;

    @NotNull(message = "密码不能为空")
    private String password;

    @NotNull(message = "手机号码不能为空")
    private String phoneNumber;

    @Email
    private String email;

    /**
     * user sex, 0 is unknown, 1 is male, 2 is female
     */
    private Byte sex;


}
