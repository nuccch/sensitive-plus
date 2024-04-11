package com.yhq.sensitive.annotation;

import com.yhq.sensitive.strategy.IStrategy;
import com.yhq.sensitive.strategy.SensitiveDefault;

import java.lang.annotation.*;

/**
 * 脱敏信息
 * @author yhq
 * @date 2021年9月6日 09点57分
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface SensitiveInfo {

    /**
     * 脱敏策略
     * @return
     */
    Class<? extends IStrategy> strategy() default SensitiveDefault.class;

    /**
     * 输入格式，使用正则表达式, 优先级大于value
     *
     * @return 格式
     */
    String pattern() default "";

    /**
     * 替换目标字符, 优先级大于value
     *
     * @return 替换目标字符串
     */
    String replaceChar() default "";

    /**
     * 开始显示几位
     * @return
     */
    int begin() default 0;

    /**
     * 结束显示几位
     * @return
     */
    int end() default 0;
}
