/*
Navicat MySQL Data Transfer

Date: 2022-01-03 21:09:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `role` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '头像地址',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '帐号状态（0停用 1正常）',
  `safe` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '加密随机码',
  `login_ip` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '最后登录IP',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='管理员表';

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', 'member', '2fe768c4abef3b434ab7209f535f58b5', '[1]', '', '1', 'NdSr', '127.0.0.1', '2022-01-03 17:27:26', '2022-01-03 17:27:26', '2020-01-03 17:05:00');
INSERT INTO `member` VALUES ('2', 'style', '9d9ee979435c6ee8499f75a9abd711b0', '[2]', '', '1', 'lnNk', '127.0.0.1', '2022-01-03 17:19:29', '2022-01-03 17:19:29', '2020-01-03 17:08:00');

-- ----------------------------
-- Table structure for error_log
-- ----------------------------
DROP TABLE IF EXISTS `error_log`;
CREATE TABLE `error_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '请求路由',
  `method` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '请求方式',
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求参数',
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '错误信息',
  `exception` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '错误类型',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='错误日志表';

-- ----------------------------
-- Records of error_log
-- ----------------------------
INSERT INTO `error_log` VALUES ('1', '/spba/member', 'PUT', '{\"role_arr[0]\":[\"4\"],\"status_name\":[\"true\"],\"role_ids\":[\"4,\"],\"password\":[\"\"],\"id\":[\"2\"],\"username\":[\"style\"],\"status\":[\"1\"]}', null, 'class java.lang.NullPointerException', '2022-01-03 17:08:14');
INSERT INTO `error_log` VALUES ('2', '/spba/member', 'PUT', '{\"role_arr[0]\":[\"4\"],\"status_name\":[\"true\"],\"role_ids\":[\"4,\"],\"password\":[\"\"],\"id\":[\"2\"],\"username\":[\"style\"],\"status\":[\"1\"]}', null, 'class java.lang.NullPointerException', '2022-01-03 17:08:15');
INSERT INTO `error_log` VALUES ('3', '/spba/member', 'PUT', '{\"status_name\":[\"true\"],\"role_arr[0][0]\":[\"2\"],\"role_ids\":[\"2,\"],\"password\":[\"\"],\"id\":[\"2\"],\"username\":[\"style\"],\"status\":[\"1\"]}', null, 'class java.lang.NullPointerException', '2022-01-03 17:08:22');
INSERT INTO `error_log` VALUES ('4', '/spba/member', 'PUT', '{\"status_name\":[\"true\"],\"role_arr[0][0]\":[\"2\"],\"role_ids\":[\"2,\"],\"password\":[\"\"],\"id\":[\"2\"],\"username\":[\"style\"],\"status\":[\"1\"]}', null, 'class java.lang.NullPointerException', '2022-01-03 17:08:37');
INSERT INTO `error_log` VALUES ('5', '/spba/member', 'PUT', '{\"status_name\":[\"true\"],\"role_arr[0][0]\":[\"2\"],\"role_ids\":[\"2,\"],\"password\":[\"\"],\"id\":[\"2\"],\"username\":[\"style\"],\"status\":[\"1\"]}', null, 'class java.lang.NullPointerException', '2022-01-03 17:09:23');
INSERT INTO `error_log` VALUES ('6', '/spba/member', 'PUT', '{\"role_arr[0]\":[\"4\"],\"status_name\":[\"true\"],\"role_ids\":[\"4,\"],\"password\":[\"\"],\"id\":[\"2\"],\"username\":[\"style\"],\"status\":[\"1\"]}', null, 'class java.lang.NullPointerException', '2022-01-03 17:11:42');
INSERT INTO `error_log` VALUES ('7', '/spba/member', 'PUT', '{\"status_name\":[\"false\"],\"role_arr[0][0]\":[\"2\"],\"role_ids\":[\"2,\"],\"password\":[\"\"],\"id\":[\"2\"],\"username\":[\"style\"],\"status\":[\"0\"]}', null, 'class java.lang.NullPointerException', '2022-01-03 17:11:47');
INSERT INTO `error_log` VALUES ('8', '/spba/member', 'PUT', '{\"status_name\":[\"false\"],\"role_arr[0][0]\":[\"2\"],\"role_ids\":[\"2,\"],\"password\":[\"\"],\"id\":[\"2\"],\"username\":[\"style\"],\"status\":[\"0\"]}', null, 'class java.lang.NullPointerException', '2022-01-03 17:11:48');
INSERT INTO `error_log` VALUES ('9', '/spba/member', 'PUT', '{\"status_name\":[\"true\"],\"role_arr[0][0]\":[\"2\"],\"role_ids\":[\"2,\"],\"password\":[\"\"],\"id\":[\"2\"],\"username\":[\"style\"],\"status\":[\"1\"]}', null, 'class java.lang.NullPointerException', '2022-01-03 17:13:27');
INSERT INTO `error_log` VALUES ('10', '/spba/member', 'PUT', '{\"status_name\":[\"true\"],\"role_arr[0][0]\":[\"2\"],\"role_ids\":[\"2,\"],\"password\":[\"\"],\"id\":[\"2\"],\"username\":[\"style\"],\"status\":[\"1\"]}', null, 'class java.lang.NullPointerException', '2022-01-03 17:16:12');
INSERT INTO `error_log` VALUES ('11', '/spba/menu', 'GET', '{}', 'Request method \'GET\' not supported', 'class org.springframework.web.HttpRequestMethodNotSupportedException', '2022-01-03 17:20:53');

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `admin_id` int NOT NULL DEFAULT '0',
  `login_ip` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='管理员登录日志表';

-- ----------------------------
-- Records of login_log
-- ----------------------------
INSERT INTO `login_log` VALUES ('1', '1', '127.0.0.1', '2022-01-03 17:07:19');
INSERT INTO `login_log` VALUES ('2', '1', '127.0.0.1', '2022-01-03 17:15:02');
INSERT INTO `login_log` VALUES ('3', '1', '127.0.0.1', '2022-01-03 17:15:19');
INSERT INTO `login_log` VALUES ('4', '1', '127.0.0.1', '2022-01-03 17:18:17');
INSERT INTO `login_log` VALUES ('5', '2', '127.0.0.1', '2022-01-03 17:19:29');
INSERT INTO `login_log` VALUES ('6', '1', '127.0.0.1', '2022-01-03 17:21:09');
INSERT INTO `login_log` VALUES ('7', '1', '127.0.0.1', '2022-01-03 17:27:26');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `parent_id` int NOT NULL DEFAULT '0' COMMENT '父菜单ID',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `path` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '组件路径',
  `perms` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '权限标识',
  `icon` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '菜单图标',
  `sort` int NOT NULL DEFAULT '0' COMMENT '显示顺序',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '菜单状态（0停用 1正常 ）',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='菜单权限表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '系统', '0', 'M', '', '', 'el-icon-s-tools', '0', '1', '2022-01-03 17:15:34', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('2', '首页', '1', 'C', 'home', 'home', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('3', '管理员', '1', 'C', 'member', 'member:list', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('4', '管理员查询', '3', 'F', '', 'member:query', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('5', '管理员新增', '3', 'F', '', 'member:add', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('6', '管理员编辑', '3', 'F', '', 'member:edit', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('7', '管理员删除', '3', 'F', '', 'member:del', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('8', '角色管理', '1', 'C', 'role', 'role:list', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('9', '查询角色', '8', 'F', '', 'role:query', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('10', '新增角色', '8', 'F', '', 'role:add', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('11', '编辑角色', '8', 'F', '', 'role:edit', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('12', '删除角色', '8', 'F', '', 'role:del', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('13', '菜单管理', '1', 'C', 'menus', 'menu:list', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('14', '菜单查询', '13', 'F', '', 'menu:query', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('15', '新增菜单', '13', 'F', '', 'menu:add', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('16', '编辑菜单', '13', 'F', '', 'menu:edit', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('17', '删除菜单', '13', 'F', '', 'menu:del', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('18', '日志', '0', 'M', '', '', 'el-icon-s-order', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('19', '操作日志', '18', 'C', 'operatelog', 'operation:log', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('20', '登录日志', '18', 'C', 'loginlog', 'login:log', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('21', '错误日志', '18', 'C', 'errorlog', 'error:log', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('22', '组件', '0', 'M', '', '', 'el-icon-menu', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('23', '剪切板', '22', 'C', 'clipboard', 'clipboard', '', '1', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('24', '上传视频', '22', 'C', 'uploadvideo', 'upload:video', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `menu` VALUES ('25', '导出Excel', '22', 'C', 'export', 'export', '', '0', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');

-- ----------------------------
-- Table structure for operate_log
-- ----------------------------
DROP TABLE IF EXISTS `operate_log`;
CREATE TABLE `operate_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `admin_id` int NOT NULL DEFAULT '0' COMMENT '管理员ID',
  `username` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '管理员名称',
  `url` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '请求路由',
  `method` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '请求方式',
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '请求参数',
  `ip` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'ip',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='操作日志表';

-- ----------------------------
-- Records of operate_log
-- ----------------------------
INSERT INTO `operate_log` VALUES ('1', '1', 'member', '/spba/role', 'PUT', '{}', '127.0.0.1', '2022-01-03 17:07:29');
INSERT INTO `operate_log` VALUES ('2', '1', 'member', '/spba/role', 'PUT', '{}', '127.0.0.1', '2022-01-03 17:15:30');
INSERT INTO `operate_log` VALUES ('3', '1', 'member', '/spba/menu', 'PUT', '{\"status_name\":[\"true\"],\"icon\":[\"el-icon-s-tools\"],\"sort\":[\"0\"],\"type\":[\"M\"],\"path\":[\"\"],\"parent_id\":[\"0\"],\"name\":[\"系统\"],\"perms\":[\"\"],\"id\":[\"1\"],\"status\":[\"1\"]}', '127.0.0.1', '2022-01-03 17:15:34');
INSERT INTO `operate_log` VALUES ('4', '1', 'member', '/spba/member', 'PUT', '{\"role_arr[0]\":[\"2\"],\"status_name\":[\"true\"],\"role_ids\":[\"2,\"],\"password\":[\"\"],\"id\":[\"2\"],\"username\":[\"style\"],\"status\":[\"1\"]}', '127.0.0.1', '2022-01-03 17:16:35');
INSERT INTO `operate_log` VALUES ('5', '1', 'member', '/spba/member', 'PUT', '{\"role_arr[0]\":[\"2\"],\"status_name\":[\"true\"],\"role_ids\":[\"2,\"],\"password\":[\"\"],\"id\":[\"2\"],\"username\":[\"style\"],\"status\":[\"1\"]}', '127.0.0.1', '2022-01-03 17:16:55');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `permission` json NOT NULL COMMENT '权限集合',
  `root` tinyint NOT NULL DEFAULT '0' COMMENT '超级管理员（0否 1是）',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '角色状态（0停用 1正常）',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '[0]', '1', '1', '2020-01-03 17:05:00', '2020-01-03 17:05:00');
INSERT INTO `role` VALUES ('2', '技术', '[2, 5, 6, 11, 12, 1, 3, 8]', '0', '1', '2022-01-03 17:15:30', '2020-01-03 17:07:29');

-- ----------------------------
-- Table structure for track_event
-- ----------------------------
DROP TABLE IF EXISTS `track_event`;
CREATE TABLE `track_event` (
  `id` int NOT NULL AUTO_INCREMENT,
  `module` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '模块名称',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '事件内容',
  `category` tinyint NOT NULL DEFAULT '1' COMMENT '事件类别（1点击 2页面展示 3跳转页面 4跳转外链 5触发行为）',
  `trigger_time` datetime NOT NULL COMMENT '触发时间',
  `page_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '当前页面URL',
  `element_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '触发元素标识',
  `user_agent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '浏览器UA',
  `ip` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '用户IP',
  `session_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '会话ID',
  `extra_data` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '扩展数据(JSON)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_module` (`module`),
  KEY `idx_category` (`category`),
  KEY `idx_trigger_time` (`trigger_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='前端埋点事件表';

DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `subtitle` varchar(512) DEFAULT NULL,
  `content` longtext,
  `category` varchar(50) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;