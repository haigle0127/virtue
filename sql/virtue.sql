/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019 (8.0.19)
 Source Host           : localhost:3306
 Source Schema         : virtue

 Target Server Type    : MySQL
 Target Server Version : 80019 (8.0.19)
 File Encoding         : 65001

 Date: 18/06/2026 15:40:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict_data
-- ----------------------------
DROP TABLE IF EXISTS `dict_data`;
CREATE TABLE `dict_data`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典数据ID',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典标签',
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `color_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '颜色类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '样式类名',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_dict_type`(`dict_type` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_data
-- ----------------------------
INSERT INTO `dict_data` VALUES (1, 1, '男', '0', 'system_user_sex', '0', 'primary', '', '', '2026-06-12 14:48:27', '2026-06-12 14:48:27', NULL, NULL, b'0');
INSERT INTO `dict_data` VALUES (3, 2, '女', '1', 'system_user_sex', '0', 'danger', '', '', '2026-06-12 14:48:27', '2026-06-12 14:48:27', NULL, NULL, b'0');
INSERT INTO `dict_data` VALUES (5, 3, '未知', '2', 'system_user_sex', '0', 'info', '', '', '2026-06-12 14:48:27', '2026-06-12 14:48:27', NULL, NULL, b'0');
INSERT INTO `dict_data` VALUES (7, 1, '正常', '0', 'common_status', '0', 'primary', '', '', '2026-06-12 14:48:27', '2026-06-12 14:48:27', NULL, NULL, b'0');
INSERT INTO `dict_data` VALUES (9, 2, '停用', '1', 'common_status', '0', 'danger', '', '', '2026-06-12 14:48:27', '2026-06-12 14:48:27', NULL, NULL, b'0');
INSERT INTO `dict_data` VALUES (17, 1, '通知', '1', 'system_notice_type', '0', 'primary', '', '', '2026-06-12 14:48:27', '2026-06-12 14:48:27', NULL, NULL, b'0');
INSERT INTO `dict_data` VALUES (19, 2, '公告', '2', 'system_notice_type', '0', 'warning', '', '', '2026-06-12 14:48:27', '2026-06-12 14:48:27', NULL, NULL, b'0');
INSERT INTO `dict_data` VALUES (21, 1, '成功', '0', 'system_login_result', '0', 'success', '', '', '2026-06-12 14:48:27', '2026-06-12 14:48:27', NULL, NULL, b'0');
INSERT INTO `dict_data` VALUES (23, 2, '失败', '1', 'system_login_result', '0', 'danger', '', '', '2026-06-12 14:48:27', '2026-06-12 14:48:27', NULL, NULL, b'0');

-- ----------------------------
-- Table structure for dict_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_type`;
CREATE TABLE `dict_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典类型ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典名称',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_type`(`type` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_type
-- ----------------------------
INSERT INTO `dict_type` VALUES (1, '用户性别', 'system_user_sex', '0', '用户性别列表', '2026-06-12 14:48:27', '2026-06-12 14:48:27', NULL, NULL, b'0');
INSERT INTO `dict_type` VALUES (3, '系统状态', 'common_status', '0', '通用状态列表', '2026-06-12 14:48:27', '2026-06-12 14:48:27', NULL, NULL, b'0');
INSERT INTO `dict_type` VALUES (7, '角色类型', 'system_role_type', '0', '角色类型列表', '2026-06-12 14:48:27', '2026-06-12 14:48:27', NULL, NULL, b'0');
INSERT INTO `dict_type` VALUES (11, '通知类型', 'system_notice_type', '0', '通知类型列表', '2026-06-12 14:48:27', '2026-06-12 14:48:27', NULL, NULL, b'0');
INSERT INTO `dict_type` VALUES (13, '登录类型', 'system_login_type', '0', '登录日志类型', '2026-06-12 14:48:27', '2026-06-12 14:48:27', NULL, NULL, b'0');
INSERT INTO `dict_type` VALUES (15, '登录结果', 'system_login_result', '0', '登录日志结果', '2026-06-12 14:48:27', '2026-06-12 14:48:27', NULL, NULL, b'0');

-- ----------------------------
-- Table structure for role_info
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `mark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色标识，鉴权唯一依据',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色说明',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '角色状态 0正常 1停用',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除 0未删除 1已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_mark`(`mark` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_info
-- ----------------------------
INSERT INTO `role_info` VALUES (1, '超级管理员角色', 'admin', '超级管理员，拥有全部权限', 0, 1, 1, '2019-06-20 17:20:25', 1141240498145460224, '2019-09-25 14:52:25', 0);
INSERT INTO `role_info` VALUES (2, '普通管理员角色', 'manager', '普通管理员', 0, 1, 1141240498145460224, '2019-09-03 10:39:46', 1141240498145460224, '2019-09-19 14:31:15', 0);
INSERT INTO `role_info` VALUES (3, '管理员', 'operator', '角色简介', 0, 1, 1141240498145460224, '2020-09-15 09:23:56', 1141240498145460224, '2020-09-15 09:23:56', 0);
INSERT INTO `role_info` VALUES (4, '管理员', 'role4', '角色简介', 0, 1, 1141240498145460224, '2020-09-15 09:25:01', 1141240498145460224, '2020-09-15 09:25:01', 0);
INSERT INTO `role_info` VALUES (5, '管理员345', 'role5', '角色简介', 0, 11, 1141240498145460224, '2020-09-17 10:12:04', NULL, '2024-11-27 18:33:55', 0);
INSERT INTO `role_info` VALUES (6, '管理员', 'role6', '角色简介', 0, 1, 1141240498145460224, '2020-09-17 10:12:06', NULL, '2024-11-30 16:47:40', 0);
INSERT INTO `role_info` VALUES (7, '管理员', 'role7', '角色简介', 0, 1, 1141240498145460224, '2020-09-17 16:57:23', 1141240498145460224, '2020-09-17 16:57:23', 0);
INSERT INTO `role_info` VALUES (8, '管理员12', 'role8', '角色简介', 0, 1, 1141240498145460224, '2020-09-17 16:58:45', NULL, '2024-11-27 18:32:44', 0);
INSERT INTO `role_info` VALUES (9, '管理员', 'role9', '角色简介', 0, 1, 1141240498145460224, '2020-09-17 16:58:55', NULL, '2024-11-27 17:59:10', 0);
INSERT INTO `role_info` VALUES (10, '管理员', 'role10', '角色简介', 1, 1, 1141240498145460224, '2020-09-17 17:29:50', NULL, '2024-11-15 17:09:47', 0);
INSERT INTO `role_info` VALUES (11, '管理员', 'role11', '角色简介', 0, 1, 1141240498145460224, '2020-09-17 17:30:01', 1141240498145460224, '2020-09-17 17:30:01', 0);
INSERT INTO `role_info` VALUES (12, '哈哈哈', 'role12', '角色简介', 0, 1, 1141240498145460224, '2020-09-18 10:04:41', 1141240498145460224, '2020-09-18 10:04:41', 0);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `open_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `birth` datetime NULL DEFAULT NULL COMMENT '出生日期',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'BCrypt 密码，初始密码 123456',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `education` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学历',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '用户状态 0正常 1停用',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '男' COMMENT '性别 男 女',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '1 表示删除，0 表示未删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC, `username` ASC, `email` ASC, `phone` ASC) USING BTREE COMMENT '唯一字段'
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统管理用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_info（密码统一为 123456 的 BCrypt 哈希）
-- ----------------------------
INSERT INTO `user_info` VALUES (1, NULL, 'admin', '昵称1', '', '13800000001', NULL, '$2a$10$V3mtxEDJROJf3qXVLTN9Q.hUGYUw31fXB21tY.23tb6ovSlHMH5aS', '', NULL, 0, NULL, '1', NULL, '2020-11-22 14:33:39', NULL, '2024-12-17 14:37:41', 0);
INSERT INTO `user_info` VALUES (2, NULL, '普通管理员', '昵称2', 'user2@example.com', '13800000002', NULL, '$2a$10$V3mtxEDJROJf3qXVLTN9Q.hUGYUw31fXB21tY.23tb6ovSlHMH5aS', NULL, NULL, 0, NULL, '0', NULL, '2020-11-22 14:33:44', NULL, '2020-11-22 14:33:49', 0);
INSERT INTO `user_info` VALUES (3, NULL, NULL, '234543', 'user3@example.com', '13800000003', NULL, '$2a$10$V3mtxEDJROJf3qXVLTN9Q.hUGYUw31fXB21tY.23tb6ovSlHMH5aS', NULL, NULL, 0, NULL, '男', NULL, '2024-12-12 15:31:39', NULL, '2024-12-12 15:34:03', 0);
INSERT INTO `user_info` VALUES (4, NULL, '23r4e32r', '213', 'user4@example.com', '13800000004', NULL, '$2a$10$V3mtxEDJROJf3qXVLTN9Q.hUGYUw31fXB21tY.23tb6ovSlHMH5aS', NULL, NULL, 0, NULL, '男', NULL, '2024-12-17 14:38:05', NULL, '2024-12-17 14:38:05', 0);
INSERT INTO `user_info` VALUES (5, NULL, '234', '34543', 'user5@example.com', '13800000005', NULL, '$2a$10$V3mtxEDJROJf3qXVLTN9Q.hUGYUw31fXB21tY.23tb6ovSlHMH5aS', NULL, NULL, 0, NULL, '男', NULL, '2024-12-17 16:03:15', NULL, '2024-12-17 16:03:15', 0);

-- ----------------------------
-- Table structure for auth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `auth_refresh_token`;
CREATE TABLE `auth_refresh_token`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `session_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录会话ID，同一次登录的轮换共用',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `login_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'admin' COMMENT '登录类型 admin/app/pc',
  `token_hash` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '刷新令牌的 SHA-256 摘要',
  `expires_time` datetime NOT NULL COMMENT '过期时间',
  `used` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已轮换 0未使用 1已使用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_token_hash`(`token_hash` ASC) USING BTREE,
  INDEX `idx_session_id`(`session_id` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_expires_time`(`expires_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '刷新令牌' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);

SET FOREIGN_KEY_CHECKS = 1;
