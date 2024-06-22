package cc.sika.mapper;

import cc.sika.dto.PasswordSearchDTO;
import cc.sika.po.SikaPW;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SikaPWMapper {
    /**
     * 获取系统中所有的密码, 按照用户id进行排序, 排序后的内容按照pw_id二级排列
     * @return 密码po列表
     */
    List<SikaPW> selectAllPassword();


    /**
     * 获取用户保存的密码
     * @return 密码po列表
     */
    List<SikaPW> selectUserSavedPassword(PasswordSearchDTO passwordSearchDTO, @Param("userId") Long userId);

    List<SikaPW> likeSelectUserSavedPassword(String condition, @Param("userId") Long userId);

    int insertPassword(SikaPW po);

    int deletePassword(@Param("pwId")Long pwId);

    int updatePassword(SikaPW po);
}
