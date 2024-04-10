package com.yhq.sensitive.strategy;


import com.yhq.sensitive.enums.SensitiveStrategyLength;
import com.yhq.sensitive.util.SensitiveInfoUtils;

/**
 * 固话脱敏
 * @author yhq
 * @date 2021年9月6日 16点13分
 **/
public class SensitiveFixedPhone implements IStrategy {

    @Override
    public String desensitization(String fixedPhone,String replace,int begin ,int end) {
        if(begin != SensitiveStrategyLength.FIXED_PHONE_DISPLAY_LAST_FOUR.getBegin() && begin !=0 &&
                end != SensitiveStrategyLength.FIXED_PHONE_DISPLAY_LAST_FOUR.getEnd() && end !=0){
            return SensitiveInfoUtils.fixedPhone(fixedPhone,replace,end);
        }
        return SensitiveInfoUtils.fixedPhone(fixedPhone, replace, SensitiveStrategyLength.FIXED_PHONE_DISPLAY_LAST_FOUR.getEnd());
    }

}
