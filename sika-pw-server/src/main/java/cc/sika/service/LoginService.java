package cc.sika.service;

import cc.sika.dto.UserLoginDTO;
import cc.sika.dto.UserRegisterDTO;
import cc.sika.vo.UserVO;

public interface LoginService {

    /**
     * 登录并获取用户vo
     * @param loginDTO 登录表单对象
     */
    UserVO loginAndGetVO(UserLoginDTO loginDTO);

    /**
     * 用户注册接口, 注册成功直接进行系统登录并响应用户vo
     * @param registerDTO 注册表单对象
     */
    UserVO register(UserRegisterDTO registerDTO);
}
