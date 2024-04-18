package com.yhq.sensitive.converter;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chench
 * @date 2024.04.17
 */
public class SensitiveLogbackMessageConverterTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SensitiveLogbackMessageConverterTest.class);

    String mobile = "13241470086";
    String telephone = mobile;
    String idCard = "123456789012345678"; // 18位身份证号
    String password = "123456";
    String chineseName = "欧阳霸主";

    @Test
    public void testMobile() {
        LOGGER.info("mobile:{}", mobile);
        LOGGER.info("mobile={}", mobile);
        LOGGER.info("mobile,{}", mobile);
        LOGGER.info("mobile {}", mobile);
    }

    @Test
    public void testTelephone() {
        LOGGER.info("telephone:{}", telephone);
        LOGGER.info("telephone={}", telephone);
        LOGGER.info("telephone,{}", telephone);
        LOGGER.info("telephone {}", telephone);
    }

    @Test
    public void testIdCard() {
        LOGGER.info("idcard:{}", idCard);
        idCard = "12345678901234567X";
        LOGGER.info("idcard:{}", idCard);
        LOGGER.info("idcard={}", idCard);
        LOGGER.info("idcard,{}", idCard);
        LOGGER.info("idcard {}", idCard);
        LOGGER.info("idcard  {}", idCard);
    }

    @Test
    public void testPassword() {
        LOGGER.info("password:{}", password);
        LOGGER.info("password={}", password);
        LOGGER.info("password,{}", password);
        LOGGER.info("password {}", password);
    }

    @Test
    public void testChineseName() {
        LOGGER.info("name:{}", chineseName);
        LOGGER.info("name={}", chineseName);
        LOGGER.info("name,{}", chineseName);
        LOGGER.info("name {}", chineseName);

        LOGGER.info("customerName:{}", chineseName);
        LOGGER.info("customerName={}", chineseName);
        LOGGER.info("customerName,{}", chineseName);
        LOGGER.info("customerName {}", chineseName);

        LOGGER.info("userName:{}", chineseName);
        LOGGER.info("userName={}", chineseName);
        LOGGER.info("userName,{}", chineseName);
        LOGGER.info("userName {}", chineseName);

        // 只会对中文名进行脱敏
        chineseName = "张三丰";
        LOGGER.info("name:{}", chineseName);
        LOGGER.info("name={}", chineseName);
        LOGGER.info("name,{}", chineseName);
        LOGGER.info("name {}", chineseName);

        LOGGER.info("customerName:{}", chineseName);
        LOGGER.info("customerName={}", chineseName);
        LOGGER.info("customerName,{}", chineseName);
        LOGGER.info("customerName {}", chineseName);

        LOGGER.info("userName:{}", chineseName);
        LOGGER.info("userName={}", chineseName);
        LOGGER.info("userName,{}", chineseName);
        LOGGER.info("userName {}", chineseName);

        // 英文名不会脱敏
        chineseName = "张abc";
        LOGGER.info("name:{}", chineseName);
        LOGGER.info("name={}", chineseName);
        LOGGER.info("name,{}", chineseName);
        LOGGER.info("name {}", chineseName);

        LOGGER.info("customerName:{}", chineseName);
        LOGGER.info("customerName={}", chineseName);
        LOGGER.info("customerName,{}", chineseName);
        LOGGER.info("customerName {}", chineseName);

        LOGGER.info("userName:{}", chineseName);
        LOGGER.info("userName={}", chineseName);
        LOGGER.info("userName,{}", chineseName);
        LOGGER.info("userName {}", chineseName);
    }

    @Test
    public void testAll() {
        LOGGER.info("mobile:{}, idcard:{}, password:{}, name:{}", mobile, idCard, password, chineseName);
    }
}