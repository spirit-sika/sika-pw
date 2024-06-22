package cc.sika.controller;

import cc.sika.dto.UserLoginDTO;
import cc.sika.dto.UserRegisterDTO;
import cc.sika.service.CaptchaService;
import cc.sika.service.LoginService;
import cc.sika.vo.CaptchaVO;
import cc.sika.vo.Result;
import cc.sika.vo.UserVO;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CaptchaService captchaService;
    private final LoginService loginService;

    @Operation(summary = "获取验证码接口")
    @ApiResponse(content = @Content(schema = @Schema(implementation = CaptchaVO.class)))
    @GetMapping("captcha-image")
    @SaIgnore
    public Result<CaptchaVO> captchaImage() {
        return Result.success(captchaService.generateCaptcha());
    }

    @Operation(summary = "登录接口, 登录成功返回用户信息vo与token")
    @ApiResponse(content = @Content(schema = @Schema(implementation = UserVO.class)))
    @PostMapping("login")
    @SaIgnore
    public Result<UserVO> login(@RequestBody UserLoginDTO loginDTO) {
        return Result.success(loginService.loginAndGetVO(loginDTO));
    }

    @Operation(summary = "用户注册接口, 注册成功响应用户vo")
    @ApiResponse(content = @Content(schema = @Schema(implementation = UserVO.class)))
    @PostMapping("register")
    @SaIgnore
    public Result<UserVO> register(@RequestBody UserRegisterDTO registerDTO) {
        return Result.success(loginService.register(registerDTO));
    }

    @Operation(summary = "退出登录接口")
    @ApiResponse(content = @Content(schema = @Schema(implementation = String.class)))
    @PostMapping("logout")
    @SaCheckLogin
    public Result<String> logout() {
        StpUtil.logout();
        return Result.successMessage("退出登录成功!");
    }

    @GetMapping("check-login")
    @SaCheckLogin
    public Result<String> checkLogin() {
        return Result.success("用户已登录");
    }
}
