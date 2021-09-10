# 1、说明
数据脱敏插件，目前支持地址脱敏、银行卡号脱敏、中文姓名脱敏、固话脱敏、身份证号脱敏、手机号脱敏、密码脱敏
一个是正则脱敏、另外一个根据显示长度脱敏，默认是正则脱敏，可以根据自己的需要配置自己的规则

具体使用请参考单元测试下的
```
com.yhq.sensitive.UserEntity
com.yhq.sensitive.SensitiveTests.test
```

# 2、注解说明

|注解名称|作用描述|
| --------| :----: |
|@SensitiveAddress|地址脱敏|
|@SensitiveBankCard|银行卡脱敏|
|@SensitiveChineseName|中文姓名脱敏|
|@SensitiveFixedPhone|固话脱敏|
|@SensitiveIdCard|身份证脱敏|
|@SensitiveMobile|手机号码脱敏|
|@SensitivePassword|密码脱敏|

# 3、重写脱敏展示的内容

|注解名称|作用描述|
| --------| :----: |
|@SensitiveInfo(strategy = SensitiveAddress.class)|地址脱敏|
|@SensitiveInfo(strategy = SensitiveBankCard.class)|银行卡脱敏|
|@SensitiveInfo(strategy = SensitiveChineseName.class)|中文姓名脱敏|
|@SensitiveInfo(strategy = SensitiveFixedPhone.class)|固话脱敏|
|@SensitiveInfo(strategy = SensitiveIdCard.class)|身份证脱敏|
|@SensitiveInfo(strategy = SensitiveMobile.class)|手机号码脱敏|
|@SensitiveInfo(strategy = SensitivePassword.class)|密码脱敏|

如11位的手机号,默认脱敏策略是 显示前三后四，如 `183****1309`,自定义策略后
```
@SensitiveInfo(strategy = SensitiveMobile.class,begin = 4,end = 3)
```
显示结果为  `1837****309`


银行卡号自定义脱敏，例如
```
@SensitiveInfo(pattern = "(?<=\\w{6})\\w(?=\\w{4})",replaceChar = "*")
```

整体测试结果如下

```
{
    "userNamePattern":"张**",
    "userNameLength":"张**",
    "passwordPattern":"************",
    "passwordLength":"******",
    "idCardPattern":"**************6789",
    "idCardLength":"********6789",
    "fixedPhonePattern":"********4321",
    "fixedPhoneLength":"********4321",
    "mobilePattern":"156****0987",
    "mobileLength":"156****0987",
    "addressPattern":"北京市东城*****戴斯酒店",
    "addressLength":"北京市东城区东华门街道北京******",
    "emailPattern":"23***@qq.com",
    "emailLength":"2****@qq.com",
    "bankCardPattern":"6212***********2455",
    "bankCardCustomizePattern":"621226*********2455",
    "bankCardLength":"621226*********2455"
}
```

# 4 、日志脱敏

user 为实体对象
```
log.info(JsonMapper.nonNullMapper().toJson(userEntity));
```