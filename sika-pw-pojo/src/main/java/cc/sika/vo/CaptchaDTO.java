package cc.sika.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String captchaKey;
    private String userValue;
}
