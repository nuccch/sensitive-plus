package com.yhq.sensitive;

import com.yhq.sensitive.constant.SensitiveReplaceChars;
import com.yhq.sensitive.core.JacksonOutput;
import com.yhq.sensitive.enums.SensitiveLength;
import com.yhq.sensitive.enums.SensitivePattern;
import com.yhq.sensitive.util.SensitiveInfoUtils;
import com.yhq.sensitive.util.SensitiveJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class SensitiveTests {
	String bankCard = "6212262502009182455";
	String chineseName = "张三四";
	String password = "122345676543";
	String idCard = "432145167805126789";
	String fixedPhone = "076512344321";
	String mobile = "15678900987";
	String address = "北京市朝阳区望京路王家村张三路128号";
	String email = "23345acvsde@qq.com";
	UserEntity userEntity = UserEntity.builder()
			.userNamePattern(chineseName)
			.userNameLength(chineseName)
			.passwordPattern(password)
			.passwordLength(password)
			.idCardPattern(idCard)
			.idCardLength(idCard)
			.fixedPhonePattern(fixedPhone)
			.fixedPhoneLength(fixedPhone)
			.mobilePattern(mobile)
			.mobileLength(mobile)
			.addressPattern(address)
			.addressLength(address)
			.emailPattern(email)
			.emailLength(email)
			.bankCardPattern(bankCard)
			.bankCardCustomizePattern(bankCard)
			.bankCardLength(bankCard)
			.build();

	@Test
	public void testToJsonStringV1() {
		printSensitive(JacksonOutput.nonNullMapper().toJson(userEntity));
	}

	/**
	 * 使用封装的Json序列化工具类
	 */
	@Test
	public void testToJsonStringV2() {
		printSensitive(SensitiveJsonUtils.toJson(userEntity));
	}

	/**
	 * 对银行卡脱敏：根据长度
	 */
	@Test
	public void testSensitiveBankCardByLength() {
		String sensitive = SensitiveInfoUtils.bankCard(bankCard, SensitiveReplaceChars.ASTERISK_SIMPLE_DEFAULT,
				SensitiveLength.BANKCARD_DISPLAY_FIRST_SIX_LAST_FOUR.getBegin(), SensitiveLength.BANKCARD_DISPLAY_FIRST_SIX_LAST_FOUR.getEnd());
		printSensitive(bankCard, sensitive);
	}

	/**
	 * 对银行卡脱敏：根据正则
	 */
	@Test
	public void testSensitiveBankCardByPattern() {
		String sensitive = SensitiveInfoUtils.bankCard(bankCard, SensitivePattern.BANKCARD_DISPLAY_FIRST_FIX_LAST_FOUR);
		printSensitive(bankCard, sensitive);
	}

	/**
	 * 对银行卡脱敏：默认策略
	 */
	@Test
	public void testSensitiveBankCardByDefault() {
		log.info(SensitiveInfoUtils.bankCard(bankCard));
	}

	/**
	 * 对中文姓名脱敏：根据显示长度
	 */
	@Test
	public void testSensitiveChineseNameByLength() {
		log.info(SensitiveInfoUtils.chineseName(chineseName, SensitiveReplaceChars.ASTERISK_SIMPLE_DEFAULT,2));
	}

	/**
	 * 对中文姓名脱敏：根据正则
	 */
	@Test
	public void testSensitiveChineseNameByPattern() {
		log.info(SensitiveInfoUtils.chineseName(chineseName, SensitivePattern.CHINESE_NAME_DISPLAY_FIRST_ONE));
	}

	/**
	 * 对中文姓名脱敏：默认策略
	 */
	@Test
	public void testSensitiveChineseNameByDefault() {
		log.info(SensitiveInfoUtils.chineseName(chineseName));
	}

	/**
	 * 对密码脱敏：根据显示长度
	 */
	@Test
	public void testSensitivePasswordByLength() {
		String sensitive = SensitiveInfoUtils.password(password, 3);
		printSensitive(password, sensitive);
	}

	/**
	 * 对密码脱敏：根据正则策略
	 */
	@Test
	public void testSensitivePasswordByPattern() {
		String sensitive = SensitiveInfoUtils.password(password, SensitivePattern.PASSWORD_REPLACE_ALL);
		printSensitive(password, sensitive);
	}

	/**
	 * 对密码脱敏：默认策略
	 */
	@Test
	public void testSensitivePasswordByDefault() {
		String sensitive = SensitiveInfoUtils.password(password);
		printSensitive(password, sensitive);
	}

	/**
	 * 对身份证号脱敏：根据显示长度
	 */
	@Test
	public void testSensitiveIdCardNumByLength() {
		String sensitive = SensitiveInfoUtils.idCard(idCard, SensitiveLength.ID_CARD_DISPLAY_LAST_FOUR.getEnd());
		printSensitive(idCard, sensitive);
	}

	/**
	 * 对身份证号脱敏：根据正则策略
	 */
	@Test
	public void testSensitiveIdCardByPattern() {
		String sensitive = SensitiveInfoUtils.idCard(idCard, SensitivePattern.ID_CARD_DISPLAY_LAST_FOUR);
		printSensitive(idCard, sensitive);
	}

	/**
	 * 对身份证号脱敏：默认策略
	 */
	@Test
	public void testSensitiveIdCardByDefault() {
		String sensitive = SensitiveInfoUtils.idCard(idCard);
		printSensitive(idCard, sensitive);
	}

	/**
	 * 对固定电话脱敏：根据显示长度策略
	 */
	@Test
	public void testSensitiveFixedPhoneByLength() {
		String sensitive = SensitiveInfoUtils.fixedPhone(fixedPhone, SensitiveLength.FIXED_PHONE_DISPLAY_LAST_FOUR.getEnd());
		printSensitive(fixedPhone, sensitive);
	}

	/**
	 * 对固定电话脱敏：根据正则策略
	 */
	@Test
	public void testSensitiveFixedPhoneByPattern() {
		String sensitive = SensitiveInfoUtils.fixedPhone(fixedPhone, SensitivePattern.FIXED_PHONE_DISPLAY_LAST_FOUR);
		printSensitive(fixedPhone, sensitive);
	}

	/**
	 * 对固定电话脱敏：默认策略
	 */
	@Test
	public void testSensitiveFixedPhoneByDefault() {
		String sensitive = SensitiveInfoUtils.fixedPhone(fixedPhone);
		printSensitive(fixedPhone, sensitive);
	}

	/**
	 * 对手机号码脱敏：根据显示长度策略
	 */
	@Test
	public void testSensitiveMobilePhoneByLength() {
		String sensitive = SensitiveInfoUtils.mobilePhone(mobile, SensitiveLength.MOBILE_DISPLAY_FIRST_THREE_LAST_FOUR.getBegin(),
				SensitiveLength.MOBILE_DISPLAY_FIRST_THREE_LAST_FOUR.getEnd());
		printSensitive(mobile, sensitive);
	}

	/**
	 * 对手机号码脱敏：根据正则策略
	 */
	@Test
	public void testSensitiveMobilePhoneByPattern() {
		String sensitive = SensitiveInfoUtils.mobilePhone(mobile, SensitivePattern.MOBILE_DISPLAY_FIRST_THREE_LAST_FOUR);
		printSensitive(mobile, sensitive);
	}

	/**
	 * 对手机号码脱敏：默认策略
	 */
	@Test
	public void testSensitiveMobilePhoneByDefault() {
		String sensitive = SensitiveInfoUtils.mobilePhone(mobile);
		printSensitive(mobile, sensitive);
	}

	/**
	 * 对地址脱敏：显示长度策略
	 */
	@Test
	public void testSensitiveAddressByLength() {
		String sensitive = SensitiveInfoUtils.address(address, SensitiveReplaceChars.ASTERISK_SIMPLE_DEFAULT,
				SensitiveLength.ADDRESS_HIDE_LAST_SIX.getEnd());
		printSensitive(address, sensitive);
	}

	/**
	 * 对地址脱敏：根据正则策略
	 */
	@Test
	public void testSensitiveAddressByPattern() {
		String sensitive = SensitiveInfoUtils.address(address, SensitivePattern.ADDRESS_DISPLAY_FIRST_FIVE_LAST_FOUR);
		printSensitive(address, sensitive);
	}

	/**
	 * 对地址脱敏：默认策略
	 */
	@Test
	public void testSensitiveAddressByDefault() {
		String sensitive = SensitiveInfoUtils.address(address);
		printSensitive(address, sensitive);
	}

	/**
	 * 对邮箱脱敏：根据显示长度策略
	 */
	@Test
	public void testSensitiveEmailByLength() {
		String sensitive = SensitiveInfoUtils.email(email, SensitiveLength.EMAIL_DISPLAY_FIRST.getBegin());
		printSensitive(email, sensitive);
	}

	/**
	 * 对邮箱脱敏：根据正则策略
	 */
	@Test
	public void testSensitiveEmailByPattern() {
		String sensitive = SensitiveInfoUtils.email(email, SensitivePattern.EMAIL_HIDE_LAST_THREE);
		printSensitive(email, sensitive);
	}

	/**
	 * 对邮箱脱敏：根据正则策略
	 */
	@Test
	public void testSensitiveEmailByDefault() {
		String sensitive = SensitiveInfoUtils.email(email);
		printSensitive(email, sensitive);
	}

	private void printSensitive(String source, String sensitive) {
		System.out.println(String.format("%s\n%s", source, sensitive));
	}

	private void printSensitive(String sensitive) {
		System.out.println(String.format("%s", sensitive));
	}
}
