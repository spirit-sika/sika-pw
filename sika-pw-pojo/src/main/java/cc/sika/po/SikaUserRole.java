package cc.sika.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SikaUserRole {

    /**
     * mapping id of the user and role
     */
    private Long userRoleId;

    private Long userId;

    private Long roleId;
}
