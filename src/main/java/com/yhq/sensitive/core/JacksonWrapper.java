package com.yhq.sensitive.core;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 简单封装Jackson，实现JSON String<->Java Object转换的Mapper.
 * 可以直接使用公共示例JsonMapper.INSTANCE, 也可以使用不同的builder函数创建实例，封装不同的输出风格,
 * 不要使用GSON, 在对象稍大时非常缓慢.
 * 注意: 需要参考本模块的POM文件，显式引用jackson.
 * @author yhq
 * @date 2021年9月6日 14点02分
 */
@Slf4j
public class JacksonWrapper implements JsonOutput, SensitiveWrapper {
    private ObjectMapper mapper;

    public JacksonWrapper() {
        this(null);
    }

    public JacksonWrapper(Include include) {
        this.mapper = new ObjectMapper();
        // 设置输出时包含属性的风格
        if (include != null) {
            this.mapper.setSerializationInclusion(include);
        }

        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /**
     * 创建只输出非Null的属性到Json字符串的Mapper.
     * @return jsonMapper
     */
    public static JacksonWrapper nonNullMapper() {
        return new JacksonWrapper(Include.NON_NULL);
    }

    /**
     * Object可以是POJO，也可以是Collection或数组。 如果对象为Null, 返回"null". 如果集合为空集合, 返回"[]".
     * @param object pojo对象
     * @return jsonString
     */
    @Override
    public String toJson(Object object) {
        try {
            return this.mapper.writeValueAsString(sensitive(object));
        } catch (IOException e) {
            log.warn("write to json string error:" + object, e);
            return null;
        }
    }
}
