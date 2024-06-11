package cc.sika.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SikaRole implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * role_id
     */
    private Long roleId;

    /**
     * role name
     */
    private String roleName;

    /**
     * the nickname of person who created the role
     */
    private String createBy;

    private LocalDateTime createTime;

    /**
     * the nickname of person who update the role
     */
    private String updateBy;

    private LocalDateTime updateTime;
}
