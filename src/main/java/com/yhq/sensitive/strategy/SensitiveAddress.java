package com.yhq.sensitive.strategy;

import com.yhq.sensitive.enums.SensitiveLength;
import com.yhq.sensitive.util.SensitiveInfoUtils;

/**
 * 地址脱敏
 * @author yhq
 * @date 2021年9月6日 16点13分
 **/
public class SensitiveAddress implements IStrategy {

    @Override
    public String desensitization(String address, String replace, int begin, int end) {
        if(end != SensitiveLength.ADDRESS_HIDE_LAST_SIX.getEnd() && end !=0 ){
            return SensitiveInfoUtils.address(address,replace, end);
        }
        return SensitiveInfoUtils.address(address, replace, SensitiveLength.ADDRESS_HIDE_LAST_SIX.getEnd());
    }

}
