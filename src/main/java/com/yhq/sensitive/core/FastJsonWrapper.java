package com.yhq.sensitive.core;

import com.alibaba.fastjson.JSON;

/**
 * 封装FastJson，实现对象属性脱敏序列化
 * @author chench
 * @date 2024.04.11
 */
public class FastJsonWrapper implements JsonOutput, SensitiveWrapper {
    private static final FastJsonWrapper instance = new FastJsonWrapper();

    public static FastJsonWrapper getInstance() {
        return instance;
    }

    @Override
    public String toJson(Object object) {
        return JSON.toJSONString(sensitive(object));
    }

    private FastJsonWrapper() {}
}