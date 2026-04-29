<p style="text-align: center;">
  <img width="381" src=".image/title.png"  alt="">
</p>

<p style="text-align: center;">
  <a href="https://spring.io/projects/spring-boot/">
    <img src="https://img.shields.io/badge/springboot-3.5.11-green" alt="springboot">
  </a>
  <a href="https://connect2id.com/products/nimbus-jose-jwt">
    <img src="https://img.shields.io/badge/nimbus--jose--jwt-9.37-blue" alt="nimbus-jose-jwt">
  </a>
  <a href="https://gitee.com/baomidou/mybatis-plus/">
    <img src="https://img.shields.io/badge/mybatis--plus-3.5.15-brightgreen" alt="mybatis-plus">
  </a>
  <a href="https://www.mysql.com/">
    <img src="https://img.shields.io/badge/mysql-8.0-%234479a1" alt="mysql">
  </a>
  <a href="https://redis.io/">
    <img src="https://img.shields.io/badge/redis-5.0-red" alt="redis">
  </a>
</p>

### 项目简介

[virtue](https://gitee.com/haigle/virtue) 是一个基于 Spring Boot 实现快速搭建项目的解决方案，遵循阿里 Java 开发手册，代码设计优雅。认证层采用 [nimbus-jose-jwt](https://connect2id.com/products/nimbus-jose-jwt) 实现无状态 JWT 登录，通过自定义 `@RequireLogin` 注解控制接口登录拦截；前端框架使用 [element-plus](https://element-plus.org/zh-CN/)，基于 Vue 3.0。

虽然框架还在不断成熟和完善中，但致力于提供一个功能强大且灵活的工具，帮助高效实现各种需求。

## 技术栈

| 技术            | 版本    | 说明             |
| --------------- | ------- | ---------------- |
| Spring Boot     | 3.5.11  | 基础框架         |
| nimbus-jose-jwt | 9.37    | JWT 生成与校验   |
| MyBatis-Plus    | 3.5.15  | ORM 框架         |
| MySQL           | 8.0     | 关系型数据库     |
| Redis           | 5.0+    | 缓存             |
| Hutool          | 5.8.40  | Java 工具库      |
| Smart-Doc       | 3.1.2   | 接口文档生成     |

## 结构

```
virtue
|
|--- cn.haigle.virtue
|   |-- common ---------------------- 公共资源模块
|   |   |-- security ---------------- 安全认证模块（JWT + 注解拦截）
|   |   |   |-- annotation ---------- 自定义注解（@RequireLogin）
|   |   |   |-- config -------------- 安全配置
|   |   |   |-- context ------------- 安全上下文（ThreadLocal 存 userId）
|   |   |   |-- interceptor --------- 登录拦截器
|   |   |   |-- jwt ----------------- JWT 工具类
|   |   |-- web --------------------- Web 配置
|   |   |   |-- config -------------- 自动前缀路由映射、消息转换器等
|   |-- controller ------------------ 控制器接口模块
|   |   |-- admin ------------------- 管理端控制器（自动映射 /api/admin 前缀）
|   |   |-- app --------------------- Web 端控制器（自动映射 /api/app 前缀）
|   |-- convert --------------------- 实体类转换
|   |-- mapper ---------------------- 实体映射器模块
|   |   |-- dataobject -------------- 数据库实体类
|   |   |-- mysql ------------------- MyBatis 映射接口
|   |-- service --------------------- 服务模块
|
|--- resource
|   |-- mapper ---------------------- SQL 文件
|   |-- application.yml ------------- 基本配置文件
|   |-- application-dev.yml --------- 开发环境配置文件
|   |-- smart-doc.json -------------- Smart-Doc 配置文件
```

## 核心特性

### JWT 无状态登录

- 登录成功后服务端签发 JWT Token，客户端通过 `Authorization: Bearer <token>` 请求头携带
- Token 有效期 24 小时，服务端无会话状态，无需 Redis 存储登录态

### @RequireLogin 注解

- 标注在 **Controller 类**上：该类所有接口都需要登录
- 标注在 **方法**上：仅该方法需要登录，优先级高于类级别
- **未标注**的接口：无需登录，可直接访问

示例：

```java
@RestController
@RequestMapping("/user")  // 实际映射为 /api/admin/user
@RequireLogin             // 该类所有接口需要登录
public class UserController {

    @GetMapping("/page")
    public ApiResult<PageResult<UserPageRespVO>> page(UserPageReqVO reqVO) {
        // 业务逻辑...
    }
}
```

### 自动前缀路由

- `controller.admin.*` 包下的 Controller 自动添加 `/api/admin` 前缀（`login` 子包除外）
- `controller.app.*` 包下的 Controller 自动添加 `/api/app` 前缀
- 若类上显式写以 `/api` 开头的完整路径，保持向后兼容，不再追加前缀

示例：

```java
// 包路径：controller.admin.user
@RequestMapping("/user")  // 实际映射为 /api/admin/user
public class UserController {}
```

## 开发环境

建议开发者使用以下环境，避免版本带来的问题：

- IDE: IntelliJ IDEA
- DB: MySQL 8.X
- JDK: Java 21
- Maven: 3.9+

## License

[MIT](https://gitee.com/haigle/virtue/blob/master/LICENSE)

virtue &copy; 2016-2025.  [haigletech](www.haigle.cn) 监制.
