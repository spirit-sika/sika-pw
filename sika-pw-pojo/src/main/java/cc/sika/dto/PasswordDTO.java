package cc.sika.dto;

import cc.sika.po.SikaPW;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PasswordDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long pwId;
    private String domainName;
    private String account;
    @NotNull(message = "密码不能为空")
    private String password;

    public SikaPW toPO(@Nullable Long pwId, Long userId, String createBy, String updateBy) {
        return SikaPW.builder()
                .pwId(pwId)
                .domainName(this.getDomainName())
                .userId(userId)
                .account(this.getAccount())
                .password(this.getPassword())
                .createBy(createBy)
                .updateBy(updateBy).build();
    }
}
