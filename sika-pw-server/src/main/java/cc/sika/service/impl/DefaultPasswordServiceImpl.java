package cc.sika.service.impl;

import cc.sika.mapper.SikaPWMapper;
import cc.sika.po.SikaPW;
import cc.sika.service.PasswordService;
import cc.sika.utils.PageUtils;
import cc.sika.vo.SikaPWVO;
import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public PageInfo<SikaPWVO> listUserPasswords(int pageNum, int pageSize) {
        Long loginId = Long.valueOf(StpUtil.getLoginId().toString());
        try(Page<Object> page = PageHelper.startPage(pageNum, pageSize)) {
            // 查询并构建分页对象
            List<SikaPW> passwords = sikaPWMapper.selectUserSavedPassword(loginId);
            PageInfo<SikaPW> poPageInfo = new PageInfo<>(passwords);
            // 转换vo
            return PageUtils.toVO(poPageInfo, SikaPW::toVO);
        }
    }
}
