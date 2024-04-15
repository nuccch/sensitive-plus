package com.yhq.sensitive.util;

import com.yhq.sensitive.core.FastJsonWrapper;
import com.yhq.sensitive.core.GsonWrapper;
import com.yhq.sensitive.core.JacksonWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 脱敏信息JSON序列化
 *
 * @author chench
 * @date 2024.04.10
 */
@Slf4j
public class SensitiveJsonUtils {
    /**
     * 将对象转换为JSON字符串，如果对象属性中使用了脱敏注解，在转换时会进行脱敏处理
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        if (JsonChecker.isJacksonPresent()) {
            // 优先使用jackson进行序列化
            return JacksonWrapper.nonNullMapper().toJson(object);
        } else if (JsonChecker.isFastjsonPresent()) {
            // 使用FastJson序列化
            return FastJsonWrapper.getInstance().toJson(object);
        } else if (JsonChecker.isGsonPresent()) {
            // 使用Gson序列化
            return GsonWrapper.getInstance().toJson(object);
        } else {
            log.error("Can not find any JSON serializer! Please add some such as jackson, fastjson or gson");
        }
        return null;
    }

}