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

[简体中文](./README.md) | English

## Introduction

[virtue] (https://gitee.com/haigle/virtue) is a privilege management solution, which is based on spring boot implementation and follows the Ali java development manual. Code implementation is more elegant than spring mvc. Caching can be based on no complex and bloated caching system, but it simply implements its own redis cache. You can turn it off in the configuration, or you can customize any caching system to achieve what you think. The permission module implements the control of the Spring Framework's Andler Interceptor, a servlet filter interface, to achieve full takeover. The framework also supports international voice and is taken over by spring framework's built-in i18n. Back-end front-end framework technology uses [vue-element-admin] (https://panjiachen.gitee.io/vue-element-admin-site/zh) open source front-end framework, which is based on [vue] (https://cn.vuejs.org) (progressive framework for building data-driven web interfaces), [element-ui] (https://element.eleme.cn) (desktop end group based on Vue 2.0) Part library.

The more you think about it, the harder it will be to make a good design. Our framework is not mature yet, but the little product we hope to make will help you accomplish everything you want to accomplish.

***
** Welcome you to join our QQ group 672036656, basically no one squeaks, maybe few people, if you encounter problems, the group owners will patiently answer. Friends without QQ can email me haigle0127@foxmail.com**

***

## Structure

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
|	├── i18n ----------------------- 支持国际化
|	├── application.yml ---------- 基本配置文件
|	├── application-dev.yml ----- 开发环境配置文件
|	├── application-pro.yml ----- 生产配置文件
```

## Development environment

Developers are advised to use the following environments to avoid version problems
* IDE: idea
* DB: Mysql5.7
* JDK: JAVA 8

## Pictures

![登录页面 | center](https://haigle.gitee.io/static_resources/virtue/image/登录页面.jpg)

![首页1 | center](https://haigle.gitee.io/static_resources/virtue/image/首页1.jpg)

![首页2 | center](https://haigle.gitee.io/static_resources/virtue/image/首页2.jpg)

![权限页面 | center](https://haigle.gitee.io/static_resources/virtue/image/权限页面.jpg)


## License

[MIT](https://github.com/haigle0127/virtue/blob/master/LICENSE)

virtue © 1993-2021.  [haigletech](www.haigle.cn)Producer.
