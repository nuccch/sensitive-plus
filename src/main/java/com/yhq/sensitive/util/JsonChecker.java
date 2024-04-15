package com.yhq.sensitive.util;

import org.springframework.util.ClassUtils;

/**
 * JSON库检查
 * @author chench
 * @date 2024.04.12
 */
public abstract class JsonChecker {
    // 存在jackson库
    private static final boolean jacksonPresent =
            ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper",
                    SensitiveJsonUtils.class.getClassLoader()) &&
                    ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator",
                            SensitiveJsonUtils.class.getClassLoader());
    // 存在fastjson库
    private static final boolean fastjsonPresent =
            ClassUtils.isPresent("com.alibaba.fastjson.JSON",
                    SensitiveJsonUtils.class.getClassLoader());
    // 存在gson库
    private static final boolean gsonPresent =
            ClassUtils.isPresent("com.google.gson.Gson",
                    SensitiveJsonUtils.class.getClassLoader());

    public static boolean isJacksonPresent() {
        return jacksonPresent;
    }

    public static boolean isFastjsonPresent() {
        return fastjsonPresent;
    }

    public static boolean isGsonPresent() {
        return gsonPresent;
    }
}