package cc.sika.handler;

import cc.sika.mapper.SikaUserMapper;
import cn.dev33.satoken.stp.StpInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorizationHandler implements StpInterface {

    private final SikaUserMapper sikaUserMapper;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return List.of();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return sikaUserMapper.selectUserRole(Long.valueOf(loginId.toString()));
    }
}
