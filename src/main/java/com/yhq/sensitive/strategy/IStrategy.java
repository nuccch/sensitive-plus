package com.yhq.sensitive.strategy;

import com.yhq.sensitive.util.SensitiveInfoUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 脱敏策略
 * @author yhq
 * @date 2021年8月31日 14点00分
 */
public interface IStrategy {

    /**
     * 脱敏的具体实现方法
     * @param source 脱敏前的值
     * @param replace 脱敏替换字符
     * @param begin 内容开始显示的长度
     * @param end 内容末尾显示的长度
     * @return 返回脱敏后的信息
     */
    String desensitization(final String source, String replace, int begin, int end);

    /**
     * 脱敏的具体实现方法
     * @param source 脱敏前的值
     * @param replace 脱敏替换字符
     * @param pattern 内容显示正则
     * @return 返回脱敏后的信息
     */
    default String desensitizationByPattern(String source, String replace, String pattern) {
        if (!isDesensitizable(source)) {
            return source;
        }
        return SensitiveInfoUtils.patternReplace(source, replace, pattern);
    }

    /**
     * 检查是否可脱敏
     * @param source 脱敏前的值
     * @return
     */
    default boolean isDesensitizable(String source) {
        return StringUtils.isNotBlank(source) && !"null".equals(source.trim());
    }

}
