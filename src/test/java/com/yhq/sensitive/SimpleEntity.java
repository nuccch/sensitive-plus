package com.yhq.sensitive;

import com.yhq.sensitive.annotation.*;
import lombok.Builder;
import lombok.Data;

/**
 * @author chench
 * @date 2024.04.11
 */
@Data
@Builder
public class SimpleEntity {
    /** 中文姓名 */
    @SensitiveLengthChineseName
    String chineseName;

    /** 手机号 */
    @SensitiveLengthMobile
    String mobile;

    /** 固定电话 */
    @SensitiveLengthFixedPhone
    String fixPhone;

    /** 地址 */
    @SensitiveLengthAddress
    String address;

    /** 身份证号 */
    @SensitiveLengthIdCard
    String idCard;

    /** 银行卡号 */
    @SensitiveLengthBankCard
    String bankCard;

    /** 邮箱 */
    @SensitiveLengthEmail
    String email;

    /** 密码 */
    @SensitiveLengthPassword
    String password;
}