package com.yhq.sensitive.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.yhq.sensitive.constant.SensitiveReplaceChars;

import java.lang.annotation.*;

/**
 * 使用显示长度规则对中文姓名脱敏
 * @author yhq
 * @date 2021年9月7日 08点51分
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(
        strategy = com.yhq.sensitive.strategy.SensitiveChineseName.class,
        begin = 1, /** 只显示第1个字符 */
        end = 0,
        replaceChar = SensitiveReplaceChars.ASTERISK_SIMPLE_DEFAULT
)
@JacksonAnnotationsInside
public @interface SensitiveLengthChineseName {

}
