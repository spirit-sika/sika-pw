package cc.sika.po;

import cc.sika.vo.SikaPWVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SikaPW implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long pwId;
    private String domainName;
    private Long userId;
    private String account;
    private String password;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;

    public SikaPWVO toVO() {
        return SikaPWVO.builder()
                .pwId(this.getPwId())
                .domainName(this.getDomainName())
                .account(this.getAccount())
                .password(this.getPassword())
                .createBy(this.getCreateBy())
                .createTime(this.getCreateTime())
                .updateBy(this.getUpdateBy())
                .updateTime(this.getUpdateTime())
                .build();
    }
}
