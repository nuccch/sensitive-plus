package com.yhq.sensitive.enums;

import lombok.Getter;

/**
 * 显示长度脱敏策略
 * @author yhq
 * @date 2021年9月6日 13点33分
 **/
public enum SensitiveStrategyLength {

    /**
     * 中文姓名，只显示第1个字符
     */
    CHINESE_NAME_DISPLAY_FIRST_ONE(1,0),

    /**
     * 密码，将所有字符替换为6个特定字符
     */
    PASSWORD_REPLACE_SIX(6,0),

    /**
     * 身份证号，只显示后4位
     */
    ID_CARD_DISPLAY_LAST_FOUR(0,4),

    /**
     * 固定电话，只显示后4位
     */
    FIXED_PHONE_DISPLAY_LAST_FOUR(0,4),

    /**
     * 手机号码，只显示前3位及后4位
     */
    MOBILE_DISPLAY_FIRST_THREE_LAST_FOUR(3,4),

    /**
     * 地址，隐藏后6位
     */
    ADDRESS_HIDE_LAST_SIX(0,6),

    /**
     * 邮箱，只显示第1位
     */
    EMAIL_DISPLAY_FIRST(1,0),

    /**
     * 银行卡号，只显示前6位及后4位
     */
    BANKCARD_DISPLAY_FIRST_SIX_LAST_FOUR(6,4),

    /**
     * 默认策略
     */
    DEFAULT(6,0),
    ;

    SensitiveStrategyLength(int begin, int end){
        this.begin = begin;
        this.end = end;
    };
    /**
     * 开始长度
     */
    @Getter
    private int begin;

    /**
     * 结束长度
     */
    @Getter
    private int end;

}
