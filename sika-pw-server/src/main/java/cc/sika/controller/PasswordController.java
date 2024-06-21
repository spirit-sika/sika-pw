package cc.sika.controller;

import cc.sika.service.PasswordService;
import cc.sika.vo.Result;
import cc.sika.vo.SikaPWVO;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/pw")
public class PasswordController {
    private final PasswordService passwordService;

    @Operation(summary = "分页获取系统保存的所有密码")
    @ApiResponse(content = @Content(schema = @Schema(implementation = PageInfo.class)))
    @GetMapping("list")
    @SaCheckRole("admin")
    public Result<PageInfo<SikaPWVO>> list(int pageNum, int pageSize) {
        return Result.success(passwordService.listAllPasswords(pageNum, pageSize));
    }

    @Operation(summary = "分页获取用户保存的所有密码")
    @ApiResponse(content = @Content(schema = @Schema(implementation = PageInfo.class)))
    @GetMapping("list-own")
    @SaCheckLogin
    public Result<PageInfo<SikaPWVO>> listByUser(int pageNum, int pageSize) {
        return Result.success(passwordService.listUserPasswords(pageNum, pageSize));
    }
}
