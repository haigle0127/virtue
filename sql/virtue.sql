/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012 (8.0.12)
 Source Host           : localhost:3306
 Source Schema         : virtue

 Target Server Type    : MySQL
 Target Server Version : 80012 (8.0.12)
 File Encoding         : 65001

 Date: 18/12/2024 17:04:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
    `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '祖级列表',
    `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
    `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
    `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
    `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
    `state` tinyint(1) NULL DEFAULT NULL COMMENT '部门状态 0正常 1停用',
    `sort` int(100) NULL DEFAULT NULL COMMENT '排序',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
    `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除 0未删除 1已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统管理部门' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 0, '', '总部一', '领导1', '13365', '991', 0, 1, 'ed', NULL, '2024-11-30 16:15:40', 1, '2024-11-30 16:15:44', 0);
INSERT INTO `sys_dept` VALUES (2, 0, '', '总部二', '领导1', '13365', '991', 0, 1, 'ed', NULL, '2024-11-30 16:15:40', 1, '2024-11-30 16:15:44', 0);
INSERT INTO `sys_dept` VALUES (3, 2, '', '分部二', '领导1', '13365', '991', 0, 1, 'ed', NULL, '2024-11-30 16:15:40', 1, '2024-11-30 16:15:44', 0);
INSERT INTO `sys_dept` VALUES (4, 2, '', '分部三', '领导1', '13365', '991', 0, 1, 'ed', NULL, '2024-11-30 16:15:40', 1, '2024-11-30 16:15:44', 0);
INSERT INTO `sys_dept` VALUES (5, 2, '', '分部三', '领导1', '13365', '991', 0, 1, 'ed', NULL, '2024-11-30 16:15:40', 1, '2024-11-30 16:15:44', 0);
INSERT INTO `sys_dept` VALUES (6, 1, '', '分部四', '领导1', '13365', '991', 0, 1, 'ed', NULL, '2024-11-30 16:15:40', 1, '2024-11-30 16:15:44', 0);
INSERT INTO `sys_dept` VALUES (7, 1, '', '分部四', '领导1', '13365', '991', 0, 1, 'ed', NULL, '2024-11-30 16:15:40', 1, '2024-11-30 16:15:44', 0);
INSERT INTO `sys_dept` VALUES (8, 1, '', '分部四', '领导1', '13365', '991', 0, 1, 'ed', NULL, '2024-11-30 16:15:40', 1, '2024-11-30 16:15:44', 0);
INSERT INTO `sys_dept` VALUES (9, 3, '', '部门一', '领导1', '13365', '991', 0, 1, 'ed', NULL, '2024-11-30 16:15:40', 1, '2024-11-30 16:15:44', 0);
INSERT INTO `sys_dept` VALUES (10, 3, '', '部门二', '领导1', '13365', '991', 0, 1, 'ed', NULL, '2024-11-30 16:15:40', 1, '2024-11-30 16:15:44', 0);
INSERT INTO `sys_dept` VALUES (11, 4, '', '部门三', '领导1', '13365', '991', 0, 1, 'ed', NULL, '2024-11-30 16:15:40', 1, '2024-11-30 16:15:44', 0);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父ID',
    `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由地址',
    `route_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由名称(路由地址一样的驼峰结构)',
    `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名',
    `component` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
    `param` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由参数',
    `iframe` tinyint(1) NULL DEFAULT NULL COMMENT '是否为外链 0否 1是',
    `cache` tinyint(1) NULL DEFAULT NULL COMMENT '是否缓存 0不缓存 1缓存',
    `state` tinyint(1) NULL DEFAULT NULL COMMENT '菜单状态 0正常 1停用',
    `visible` tinyint(1) NULL DEFAULT NULL COMMENT '显示状态 0显示 1隐藏',
    `redirect` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '重定向',
    `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
    `menu_type` enum('CATALOG','MENU','VIEW') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单类型 CATALOG目录 MENU菜单 VIEW按钮',
    `power` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
    `sort` int(100) NULL DEFAULT NULL COMMENT '排序',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限说明',
    `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除 0未删除 1已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, 'dashboard', 'Dashboard', '首页', NULL, NULL, 0, 0, 0, 0, NULL, 'excel', 'CATALOG', 'home', 23, '', 1, '2019-09-25 14:39:01', NULL, '2024-11-13 17:28:37', 0);
INSERT INTO `sys_menu` VALUES (2, 0, 'system', 'System', '系统管理', NULL, NULL, 0, 0, 0, 0, NULL, 'server', 'CATALOG', 'system', 1, '', 1, '2019-08-29 13:06:30', NULL, '2024-11-13 17:29:47', 0);
INSERT INTO `sys_menu` VALUES (3, 2, 'user', 'User', '用户管理', 'system/user/index', NULL, 0, 0, 0, 0, NULL, 'bx:bx-user', 'MENU', 'system-user', 1, '', 1, '2019-08-29 13:27:16', 1, '2019-09-25 14:51:10', 0);
INSERT INTO `sys_menu` VALUES (4, 2, 'role', 'Role', '角色管理', 'system/role/index', NULL, 0, 0, 0, 0, NULL, 'carbon:user-role', 'MENU', 'system-role', 1, '', 1, '2019-08-30 08:53:38', 1, '2019-09-25 14:51:03', 0);
INSERT INTO `sys_menu` VALUES (5, 2, 'menu', 'Menu', '菜单管理', 'system/menu/index', NULL, 0, 0, 0, 0, NULL, 'ant-design:menu-outlined', 'MENU', 'system-menu', 1, '', 1, '2019-08-30 08:54:02', 1, '2019-09-10 14:38:11', 0);
INSERT INTO `sys_menu` VALUES (6, 3, NULL, 'List', '用户列表', NULL, NULL, 0, 0, 0, 0, NULL, NULL, 'VIEW', 'sys-user-page', 1, '', 1, '2019-09-17 09:02:47', NULL, '2024-12-18 16:53:47', 0);
INSERT INTO `sys_menu` VALUES (7, 3, NULL, 'Add', '用户添加', NULL, NULL, 0, 0, 0, 0, NULL, NULL, 'VIEW', 'system-user-add', 1, '', 1, '2019-09-17 09:02:47', NULL, '2024-12-18 16:54:18', 0);
INSERT INTO `sys_menu` VALUES (8, 3, NULL, 'Edit', '用户修改', NULL, NULL, 0, 0, 0, 0, NULL, NULL, 'VIEW', 'system-user-edit', 1, '', 1, '2019-09-17 09:05:24', NULL, '2024-12-18 16:54:24', 0);
INSERT INTO `sys_menu` VALUES (9, 3, NULL, 'Delete', '用户删除', NULL, NULL, 0, 0, 0, 0, NULL, NULL, 'VIEW', 'system-user-delete', 1, '', 1, '2019-09-17 09:05:53', NULL, '2024-12-18 16:54:32', 0);
INSERT INTO `sys_menu` VALUES (11, 4, NULL, 'Add', '角色添加', NULL, NULL, 0, 0, 0, 0, NULL, NULL, 'VIEW', 'system-role-add', 1, '', 1, '2019-09-17 08:59:13', NULL, '2024-12-18 16:56:25', 0);
INSERT INTO `sys_menu` VALUES (12, 4, NULL, 'Edit', '角色修改', NULL, NULL, 0, 0, 0, 0, NULL, NULL, 'VIEW', 'system-role-edit', 1, '', 1, '2019-09-17 08:59:57', NULL, '2024-12-18 16:56:20', 0);
INSERT INTO `sys_menu` VALUES (13, 4, NULL, 'Delete', '角色删除', NULL, NULL, 0, 0, 0, 0, NULL, NULL, 'VIEW', 'system-role-delete', 1, '', 1, '2019-09-17 09:01:44', NULL, '2024-12-18 16:56:15', 0);
INSERT INTO `sys_menu` VALUES (15, 5, NULL, 'Add', '菜单添加', NULL, NULL, 0, 0, 0, 0, NULL, NULL, 'VIEW', 'system-menu-add', 1, '', 1, '2019-09-10 14:37:57', NULL, '2024-12-18 16:58:19', 0);
INSERT INTO `sys_menu` VALUES (16, 5, NULL, 'Edit', '菜单修改', NULL, NULL, 0, 0, 0, 0, NULL, NULL, 'VIEW', 'system-menu-edit', 1, '', 1, '2019-09-10 14:39:06', NULL, '2024-12-18 16:58:27', 0);
INSERT INTO `sys_menu` VALUES (17, 5, NULL, 'Delete', '菜单删除', NULL, NULL, 0, 0, 0, 0, NULL, NULL, 'VIEW', 'system-menu-delete', 1, '', 1, '2019-09-17 08:58:30', NULL, '2024-12-18 16:58:32', 0);
INSERT INTO `sys_menu` VALUES (18, 1, 'analysis', 'Analysis', '统计', 'dashboard/analysis/index', NULL, 0, 0, 0, 0, NULL, 'ant-design:barcode-outlined', 'MENU', 'home-statistic', 1, '', 1, '2019-09-25 14:49:57', 1, '2019-09-25 14:49:57', 0);
INSERT INTO `sys_menu` VALUES (19, 1, 'workbench', 'Workbench', '面板', 'dashboard/workbench/index', NULL, 0, 0, 0, 0, NULL, 'ant-design:code-twotone', 'MENU', 'home-panel', 1, '', 1, '2019-09-25 14:50:44', 1, '2019-09-25 14:50:44', 0);
INSERT INTO `sys_menu` VALUES (20, 1, '12', NULL, '12', NULL, NULL, 1, 0, 1, 1, NULL, '404', 'VIEW', '12', 1, NULL, NULL, '2024-11-13 17:31:34', NULL, '2024-11-13 17:31:34', 0);
INSERT INTO `sys_menu` VALUES (21, 4, NULL, NULL, '角色列表', NULL, NULL, 0, 0, 0, 0, NULL, NULL, 'VIEW', 'sys-role-page', 1, NULL, NULL, '2024-12-18 16:56:05', NULL, '2024-12-18 16:56:05', 0);
INSERT INTO `sys_menu` VALUES (22, 5, NULL, NULL, '菜单列表', NULL, NULL, 0, 0, 0, 0, NULL, NULL, 'VIEW', 'sys-menu-list', 1, NULL, NULL, '2024-12-18 16:57:08', NULL, '2024-12-18 16:59:05', 0);

-- ----------------------------
-- Table structure for sys_pai
-- ----------------------------
DROP TABLE IF EXISTS `sys_pai`;
CREATE TABLE `sys_pai`  (
    `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型key',
    `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签value'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_pai
-- ----------------------------
INSERT INTO `sys_pai` VALUES ('1', '1');
INSERT INTO `sys_pai` VALUES ('2', '2');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名',
    `mark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
    `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据范围 1全部数据权限 2自定数据权限 3本部门数据权限 4本部门及以下数据权限',
    `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项关联显示 0不关联 1关联',
    `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项关联显示 0不关联 1关联',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色说明',
    `state` tinyint(1) NULL DEFAULT NULL COMMENT '角色状态 0正常 1停用',
    `sort` int(100) NULL DEFAULT NULL COMMENT '排序',
    `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除 0未删除 1已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员角色', NULL, '1', 1, 1, '1', 0, 1, 1, '2019-06-20 17:20:25', 1141240498145460224, '2019-09-25 14:52:25', 0);
INSERT INTO `sys_role` VALUES (2, '普通管理员角色', NULL, '1', 1, 1, '', 0, 1, 1141240498145460224, '2019-09-03 10:39:46', 1141240498145460224, '2019-09-19 14:31:15', 0);
INSERT INTO `sys_role` VALUES (3, '管理员', NULL, '1', 1, 1, '角色简介', 0, 1, 1141240498145460224, '2020-09-15 09:23:56', 1141240498145460224, '2020-09-15 09:23:56', 0);
INSERT INTO `sys_role` VALUES (4, '管理员', NULL, '1', 1, 1, '角色简介', 0, 1, 1141240498145460224, '2020-09-15 09:25:01', 1141240498145460224, '2020-09-15 09:25:01', 0);
INSERT INTO `sys_role` VALUES (5, '管理员345', '12', '1', 1, 1, '角色简介', 0, 11, 1141240498145460224, '2020-09-17 10:12:04', NULL, '2024-11-27 18:33:55', 0);
INSERT INTO `sys_role` VALUES (6, '管理员', '324w', '2', 0, 1, '角色简介', 0, 1, 1141240498145460224, '2020-09-17 10:12:06', NULL, '2024-11-30 16:47:40', 0);
INSERT INTO `sys_role` VALUES (7, '管理员', NULL, '1', 1, 1, '角色简介', 0, 1, 1141240498145460224, '2020-09-17 16:57:23', 1141240498145460224, '2020-09-17 16:57:23', 0);
INSERT INTO `sys_role` VALUES (8, '管理员12', '12', '1', 1, 1, '角色简介', 0, 1, 1141240498145460224, '2020-09-17 16:58:45', NULL, '2024-11-27 18:32:44', 0);
INSERT INTO `sys_role` VALUES (9, '管理员', 'jhk', '1', 1, 1, '角色简介', 0, 1, 1141240498145460224, '2020-09-17 16:58:55', NULL, '2024-11-27 17:59:10', 0);
INSERT INTO `sys_role` VALUES (10, '管理员', '2314', '1', 1, 1, '角色简介', 1, 1, 1141240498145460224, '2020-09-17 17:29:50', NULL, '2024-11-15 17:09:47', 0);
INSERT INTO `sys_role` VALUES (11, '管理员', NULL, '1', 1, 1, '角色简介', 0, 1, 1141240498145460224, '2020-09-17 17:30:01', 1141240498145460224, '2020-09-17 17:30:01', 0);
INSERT INTO `sys_role` VALUES (12, '哈哈哈', NULL, '1', 1, 1, '角色简介', 0, 1, 1141240498145460224, '2020-09-18 10:04:41', 1141240498145460224, '2020-09-18 10:04:41', 0);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
    PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    `menu_id` bigint(20) NOT NULL COMMENT '权限ID',
    PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
     `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
     `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
     `open_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信openid',
     `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
     `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
     `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
     `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
     `birth` datetime NULL DEFAULT NULL COMMENT '出生日期',
     `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
     `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
     `education` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学历',
     `state` tinyint(1) NULL DEFAULT NULL COMMENT '用户状态 0正常 1停用',
     `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个性签名',
     `gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '男' COMMENT '性别 男 女',
     `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
     `create_time` datetime NOT NULL COMMENT '创建时间',
     `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
     `update_time` datetime NOT NULL COMMENT '更新时间',
     `deleted` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '1 表示删除，0 表示未删除',
     PRIMARY KEY (`id`) USING BTREE,
     UNIQUE INDEX `id`(`id` ASC, `username` ASC, `email` ASC, `phone` ASC) USING BTREE COMMENT '唯一字段'
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统管理用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 1, NULL, 'admin', '昵称1', '', '13800000001', NULL, 't14uK9a7twMOhDsrnJhXMA==', '', NULL, 0, NULL, '1', NULL, '2020-11-22 14:33:39', NULL, '2024-12-17 14:37:41', 0);
INSERT INTO `sys_user` VALUES (2, 11, NULL, '普通管理员', '昵称2', 'user2@example.com', '13800000002', NULL, 't14uK9a7twMOhDsrnJhXMA==', NULL, NULL, 0, NULL, '0', NULL, '2020-11-22 14:33:44', NULL, '2020-11-22 14:33:49', 0);
INSERT INTO `sys_user` VALUES (3, 5, NULL, NULL, '234543', 'user3@example.com', '13800000007', NULL, 't14uK9a7twMOhDsrnJhXMA==', NULL, NULL, 0, NULL, '男', NULL, '2024-12-12 15:31:39', NULL, '2024-12-12 15:34:03', 0);
INSERT INTO `sys_user` VALUES (4, 1, NULL, '23r4e32r', '213', 'user4@example.com', '13800000008', NULL, 't14uK9a7twMOhDsrnJhXMA==', NULL, NULL, 0, NULL, '男', NULL, '2024-12-17 14:38:05', NULL, '2024-12-17 14:38:05', 0);
INSERT INTO `sys_user` VALUES (5, 1, NULL, '234', '34543', 'user5@example.com', '13800000006', NULL, 't14uK9a7twMOhDsrnJhXMA==', NULL, NULL, 0, NULL, '男', NULL, '2024-12-17 16:03:15', NULL, '2024-12-17 16:03:15', 0);

-- ----------------------------
-- Table structure for sys_user_derive
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_derive`;
CREATE TABLE `sys_user_derive`  (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `drive_card_face` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '驾驶证正面照片',
    `drive_card_back` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '驾驶证背面照片',
    `drive_card_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '驾驶证类型',
    `drive_card_start_time` datetime NULL DEFAULT NULL COMMENT '驾驶证领取时间',
    `vehicle_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆信息',
    `vehicle_num` int(11) NULL DEFAULT NULL COMMENT '车牌号',
    `driving_experience` int(11) NULL DEFAULT NULL COMMENT '驾龄',
    `card_health_face` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '健康证照片',
    `emergency_contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '紧急联络人',
    `emergency_contact_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '紧急联络人电话',
    `drive_qrcode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '骑手推广二维码',
    `drive_signup_time` datetime NULL DEFAULT NULL COMMENT '骑手报名时间',
    `drive_regist_time` datetime NULL DEFAULT NULL COMMENT '骑手注册时间',
    `drive_balance` decimal(10, 0) NULL DEFAULT NULL COMMENT '骑手余额(可提现余额)',
    `drive_balance_frozen` decimal(10, 0) NULL DEFAULT NULL COMMENT '冻结余额(提现中余额库存)',
    `drive_balance_details_id` bigint(20) NULL DEFAULT NULL COMMENT '骑手余额明细ID',
    `drive_rating` decimal(10, 0) NULL DEFAULT NULL COMMENT '骑手评分',
    `drive_cn_city_code` bigint(20) NULL DEFAULT NULL COMMENT '注册城市CODE',
    `drive_cn_city_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '注册城市',
    `drive_regist_canal` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '注册渠道名称',
    `drive_integral` int(11) NULL DEFAULT NULL COMMENT '骑手积分',
    `drive_status` enum('在职','锁定','离职') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '骑手状态',
    `drive_lock_time` datetime NULL DEFAULT NULL COMMENT '骑手锁定时间',
    `drive_lock_cause` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '骑手锁定原因',
    `
    drive_notice_unread_num` int(11) NULL DEFAULT 0 COMMENT '骑手未读公告',
    `drive_label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '骑手标签',
    `drive_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '骑手备注',
    `drive_online` int(11) NULL DEFAULT NULL COMMENT '骑手在线时长',
    `drive_serve` int(11) NULL DEFAULT NULL COMMENT '骑手服务时长',
    `order_complete_num` int(11) NULL DEFAULT NULL COMMENT '完成订单数量',
    `order_cancel_num` int(11) NULL DEFAULT NULL COMMENT '取消订单数量',
    `drive_income` decimal(10, 0) NULL DEFAULT NULL COMMENT '骑手收入',
    `insulation_box` tinyint(4) NULL DEFAULT NULL COMMENT '1 有保温箱， 2无保温箱',
    `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `is_deleted` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '1 表示删除，0 表示未删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_derive
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                  `role_id` bigint(20) NOT NULL COMMENT '角色ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
