package com.yhq.sensitive.strategy;

import com.yhq.sensitive.enums.SensitiveStrategyLength;
import com.yhq.sensitive.util.SensitiveInfoUtils;

/**
 * 密码脱敏
 * @author yhq
 * @date 2021年9月6日 16点13分
 **/
public class SensitivePassword implements IStrategy {

    @Override
    public String desensitization(String password,String replace,int begin,int end) {
        if(begin != SensitiveStrategyLength.PASSWORD_REPLACE_SIX.getBegin() && begin !=0){
            return SensitiveInfoUtils.password(password, replace, begin);
        }
        return SensitiveInfoUtils.password(password, replace, SensitiveStrategyLength.PASSWORD_REPLACE_SIX.getBegin());
    }

}
