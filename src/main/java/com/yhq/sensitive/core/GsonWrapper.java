package com.yhq.sensitive.core;

import com.google.gson.Gson;

/**
 * 封装Gson，实现对象属性脱敏序列化
 * @author chench
 * @date 2024.04.11
 */
public class GsonWrapper implements JsonOutput, SensitiveWrapper {
    private static final GsonWrapper instance = new GsonWrapper();
    private Gson gson = new Gson();

    public static GsonWrapper getInstance() {
        return instance;
    }

    @Override
    public String toJson(Object object) {
        return this.gson.toJson(sensitive(object));
    }

    private GsonWrapper() {}
}