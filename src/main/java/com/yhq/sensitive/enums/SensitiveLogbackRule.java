package com.yhq.sensitive.enums;

import java.util.regex.Pattern;

/**
 * 应用到Logback日志脱敏场景的规则
 * @author chench
 * @date 2024.04.18
 */
public enum SensitiveLogbackRule {
    /**
     * 手机号脱敏，匹配日志格式：                     <br />
     * log.info("关键词:{}")                        <br />
     * log.info("关键词={}")                        <br />
     * log.info("关键词,{}")                        <br />
     * log.info("关键词 {}")                        <br />
     * 关键词可以为“mobile”或“telephone”             <br />
     * 将手机号脱敏为如下格式：123****5678
     */
    MOBILE_TELEPHONE("(mobile|telephone)([:|=|,| ]+)(\\w{3})(\\w{4})(\\w{4})", "$1$2$3****$5"),

    /**
     * 身份证号脱敏，匹配日志格式：                    <br />
     * log.info("idcard:{}")                        <br />
     * log.info("idcard={}")                        <br />
     * log.info("idcard,{}")                        <br />
     * log.info("idcard {}")                        <br />
     * 将身份证号脱敏为如下格式：12345***********67
     */
    ID_CARD("(idcard)([:|=|,| ]+)(\\w{5})(\\w{1,})(\\w{2})", "$1$2$3***********$5"),

    /**
     * 密码脱敏，匹配日志格式：                       <br />
     * log.info("password:{}")                      <br />
     * log.info("password={}")                      <br />
     * log.info("password,{}")                      <br />
     * log.info("password {}")                      <br />
     * 将密码信息替换为固定的5个星号
     */
    PASSWORD("(password)([:|=|,| ]+)(\\w+)", "$1$2*****"),


    /**
     * 中文姓名脱敏，匹配的日志格式：                   <br />
     * log.info("关键词:{}")                          <br />
     * log.info("关键词={}")                          <br />
     * log.info("关键词,{}")                          <br />
     * log.info("关键词 {}")                          <br />
     * 关键词可以为“customerName”或“userName”或“name”  <br />
     * 将中文名脱敏为如下格式：张**
     */
    CHINESE_NAME("(customerName|userName|name)([:|=|,| ]+)([\\u4E00-\\u9FA5]{1})[\\u4E00-\\u9FA5]{1,}", "$1$2$3**")
    ;

    private String regex;
    private String replacement;
    SensitiveLogbackRule(String regex, String replacement) {
        this.regex = regex;
        this.replacement = replacement;
    }

    public String getRegex() {
        return regex;
    }

    public String getReplacement() {
        return replacement;
    }

    public String apply(String message) {
        return Pattern.compile(regex).matcher(message).replaceAll(replacement);
    }
}