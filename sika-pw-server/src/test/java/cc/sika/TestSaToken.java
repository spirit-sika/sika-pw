package cc.sika;

import cn.dev33.satoken.secure.SaSecureUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestSaToken {

    @Test
    void testPasswordBuilder() {
        String sha256 = SaSecureUtil.sha256("123456");
        // 8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92
        System.out.println("sha256 = " + sha256);
        assert sha256.length() == 64 : "SaSecureUtil.sha256(${password}) build password fail";
        System.out.println("sha256.length() = " + sha256.length());
    }
}
