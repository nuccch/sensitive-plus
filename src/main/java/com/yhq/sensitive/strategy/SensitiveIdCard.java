package com.yhq.sensitive.strategy;

import com.yhq.sensitive.enums.SensitiveStrategyLength;
import com.yhq.sensitive.util.SensitiveInfoUtils;

/**
 * 身份证号脱敏
 * @author yhq
 * @date 2021年9月6日 16点13分
 **/
public class SensitiveIdCard implements IStrategy {

    @Override
    public String desensitization(String idCardNum,String replace,int begin ,int end) {
        if(end != SensitiveStrategyLength.ID_CARD_DISPLAY_LAST_FOUR.getEnd() && end !=0){
            return SensitiveInfoUtils.idCard(idCardNum, replace, end);
        }
        return SensitiveInfoUtils.idCard(idCardNum, replace, SensitiveStrategyLength.ID_CARD_DISPLAY_LAST_FOUR.getEnd());
    }

}
