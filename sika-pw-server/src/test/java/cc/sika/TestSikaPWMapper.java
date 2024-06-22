package cc.sika;

import cc.sika.dto.PasswordSearchDTO;
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
        PageHelper.startPage(1,10);
        PasswordSearchDTO searchDTO1 = PasswordSearchDTO.builder()
                .domainName("Nakayama Shino")
                .account("dHxEzd6zA6")
                .build();
        List<SikaPW> password = sikaPWMapper.selectUserSavedPassword(searchDTO1, 4L);
        assert !password.isEmpty();

        PageHelper.startPage(1,10);
        PasswordSearchDTO searchDTO2 = PasswordSearchDTO.builder()
                .domainName("Jin Xiuying")
                .build();
        password = sikaPWMapper.selectUserSavedPassword(searchDTO2, 4L);
        assert !password.isEmpty();

        PageHelper.startPage(1,10);
        PasswordSearchDTO searchDTO3 = PasswordSearchDTO.builder()
                .account("hq4bg10UR5")
                .build();
        password = sikaPWMapper.selectUserSavedPassword(searchDTO3, 4L);
        assert !password.isEmpty();
    }

    @Test
    void likeSelectUserSavedPasswordTest() {
        PageHelper.startPage(1,10);
        List<SikaPW> password = sikaPWMapper.likeSelectUserSavedPassword("TsWPlrOSIj", 3L);
        assert !password.isEmpty();
    }
}
