package com.yhq.sensitive.strategy;

import com.yhq.sensitive.enums.SensitiveLength;
import com.yhq.sensitive.util.SensitiveInfoUtils;

/**
 * 电子邮箱脱敏
 * @author yhq
 * @date 2021年9月6日 16点13分
 **/
public class SensitiveEmail implements IStrategy {

    @Override
    public String desensitization(String email,String replace,int begin,int end) {
        if(begin != SensitiveLength.EMAIL_DISPLAY_FIRST.getBegin() && begin !=0 ){
            return SensitiveInfoUtils.email(email,begin);
        }
        return SensitiveInfoUtils.email(email, SensitiveLength.EMAIL_DISPLAY_FIRST.getBegin());
    }

}
