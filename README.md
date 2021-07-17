<p align="center">
  <img width="320" src="http://haigle.gitee.io/static_resources/virtue/image/title.png">
</p>

<p align="center">
  <a href="https://spring.io/projects/spring-boot/">
    <img src="https://img.shields.io/badge/springboot-2.4.0-green" alt="springboot">
  </a>
 <a href="https://mybatis.org/mybatis-3/">
    <img src="https://img.shields.io/badge/mybatis-3-red" alt="mybatis">
  </a>
   <a href="https://swagger.io">
    <img src="https://img.shields.io/badge/swagger2-3-bule" alt="swagger2">
  </a>
  <a href="https://www.mysql.com/">
    <img src="https://img.shields.io/badge/mysql-8-%234479a1" alt="mysql">
  </a>
  <a href="https://redis.io/">
    <img src="https://img.shields.io/badge/redis-red" alt="redis">
  </a>
</p>

简体中文 | [English](./README_en_US.md)

## 简介

[virtue](https://gitee.com/haigle/virtue) 是一个权限管理解决方案，它基于springboot实现，遵循阿里java开发手册，代码实现相对于springmvc更加优雅。缓存可以不基于任何缓存复杂臃肿的缓存系统，但是简单的实现了自带的redis缓存。在配置里可以关闭，也可以自定义任何缓存系统，来实现你心目中的样子。权限模块是实现了springframework的HandlerInterceptor这个servlet过滤器接口进行控制，实现完全接管。后端的前端框架技术采用 [vue-element-admin](https://panjiachen.gitee.io/vue-element-admin-site/zh) 开源前端框架，它基于 [vue](https://cn.vuejs.org)（构建数据驱动的 web 界面的渐进式框架），[element-ui](https://element.eleme.cn) （基于 Vue 2.0 的桌面端组件库）。
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
