package cc.sika.service;

import cc.sika.exception.CaptchaException;
import cc.sika.vo.CaptchaVO;

public interface CaptchaService {
    /**
     * 生成验证码, 使用雪花算法生成验证码的键并将验证码放入到redis中设置有效时长
     * @return 返回带有验证码键值对的VO实体, 包含验证码的可寻键
     */
    CaptchaVO generateCaptcha();

    /**
     * 验证码校验, 校验不通过直接给出运行时异常
     * @param key 验证码键
     * @param code 用户提交的值
     * @throws CaptchaException <ul><li>没有提交验证码键</li><li>缓存中没有对应的键值</li><li>值校验不通过</li></ul>
     */
    void checkCaptcha(String key, String code);
}
