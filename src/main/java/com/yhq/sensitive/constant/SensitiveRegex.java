package com.yhq.sensitive.constant;

/**
 * 脱敏正则表达式
 * @author chench
 * @date 2024.04.11
 */
public abstract class SensitiveRegex {
    /** 中文姓名，只显示第一个字符，其他字符使用特定字符代替 */
    public static final String CHINESE_NAME_DISPLAY_FIRST_ONE = "(?<=.{1}).";

    /** 密码，将所有字符都替换为特定字符 */
    public static final String PASSWORD_REPLACE_ALL = "(?<=).";

    /** 身份证号，只显示最后4为，其他位替换为特定字符 */
    public static final String ID_CARD_DISPLAY_LAST_FOUR = "(?<=\\w{0})\\w(?=\\w{4})";

    /** 固定电话，只显示最后4位，其他位替换为特定字符 */
    public static final String FIXED_PHONE_DISPLAY_LAST_FOUR = "(?<=\\w{0})\\w(?=\\w{4})";

    /** 手机号，只显示前3位及后4位，其他位替换为特定字符 */
    public static final String MOBILE_DISPLAY_FIRST_THREE_LAST_FOUR = "(?<=\\w{3})\\w(?=\\w{4})";

    /** 地址，只显示前5位及后4位，其他位替换为特定字符 */
    public static final String ADDRESS_DISPLAY_FIRST_FIVE_LAST_FOUR = "(.{5}).+(.{4})";

    /** 邮箱，隐藏@符号之前的后3位 */
    public static final String EMAIL_HIDE_LAST_THREE = "(\\w+)\\w{3}@(\\w+)";

    /** 银行卡号，只显示前6位及后4位，其他位替换为特定字符 */
    public static final String BANKCARD_DISPLAY_FIRST_FIX_LAST_FOUR = "(?<=\\w{6})\\w(?=\\w{4})";
}