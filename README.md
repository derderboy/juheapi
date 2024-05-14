# 聚合api调用平台
## 快速调用：
- 引入sdk
```maven
    <dependency>
        <groupId>com.derder</groupId>
        <artifactId>juheapi-client-sdk</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <scope>compile</scope>
    </dependency>
```
- 配置ak,sk
```yml
juheapi:
  client:
    access-key: xxxx
    secret-key: xxxx
```

- 注入依赖
  
```java
    @Resource
    private BaseContext baseContext;
```
- 开始调用  
  uri为在线调用时的url端口号之后的地址  
  userRequestParams 为请求参数  
  method 为请求方式  
  
```java
   String result = baseContext.handler(uri, userRequestParams, method);
```

