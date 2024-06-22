package cc.sika.service.impl;

import cc.sika.constant.UserConstant;
import cc.sika.dto.PasswordDTO;
import cc.sika.dto.PasswordSearchDTO;
import cc.sika.mapper.SikaPWMapper;
import cc.sika.po.SikaPW;
import cc.sika.po.SikaUser;
import cc.sika.service.PasswordService;
import cc.sika.utils.PageUtils;
import cc.sika.vo.SikaPWVO;
import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service("passwordService")
@RequiredArgsConstructor
public class DefaultPasswordServiceImpl implements PasswordService {
    private final SikaPWMapper sikaPWMapper;

    @Override
    @SuppressWarnings("unused")
    public PageInfo<SikaPWVO> listAllPasswords(int pageNum, int pageSize) {
        try(Page<Object> page = PageHelper.startPage(pageNum, pageSize)) {
            // 查询并构建分页对象
            List<SikaPW> passwords = sikaPWMapper.selectAllPassword();
            PageInfo<SikaPW> poPageInfo = new PageInfo<>(passwords);
            // 转换vo
            return PageUtils.toVO(poPageInfo, SikaPW::toVO);
        }

    }

    @Override
    @SuppressWarnings("unused")
    public PageInfo<SikaPWVO> listUserPasswords(PasswordSearchDTO searchDTO) {
        // 防止分页数据为空
        checkPage(searchDTO);
        Long loginId = Long.valueOf(StpUtil.getLoginId().toString());
        try(Page<Object> page = PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize())) {
            /* 查询并构建分页对象 */
            List<SikaPW> passwords;
            // 精确查找
            if (!StringUtils.hasText(searchDTO.getCondition())) {
                passwords = sikaPWMapper.selectUserSavedPassword(searchDTO, loginId);
            }
            // 模糊查找
            else {
                passwords = sikaPWMapper.likeSelectUserSavedPassword(searchDTO.getCondition(), loginId);
            }
            PageInfo<SikaPW> poPageInfo = new PageInfo<>(passwords);
            // 转换vo
            return PageUtils.toVO(poPageInfo, SikaPW::toVO);
        }
    }

    @Override
    @Transactional
    public int addPassword(PasswordDTO passwordDTO) {
        SikaUser sikaUser = (SikaUser) StpUtil.getSession()
                .get(UserConstant.CURRENT_USER + Long.valueOf(StpUtil.getLoginId().toString()));
        SikaPW po = passwordDTO.toPO(null, sikaUser.getUserId(), sikaUser.getNickname(), null);
        return sikaPWMapper.insertPassword(po);
    }

    @Override
    @Transactional
    public int updatePassword(PasswordDTO passwordDTO) {
        SikaUser sikaUser = (SikaUser) StpUtil.getSession()
                .get(UserConstant.CURRENT_USER + Long.valueOf(StpUtil.getLoginId().toString()));
        SikaPW po = passwordDTO.toPO(passwordDTO.getPwId(), sikaUser.getUserId(), null, sikaUser.getNickname());
        return sikaPWMapper.updatePassword(po);
    }

    @Override
    public int deletePassword(Long id) {
//        if (ArrayUtil.isEmpty(ids)) {
//            return 0;
//        }
//        int count = 0;
//        for(Long id : ids) {
//            sikaPWMapper.deletePassword(id);
//            count++;
//        }
        return sikaPWMapper.deletePassword(id);
    }

    private void checkPage(PasswordSearchDTO searchDTO) {
        if (Objects.isNull(searchDTO.getPageNum()) || searchDTO.getPageNum() <= 0) {
            searchDTO.setPageNum(1);
        }
        if (Objects.isNull(searchDTO.getPageSize()) || searchDTO.getPageSize() <= 0) {
            searchDTO.setPageSize(10);
        }
    }
}
