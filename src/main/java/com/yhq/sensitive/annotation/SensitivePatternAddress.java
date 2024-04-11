package com.yhq.sensitive.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.yhq.sensitive.constant.SensitiveRegex;
import com.yhq.sensitive.constant.SensitiveReplaceChars;

import java.lang.annotation.*;

/**
 * 使用正则规则对地址脱敏
 * @author yhq
 * @date 2021年9月7日 08点51分
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(
        strategy = com.yhq.sensitive.strategy.SensitiveAddress.class,
        pattern = SensitiveRegex.ADDRESS_DISPLAY_FIRST_FIVE_LAST_FOUR,
        replaceChar = SensitiveReplaceChars.ASTERISK_PATTERN_ADDRESS
)
@JacksonAnnotationsInside
public @interface SensitivePatternAddress {

}
