package com.yhq.sensitive.util;

import com.yhq.sensitive.core.FastJsonOutput;
import com.yhq.sensitive.core.GsonOutput;
import com.yhq.sensitive.core.JacksonOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

/**
 * 脱敏信息JSON序列化
 *
 * @author chench
 * @date 2024.04.10
 */
public class SensitiveJsonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SensitiveJsonUtils.class);
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

    /**
     * 将对象转换为JSON字符串，如果对象属性中使用了脱敏注解，在转换时会进行脱敏处理
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        if (jacksonPresent) {
            // 优先使用jackson进行序列化
            return JacksonOutput.nonNullMapper().toJson(object);
        } else if (fastjsonPresent) {
            // 使用FastJson序列化
            return FastJsonOutput.getInstance().toJson(object);
        } else if (gsonPresent) {
            // 使用Gson序列化
            return GsonOutput.getInstance().toJson(object);
        } else {
            LOGGER.error("Can not find any JSON serializer! Please add some such as jackson, fastjson or gson");
        }
        return null;
    }

}