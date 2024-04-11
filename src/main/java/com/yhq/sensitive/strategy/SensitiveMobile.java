package com.yhq.sensitive.strategy;

import com.yhq.sensitive.enums.SensitiveLength;
import com.yhq.sensitive.util.SensitiveInfoUtils;

/**
 * 手机号码脱敏
 * @author yhq
 * @date 2021年9月6日 16点13分
 **/
public class SensitiveMobile implements IStrategy {

    @Override
    public String desensitization(String mobile,String replace,int begin ,int end) {
        if(begin != SensitiveLength.MOBILE_DISPLAY_FIRST_THREE_LAST_FOUR.getBegin() && begin !=0 &&
                end != SensitiveLength.MOBILE_DISPLAY_FIRST_THREE_LAST_FOUR.getEnd() && end !=0){
            return SensitiveInfoUtils.mobilePhone(mobile,replace,begin,end);
        }
        return SensitiveInfoUtils.mobilePhone(mobile, replace,
                SensitiveLength.MOBILE_DISPLAY_FIRST_THREE_LAST_FOUR.getBegin(), SensitiveLength.MOBILE_DISPLAY_FIRST_THREE_LAST_FOUR.getEnd());
    }

}
