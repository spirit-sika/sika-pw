package cc.sika.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordSearchDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String domainName;
    private String account;
    private String condition;
    private Integer pageNum;
    private Integer pageSize;
}
