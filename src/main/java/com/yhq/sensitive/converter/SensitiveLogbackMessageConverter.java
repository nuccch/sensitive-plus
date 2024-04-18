package com.yhq.sensitive.converter;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.yhq.sensitive.enums.SensitiveLogbackRule;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * 基于Logback自定义MessageConverter
 * @author chench
 * @date 2024.04.16
 */
@Slf4j
public class SensitiveLogbackMessageConverter extends MessageConverter {
    // 脱敏规则列表
    private List<SensitiveLogbackRule> ruleList = Arrays.asList(new SensitiveLogbackRule[]{
            SensitiveLogbackRule.MOBILE_TELEPHONE,
            SensitiveLogbackRule.ID_CARD,
            SensitiveLogbackRule.PASSWORD,
            SensitiveLogbackRule.CHINESE_NAME,
    });

    @Override
    public String convert(ILoggingEvent event) {
        // 重写convert方法，返回脱敏后日志内容
        return doConvert(event);
    }

    /**
     * 日志内容转换
     *
     * @param event
     * @return
     */
    private String doConvert(ILoggingEvent event) {
        String result = event.getFormattedMessage();
        if (ruleList != null) {
            for (SensitiveLogbackRule rule : ruleList) {
                result = rule.apply(result);
            }
        } else {
            result = super.convert(event);
        }
        return result;
    }
}