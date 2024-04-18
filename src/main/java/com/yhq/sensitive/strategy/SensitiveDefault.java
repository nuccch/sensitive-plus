package com.yhq.sensitive.strategy;

import com.yhq.sensitive.enums.SensitiveLength;
import com.yhq.sensitive.util.SensitiveInfoUtils;

/**
 * 默认脱敏策略
 * @author yhq
 * @date 2021年9月6日 13点30分
 */
public class SensitiveDefault implements IStrategy{

    @Override
    public String desensitization(String source, String replace, int begin, int end) {
        if (!isDesensitizable(source)) {
            return source;
        }
        return SensitiveInfoUtils.password(source, replace, SensitiveLength.DEFAULT.getBegin());
    }
}
