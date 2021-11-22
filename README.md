<p align="center">
  <img width="320" src="http://haigle.gitee.io/static_resources/virtue/image/title.png"  alt="">
</p>

<p align="center">
  <a href="https://spring.io/projects/spring-boot/">
    <img src="https://img.shields.io/badge/springboot-2.5.6-green" alt="springboot">
  </a>
  <a href="https://gitee.com/dromara/sa-token/">
    <img src="https://img.shields.io/badge/satoken-1.28.0-bule" alt="springboot">
  </a>
 <a href="https://mybatis.org/mybatis-3/">
    <img src="https://img.shields.io/badge/mybatis-3-red" alt="mybatis">
  </a>
   <a href="https://gitee.com/smart-doc-team/smart-doc">
    <img src="https://img.shields.io/badge/smartdoc-2.3.1-yellow" alt="swagger2">
  </a>
  <a href="https://www.mysql.com/">
    <img src="https://img.shields.io/badge/mysql-8.2-%234479a1" alt="mysql">
  </a>
  <a href="https://redis.io/">
    <img src="https://img.shields.io/badge/redis-3.0-red" alt="redis">
  </a>
</p>

简体中文 | [English](./README_en_US.md)

## 简介

[virtue](https://gitee.com/haigle/virtue) 是一个权限管理解决方案，它基于springboot实现，遵循阿里java开发手册，代码优雅。我们吧权限控制交给了更好用[Sa-token](https://sa-token.dev33.cn/)。前端框架技术采用 [vue-element-admin](https://panjiachen.gitee.io/vue-element-admin-site/zh) ，VUE3.0换成了[vben](https://gitee.com/annsion/vue-vben-admin)。

想的越多，就越难做出好的设计，我们的框架目前还不够成熟，但是我们希望做的这个小小的产品希望可以帮助你完成一切你想完成的事。

master主分支改为gradle版本项目
***

**联系我们：鹅群672036656**

***

## 结构

```
virtue
| 
|———com.haigle.virtue
|	├── admin --------------------- 后台管理接口      
|	├── app ------------------------业务接口预留
|	├── common  ----------------- 框架整合核心模块
|   ├── config --------------------- spring配置模块
|
|── resource
|	├── application.yml ---------- 基本配置文件
|	├── application-dev.yml ----- 开发环境配置文件
|	├── application-pro.yml ----- 生产配置文件
```

## 开发环境

建议开发者使用以下环境，这样避免版本带来的问题
* IDE: idea
* DB: Mysql8.X
* JDK: JAVA 11

## 系统图片

![登录页面 | center](https://haigle.gitee.io/static_resources/virtue/image/登录页面.jpg)

![首页1 | center](https://haigle.gitee.io/static_resources/virtue/image/首页1.jpg)

![首页2 | center](https://haigle.gitee.io/static_resources/virtue/image/首页2.jpg)

![权限页面 | center](https://haigle.gitee.io/static_resources/virtue/image/权限页面.jpg)


## License

[MIT](https://gitee.com/haigle/virtue/blob/master/LICENSE)

virtue © 2015-2021.  [haigletech](www.haigle.cn)监制.
