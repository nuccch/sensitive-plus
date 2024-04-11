package com.yhq.sensitive.util;

import com.yhq.sensitive.core.FastJsonOutput;
import com.yhq.sensitive.core.GsonOutput;
import com.yhq.sensitive.core.JacksonOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 脱敏信息JSON序列化
 *
 * @author chench
 * @date 2024.04.10
 */
public class SensitiveJsonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SensitiveJsonUtils.class);
    private static final String JACKSON = "com.fasterxml.jackson.core.JsonGenerator";
    private static final String FASTJSON = "com.alibaba.fastjson.JSON";
    private static final String GSON = "com.google.gson.Gson";

    /**
     * 将对象转换为JSON字符串，如果对象属性中使用了脱敏注解，在转换时会进行脱敏处理
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        if (checkClassExists(JACKSON)) {
            // 优先使用jackson进行序列化
            return JacksonOutput.nonNullMapper().toJson(object);
        } else if (checkClassExists(FASTJSON)) {
            // 使用FastJson序列化
            return FastJsonOutput.getInstance().toJson(object);
        } else if (checkClassExists(GSON)) {
            // 使用Gson序列化
            return GsonOutput.getInstance().toJson(object);
        } else {
            LOGGER.error("Can not find any JSON serializer! Please add some such as jackson, fastjson or gson");
        }
        return null;
    }

    /**
     * 检查指定类是否存在
     * @param clazz
     * @return
     */
    private static boolean checkClassExists(String clazz) {
        try {
            Class.forName(clazz, false, SensitiveJsonUtils.class.getClassLoader());
            return true;
        } catch (ClassNotFoundException e) {
            LOGGER.warn("{} not found", clazz);
        }
        return false;
    }

}