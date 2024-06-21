package cc.sika;

import cc.sika.mapper.SikaPWMapper;
import cc.sika.po.SikaPW;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestSikaPWMapper {
    @Resource
    private SikaPWMapper sikaPWMapper;

    @Test
    void selectAllPasswordTest() {
        List<SikaPW> passwords = sikaPWMapper.selectAllPassword();
        assert !passwords.isEmpty();
        assert passwords.get(0).getUserId() >= passwords.get(1).getUserId();
    }

    @Test
    void pageSelectAllPasswordTest() {
        PageHelper.startPage(2,10);
        List<SikaPW> passwords = sikaPWMapper.selectAllPassword();
        assert !passwords.isEmpty();
        assert passwords.get(0).getUserId() >= passwords.get(1).getUserId();
    }

    @Test
    void selectUserSavedPasswordTest() {
        List<SikaPW> passwords = sikaPWMapper.selectUserSavedPassword(4L);
        assert !passwords.isEmpty();
        System.out.println("passwords = " + passwords);
    }
}
