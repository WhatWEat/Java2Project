# CS209 Project

## 小组成员：

| 实验组号 | 姓名   | 学号     |
| -------- | ------ | -------- |
| 2        | 程嘉朗 | 12111622 |
| 2        | 汤玉磊 | 12111908 |

## 爬虫框架与思路

1. 使用的外部库

   `org.apache.httpcomponents`

2. 思路

   请求发送：创建`CloseableHttpClient`和`HttpGet`两个类的对象进行请求的发送。

   ```java
   CloseableHttpClient httpClient = HttpClientBuilder.create().build();
   HttpGet request = new HttpGet(apiUrl);
   ```

   url获取：访问`https://api.stackexchange.com/docs`上关于questions和answers的数据进行预览，在筛选完想要的信息之后，复制相应的url即可

3. tips
   - 每次设置pagesize为100，有利于快速获取数据，且减少quota_remaining的消耗
   - 有些电脑在爬虫是会遇到`statuscode == 400`这个时候不妨申请一下密钥，附在url尾部，问题便能解决。
4. 数据存储
   - 考虑到数据规模并不是很大，所以采用`.json`文件进行存取

## 问题解决思路



## 网页框架

- 后端

  Springboot

- 前端

  Vue
