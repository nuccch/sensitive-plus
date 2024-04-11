package com.yhq.sensitive.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.yhq.sensitive.constant.SensitiveReplaceChars;

import java.lang.annotation.*;

/**
 * 使用显示长度规则对地址脱敏
 * @author yhq
 * @date 2021年9月7日 08点51分
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(
        strategy = com.yhq.sensitive.strategy.SensitiveAddress.class,
        begin = 0,
        end = 6, /** 隐藏后6位 */
        replaceChar = SensitiveReplaceChars.ASTERISK_SIMPLE_DEFAULT
)
@JacksonAnnotationsInside
public @interface SensitiveLengthAddress {

}
