package com.yhq.sensitive.constant;

/**
 * 脱敏替换字符
 * @author chench
 * @date 2024.04.10
 */
public abstract class SensitiveReplaceChars {
    /** 空字符 */
    public static final String EMPTY = "";

    /** 星号 */
    public static final String ASTERISK_SIMPLE_DEFAULT = "*";

    /** 星号替换，地址使用 */
    public static final String ASTERISK_PATTERN_ADDRESS = "$1*****$2";

    /** 星号替换，邮箱使用 */
    public static final String ASTERISK_PATTERN_EMAIL = "$1***@$2";

}