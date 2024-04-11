package com.yhq.sensitive.core;

import com.alibaba.fastjson.JSON;

/**
 * 封装FastJson，实现对象属性脱敏序列化
 * @author chench
 * @date 2024.04.11
 */
public class FastJsonOutput implements JsonOutput {
    private static final FastJsonOutput instance = new FastJsonOutput();

    public static FastJsonOutput getInstance() {
        return instance;
    }

    @Override
    public String toJson(Object object) {
        return JSON.toJSONString(sensitive(object));
    }

    private FastJsonOutput() {}
}