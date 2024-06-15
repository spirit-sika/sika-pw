package cc.sika.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * user id
     */
    private Long userId;

    /**
     * user show name
     */
    private String nickname;

    /**
     * user mobile phone number also the login account
     */
    private String phoneNumber;

    private String email;

    /**
     * user sex, 0 is unknown, 1 is male, 2 is female
     */
    private Byte sex;
}
