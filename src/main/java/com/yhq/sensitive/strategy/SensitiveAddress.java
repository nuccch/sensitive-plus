package com.yhq.sensitive.strategy;

import com.yhq.sensitive.enums.SensitiveStrategyLength;
import com.yhq.sensitive.util.SensitiveInfoUtils;

/**
 * 地址脱敏
 * @author yhq
 * @date 2021年9月6日 16点13分
 **/
public class SensitiveAddress implements IStrategy {

    @Override
    public String desensitization(String address, String replace, int begin, int end) {
        if(begin != SensitiveStrategyLength.ADDRESS_HIDE_LAST_SIX.getBegin() && begin !=0 ){
            return SensitiveInfoUtils.address(address,replace, begin);
        }
        return SensitiveInfoUtils.address(address, replace, SensitiveStrategyLength.ADDRESS_HIDE_LAST_SIX.getBegin());
    }

}
