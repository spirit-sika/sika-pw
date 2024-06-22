package cc.sika.controller;

import cc.sika.dto.PasswordDTO;
import cc.sika.dto.PasswordSearchDTO;
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
import org.springframework.web.bind.annotation.*;

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
    public Result<PageInfo<SikaPWVO>> listByUser(PasswordSearchDTO searchDTO) {
        return Result.success(passwordService.listUserPasswords(searchDTO));
    }

    @Operation(summary = "添加密码")
    @ApiResponse(content = @Content(schema = @Schema(implementation = String.class)))
    @PostMapping
    @SaCheckLogin
    public Result<String> add(@RequestBody PasswordDTO passwordDTO) {
        int result = passwordService.addPassword(passwordDTO);
        if (result == 1) {
            return Result.success("密码保存成功");
        }
        else {
            return Result.error();
        }
    }

    @Operation(summary = "修改密码")
    @ApiResponse(content = @Content(schema = @Schema(implementation = String.class)))
    @SaCheckLogin
    @PutMapping
    public Result<String> update(@RequestBody PasswordDTO passwordDTO) {
        int updated = passwordService.updatePassword(passwordDTO);
        if (updated == 1) {
            return Result.success("更新成功");
        }
        else {
            return Result.error();
        }
    }

    @SaCheckLogin
    @DeleteMapping
    public Result<String> delete(@RequestBody Long id) {
        int i = passwordService.deletePassword(id);
        return Result.success("成功删除" + i + "条记录");
    }

}
