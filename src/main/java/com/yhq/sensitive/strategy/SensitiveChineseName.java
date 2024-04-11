package com.yhq.sensitive.strategy;

import com.yhq.sensitive.enums.SensitiveLength;
import com.yhq.sensitive.util.SensitiveInfoUtils;

/**
 * 中文名称脱敏
 * @author yhq
 * @date 2021年9月6日 16点13分
 **/
public class SensitiveChineseName implements IStrategy {

    @Override
    public String desensitization(String source,String replace,int begin,int end) {
        if(begin != SensitiveLength.CHINESE_NAME_DISPLAY_FIRST_ONE.getBegin() && begin !=0){
            return SensitiveInfoUtils.chineseName(source, replace, begin);
        }
        return SensitiveInfoUtils.chineseName(source, replace, SensitiveLength.CHINESE_NAME_DISPLAY_FIRST_ONE.getBegin());
    }

}
