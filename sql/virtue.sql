/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : virtue

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 29/11/2021 22:37:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键ID',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父ID',
  `path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名',
  `order_no` int(100) NULL DEFAULT NULL COMMENT '排序',
  `component` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `redirect` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `menu_type` enum('MENU','VIEW') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单类型（MENU目录 VIEW按钮）',
  `power` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `ename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限英文名',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限说明',
  `has_children` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '1 存在子类，0 表示不存在',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '1 表示删除，0 表示未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '/dashboard', '首页', NULL, 'LAYOUT', '/dashboard/analysis', 'ion:grid-outline', 'MENU', 'home', 'Home', '', 1, 1141240498145460224, '2019-09-25 14:39:01', 1141240498145460224, '2019-09-25 14:48:06', 0);
INSERT INTO `sys_menu` VALUES (2, 0, '/system', '系统管理', NULL, 'LAYOUT', NULL, 'ion:settings-outline', 'MENU', 'system', 'SystemManage', '', 1, 1141240498145460224, '2019-08-29 13:06:30', 1141240498145460224, '2019-09-25 14:50:56', 0);
INSERT INTO `sys_menu` VALUES (3, 2, 'user', '用户管理', NULL, '/system/user/index', NULL, 'bx:bx-user', 'MENU', 'system-user', 'UserManage', '', 1, 1141240498145460224, '2019-08-29 13:27:16', 1141240498145460224, '2019-09-25 14:51:10', 0);
INSERT INTO `sys_menu` VALUES (4, 2, 'role', '角色管理', NULL, '/system/role/index', NULL, 'carbon:user-role', 'MENU', 'system-role', 'RoleManage', '', 1, 1141240498145460224, '2019-08-30 08:53:38', 1141240498145460224, '2019-09-25 14:51:03', 0);
INSERT INTO `sys_menu` VALUES (5, 2, 'menu', '菜单管理', NULL, '/system/menu/index', NULL, 'ant-design:menu-outlined', 'MENU', 'system-menu', 'MenuManage', '', 1, 1141240498145460224, '2019-08-30 08:54:02', 1141240498145460224, '2019-09-10 14:38:11', 0);
INSERT INTO `sys_menu` VALUES (6, 3, 'list', '列表', NULL, NULL, NULL, NULL, 'VIEW', 'system-user-list', 'UserList', '', 0, 1141240498145460224, '2019-09-17 09:02:47', 1141240498145460224, '2019-09-17 09:02:47', 0);
INSERT INTO `sys_menu` VALUES (7, 3, 'add', '添加', NULL, NULL, NULL, NULL, 'VIEW', 'system-user-add', 'UserAdd', '', 0, 1141240498145460224, '2019-09-17 09:02:47', 1141240498145460224, '2019-09-17 09:02:47', 0);
INSERT INTO `sys_menu` VALUES (8, 3, 'edit', '修改', NULL, NULL, NULL, NULL, 'VIEW', 'system-user-edit', 'UserEdit', '', 0, 1141240498145460224, '2019-09-17 09:05:24', 1141240498145460224, '2019-09-17 09:05:24', 0);
INSERT INTO `sys_menu` VALUES (9, 3, 'delete', '删除', NULL, NULL, NULL, NULL, 'VIEW', 'system-user-delete', 'UserDelete', '', 0, 1141240498145460224, '2019-09-17 09:05:53', 1141240498145460224, '2019-09-17 09:05:53', 0);
INSERT INTO `sys_menu` VALUES (11, 4, 'add', '添加', NULL, NULL, NULL, NULL, 'VIEW', 'system-role-add', 'RoleAdd', '', 0, 1141240498145460224, '2019-09-17 08:59:13', 1141240498145460224, '2019-09-17 08:59:13', 0);
INSERT INTO `sys_menu` VALUES (12, 4, 'edit', '修改', NULL, NULL, NULL, NULL, 'VIEW', 'system-role-edit', 'RoleEdit', '', 0, 1141240498145460224, '2019-09-17 08:59:57', 1141240498145460224, '2019-09-17 08:59:57', 0);
INSERT INTO `sys_menu` VALUES (13, 4, 'delete', '删除', NULL, NULL, NULL, NULL, 'VIEW', 'system-role-delete', 'RoleDelete', '', 0, 1141240498145460224, '2019-09-17 09:01:44', 1141240498145460224, '2019-09-17 09:01:44', 0);
INSERT INTO `sys_menu` VALUES (15, 5, 'add', '添加', NULL, NULL, NULL, NULL, 'VIEW', 'system-menu-add', 'MenuAdd', '', 0, 1141240498145460224, '2019-09-10 14:37:57', 1141240498145460224, '2019-09-10 14:37:57', 0);
INSERT INTO `sys_menu` VALUES (16, 5, 'edit', '修改', NULL, NULL, NULL, NULL, 'VIEW', 'system-menu-edit', 'MenuEdit', '', 0, 1141240498145460224, '2019-09-10 14:39:06', 1141240498145460224, '2019-09-10 14:39:06', 0);
INSERT INTO `sys_menu` VALUES (17, 5, 'delete', '删除', NULL, NULL, NULL, NULL, 'VIEW', 'system-menu-delete', 'MenuDelete', '', 0, 1141240498145460224, '2019-09-17 08:58:30', 1141240498145460224, '2019-09-17 08:58:30', 0);
INSERT INTO `sys_menu` VALUES (18, 1, 'analysis', '统计', NULL, '/dashboard/analysis/index', NULL, 'ant-design:barcode-outlined', 'MENU', 'home-statistic', 'Statistic', '', 0, 1141240498145460224, '2019-09-25 14:49:57', 1141240498145460224, '2019-09-25 14:49:57', 0);
INSERT INTO `sys_menu` VALUES (19, 1, 'workbench', '面板', NULL, '/dashboard/workbench/index', NULL, 'ant-design:code-twotone', 'MENU', 'home-panel', 'Panel', '', 0, 1141240498145460224, '2019-09-25 14:50:44', 1141240498145460224, '2019-09-25 14:50:44', 0);

-- ----------------------------
-- Table structure for sys_organize
-- ----------------------------
DROP TABLE IF EXISTS `sys_organize`;
CREATE TABLE `sys_organize`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键ID',
  `parent_id` bigint(20) NOT NULL COMMENT '父级ID',
  `parent_ids` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所有父级ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '1 表示删除，0 表示未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '组织机构' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_organize
-- ----------------------------

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
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键ID',
  `organization_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '组织机构ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `ename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色英文名',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色说明',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '1 表示删除，0 表示未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, NULL, '超级管理员角色', 'SuperAdmin', '1', 1, '2019-06-20 17:20:25', 1141240498145460224, '2019-09-25 14:52:25', 0);
INSERT INTO `sys_role` VALUES (1168715176417951744, NULL, '普通管理员角色', 'GeneralAdmin', '', 1141240498145460224, '2019-09-03 10:39:46', 1141240498145460224, '2019-09-19 14:31:15', 0);
INSERT INTO `sys_role` VALUES (1305678697797255168, NULL, '管理员', '别名1', '角色简介', 1141240498145460224, '2020-09-15 09:23:56', 1141240498145460224, '2020-09-15 09:23:56', 0);
INSERT INTO `sys_role` VALUES (1305678971089715200, NULL, '管理员', '别名1', '角色简介', 1141240498145460224, '2020-09-15 09:25:01', 1141240498145460224, '2020-09-15 09:25:01', 0);
INSERT INTO `sys_role` VALUES (1306415595125735424, NULL, '管理员', '别名1', '角色简介', 1141240498145460224, '2020-09-17 10:12:04', 1141240498145460224, '2020-09-17 10:12:04', 0);
INSERT INTO `sys_role` VALUES (1306415604328038400, NULL, '管理员', '别名1', '角色简介', 1141240498145460224, '2020-09-17 10:12:06', 1141240498145460224, '2020-09-17 10:12:06', 0);
INSERT INTO `sys_role` VALUES (1306517600833896448, NULL, '管理员', '1', '角色简介', 1141240498145460224, '2020-09-17 16:57:23', 1141240498145460224, '2020-09-17 16:57:23', 0);
INSERT INTO `sys_role` VALUES (1306517944578080768, NULL, '管理员', '1', '角色简介', 1141240498145460224, '2020-09-17 16:58:45', 1141240498145460224, '2020-09-17 16:58:45', 0);
INSERT INTO `sys_role` VALUES (1306517983878709248, NULL, '管理员', '1', '角色简介', 1141240498145460224, '2020-09-17 16:58:55', 1141240498145460224, '2020-09-17 16:58:55', 0);
INSERT INTO `sys_role` VALUES (1306525766531416064, NULL, '管理员', '11111111222222', '角色简介', 1141240498145460224, '2020-09-17 17:29:50', 1141240498145460224, '2020-09-18 14:55:43', 0);
INSERT INTO `sys_role` VALUES (1306525811213336576, NULL, '管理员', '哈哈', '角色简介', 1141240498145460224, '2020-09-17 17:30:01', 1141240498145460224, '2020-09-17 17:30:01', 0);
INSERT INTO `sys_role` VALUES (1306776128693731328, NULL, '哈哈哈', '嘿嘿嘿', '角色简介', 1141240498145460224, '2020-09-18 10:04:41', 1141240498145460224, '2020-09-18 10:04:41', 0);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '权限ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1168747785063759872, 1166940161808793600);
INSERT INTO `sys_role_menu` VALUES (1168747785063759872, 1167238638832975872);
INSERT INTO `sys_role_menu` VALUES (1168805947280195584, 1167238638832975872);
INSERT INTO `sys_role_menu` VALUES (1168715600038461440, 1166940161808793600);
INSERT INTO `sys_role_menu` VALUES (1171297560770379776, 1166940161808793600);
INSERT INTO `sys_role_menu` VALUES (1171297612561645568, 1166940161808793600);
INSERT INTO `sys_role_menu` VALUES (1171297639426162688, 1167238638832975872);
INSERT INTO `sys_role_menu` VALUES (1174194403481223168, 1166945389807403008);
INSERT INTO `sys_role_menu` VALUES (1174194403481223168, 1173764201286467584);
INSERT INTO `sys_role_menu` VALUES (1174194403481223168, 1173764857942507520);
INSERT INTO `sys_role_menu` VALUES (1174194403481223168, 1173764980449738752);
INSERT INTO `sys_role_menu` VALUES (1174194403481223168, 1173763303466336256);
INSERT INTO `sys_role_menu` VALUES (1174194403481223168, 1173763937569603584);
INSERT INTO `sys_role_menu` VALUES (1, 1166940161808793600);
INSERT INTO `sys_role_menu` VALUES (1, 1166945389807403008);
INSERT INTO `sys_role_menu` VALUES (1, 1173764201286467584);
INSERT INTO `sys_role_menu` VALUES (1, 1173764857942507520);
INSERT INTO `sys_role_menu` VALUES (1, 1173764980449738752);
INSERT INTO `sys_role_menu` VALUES (1, 1167238917104074752);
INSERT INTO `sys_role_menu` VALUES (1, 1173763303466336256);
INSERT INTO `sys_role_menu` VALUES (1, 1173763486375739392);
INSERT INTO `sys_role_menu` VALUES (1, 1173763937569603584);
INSERT INTO `sys_role_menu` VALUES (1, 1167239016202895360);
INSERT INTO `sys_role_menu` VALUES (1, 1171311831424172032);
INSERT INTO `sys_role_menu` VALUES (1, 1171312122617921536);
INSERT INTO `sys_role_menu` VALUES (1, 1173763122444369920);
INSERT INTO `sys_role_menu` VALUES (1, 1176750670200438784);
INSERT INTO `sys_role_menu` VALUES (1168715176417951744, 1166940161808793600);
INSERT INTO `sys_role_menu` VALUES (1168715176417951744, 1166945389807403008);
INSERT INTO `sys_role_menu` VALUES (1168715176417951744, 1173764201286467584);
INSERT INTO `sys_role_menu` VALUES (1168715176417951744, 1173764857942507520);
INSERT INTO `sys_role_menu` VALUES (1168715176417951744, 1173764980449738752);
INSERT INTO `sys_role_menu` VALUES (1168715176417951744, 1167238917104074752);
INSERT INTO `sys_role_menu` VALUES (1168715176417951744, 1173763303466336256);
INSERT INTO `sys_role_menu` VALUES (1168715176417951744, 1173763486375739392);
INSERT INTO `sys_role_menu` VALUES (1168715176417951744, 1173763937569603584);
INSERT INTO `sys_role_menu` VALUES (1168715176417951744, 1167239016202895360);
INSERT INTO `sys_role_menu` VALUES (1168715176417951744, 1171311831424172032);
INSERT INTO `sys_role_menu` VALUES (1168715176417951744, 1171312122617921536);
INSERT INTO `sys_role_menu` VALUES (1168715176417951744, 1173763122444369920);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键ID',
  `open_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  `organize_id` bigint(20) NULL DEFAULT NULL COMMENT '组织机构ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `birth` datetime NULL DEFAULT NULL COMMENT '出生日期',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `education` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学历',
  `salt` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '盐',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '男' COMMENT '性别 男 女',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '1 表示删除，0 表示未删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`, `username`, `email`, `phone`) USING BTREE COMMENT '唯一字段'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统管理用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1141240498145460224, NULL, NULL, 'admin', '', '17386852916', NULL, 'oXXzos+jRsM=', '', NULL, 'Ww81gwJdSeEQG5Ule06yC3dfPPQ0wuImSTKQ5nLV8YSuVhraiPcwLuhqWzrDFZUYWH0d5uwlro6sdt0uAq3L9qbCinXb9dKCoI00ElhcXpESudWSmsgqRFw5dceGbxxx8hKhafSC78Mb9Q8tKD30nPtfMdptqsNZTwRdQxe1DLKZUpB6Ma8HQMNP0FPiZ9GSvqvuLjV1O1GKeGaP3McqrlyqIqsKtEKEHnVxoX784EJt5a47ySQMkYV1uOtyuJMKAQpVOaKYBgduKbAZLfL2IctHDzOZ3pgmRv8QVMKvMcOZCXDwW4gHvJ44H3Mx5cmoHBPzaLJdowzomTLK6d4HtkJoU3jozrK2YEGy5SsSBDzszPv9u4F06HvVbxIpR5RinS9Qq5W0qJtlqSgtoU1cYkyflK84QfBjhDlczvOqpJM0DYYMuiblPJc5xIy5rmIZf6VxPIguUUzb2wDXl0lfCzsrp8smjfAkwg7iiNdAFPVaBofNHOb8FCgqXwPXuYXA', NULL, '1', NULL, '2020-11-22 14:33:39', NULL, '2020-11-22 14:33:46', 0);
INSERT INTO `sys_user` VALUES (1170943926614360064, NULL, NULL, '普通管理员', '940121265@qq.com', '17386852915', NULL, 'JOnza0wZmgQ=', NULL, NULL, 'QGmURJI3V2as9xYse6xz6ci14NvBgWZR267n74JKwy3Qt4bvvmdAqm3gUNX3qVKmoYTh1pFFEvW5NEvfcrosX2b0dEhOPlamDTf2rUwBYd8FogdMWi8B2o3Xwf6KBBOYnuwPivKUcw7w7Vu8ItItYw0u8zHmnvtTmROruDh1yPkVCIh0JnvjvCCPm86LoLp0zqO2LVRJeE0NYar5K1B1ep49U6PIgqkpzcPEWBJK0BxtWsEFc6UV8PPXFEEUsp7xpX0G0oCmpJ5mzYWYcCNG8VTxQkI0hOWvAI7I9Muxpjl0HsJzgJk9jsKdJq92tTZrdQdNY4S4dAvQpv56ZbPKTfxbQSZNVkmjU0M2oV43bzdj7PtKz76CY0ygbmrI6X424m0OvvCWSR2Y30JacpqUv42hbuLXtn2UXa4fBAaLkJ7T9ezSNG3FKDzfOt8KtU97On0CthQCm1pWN6HYAHSxzbZfsb37Yyr8ex61Y0yQ5WdnLScF7uvhWBGFIWRhoPVW', NULL, '0', NULL, '2020-11-22 14:33:44', NULL, '2020-11-22 14:33:49', 0);

-- ----------------------------
-- Table structure for sys_user_derive
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_derive`;
CREATE TABLE `sys_user_derive`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键ID',
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
INSERT INTO `sys_user_role` VALUES (1141240498145460224, 1168715176417951744);

SET FOREIGN_KEY_CHECKS = 1;
