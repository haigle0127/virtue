<p style="text-align: center;">
  <img width="381" src=".image/title.png"  alt="">
</p>

<p style="text-align: center;">
  <a href="https://spring.io/projects/spring-boot/">
    <img src="https://img.shields.io/badge/springboot-3.4.0-green" alt="springboot">
  </a>
  <a href="https://gitee.com/dromara/sa-token/">
    <img src="https://img.shields.io/badge/satoken-1.39.0-bule" alt="springboot">
  </a>
  <a href="https://gitee.com/baomidou/mybatis-plus/">
    <img src="https://img.shields.io/badge/mybatis-plus-3/?style=flat-square" alt="mybatis-plus">
  </a>
  <a href="https://www.mysql.com/">
    <img src="https://img.shields.io/badge/mysql-8.2-%234479a1" alt="mysql">
  </a>
  <a href="https://redis.io/">
    <img src="https://img.shields.io/badge/redis-5.0-red" alt="redis">
  </a>
</p>


### 项目简介
[virtue](https://gitee.com/haigle/virtue) 是一个基于 Spring Boot 实现快速搭建项目的解决方案，遵循阿里 Java 开发手册，代码设计优雅。我们采用 [sa-token](https://sa-token.dev33.cn/) 进行权限控制，前端框架则使用了 [element-plus](https://element-plus.org/zh-CN/)，基于 Vue 3.0。
虽然我们的框架还在不断成熟和完善中，但我们致力于提供一个功能强大且灵活的工具，帮助您高效地实现各种需求


***

**联系我们：鹅群672036656**

***

## 结构
```
virtue
| 
|———com.haigle.virtue
|   |── common ---------------------- 公共资源模块
|   |── controller ------------------ 控制器接口模块
|       |── admin ------------------- 管理端的控制器
|       |── app --------------------- web端的控制器
|   |── convert --------------------- 实体类转换
|   |── mapper ---------------------- 实体映射器模块
|       |── dataobject -------------- 数据库实体类
|       |── mysql ------------------- mysql映射
|   |── service --------------------- 服务模块
|
|── resource
|	|── mapper ------------------ SQL文件
|	|── application.yml --------- 基本配置文件
|	|── application-dev.yml ----- 开发环境配置文件
```

## 开发环境

建议开发者使用以下环境，这样避免版本带来的问题
* IDE: IDEA
* DB: MySql8.X
* JDK: JAVA 21

[//]: # (## 系统图片)

[//]: # ()
[//]: # (![登录页面 | center]&#40;https://haigle.gitee.io/static_resources/virtue/image/登录页面.jpg&#41;)

[//]: # ()
[//]: # (![首页1 | center]&#40;https://haigle.gitee.io/static_resources/virtue/image/首页1.jpg&#41;)

[//]: # ()
[//]: # (![首页2 | center]&#40;https://haigle.gitee.io/static_resources/virtue/image/首页2.jpg&#41;)

[//]: # ()
[//]: # (![权限页面 | center]&#40;https://haigle.gitee.io/static_resources/virtue/image/权限页面.jpg&#41;)


## License

[MIT](https://gitee.com/haigle/virtue/blob/master/LICENSE)

virtue © 2016-2025.  [haigletech](www.haigle.cn)监制.
