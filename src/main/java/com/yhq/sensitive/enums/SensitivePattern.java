package com.yhq.sensitive.enums;

import com.yhq.sensitive.constant.SensitiveRegex;
import com.yhq.sensitive.constant.SensitiveReplaceChars;
import lombok.Getter;

/**
 * 正则
 * @author yhq
 * @date 2021年9月6日 13点33分
 **/
public enum SensitivePattern {

    /**
     * 中文姓名，只显示第一个字符，其他字符使用特定字符代替
     */
    CHINESE_NAME_DISPLAY_FIRST_ONE(SensitiveRegex.CHINESE_NAME_DISPLAY_FIRST_ONE,
            SensitiveReplaceChars.ASTERISK_SIMPLE_DEFAULT),

    /**
     * 密码，将所有字符都替换为特定字符
     */
    PASSWORD_REPLACE_ALL(SensitiveRegex.PASSWORD_REPLACE_ALL,
            SensitiveReplaceChars.ASTERISK_SIMPLE_DEFAULT),

    /**
     * 身份证号，只显示最后4为，其他位替换为特定字符
     */
    ID_CARD_DISPLAY_LAST_FOUR(SensitiveRegex.ID_CARD_DISPLAY_LAST_FOUR,
            SensitiveReplaceChars.ASTERISK_SIMPLE_DEFAULT),

    /**
     * 固定电话，只显示最后4位，其他位替换为特定字符
     */
    FIXED_PHONE_DISPLAY_LAST_FOUR(SensitiveRegex.FIXED_PHONE_DISPLAY_LAST_FOUR,
            SensitiveReplaceChars.ASTERISK_SIMPLE_DEFAULT),

    /**
     * 手机号，只显示前3位及后4位，其他位替换为特定字符
     */
    MOBILE_DISPLAY_FIRST_THREE_LAST_FOUR(SensitiveRegex.MOBILE_DISPLAY_FIRST_THREE_LAST_FOUR,
            SensitiveReplaceChars.ASTERISK_SIMPLE_DEFAULT),

    /**
     * 地址，只显示前5位及后4位，其他位替换为特定字符
     */
    ADDRESS_DISPLAY_FIRST_FIVE_LAST_FOUR(SensitiveRegex.ADDRESS_DISPLAY_FIRST_FIVE_LAST_FOUR,
            SensitiveReplaceChars.ASTERISK_PATTERN_ADDRESS),

    /**
     * 邮箱，隐藏@符号之前的后3位
     */
    EMAIL_HIDE_LAST_THREE(SensitiveRegex.EMAIL_HIDE_LAST_THREE,
            SensitiveReplaceChars.ASTERISK_PATTERN_EMAIL),

    /**
     * 银行卡号，只显示前6位及后4位，其他位替换为特定字符
     */
    BANKCARD_DISPLAY_FIRST_FIX_LAST_FOUR(SensitiveRegex.BANKCARD_DISPLAY_FIRST_FIX_LAST_FOUR,
            SensitiveReplaceChars.ASTERISK_SIMPLE_DEFAULT),

    /**
     * 默认策略
     */
    DEFAULT("",SensitiveReplaceChars.EMPTY)
    ;

    SensitivePattern(String pattern, String replaceChar){
        this.pattern = pattern;
        this.replaceChar = replaceChar;
    }

    /**
     * 正则-输入格式(1,2,2)
     */
    @Getter
    private String pattern;

    /**
     * 替换后的字符
     */
    @Getter
    private String replaceChar;

}
