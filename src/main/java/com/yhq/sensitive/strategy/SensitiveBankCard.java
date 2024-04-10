package com.yhq.sensitive.strategy;

import com.yhq.sensitive.enums.SensitiveStrategyLength;
import com.yhq.sensitive.util.SensitiveInfoUtils;

/**
 * 银行卡号脱敏
 * @author yhq
 * @date 2021年9月6日 16点13分
 **/
public class SensitiveBankCard implements IStrategy {

    @Override
    public String desensitization(String bankCard,String replace,int begin, int end) {
        if(begin != SensitiveStrategyLength.BANKCARD_DISPLAY_FIRST_SIX_LAST_FOUR.getBegin() && begin !=0 &&
                end != SensitiveStrategyLength.BANKCARD_DISPLAY_FIRST_SIX_LAST_FOUR.getEnd() && end !=0){
            return SensitiveInfoUtils.bankCard(bankCard,replace,begin,end);
        }
        return SensitiveInfoUtils.bankCard(bankCard, replace,
                SensitiveStrategyLength.BANKCARD_DISPLAY_FIRST_SIX_LAST_FOUR.getBegin(), SensitiveStrategyLength.BANKCARD_DISPLAY_FIRST_SIX_LAST_FOUR.getEnd());
    }

}
