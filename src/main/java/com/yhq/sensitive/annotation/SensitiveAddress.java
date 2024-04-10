package com.yhq.sensitive.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.yhq.sensitive.constant.SensitiveReplaceChars;

import java.lang.annotation.*;

/**
 * 地址脱敏
 * @author yhq
 * @date 2021年9月7日 08点51分
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(
        strategy = com.yhq.sensitive.strategy.SensitiveAddress.class,
        pattern = "(.{5}).+(.{4})",
        replaceChar = SensitiveReplaceChars.ASTERISK_PATTERN_ADDRESS
)
@JacksonAnnotationsInside
public @interface SensitiveAddress {

}
