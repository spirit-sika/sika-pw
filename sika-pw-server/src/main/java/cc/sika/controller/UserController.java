package cc.sika.controller;

import cc.sika.service.CaptchaService;
import cc.sika.vo.CaptchaVO;
import cc.sika.vo.Result;
import cn.dev33.satoken.annotation.SaIgnore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CaptchaService captchaService;

    @Operation(summary = "获取验证码接口")
    @ApiResponse(content = @Content(schema = @Schema(implementation = CaptchaVO.class)))
    @GetMapping("captcha-image")
    @SaIgnore
    public Result<CaptchaVO> captchaImage() {
        return Result.success(captchaService.generateCaptcha());
    }


}
