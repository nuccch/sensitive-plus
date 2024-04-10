package com.yhq.sensitive.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.yhq.sensitive.constant.SensitiveReplaceChars;

import java.lang.annotation.*;

/**
 * 邮箱脱敏
 * @author yhq
 * @date 2021年9月7日 08点51分
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(
        strategy = com.yhq.sensitive.strategy.SensitiveEmail.class,
        pattern = "(\\w+)\\w{3}@(\\w+)",
        replaceChar = SensitiveReplaceChars.ASTERISK_PATTERN_EMAIL
)
@JacksonAnnotationsInside
public @interface SensitiveEmail {

}
