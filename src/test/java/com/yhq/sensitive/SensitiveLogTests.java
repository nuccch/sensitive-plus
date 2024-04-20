package com.yhq.sensitive;

import com.alibaba.fastjson.JSON;
import com.yhq.sensitive.util.SensitiveInfoUtils;
import com.yhq.sensitive.util.SensitiveJsonUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 打印脱敏日志 <br />
 * 由应用决定是否需要脱敏：在打印日志之前进行脱敏处理 <br />
 * 约定： <br />
 * 1.字符串如果需要脱敏，直接调用脱敏工具处理 <br />
 * 2.对象属性如果需要脱敏，则使用脱敏注解进行标记，在打印日志时将对象转换为JSON字符串
 * @author chench
 * @date 2024.04.11
 */
public class SensitiveLogTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(SensitiveLogTests.class);

    String chineseName = "赵子龙";
    String mobile = "13242429876";
    String fixPhone = "010-32342214";
    String address = "西川成都蜀国大将军府";
    String idCard = "123456789012345678";
    String bankCard = "6666666666666666666";
    String email = "zhaozilong@chengdu.com";
    String password = "1234567890";

    SimpleEntity entity = SimpleEntity.builder()
            .chineseName(chineseName)
            .mobile(mobile)
            .fixPhone(fixPhone)
            .address(address)
            .idCard(idCard)
            .bankCard(bankCard)
            .email(email)
            .password(password)
            .build();

    /**
     * 对字符串脱敏后打印日志
     */
    @Test
    public void testSensitiveString() {
        // 中文名
        LOGGER.info("chineseName: {}", SensitiveInfoUtils.chineseName(chineseName));
        // 手机号
        LOGGER.info("mobile: {}", SensitiveInfoUtils.mobilePhone(mobile));
        // 固定电话
        LOGGER.info("fixPhone: {}", SensitiveInfoUtils.fixedPhone(fixPhone));
        // 地址
        LOGGER.info("address: {}", SensitiveInfoUtils.address(address));
        // 身份证号
        LOGGER.info("idCard: {}", SensitiveInfoUtils.idCard(idCard));
        // 银行卡号
        LOGGER.info("bankCard: {}", SensitiveInfoUtils.bankCard(bankCard));
        // 邮箱
        LOGGER.info("email: {}", SensitiveInfoUtils.email(email));
        // 密码
        LOGGER.info("password: {}", SensitiveInfoUtils.password(password));
    }

    /**
     * 将对象类型转换为JSON字符串后打印日志
     */
    @Test
    public void testSensitiveObject2Json() {
        LOGGER.info("entity: {}", SensitiveJsonUtils.toJson(entity));
    }

    @Test
    public void testSensitiveObject() {
        LOGGER.info("entity: {}", JSON.toJSONString(SensitiveInfoUtils.desensitive(entity)));
    }

}