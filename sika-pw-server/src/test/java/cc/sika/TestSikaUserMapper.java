package cc.sika;

import cc.sika.constant.UserConstant;
import cc.sika.mapper.SikaUserMapper;
import cc.sika.po.SikaUser;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.captcha.generator.RandomGenerator;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
public class TestSikaUserMapper {

    @Resource
    private SikaUserMapper sikaUserMapper;

    @Test
    void insertTest() {
        SikaUser user = SikaUser.builder()
                .nickname("测试添加账号")
                .password(SaSecureUtil.sha256("123456"))
                .phoneNumber(phoneBuilder())
                .email("123456@qq.com")
                .sex((byte) 0)
                .createBy("单元测试").build();
        int result = sikaUserMapper.insert(user);
        assert result == 1;
        assert user.getUserId() != null && user.getUserId() > 0;
    }

    @Test
    void loginTest() {
        SikaUser sikaUser = sikaUserMapper.selectByPhoneAndPassword("11111111111", SaSecureUtil.sha256("123456"));
        assert sikaUser != null;
        assert sikaUser.getUserId() > 0;
    }

    @Test
    void phoneQueryTest() {
        // 如果存在多条记录, MyBatis会报错 org.mybatis.spring.MyBatisSystemException
        SikaUser sikaUser = sikaUserMapper.selectByPhone("11111111111");
        assert sikaUser != null;
        assert sikaUser.getUserId() > 0;
    }

    @Test
    void bindingRoleTest() {
        int bindingResult = sikaUserMapper.bindingRole(5L, UserConstant.USER_ID);
        assert bindingResult == 1;
    }

    private String phoneBuilder() {
        List<String> existPhones = sikaUserMapper.selectAllPhoneNumber();
        RandomGenerator randomGenerator = new RandomGenerator(StandardCharsets.UTF_8.name(), 11);
        String randomNumber = randomGenerator.generate();
        while (existPhones.contains(randomNumber)) {
            randomNumber = randomGenerator.generate();
        }
        return randomNumber;
    }
}
