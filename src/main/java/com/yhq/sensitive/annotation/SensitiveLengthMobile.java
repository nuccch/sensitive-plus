package com.yhq.sensitive.annotation;

import com.yhq.sensitive.constant.SensitiveReplaceChars;

import java.lang.annotation.*;

/**
 * 使用显示长度规则对手机号脱敏
 * @author yhq
 * @date 2021年9月7日 08点51分
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(
        strategy = com.yhq.sensitive.strategy.SensitiveMobile.class,
        begin = 3, /** 只显示前3位及后4位 */
        end = 4,
        replaceChar = SensitiveReplaceChars.ASTERISK_SIMPLE_DEFAULT
)
public @interface SensitiveLengthMobile {

}
