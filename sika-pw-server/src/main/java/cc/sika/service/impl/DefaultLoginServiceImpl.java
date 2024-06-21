package cc.sika.service.impl;

import cc.sika.dto.UserLoginDTO;
import cc.sika.dto.UserRegisterDTO;
import cc.sika.exception.IllegalPhoneException;
import cc.sika.exception.LoginException;
import cc.sika.exception.RegisterException;
import cc.sika.mapper.SikaUserMapper;
import cc.sika.po.SikaUser;
import cc.sika.service.CaptchaService;
import cc.sika.service.LoginService;
import cc.sika.vo.UserVO;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class DefaultLoginServiceImpl implements LoginService {

    private final CaptchaService captchaService;
    private final SikaUserMapper sikaUserMapper;

    @Override
    public UserVO loginAndGetVO(UserLoginDTO loginDTO) {
        if (BeanUtil.isEmpty(loginDTO)) {
            throw new LoginException();
        }
        // 校验
        SikaUser sikaUser = checkForm(loginDTO);
        // 登录
        if (loginDTO.getRememberMe()) {
            doLogin(sikaUser, true, TimeUnit.SECONDS, 60*60*24*15);
        }
        else {
            doLogin(sikaUser, false, null, 0);
        }

        // 转换VO
        return toUserVO(sikaUser);
    }


    @Override
    public UserVO register(UserRegisterDTO registerDTO) {
        // 校验手机号码是否存在
        List<String> existPhones = sikaUserMapper.selectAllPhoneNumber();
        if (!Objects.isNull(existPhones)
                && !existPhones.isEmpty()
                && existPhones.contains(registerDTO.getPhoneNumber())) {
            throw new IllegalPhoneException(IllegalPhoneException.PHONE_EXIST);
        }

        SikaUser registerUserPO = registerDTOToSikaUser(registerDTO);
        int insertResult = sikaUserMapper.insert(registerUserPO);
        if (insertResult != 0) {
            throw new RegisterException(RegisterException.REGISTER_ERROR);
        }
        doLogin(registerUserPO, true, TimeUnit.DAYS, 1);
        return toUserVO(registerUserPO);
    }

    /**
     * 执行登录逻辑, 校验用户表单信息, 成功后将用户信息放入 Satoken 的会话容器缓存
     * @param sikaUser 用户表单信息
     */
    private void doLogin(SikaUser sikaUser, boolean rememberMe, TimeUnit timeUnit, long timeout) {
        Long userId = sikaUser.getUserId();
        // 记住我, 按照指定的时间
        if (rememberMe) {
            StpUtil.login(userId, new SaLoginModel().setTimeout(timeUnit.toSeconds(timeout)));
        }
        else {
            StpUtil.login(userId);
        }
        // 缓存用户信息
        StpUtil.getSession().set(LoginException.CURRENT_USER, sikaUser);
    }

    /**
     * 进行表单校验
     * @return 校验成功返回查询到的用户PO
     */
    private SikaUser checkForm(UserLoginDTO loginDTO) {
        // 校验验证码
        captchaService.checkCaptcha(loginDTO.getCodeKey(), loginDTO.getCode());

        // 验证用户信息
        String phone = loginDTO.getPhoneNumber();
        String password = SaSecureUtil.sha256(loginDTO.getPassword());
        SikaUser loginUser = sikaUserMapper.selectByPhoneAndPassword(phone, password);

        // 校验不通过, 定位原因
        if (Objects.isNull(loginUser)) {
            SikaUser phoneExists = sikaUserMapper.selectByPhone(phone);
            // 号码未注册
            if (Objects.isNull(phoneExists)) {
                throw new LoginException(LoginException.PHONE_NO_EXIST);
            }
            // 输入错误
            throw new LoginException(LoginException.MISMATCH);
        }
        return loginUser;
    }

    /**
     * 将注册表单的对象转为用户PO
     */
    private SikaUser registerDTOToSikaUser(UserRegisterDTO registerDTO) {
        SikaUser sikaUser = BeanUtil.copyProperties(registerDTO, SikaUser.class);
        sikaUser.setCreateBy(registerDTO.getNickname());
        if (StpUtil.isLogin()) {
            SikaUser loginUser = (SikaUser)StpUtil.getSession().get(StpUtil.getLoginIdAsString());
            if (!Objects.isNull(loginUser)) {
                sikaUser.setCreateBy(loginUser.getNickname());
            }
        }
        return sikaUser;
    }

    /**
     * 将po转为用户vo
     */
    private static UserVO toUserVO(SikaUser sikaUser) {
        UserVO vo = BeanUtil.copyProperties(sikaUser, UserVO.class, "sex");
        vo.setToken(StpUtil.getTokenValue());
        switch (sikaUser.getSex()) {
            case 1: vo.setSex("男");break;
            case 2: vo.setSex("女");break;
            default: vo.setSex("未知");
        }
        return vo;
    }
}
