package com.yhq.sensitive.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.yhq.sensitive.constant.SensitiveRegex;
import com.yhq.sensitive.constant.SensitiveReplaceChars;

import java.lang.annotation.*;

/**
 * 使用正则规则对银行卡号脱敏
 * @author yhq
 * @date 2021年9月7日 08点51分
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(
        strategy = com.yhq.sensitive.strategy.SensitiveChineseName.class,
        pattern = SensitiveRegex.BANKCARD_DISPLAY_FIRST_FIX_LAST_FOUR,
        replaceChar = SensitiveReplaceChars.ASTERISK_SIMPLE_DEFAULT
)
@JacksonAnnotationsInside
public @interface SensitivePatternBankCard {

}
