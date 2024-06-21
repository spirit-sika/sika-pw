package cc.sika.mapper;

import cc.sika.po.SikaUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SikaUserMapper {
    SikaUser selectByPhoneAndPassword(@Param("phoneNumber") String phoneNumber, @Param("password") String password);

    SikaUser selectByPhone(@Param("phoneNumber") String phoneNumber);

    int insert(SikaUser sikaUser);

    List<String> selectAllPhoneNumber();

    List<String> selectUserRole(@Param("userId")Long userId);

    int bindingRole(@Param("userId") Long userId, @Param("roleId")Long roleId);
}
