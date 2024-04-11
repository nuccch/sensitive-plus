package com.yhq.sensitive.annotation;

import com.yhq.sensitive.constant.SensitiveReplaceChars;

import java.lang.annotation.*;

/**
 * 使用显示长度规则对固定号码脱敏
 * @author yhq
 * @date 2021年9月7日 08点51分
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(
        strategy = com.yhq.sensitive.strategy.SensitiveFixedPhone.class,
        begin = 0,
        end = 4, /** 只显示后4位 */
        replaceChar = SensitiveReplaceChars.ASTERISK_SIMPLE_DEFAULT
)
public @interface SensitiveLengthFixedPhone {

}
