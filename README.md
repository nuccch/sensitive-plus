# README

## 1、说明

数据脱敏插件，目前支持地址脱敏、银行卡号脱敏、中文姓名脱敏、固话脱敏、身份证号脱敏、手机号脱敏、密码脱敏。  
一个是正则脱敏、另外一个根据显示长度脱敏，默认是正则脱敏，可以根据自己的需要配置自己的规则。  

具体使用请参考单元测试下的  
```
com.yhq.sensitive.UserEntity
com.yhq.sensitive.SimpleEntity
com.yhq.sensitive.SensitiveTests
com.yhq.sensitive.SensitiveLogTests
com.yhq.sensitive.converter.SensitiveLogbackMessageConverterTest
```

## 2、注解说明

| 注解名称                         |      作用描述       |
|------------------------------|:---------------:|
| @SensitiveLengthAddress      |  使用显示长度规则对地址脱敏  |
| @SensitiveLengthBankCard     | 使用显示长度规则对银行卡脱敏  |
| @SensitiveLengthChineseName  | 使用显示长度规则对中文姓名脱敏 |
| @SensitiveLengthEmail        |  使用显示长度规则对邮箱脱敏  |
| @SensitiveLengthFixedPhone   | 使用显示长度规则对固定号码脱敏 |
| @SensitiveLengthIdCard       | 使用显示长度规则对身份证号脱敏 |
| @SensitiveLengthMobile       | 使用显示长度规则对手机号码脱敏 |
| @SensitiveLengthPassword     |  使用显示长度规则对密码脱敏  |
| @SensitivePatternAddress     |   使用正则规则对地址脱敏   |
| @SensitivePatternBankCard    |  使用正则规则对银行卡脱敏   |
| @SensitivePatternChineseName |  使用正则规则对中文姓名脱敏  |
| @SensitivePatternEmail       |   使用正则规则对邮箱脱敏   |
| @SensitivePatternFixedPhone  |  使用正则规则对固定号码脱敏  |
| @SensitivePatternIdCard      |  使用正则规则对身份证号脱敏  |
| @SensitivePatternMobile      |  使用正则规则对手机号码脱敏  |
| @SensitivePatternPassword    |   使用正则规对密码脱敏    |

## 3、重写脱敏展示的内容

| 注解名称                                                  |  作用描述  |
|-------------------------------------------------------|:------:|
| @SensitiveInfo(strategy = SensitiveAddress.class)     |  地址脱敏  |
| @SensitiveInfo(strategy = SensitiveBankCard.class)    | 银行卡脱敏  |
| @SensitiveInfo(strategy = SensitiveChineseName.class) | 中文姓名脱敏 |
| @SensitiveInfo(strategy = SensitiveEmail.class)       |  邮箱脱敏  |
| @SensitiveInfo(strategy = SensitiveFixedPhone.class)  |  固话脱敏  |
| @SensitiveInfo(strategy = SensitiveIdCard.class)      | 身份证脱敏  |
| @SensitiveInfo(strategy = SensitiveMobile.class)      | 手机号码脱敏 |
| @SensitiveInfo(strategy = SensitivePassword.class)    |  密码脱敏  |

如11位的手机号，默认脱敏策略是显示前三后四，如 `183****1309`，自定义策略后
```
@SensitiveInfo(strategy = SensitiveMobile.class,begin = 4,end = 3)
```
显示结果为`1837****309`


银行卡号自定义脱敏，例如
```
@SensitiveInfo(pattern = "(?<=\\w{6})\\w(?=\\w{4})",replaceChar = "*")
```

## 4、日志脱敏

日志脱敏有2种实现办法：  
其一，基于Logback实现自定义`ch.qos.logback.classic.pattern.MessageConverter`，重写`convert()`方法。  
其二，打印日志之前先对消息内容进行脱敏。  


### 基于MessageConverter

需要在`logback.xml`中明确指定自定义MessageConverter，示例：  
```xml
<conversionRule conversionWord="msg" converterClass="com.yhq.sensitive.converter.SensitiveLogbackMessageConverter"/>
```
这种方式实现的日志脱敏需要在打印日志时使用特定的格式，主要依赖正则表达式规则，如：  
```
// 对手机号进行脱敏
// 处理正则：(mobile|telephone)([:|=|,| ]+)(\w{3})(\w{4})(\w{4})
log.info("mobile:{}", mobile);
```
另外，该方式实现的日志脱敏存在性能瓶颈，因为在`convert()`方法中需要对每条日志消息应用所有的脱敏规则。  


### 先脱敏再打印

日志脱敏先在应用层先对日志内容脱敏再打印，分为两种情况处理。  

#### 字符串

对于输出到日志中的内容是单纯的字符串这种情况，直接调用工具方法先脱敏后再打印到日志。  
```
String chineseName = "赵子龙";
String mobile = "13242429876";
String fixPhone = "010-32342214";
String address = "西川成都蜀国大将军府";
String idCard = "123456789012345678";
String bankCard = "6666666666666666666";
String email = "zhaozilong@chengdu.com";
String password = "1234567890";

// 中文名
LOGGER.info("chineseName: {}", SensitiveInfoUtils.chineseName(chineseName));
// 手机号
LOGGER.info("mobile: {}", SensitiveInfoUtils.mobilePhone(mobile));
// 固定电话
LOGGER.info("fixPhone: {}", SensitiveInfoUtils.fixedPhone(fixPhone));
// 地址
LOGGER.info("address: {}", SensitiveInfoUtils.address(address));
// 身份证号
LOGGER.info("idCard: {}", SensitiveInfoUtils.idCard(idCard));
// 银行卡号
LOGGER.info("bankCard: {}", SensitiveInfoUtils.bankCard(bankCard));
// 邮箱
LOGGER.info("email: {}", SensitiveInfoUtils.email(email));
// 密码
LOGGER.info("password: {}", SensitiveInfoUtils.password(password));
```
在日志中打印脱敏后的内容：
```
14:24:54.195 [main] INFO com.yhq.sensitive.SensitiveLogTests - chineseName: 赵**
14:24:54.197 [main] INFO com.yhq.sensitive.SensitiveLogTests - mobile: 132****9876
14:24:54.198 [main] INFO com.yhq.sensitive.SensitiveLogTests - fixPhone: ********2214
14:24:54.198 [main] INFO com.yhq.sensitive.SensitiveLogTests - address: 西川成都******
14:24:54.198 [main] INFO com.yhq.sensitive.SensitiveLogTests - idCard: **************5678
14:24:54.198 [main] INFO com.yhq.sensitive.SensitiveLogTests - bankCard: 666666*********6666
14:24:54.198 [main] INFO com.yhq.sensitive.SensitiveLogTests - email: z*********@chengdu.com
14:24:54.198 [main] INFO com.yhq.sensitive.SensitiveLogTests - password: ******
```

#### 实体对象

如果打印到日志中的内容为实体对象，在打印日志之前将对象序列化为JSON字符串。  

user为实体对象  
```
log.info(SensitiveJsonUtils.toJson(user));
```

整体测试结果如下  
```
{
	"userNamePattern": "张**",
	"userNameLength": "张**",
	"passwordPattern": "************",
	"passwordLength": "******",
	"idCardPattern": "**************6789",
	"idCardLength": "**************6789",
	"fixedPhonePattern": "********4321",
	"fixedPhoneLength": "********4321",
	"mobilePattern": "156****0987",
	"mobileLength": "156****0987",
	"addressPattern": "北京市朝阳*****128号",
	"addressLength": "北京市朝阳区望京路王家村张******",
	"emailPattern": "23345acv***@qq.com",
	"emailLength": "2**********@qq.com",
	"bankCardPattern": "621226*********2455",
	"bankCardCustomizePattern": "621226*********2455",
	"bankCardLength": "621226*********2455"
}
```

目前支持3种JSON序列化组件：  
[Jackson](https://github.com/FasterXML/jackson-databind)  
[Fastjson](https://github.com/alibaba/fastjson)  
[Gson](https://github.com/google/gson)  


## 5、接口数据脱敏

目前支持基于Spring MVC框架的JSON响应数据脱敏处理。  
具体实现是在需要脱敏的实体属性上使用脱敏注解进行标注，并应用自定义`HttpMessageConverter`。  
```java
public class Account {
    private Long id = 0L;
    @SensitiveLengthChineseName
    private String username = "";
    @SensitiveLengthPassword
    private String password = "";
    private Integer age = 0;
    private Short sex = 0;
    private Date createTime = null;
    private Date updateTime = null;
}

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);
        // 将自定义HttpMessageConverter放在第一个，确保生效
        converters.add(0, new SensitiveFastJsonMessageConverter());
    }
}
```
这样，在接口返回JSON数据时会自动对使用了脱敏注解的属性进行处理，响应数据示例：  
```json
{
    "id": 1,
    "username": "张**",
    "password": "******",
    "age": 23,
    "sex": 1,
    "createTime": null,
    "updateTime": null
}
```
参考[SpringBoot中自定义HttpMessageConverter](https://juejin.cn/post/7007715644774825992)  


## 6、DFA算法 敏感词库脱敏

读取敏感词库 com.yhq.sensitive.util.SensitiveWordInit
敏感词工具类 com.yhq.sensitive.util.SensitiveWordFilter

<font color='red'>由于一些词汇违规，所以以拼音代替，请自行脑补</font>

| 方法                                                                |   作用描述    |
|-------------------------------------------------------------------|:---------:|
| boolean isContainSensitiveWord(String txt,int matchType)          | 判断是否存在敏感词 |
| Set<String> getSensitiveWord(String txt , int matchType)          |   获取敏感词   |
| replaceSensitiveWord(String txt,int matchType,String replaceChar) |   敏感词替代   |

单元测试类
```
com.yhq.sensitive.SensitiveWordFilterTest.test

测试结果如下：

17:02:43.507 [main] INFO com.yhq.sensitive.SensitiveWordFilterTest - 敏感词的数量：893
17:02:43.511 [main] INFO com.yhq.sensitive.SensitiveWordFilterTest - 待检测语句字数：184
17:02:43.511 [main] INFO com.yhq.sensitive.SensitiveWordFilterTest - 语句中包含敏感词的个数为：3个
17:02:43.511 [main] INFO com.yhq.sensitive.SensitiveWordFilterTest - 总共消耗时间为：18
```

## 7、其他

该脱敏组件的基础实现基于开源项目：[sensitive-plus](https://gitee.com/strong_sea/sensitive-plus)，并做了大量重写和增强。  
主要的修改点：  
1. 完善脱敏注解  
2. 增加对Gson和FastJson序列化组件的支持  
3. 增加Spring MVC框架的HttpMessageConverter接口数据脱敏插件  
4. 增加基于Logback框架的MessageConverter日志脱敏插件  



