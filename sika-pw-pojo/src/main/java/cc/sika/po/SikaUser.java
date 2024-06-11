package cc.sika.po;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SikaUser implements Serializable {
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
     * user password is encrypted using sha256
     */
    private String password;

    /**
     * user mobile phone number also the login account
     */
    private String phoneNumber;

    private String email;

    /**
     * user sex, 0 is unknown, 1 is male, 2 is female
     */
    private Byte sex;

    /**
     * the nickname of the person who created the account name
     */
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
}
