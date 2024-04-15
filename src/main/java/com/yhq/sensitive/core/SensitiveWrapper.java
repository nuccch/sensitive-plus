package com.yhq.sensitive.core;

import com.yhq.sensitive.annotation.SensitiveInfo;
import com.yhq.sensitive.strategy.IStrategy;
import org.reflections.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Set;

/**
 * @author chench
 * @date 2024.04.15
 */
public interface SensitiveWrapper {
    /**
     * 对象属性脱敏
     * @param object
     * @return
     */
    default Object sensitive(Object object) {
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                // 只处理字符串类型
                if (!Objects.equals(field.getType(), String.class)) {
                    continue;
                }
                field.setAccessible(true);
                String source = String.valueOf(field.get(object));
                String sensitive;
                SensitiveInfo sensitiveInfo = getAnnotation(field);
                if (Objects.isNull(sensitiveInfo)) {
                    continue;
                }
                IStrategy strategy = sensitiveInfo.strategy().getDeclaredConstructor().newInstance();
                int begin = sensitiveInfo.begin();
                int end = sensitiveInfo.end();
                if (begin == 0 && end == 0) {
                    sensitive = strategy.desensitizationByPattern(source, sensitiveInfo.replaceChar(), sensitiveInfo.pattern());
                } else {
                    sensitive = strategy.desensitization(source, sensitiveInfo.replaceChar(), begin, end);
                }
                field.set(object, sensitive);
            }
            return object;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取对象属性上的脱敏注解
     * @param field
     * @return
     */
    default SensitiveInfo getAnnotation(Field field) {
        Set<Annotation> annotations = ReflectionUtils.getAllAnnotations(field);
        for (Annotation annotation : annotations) {
            if (Objects.equals(annotation.annotationType(), SensitiveInfo.class)) {
                return (SensitiveInfo) annotation;
            }
        }
        return null;
    }
}