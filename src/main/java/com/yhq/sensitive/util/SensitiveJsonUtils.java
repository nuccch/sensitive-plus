package com.yhq.sensitive.util;

import com.yhq.sensitive.core.JsonMapper;

/**
 * JSON序列化脱敏工具
 *
 * @author chench
 * @date 2024.04.10
 */
public abstract class SensitiveJsonUtils {

    /**
     * 将对象转换为JSON字符串，如果对象属性中使用了脱敏注解，在转换时会进行脱敏处理
     * @param obj
     * @return
     */
    public static String toJsonString(Object obj) {
        return JsonMapper.nonNullMapper().toJson(obj);
    }
}