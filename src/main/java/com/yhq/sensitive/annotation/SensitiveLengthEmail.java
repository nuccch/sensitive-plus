package com.yhq.sensitive.annotation;

import com.yhq.sensitive.constant.SensitiveReplaceChars;

import java.lang.annotation.*;

/**
 * 使用显示长度规则对邮箱脱敏
 * @author yhq
 * @date 2021年9月7日 08点51分
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(
        strategy = com.yhq.sensitive.strategy.SensitiveEmail.class,
        begin = 1, /** 只显示第1位 */
        end = 0,
        replaceChar = SensitiveReplaceChars.ASTERISK_PATTERN_EMAIL
)
public @interface SensitiveLengthEmail {

}
