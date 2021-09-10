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

# 4 、日志脱敏

user 为实体对象
```
log.info(JsonMapper.nonNullMapper().toJson(userEntity));
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

# 5 、DFA算法 脱敏

读取敏感词库 com.yhq.sensitive.util.SensitiveWordInit
敏感词工具类 com.yhq.sensitive.util.SensitiveWordFilter

<font color='red'>由于一些词汇违规，所以以拼音代替，请自行脑补</font>

|方法|作用描述|
| --------| :----: |
|boolean isContainSensitiveWord(String txt,int matchType)|判断是否存在敏感词|
|Set<String> getSensitiveWord(String txt , int matchType)|获取敏感词|
|replaceSensitiveWord(String txt,int matchType,String replaceChar)|敏感词替代|

单元测试类
```
long beginTime = System.currentTimeMillis();
File file = ResourceUtils.getFile("classpath:SensitiveWord.txt");
SensitiveWordFilter filter = new SensitiveWordFilter(file.getPath());
log.info("敏感词的数量：" + filter.sensitiveWordMap.size());
String string = "太多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释(zisha指南)怀那些自己经历的伤感。"
        + "然后(falun)功 我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
        + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜(sanji)片 深人静的晚上，关上电话静静的发呆着。";
log.info("待检测语句字数：" + string.length());

Set<String> set = filter.getSensitiveWord(string, 1);
long endTime = System.currentTimeMillis();
log.info("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
log.info("总共消耗时间为：" + (endTime - beginTime));

测试结果如下

17:02:43.507 [main] INFO com.yhq.sensitive.SensitiveWordFilterTest - 敏感词的数量：893
17:02:43.511 [main] INFO com.yhq.sensitive.SensitiveWordFilterTest - 待检测语句字数：184
17:02:43.511 [main] INFO com.yhq.sensitive.SensitiveWordFilterTest - 语句中包含敏感词的个数为：3。包含：[法轮, 三级, 自杀指南]
17:02:43.511 [main] INFO com.yhq.sensitive.SensitiveWordFilterTest - 总共消耗时间为：18
```
