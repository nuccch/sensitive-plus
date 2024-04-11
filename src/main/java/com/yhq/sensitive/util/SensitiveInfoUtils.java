package com.yhq.sensitive.util;

import com.yhq.sensitive.constant.SensitiveReplaceChars;
import com.yhq.sensitive.enums.SensitiveLength;
import com.yhq.sensitive.enums.SensitivePattern;
import org.apache.commons.lang.StringUtils;

/**
 * 数据脱敏的工具类
 * @author yhq
 * @date 2021年9月6日 14点02分
 **/
public abstract class SensitiveInfoUtils {
    /** 默认替换字符 */
    private static final String REPLACE_CHAR_DEFAULT = SensitiveReplaceChars.ASTERISK_SIMPLE_DEFAULT;

    /**
     * [中文姓名] 根据显示长度策略脱敏，只显示指定长度的前几个汉字，其他的替换位特定字符<例子：李**>
     * @param fullName 完整中文姓名
     * @param replaceChar 脱敏替换字符
     * @param length 正常显示的前几个字符个数
     * @return 脱敏后的数据
     */
    public static String chineseName(final String fullName, String replaceChar, int length) {
        if (StringUtils.isBlank(fullName)) {
            return "";
        }
        if(StringUtils.length(fullName) <= length){
            return fullName;
        }
        final String name = StringUtils.left(fullName, length);
        return StringUtils.rightPad(name, StringUtils.length(fullName), replaceChar);
    }

    /**
     * [中文姓名] 根据正则策略脱敏
     * @param fullName 完整中文姓名
     * @param pattern 正则定义
     * @return 脱敏后的数据
     */
    public static String chineseName(final String fullName, SensitivePattern pattern) {
        return patternReplace(fullName, pattern.getPattern(), pattern.getReplaceChar());
    }

    /**
     * [中文姓名] 默认使用显示长度策略脱敏
     * @param fullName 完整中文姓名
     * @return 脱敏后的数据
     */
    public static String chineseName(final String fullName) {
        return chineseName(fullName, REPLACE_CHAR_DEFAULT, SensitiveLength.CHINESE_NAME_DISPLAY_FIRST_ONE.getBegin());
    }

    /**
     * [密码] 根据显示长度策略脱敏，将密码替换为指定长度的特定字符
     * @param password 密码
     * @param replaceChar 替换字符
     * @param length 替换后的长度
     * @return 脱敏后的数据
     */
    public static String password(final String password, String replaceChar, int length) {
        if (StringUtils.isBlank(password)) {
            return "";
        }
        return getSensitiveInfo(length, replaceChar);
    }

    /**
     * [密码] 根据显示长度策略脱敏，将密码替换指定长度的字符
     * @param password 密码
     * @param length 替换后的长度
     * @return 脱敏后的数据
     */
    public static String password(final String password, int length) {
        return password(password, REPLACE_CHAR_DEFAULT, length);
    }

    /**
     * [密码] 根据正则策略脱敏
     * @param password 密码
     * @param pattern 正则定义
     * @return 脱敏后的数据
     */
    public static String password(final String password, SensitivePattern pattern) {
        return patternReplace(password, pattern.getPattern(), pattern.getReplaceChar());
    }

    /**
     * [密码] 默认使用显示长度策略脱敏
     * @param password 密码
     * @return 脱敏后的数据
     */
    public static String password(final String password) {
        return password(password, SensitiveLength.PASSWORD_REPLACE_SIX.getBegin());
    }

    /**
     * [身份证号] 根据显示长度脱敏，显示最后的指定位数，其他替换为特定字符<例子：*************5762>
     * @param idCard 身份证号
     * @param replaceChar 替换字符
     * @param length 最后显示的指定位数
     * @return 脱敏后的数据
     */
    public static String idCard(final String idCard, String replaceChar, int length) {
        if (StringUtils.isBlank(idCard)) {
            return "";
        }
        return StringUtils.leftPad(StringUtils.right(idCard, length), StringUtils.length(idCard), replaceChar);
    }

    /**
     * [身份证号] 根据显示长度策略脱敏，显示最后的指定位数
     * @param idCard 身份证号
     * @param length 最后显示的指定位数
     * @return 脱敏后的数据
     */
    public static String idCard(final String idCard, int length) {
        return idCard(idCard, REPLACE_CHAR_DEFAULT, length);
    }

    /**
     * [身份证号] 根据正则策略脱敏
     * @param idCard 身份证号
     * @param pattern 正则定义
     * @return 脱敏后的数据
     */
    public static String idCard(final String idCard, SensitivePattern pattern) {
        return patternReplace(idCard, pattern.getPattern(), pattern.getReplaceChar());
    }

    /**
     * [身份证号] 默认使用显示长度策略脱敏
     * @param idCard 身份证号
     * @return 脱敏后的数据
     */
    public static String idCard(final String idCard) {
        return idCard(idCard, SensitiveLength.ID_CARD_DISPLAY_LAST_FOUR.getEnd());
    }

    /**
     * [固定电话] 根据显示长度策略脱敏，显示最后指定长度的位数，其他位使用特定字符替换<例子：****1234>
     * @param phone 电话号码
     * @param replaceChar 替换字符
     * @param length 最后显示位数的长度
     * @return 脱敏后的数据
     */
    public static String fixedPhone(final String phone, String replaceChar, int length) {
        if (StringUtils.isBlank(phone)) {
            return "";
        }
        return StringUtils.leftPad(StringUtils.right(phone, length), StringUtils.length(phone), replaceChar);
    }

    /**
     * [固定电话] 根据显示长度策略脱敏，显示最后指定长度的位数
     * @param phone 电话号码
     * @param length 最后显示位数的长度
     * @return 脱敏后的数据
     */
    public static String fixedPhone(final String phone, int length) {
        return fixedPhone(phone, REPLACE_CHAR_DEFAULT, length);
    }

    /**
     * [固定电话] 根据正则策略脱敏
     * @param phone 电话号码
     * @param pattern 正则定义
     * @return 脱敏后的数据
     */
    public static String fixedPhone(final String phone, SensitivePattern pattern) {
        return patternReplace(phone, pattern.getPattern(), pattern.getReplaceChar());
    }

    /**
     * [固定电话] 默认根据显示长度策略脱敏
     * @param phone 电话号码
     * @return 脱敏后的数据
     */
    public static String fixedPhone(final String phone) {
        return fixedPhone(phone, SensitiveLength.FIXED_PHONE_DISPLAY_LAST_FOUR.getEnd());
    }


    /**
     * [手机号码] 根据显示长度策略脱敏，只显示前几位及后几位，其他替换为特定字符<例子:138****1234>
     * @param mobile 手机号码
     * @param replaceChar 替换字符
     * @param begin 前面要显示的位数
     * @param end 最后要显示的位数
     * @return 脱敏后的数据
     */
    public static String mobilePhone(final String mobile, String replaceChar, int begin, int end) {
        if (StringUtils.isBlank(mobile)) {
            return "";
        }
        return StringUtils.left(mobile, begin).concat(
                StringUtils.leftPad(StringUtils.right(mobile, end), StringUtils.length(mobile) - begin, replaceChar));
    }

    /**
     * [手机号码] 根据显示长度策略脱敏，只显示前几位及后几位
     * @param mobile 手机号
     * @param begin 前面要显示的位数
     * @param end 最后要显示的位数
     * @return 脱敏后的数据
     */
    public static String mobilePhone(final String mobile, int begin, int end) {
        return mobilePhone(mobile, REPLACE_CHAR_DEFAULT, begin, end);
    }

    /**
     * [手机号码] 根据正则策略脱敏
     * @param mobile 手机号
     * @param pattern 正则定义
     * @return 脱敏后的数据
     */
    public static String mobilePhone(final String mobile, SensitivePattern pattern) {
        return patternReplace(mobile, pattern.getPattern(), pattern.getReplaceChar());
    }

    /**
     * [手机号码] 默认根据显示长度策略脱敏
     * @param mobile 手机号
     * @return 脱敏后的数据
     */
    public static String mobilePhone(final String mobile) {
        return mobilePhone(mobile, SensitiveLength.MOBILE_DISPLAY_FIRST_THREE_LAST_FOUR.getBegin(), SensitiveLength.MOBILE_DISPLAY_FIRST_THREE_LAST_FOUR.getEnd());
    }

    /**
     * [地址] 根据显示长度策略脱敏，对地址结尾信息进行脱敏<例子：北京市海淀区****>
     * @param address 完整地址
     * @param replaceChar 替换字符
     * @param end 结尾需要脱敏的位数
     * @return 脱敏后的数据
     */
    public static String address(final String address, String replaceChar, final int end) {
        if (StringUtils.isBlank(address)) {
            return "";
        }
        final int length = StringUtils.length(address);
        return StringUtils.rightPad(StringUtils.left(address, length - end), length, replaceChar);
    }

    /**
     * [地址] 根据正则策略脱敏
     * @param address 完整地址
     * @param pattern 正则定义
     * @return 脱敏后的数据
     */
    public static String address(final String address, SensitivePattern pattern) {
        return patternReplace(address, pattern.getPattern(), pattern.getReplaceChar());
    }

    /**
     * [地址] 默认使用长度策略脱敏
     * @param address 完整地址
     * @return 脱敏后的数据
     */
    public static String address(final String address) {
        return address(address, REPLACE_CHAR_DEFAULT, SensitiveLength.ADDRESS_HIDE_LAST_SIX.getEnd());
    }

    /**
     * [电子邮箱] 根据显示长度策略脱敏，只显示前几个字符，其他字符用特定字符替换，@及后面的地址正常显示<例子:g**@163.com>
     * @param email 邮箱
     * @param replaceChar 替换字符
     * @param begin 前面要显示的字符个数
     * @return 脱敏后的数据
     */
    public static String email(final String email, String replaceChar, int begin) {
        if (StringUtils.isBlank(email)) {
            return "";
        }
        final int index = StringUtils.indexOf(email, "@");
        if (index <= begin) {
            return email;
        } else {
            return StringUtils.rightPad(StringUtils.left(email, begin), index, replaceChar)
                    .concat(StringUtils.mid(email, index, StringUtils.length(email)));
        }
    }

    /**
     * [电子邮箱] 根据显示长度策略脱敏，只显示前几个字符
     * @param email 邮箱
     * @param begin 前面要显示的字符个数
     * @return 脱敏后的数据
     */
    public static String email(final String email, int begin) {
        return email(email, REPLACE_CHAR_DEFAULT, begin);
    }

    /**
     * [电子邮箱] 根据正则策略脱敏
     * @param email 邮箱
     * @param pattern 正则定义
     * @return 脱敏后的数据
     */
    public static String email(final String email, SensitivePattern pattern) {
        return patternReplace(email, pattern.getPattern(), pattern.getReplaceChar());
    }

    /**
     * [电子邮箱] 默认使用长度显示策略脱敏
     * @param email 邮箱
     * @return 脱敏后的数据
     */
    public static String email(final String email) {
        return email(email, SensitiveLength.EMAIL_DISPLAY_FIRST.getBegin());
    }

    /**
     * [银行卡号] 根据显示长度策略脱敏，只显示前几位及后几位，其他位用特定字符替换<例子:6222600**********1234>
     * @param bankCard 银行卡号
     * @param replaceChar 替换字符
     * @param begin 显示的前几位
     * @param end 显示的后几位
     * @return 脱敏后的数据
     */
    public static String bankCard(final String bankCard, String replaceChar, int begin , int end) {
        if (StringUtils.isBlank(bankCard)) {
            return "";
        }
        if(needSensitive(bankCard,begin,end)){
            begin = SensitiveLength.BANKCARD_DISPLAY_FIRST_SIX_LAST_FOUR.getBegin();
            end = SensitiveLength.BANKCARD_DISPLAY_FIRST_SIX_LAST_FOUR.getEnd();
        }
        return StringUtils.left(bankCard, begin).concat(StringUtils.removeStart(
                StringUtils.leftPad(StringUtils.right(bankCard, end), StringUtils.length(bankCard), replaceChar),
                getSensitiveInfo(begin,replaceChar)));
    }

    /**
     * [银行卡号] 根据正则策略脱敏
     * @param bankCard 银行卡号
     * @param pattern 正则定义
     * @return 脱敏后的数据
     */
    public static String bankCard(final String bankCard, SensitivePattern pattern) {
        return patternReplace(bankCard, pattern.getPattern(), pattern.getReplaceChar());
    }

    /**
     * [银行卡号] 默认使用长度策略脱敏
     * @param bankCard 银行卡号
     * @return 脱敏后的数据
     */
    public static String bankCard(final String bankCard) {
        return bankCard(bankCard, REPLACE_CHAR_DEFAULT,
                SensitiveLength.BANKCARD_DISPLAY_FIRST_SIX_LAST_FOUR.getBegin(), SensitiveLength.BANKCARD_DISPLAY_FIRST_SIX_LAST_FOUR.getEnd());
    }

    /**
     * 根据正则脱敏
     * @param content 内容
     * @param replace 替换字符
     * @param pattern 正则定义
     * @return 脱敏后的数据
     */
    public static String patternReplace(final String content, String replace, String pattern) {
        return content.replaceAll(pattern, replace);
    }

    /**
     * 获取总的长度
     * @param begin 开始显示的长度
     * @param end 结尾显示的长度
     * @return 显示的总长度
     */
    private static int getAllLength(int begin ,int end){
        return begin + end;
    }

    /**
     * 是否需要脱敏：判断要显示的长度是否比总长度要大
     * @param content 内容
     * @param begin 开始长度
     * @param end  结尾长度
     * @return
     */
    private static boolean needSensitive(final String content, final int begin, final int end){
        int showLength = getAllLength(begin ,end);
        int length = StringUtils.length(content);
        if(showLength> length){
            return false;
        }
        return true;
    }

    /**
     * 返回指定长度的特定字符
     * @param length 显示的长度
     * @return 特定长度的指定字符组成的字符串
     */
    private static String getSensitiveInfo(int length, String replaceChar){
        return StringUtils.repeat(replaceChar, length);
    }

}
