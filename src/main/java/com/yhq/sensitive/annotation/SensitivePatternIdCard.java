package com.yhq.sensitive.annotation;

import com.yhq.sensitive.constant.SensitiveRegex;
import com.yhq.sensitive.constant.SensitiveReplaceChars;

import java.lang.annotation.*;

/**
 * 使用正则规则对身份证号脱敏
 * @author yhq
 * @date 2021年9月7日 08点51分
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(
        strategy = com.yhq.sensitive.strategy.SensitiveIdCard.class,
        pattern = SensitiveRegex.ID_CARD_DISPLAY_LAST_FOUR,
        replaceChar = SensitiveReplaceChars.ASTERISK_SIMPLE_DEFAULT
)
public @interface SensitivePatternIdCard {

}
