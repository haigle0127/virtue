<p style="text-align: center;">
  <img width="381" src=".image/title.png" alt="">
</p>

<p style="text-align: center;">
  <a href="https://spring.io/projects/spring-boot/">
    <img src="https://img.shields.io/badge/springboot-4.1.1-green" alt="springboot">
  </a>
  <a href="https://spring.io/projects/spring-security/">
    <img src="https://img.shields.io/badge/spring--security-7.1-blue" alt="spring-security">
  </a>
  <a href="https://gitee.com/baomidou/mybatis-plus/">
    <img src="https://img.shields.io/badge/mybatis--plus-3.5.17-brightgreen" alt="mybatis-plus">
  </a>
  <a href="https://www.mysql.com/">
    <img src="https://img.shields.io/badge/mysql-8.0-%234479a1" alt="mysql">
  </a>
  <a href="https://redis.io/">
    <img src="https://img.shields.io/badge/redis-5.0-red" alt="redis">
  </a>
</p>

## 项目简介

virtue 是一个基于 Spring Boot 4 的后台管理项目脚手架：登录鉴权使用 Spring Security + OAuth2 Resource Server 实现无状态 JWT，权限只按角色控制（`@PreAuthorize`）；数据访问使用 MyBatis-Plus；DO/VO 转换使用 Spring `BeanUtils`；接口文档使用 dwagger（OpenAPI 3）。

管理端前端为 [virtue-admin](../virtue-admin)，基于 Nuxt 4 + Nuxt UI 4。

## 技术栈

| 技术 | 版本 | 说明 |
| --- | --- | --- |
| Java | 17 | 运行环境 |
| Spring Boot | 4.1.1 | 基础框架，默认 Jackson 3 |
| Spring Security | 7.1 | 认证与鉴权 |
| OAuth2 Resource Server | 7.1 | JWT 资源服务器 |
| MyBatis-Plus | 3.5.17 | ORM 框架 |
| MySQL | 8.0 | 关系型数据库 |
| Redis | 5.0+ | 验证码缓存（spring-data-redis） |
| dwagger | 0.0.5 | 接口文档（基于 springdoc） |
| Lombok | 由 Spring Boot 管理 | 简化实体类 |

## 目录结构

```
src/main/java/cn/haigle/virtue
├── VirtueProApplication.java          启动类（开启配置扫描与定时任务）
├── common
│   ├── base/BaseDO.java               公共字段：创建人、创建时间、逻辑删除
│   ├── config/OpenApiConfig.java      OpenAPI 安全方案，给需要登录的接口加锁
│   ├── constants/StateEnum.java       状态枚举（0 正常 / 1 停用）
│   ├── enums/LoginTypeEnum.java       登录类型 admin / app / pc
│   ├── exception/                     业务异常、错误码、全局异常处理
│   ├── mybatis/                       MyBatis-Plus 配置、BaseMapperX、LambdaQueryWrapperX
│   ├── pojo/                          ApiResult、PageParam、PageResult
│   ├── security
│   │   ├── config/                    SecurityFilterChain、SecurityProperties
│   │   ├── handler/                   401 / 403 JSON 输出
│   │   ├── jwt/                       JWT 编解码、签发、测试登录解码器
│   │   ├── model/LoginUser.java       UserDetails 实现
│   │   └── util/SecurityUtils.java    当前用户、登录类型、会话 ID、权限
│   └── web
│       ├── Constant.java              /admin-api、/app-api、/pc-api 前缀常量
│       └── config/                    自动前缀路由、CORS、Long 序列化为字符串
├── controller
│   ├── admin                          管理端，自动映射 /admin-api
│   │   ├── auth                       登录、刷新令牌、退出、当前用户
│   │   ├── captcha                    验证码校验
│   │   ├── dict                       字典类型、字典数据
│   │   ├── role                       角色管理
│   │   └── user                       用户管理
│   └── app                            App 端，自动映射 /app-api（预留）
├── mapper
│   ├── dataobject                     数据库实体（DO）
│   └── mysql                          MyBatis Mapper 接口
└── service
    ├── auth                           登录认证、刷新令牌轮换与吊销
    ├── dict / role / user             系统管理服务
```

```
src/main/resources
├── mapper/                            RoleMapper.xml、UserRoleMapper.xml
├── application.yml                    公共配置（端口、MyBatis-Plus、安全白名单）
├── application-local.yml              本地环境（默认激活）
└── application-dev.yml                开发环境
```

## 快速开始

环境要求：JDK 17、Maven 3.9+、MySQL 8.x；验证码接口需要 Redis 5.0+。

1. 创建数据库 `virtue`（UTF-8），导入 `sql/virtue.sql`（脚本只包含建表与初始数据，不含建库语句）。
2. 按本机情况修改 `application-local.yml` 中的 MySQL、Redis 连接。
3. 启动 `VirtueProApplication`，服务端口 `9981`。
4. 打开接口文档 <http://localhost:9981/swagger-ui.html>，OpenAPI JSON 为 `/v3/api-docs`。

默认账号 `admin` / `123456`，种子数据中密码为 BCrypt 哈希。

`application.yml` 默认激活 `local` profile；`local` 打开了测试登录，`dev` 未配置。

## 认证与鉴权

### 令牌方案

- 访问令牌：JWT，HS256，默认 15 分钟。claims 为 `sub`（用户 ID）、`username`、`loginType`、`authorities`（形如 `ROLE_admin`）、`sid`（登录会话 ID），JOSE `typ` 固定为 `at+jwt`。
- 刷新令牌：32 字节安全随机数转 Base64Url 的不透明字符串，默认 7 天；数据库中只保存其 SHA-256 摘要。
- 每次刷新都会轮换刷新令牌；已轮换的令牌再次出现视为泄露，服务端吊销整个会话。并发刷新也会命中该检测，因此前端必须单飞刷新。
- 退出登录、修改密码、停用或删除用户，会按用户吊销刷新令牌。已签发的访问令牌是无状态的，最长 15 分钟后自然失效。
- 每天 04:00 清理过期的刷新令牌记录。
- 密码统一使用 BCrypt 存储。

客户端通过 `Authorization: Bearer <accessToken>` 访问受保护接口，`SecurityFilterChain` 使用 `STATELESS` 会话策略。

### 认证接口

| 接口 | 方法 | 说明 | 是否需要登录 |
| --- | --- | --- | --- |
| `/admin-api/auth/login` | POST | 登录，返回访问令牌与刷新令牌 | 否 |
| `/admin-api/auth/refresh-token` | POST | 请求体 `{ "refreshToken": "..." }` | 否 |
| `/admin-api/auth/logout` | POST | 吊销当前会话的刷新令牌 | 是 |
| `/admin-api/auth/me` | GET | 当前用户信息与角色标识列表 | 是 |

登录账号支持用户名、邮箱、手机号；响应字段为 `accessToken`、`refreshToken`、`userId`、`expiresTime`（毫秒时间戳）。

```bash
curl -X POST http://localhost:9981/admin-api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"123456"}'
```

### 角色鉴权

- 权限模型只有“用户 ↔ 角色”，`user_role` 多对多，一个用户可以拥有多个角色。
- 角色标识 `role_info.mark` 非空且唯一，登录或刷新时转换成 `ROLE_{mark}` 写入访问令牌；停用或已删除的角色不会被加载。
- `/admin-api/**`、`/app-api/**` 默认要求登录，白名单由 `virtue.security.public-urls` 配置。
- 接口权限通过 `@PreAuthorize` 控制：

```java
@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('admin')")
public class UserController {
    // 当前实现：用户、角色管理仅 admin 角色可访问
}
```

`hasRole('admin')` 自动匹配 authority `ROLE_admin`，与数据库中的 `mark` 对应。

### 测试登录

`virtue.security.test-login-enabled: true` 时，请求头直接用 `Authorization: Bearer test1`，即以用户 1（超级管理员，`ROLE_admin`）身份访问，无需调用登录接口。

- `local` 默认开启，`dev` 默认关闭，生产环境必须关闭。
- 开启后，Swagger 中需要登录的接口会把 `Authorization: Bearer test1` 作为请求头参数预填。

## 路由与统一约定

### 自动前缀路由

`AutoPrefixRequestMappingHandlerMapping` 按 Controller 所在包自动添加前缀：

| 包路径 | 前缀 |
| --- | --- |
| `controller.admin.*` | `/admin-api` |
| `controller.app.*` | `/app-api` |
| `controller.pc.*` | `/pc-api` |

```java
// 包路径 controller.admin.user，实际地址 /admin-api/user/page
@RequestMapping("/user")
public class UserController {}
```

### 统一响应

```json
{ "code": 200, "msg": "OK", "data": {} }
```

`code === 200` 表示成功。参数校验失败等业务错误仍返回 HTTP 200，`code` 为业务码；401、403 会同时设置对应的 HTTP 状态码。

| code | 含义 |
| --- | --- |
| 200 | 成功 |
| 401 | 登录状态无效或已过期 |
| 403 | 无权限访问 |
| 406 | 账号或密码错误 / 账号已停用 |
| 500 | 系统异常 |
| 901 | 参数校验失败 |

### 分页

请求参数继承 `PageParam`：`pageNo`（从 1 开始，默认 1）、`pageSize`（默认 10，最大 100）。分页结果：

```json
{ "list": [], "total": 0, "pageNo": 1 }
```

### Long 序列化为字符串

所有 `Long` / `BigInteger` 统一序列化为字符串，避免前端 JS 精度丢失；前端按字符串处理 ID。

### CORS

`WebConfig` 允许任意来源（`allowedOriginPatterns("*")`）并允许携带凭据，预检请求在安全链中放行。

## 接口清单

用户管理（`@PreAuthorize("hasRole('admin')")`）：

| 接口 | 说明 |
| --- | --- |
| `GET /admin-api/user/page` | 分页，支持 `username`、`mobile`、`status` |
| `GET /admin-api/user/get?id=` | 详情（含 `roleIds`） |
| `POST /admin-api/user/create` | 新增（含 `roleIds`，密码 BCrypt） |
| `PUT /admin-api/user/update` | 修改（先删后插角色关联） |
| `DELETE /admin-api/user/delete?id=` | 删除并吊销该用户刷新令牌 |
| `DELETE /admin-api/user/delete-list?ids=1,2` | 批量删除 |
| `GET /admin-api/user/simple-list` | 精简列表 |
| `PUT /admin-api/user/update-password` | `{ "id": 1, "password": "..." }` |
| `PUT /admin-api/user/update-status` | `{ "id": 1, "status": 0 }` |

角色管理（`hasRole('admin')`）：

| 接口 | 说明 |
| --- | --- |
| `GET /admin-api/role/page` | 分页，支持 `name`、`code`、`status` |
| `GET /admin-api/role/get?id=` | 详情 |
| `POST /admin-api/role/create` | 新增 |
| `PUT /admin-api/role/update` | 修改 |
| `DELETE /admin-api/role/delete?id=` | 删除 |
| `GET /admin-api/role/simple-list` | 精简列表 |

字典类型 / 字典数据（`isAuthenticated()`）：

| 接口 | 说明 |
| --- | --- |
| `GET /admin-api/dict-type/page` | 字典类型分页 |
| `GET /admin-api/dict-type/get?id=` | 字典类型详情 |
| `POST /admin-api/dict-type/create` | 新增字典类型 |
| `PUT /admin-api/dict-type/update` | 修改字典类型 |
| `DELETE /admin-api/dict-type/delete?id=` | 删除字典类型 |
| `DELETE /admin-api/dict-type/delete-list?ids=` | 批量删除 |
| `GET /admin-api/dict-type/simple-list` | 精简列表 |
| `GET /admin-api/dict-data/page` | 字典数据分页，支持 `dictType`、`label`、`status` |
| `GET /admin-api/dict-data/get?id=` | 字典数据详情 |
| `GET /admin-api/dict-data/type?type=` | 按字典类型取全部数据 |
| `POST /admin-api/dict-data/create` | 新增字典数据 |
| `PUT /admin-api/dict-data/update` | 修改字典数据 |
| `DELETE /admin-api/dict-data/delete?id=` | 删除字典数据 |
| `DELETE /admin-api/dict-data/delete-list?ids=` | 批量删除 |

验证码：

| 接口 | 说明 |
| --- | --- |
| `POST /admin-api/captcha/check` | 校验 `{ "mark": "...", "code": "..." }`，读取 Redis 键 `virtue-login-captcha:{mark}` |

> 验证码的“获取”接口目前在 `CaptchaController` 中被注释，校验接口依赖 Redis 中已存在的验证码；当前前端未接入验证码。

## 配置

```yaml
virtue:
  security:
    test-login-enabled: false
    jwt:
      secret: ${VIRTUE_JWT_SECRET}
      issuer: virtue
      access-token-ttl: 15m
      refresh-token-ttl: 7d
    public-urls:
      - /admin-api/auth/login
      - /admin-api/auth/refresh-token
      - /admin-api/captcha/**
      - /swagger-ui/**
      - /swagger-ui.html
      - /v3/api-docs/**
      - /webjars/**
      - /doc.html
      - /favicon.ico
      - /error
```

`VIRTUE_JWT_SECRET` 必须通过环境变量提供（HS256 至少 32 字节），不设置启动会直接失败；生产环境同时关闭测试登录。

## 数据表

系统表不使用 `sys_` 前缀，建表与初始数据见 `sql/virtue.sql`：

| 表名 | 说明 |
| --- | --- |
| `auth_refresh_token` | 刷新令牌（只存 SHA-256 摘要、会话 ID、是否已轮换） |
| `user_info` | 用户（BCrypt 密码，`state` 0 正常 / 1 停用） |
| `user_role` | 用户与角色关联（多对多） |
| `role_info` | 角色（`mark` 为鉴权唯一依据） |
| `dict_type` | 字典类型 |
| `dict_data` | 字典数据 |

种子数据包含 `admin` 用户、`admin` 角色及两者的关联。

## 与前端对接

前端仓库为 [virtue-admin](../virtue-admin)，通过 `NUXT_PUBLIC_API_BASE` 直连本服务（默认 `http://127.0.0.1:9981`）。

- 所有业务接口前缀为 `/admin-api`。
- 访问令牌过期后调用 `/admin-api/auth/refresh-token` 换新；必须单飞刷新，否则会触发重放检测导致会话被吊销。
- 前端根据 HTTP 401 或响应体 `code === 401` 统一跳转登录页。

## 开发约定

- 新增管理端接口放在 `controller.admin` 包下，路由前缀由包名自动添加。
- 需要登录的类或方法使用 `@PreAuthorize`，角色限制写 `hasRole('mark')`。
- DO ↔ VO 转换在 Service 方法内用 `BeanUtils.copyProperties` 完成，不单独建 convert 包；字段名不一致时显式赋值。
- 代码注释使用中文，标识符使用英文。
- 新增表名不带 `sys_` 前缀。

## 已知限制

- 访问令牌无状态，退出登录后仍可能在剩余有效期内（最长 15 分钟）被使用。
- 没有菜单、部门、按钮级权限模型，前端路由固定，鉴权粒度只有角色。
- 字典类型/字典数据的 `export-excel` 与字典数据 `simple-list` 目前是占位实现，返回空数组。
- 验证码获取接口未启用，校验接口依赖 Redis。

virtue &copy; 2016-2026. [haigletech](www.haigle.cn) 监制.
