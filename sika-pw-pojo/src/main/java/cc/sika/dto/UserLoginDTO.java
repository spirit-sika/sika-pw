package cc.sika.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String phoneNumber;
    private String password;
    private String codeKey;
    private String code;
    private Boolean rememberMe;
}
