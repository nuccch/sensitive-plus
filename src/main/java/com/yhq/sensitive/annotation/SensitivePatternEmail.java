package com.yhq.sensitive.annotation;

import com.yhq.sensitive.constant.SensitiveRegex;
import com.yhq.sensitive.constant.SensitiveReplaceChars;

import java.lang.annotation.*;

/**
 * 使用正则规则对邮箱脱敏
 * @author yhq
 * @date 2021年9月7日 08点51分
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(
        strategy = com.yhq.sensitive.strategy.SensitiveEmail.class,
        pattern = SensitiveRegex.EMAIL_HIDE_LAST_THREE,
        replaceChar = SensitiveReplaceChars.ASTERISK_PATTERN_EMAIL
)
public @interface SensitivePatternEmail {

}
