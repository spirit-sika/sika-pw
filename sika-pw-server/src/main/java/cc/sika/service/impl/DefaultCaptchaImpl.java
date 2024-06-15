package cc.sika.service.impl;

import cc.sika.constant.CacheConstant;
import cc.sika.constant.CaptchaConstant;
import cc.sika.exception.CaptchaException;
import cc.sika.service.CaptchaService;
import cc.sika.vo.CaptchaVO;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
public class DefaultCaptchaImpl implements CaptchaService {
    private final StringRedisTemplate stringRedisTemplate;
    private final ValueOperations<String, String> redisOperations;

    @Override
    public CaptchaVO generateCaptcha() {
        /* 生成验证码与键 */
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 50, 4, 100);
        String snowflakeCodeKey = IdUtil.getSnowflakeNextIdStr();
        String codeContent = lineCaptcha.getCode();

        // 将验证码放入redis设置15分钟有效时长
        cacheCode(snowflakeCodeKey, codeContent);

        // 生成可渲染的VO
        return CaptchaVO.builder()
                .captchaKey(snowflakeCodeKey)
                .base64(lineCaptcha.getImageBase64Data())
                .build();
    }

    @Override
    public boolean checkCaptcha(String key, String code) {
        /* 非法验证码 */
        if (!StringUtils.hasText(key)) {
            throw new CaptchaException(CaptchaConstant.NON_KEY);
        }

        String cacheCodeKey = CacheConstant.CODE_KEY_PREFIX + key;
        String codeInCache = redisOperations.get(cacheCodeKey);

        /* 未获取到验证码内容 */
        if (!StringUtils.hasText(codeInCache)) {
            stringRedisTemplate.delete(cacheCodeKey);
            throw new CaptchaException(CaptchaConstant.EXPIRED_CODE);
        }

        /* 忽略大小写校验验证码 */
        if (!codeInCache.equalsIgnoreCase(code)) {
            stringRedisTemplate.delete(cacheCodeKey);
            throw new CaptchaException(CaptchaConstant.ERROR_CODE);
        }

        stringRedisTemplate.delete(cacheCodeKey);
        return true;
    }

    /**
     * 默认十五分钟失效缓存验证码
     * @param captchaKey 验证码键
     * @param captchaValue 验证码值
     */
    private void cacheCode(String captchaKey, String captchaValue) {
        cacheCode(captchaKey, captchaValue, 15, TimeUnit.MINUTES);
    }

    /**
     * 带有时效的设置验证码
     * @param captchaKey 验证码键
     * @param captchaValue 验证码值
     * @param timeout 失效时长
     * @param timeUnit 失效时长单位
     */
    private void cacheCode(String captchaKey, String captchaValue, final long timeout, final TimeUnit timeUnit) {
        redisOperations.set(CacheConstant.CODE_KEY_PREFIX + captchaKey, captchaValue, timeout, timeUnit);
    }



    public DefaultCaptchaImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.redisOperations = stringRedisTemplate.opsForValue();
    }
}
