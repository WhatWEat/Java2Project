# CS209 Project

## 小组成员：

| 实验组号 | 姓名   | 学号     |
| -------- | ------ | -------- |
| 2        | 程嘉朗 | 12111622 |
| 2        | 汤玉磊 | 12111908 |

## 技术栈

- 后端框架 Springboot
  - 数据永久化: `Jackson`, `fastjson`库，文件以json形式存储
  - 爬虫：`org.apache.httpcomponents`库
  - `Collection` 和 `Colletions`
  - `Stream` API
  - Lambda expression
  - 解析html：`org.jsoup`库
  - 解析java的类，接口，方法，常量：`com.github.javaparser`库

- 前端框架 Vue
  - 图表展示 Echart
  - 美化组件 Element UI


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

## 展示与回答

### Answer

#### 数据展示

| 问题总数 | 回答总数 | 问题解决率 | 回答平均数 | 单个问题最多的回答数 |
| -------- | -------- | ---------- | ---------- | -------------------- |
| 996      | 1430     | 0.26       | 1.44       | 64                   |

![image-20230530213007397](C:\Users\86133\AppData\Roaming\Typora\typora-user-images\image-20230530213007397.png)

![image-20230530213019650](C:\Users\86133\AppData\Roaming\Typora\typora-user-images\image-20230530213019650.png)

#### 回答

可以看出，虽然问题和答案比例趋近于**1:1**，有些提问的回答数量甚至有**64**个，但是有近**60%**的问题都没有答案，在总问题中，问题的解答比只有**26%**

### Accepted Answer

#### 数据展示

![image-20230530213214118](C:\Users\86133\AppData\Roaming\Typora\typora-user-images\image-20230530213214118.png)

![image-20230530213230259](C:\Users\86133\AppData\Roaming\Typora\typora-user-images\image-20230530213230259.png)

##### 回答的时间分布

![image-20230530213334295](C:\Users\86133\AppData\Roaming\Typora\typora-user-images\image-20230530213334295.png)

#### 回答

有26.1%的问题拥有**accepted answer**，同时所有的**accepted answer**拥有的支持率都比其余**answer**高。

从问题的时间分布可以看出，绝大多数被解决的问题都可以在一天内解决，而还剩下一部分问题会留到一个礼拜之后，逐渐被解决。

### Tags

#### 数据展示

##### 经常与Java一同出现的tags

![image-20230530214403784](C:\Users\86133\AppData\Roaming\Typora\typora-user-images\image-20230530214403784.png)

##### Tags组合的支持占比

![image-20230530214416768](C:\Users\86133\AppData\Roaming\Typora\typora-user-images\image-20230530214416768.png)

##### Tags组合的浏览占比

![image-20230530214430480](C:\Users\86133\AppData\Roaming\Typora\typora-user-images\image-20230530214430480.png)

#### 回答

**Spring**和**Sptringboot**作为热门的Java的后端架构经常与Java一同出现，而**idea**和**eclipse**作为开发软件也被时常提及，**maven**作为包管理器热度也与**Springboot**相同，**android**和部分**数据库**也会和Java的tags一起出现。

### Users

#### 数据展示

##### 用户活跃行为

![image-20230530214814631](C:\Users\86133\AppData\Roaming\Typora\typora-user-images\image-20230530214814631.png)

![image-20230530215052557](C:\Users\86133\AppData\Roaming\Typora\typora-user-images\image-20230530215052557.png)

![image-20230530215102515](C:\Users\86133\AppData\Roaming\Typora\typora-user-images\image-20230530215102515.png)

![image-20230530215111212](C:\Users\86133\AppData\Roaming\Typora\typora-user-images\image-20230530215111212.png)

##### 活跃的用户

![image-20230530215127010](C:\Users\86133\AppData\Roaming\Typora\typora-user-images\image-20230530215127010.png)

#### 回答

可以看出用户活跃行为中，评论，提问，回答三者比例接近**1:1:1**，可以看出在stackoverflow上用户并没有明显的行为倾向，同时从行为分布的曲线图中可以看出，基本呈现帖子数量同活跃度呈正比。

我们通过云图展示了活跃度高的用户Phil（活跃度60），Community（活跃度30），而我们计算活跃度的公式为**提问数*5+回答数\*3+评论数\*2**，因为我们认为在社区中提问，回答，评论引发的活跃度应当依次递减。

### Java API

#### 数据展示

![image-20230530215524710](C:\Users\86133\AppData\Roaming\Typora\typora-user-images\image-20230530215524710.png)

![image-20230530215553549](C:\Users\86133\AppData\Roaming\Typora\typora-user-images\image-20230530215553549.png)

#### 回答

最为热门的Java API包括String等基本数据类型和一些图形化的类，基础数据类型的热度高这说明了，Java仍存在着大量的新入门者在StackOverflow上进行着讨论，图形化的类热度高说明了，Java仍被用作图形化界面的开发。

## Insight

1. 有很大一部分问题未得到答案，但是有答案的大多数问题在提出后的一天内就能得到解答，这体现了社区的响应速度快和活跃度高。同时，是否得到回答与问题本身的规范性和清晰性有关，这说明提问要遵循社区约定的提问规范。
2. Java 的讨论主题广泛，涵盖了从后端架构，如 Spring 和 Spring Boot，到开发工具，如 IntelliJ IDEA 和 Eclipse，再到包管理器 Maven 等各个方面。此外，移动开发（如 Android）和数据库相关问题也经常出现。
3. 根据对 Java API 的讨论来看，基础数据类型（如 String）和图形化类的热度较高，这可能反映了 Java 新手的活跃度以及 Java 在 GUI 开发中的重要性。
4. spring-boot问题的提问数量极多，但是点赞的数量却很少，原因是虽然spring-boot是及其热门的后端java框架，但是接触spring-boot需要有一定的知识储备，许多新手对Spring-boot并没有了解，对于有关springboot的问题也并不关心。
