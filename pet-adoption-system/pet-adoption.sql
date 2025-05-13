/*
 Navicat Premium Dump SQL

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50744 (5.7.44)
 Source Host           : localhost:3306
 Source Schema         : pet-adoption

 Target Server Type    : MySQL
 Target Server Version : 50744 (5.7.44)
 File Encoding         : 65001

 Date: 05/12/2024 15:58:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext COLLATE utf8mb4_unicode_ci COMMENT '评论内容',
  `user_id` int(11) NOT NULL COMMENT '评论人ID',
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论人名称',
  `user_image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评论人头像',
  `create_time` datetime DEFAULT NULL COMMENT '评论时间',
  `link_id` int(11) NOT NULL COMMENT '所评论对象的ID',
  `type` int(11) NOT NULL COMMENT '评论类型;0-帖子评论;1-互评;2-宠物详情评论',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评论表';

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for goods_center
-- ----------------------------
DROP TABLE IF EXISTS `goods_center`;
CREATE TABLE `goods_center` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `image_url_list` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品图像',
  `user_id` int(11) DEFAULT NULL COMMENT '发布人ID',
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发布人名称',
  `user_image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发布人头像',
  `price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `stock_number` int(11) DEFAULT '0' COMMENT '库存数量',
  `buy_number` int(11) DEFAULT NULL COMMENT '已购数量',
  `remark` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品中心';

-- ----------------------------
-- Records of goods_center
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for icon
-- ----------------------------
DROP TABLE IF EXISTS `icon`;
CREATE TABLE `icon` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='图标管理';

-- ----------------------------
-- Records of icon
-- ----------------------------
BEGIN;
INSERT INTO `icon` (`id`, `value`) VALUES (1, 'Plus');
INSERT INTO `icon` (`id`, `value`) VALUES (2, 'Minus');
INSERT INTO `icon` (`id`, `value`) VALUES (3, 'CirclePlus');
INSERT INTO `icon` (`id`, `value`) VALUES (4, 'Search');
INSERT INTO `icon` (`id`, `value`) VALUES (5, 'Female');
INSERT INTO `icon` (`id`, `value`) VALUES (6, 'Male');
INSERT INTO `icon` (`id`, `value`) VALUES (8, 'House');
INSERT INTO `icon` (`id`, `value`) VALUES (9, 'FullScreen');
INSERT INTO `icon` (`id`, `value`) VALUES (10, 'Loading');
INSERT INTO `icon` (`id`, `value`) VALUES (11, 'Link');
INSERT INTO `icon` (`id`, `value`) VALUES (12, 'Service');
INSERT INTO `icon` (`id`, `value`) VALUES (13, 'Pointer');
INSERT INTO `icon` (`id`, `value`) VALUES (14, 'Star');
INSERT INTO `icon` (`id`, `value`) VALUES (15, 'Notification');
INSERT INTO `icon` (`id`, `value`) VALUES (16, 'Connection');
INSERT INTO `icon` (`id`, `value`) VALUES (17, 'ChatDotRound');
INSERT INTO `icon` (`id`, `value`) VALUES (18, 'Setting');
INSERT INTO `icon` (`id`, `value`) VALUES (19, 'Clock');
INSERT INTO `icon` (`id`, `value`) VALUES (20, 'Position');
INSERT INTO `icon` (`id`, `value`) VALUES (21, 'Discount');
INSERT INTO `icon` (`id`, `value`) VALUES (22, 'Odometer');
INSERT INTO `icon` (`id`, `value`) VALUES (23, 'ChatSquare');
INSERT INTO `icon` (`id`, `value`) VALUES (24, 'ChatRound');
INSERT INTO `icon` (`id`, `value`) VALUES (25, 'ChatLineRound');
INSERT INTO `icon` (`id`, `value`) VALUES (26, 'ChatLineSquare');
INSERT INTO `icon` (`id`, `value`) VALUES (27, 'ChatDotSquare');
INSERT INTO `icon` (`id`, `value`) VALUES (28, 'View');
INSERT INTO `icon` (`id`, `value`) VALUES (29, 'Hide');
INSERT INTO `icon` (`id`, `value`) VALUES (30, 'Unlock');
INSERT INTO `icon` (`id`, `value`) VALUES (31, 'Lock');
INSERT INTO `icon` (`id`, `value`) VALUES (32, 'RefreshRight');
INSERT INTO `icon` (`id`, `value`) VALUES (33, 'RefreshLeft');
INSERT INTO `icon` (`id`, `value`) VALUES (34, 'Refresh');
INSERT INTO `icon` (`id`, `value`) VALUES (35, 'Bell');
INSERT INTO `icon` (`id`, `value`) VALUES (36, 'User');
INSERT INTO `icon` (`id`, `value`) VALUES (37, 'Warning');
INSERT INTO `icon` (`id`, `value`) VALUES (38, 'PieChart');
INSERT INTO `icon` (`id`, `value`) VALUES (39, 'Compass');
INSERT INTO `icon` (`id`, `value`) VALUES (40, 'Edit');
INSERT INTO `icon` (`id`, `value`) VALUES (41, 'Message');
INSERT INTO `icon` (`id`, `value`) VALUES (42, 'MessageBox');
INSERT INTO `icon` (`id`, `value`) VALUES (43, 'Operation');
INSERT INTO `icon` (`id`, `value`) VALUES (44, 'Crop');
INSERT INTO `icon` (`id`, `value`) VALUES (45, 'InfoFilled');
INSERT INTO `icon` (`id`, `value`) VALUES (46, 'UserFilled');
INSERT INTO `icon` (`id`, `value`) VALUES (47, 'Tools');
INSERT INTO `icon` (`id`, `value`) VALUES (48, 'HomeFilled');
INSERT INTO `icon` (`id`, `value`) VALUES (49, 'Menu');
INSERT INTO `icon` (`id`, `value`) VALUES (50, 'UploadFilled');
INSERT INTO `icon` (`id`, `value`) VALUES (51, 'Avatar');
INSERT INTO `icon` (`id`, `value`) VALUES (52, 'HelpFilled');
INSERT INTO `icon` (`id`, `value`) VALUES (53, 'Share');
INSERT INTO `icon` (`id`, `value`) VALUES (54, 'StarFilled');
INSERT INTO `icon` (`id`, `value`) VALUES (55, 'Histogram');
INSERT INTO `icon` (`id`, `value`) VALUES (56, 'Promotion');
INSERT INTO `icon` (`id`, `value`) VALUES (57, 'Ticket');
INSERT INTO `icon` (`id`, `value`) VALUES (58, 'Management');
INSERT INTO `icon` (`id`, `value`) VALUES (59, 'DataLine');
INSERT INTO `icon` (`id`, `value`) VALUES (60, 'Reading');
INSERT INTO `icon` (`id`, `value`) VALUES (61, 'Bicycle');
INSERT INTO `icon` (`id`, `value`) VALUES (62, 'OfficeBuilding');
INSERT INTO `icon` (`id`, `value`) VALUES (63, 'School');
INSERT INTO `icon` (`id`, `value`) VALUES (64, 'Printer');
INSERT INTO `icon` (`id`, `value`) VALUES (65, 'Calendar');
INSERT INTO `icon` (`id`, `value`) VALUES (66, 'CreditCard');
INSERT INTO `icon` (`id`, `value`) VALUES (67, 'Box');
INSERT INTO `icon` (`id`, `value`) VALUES (68, 'Money');
INSERT INTO `icon` (`id`, `value`) VALUES (69, 'Refrigerator');
INSERT INTO `icon` (`id`, `value`) VALUES (70, 'Cpu');
INSERT INTO `icon` (`id`, `value`) VALUES (71, 'Football');
INSERT INTO `icon` (`id`, `value`) VALUES (72, 'Brush');
INSERT INTO `icon` (`id`, `value`) VALUES (73, 'Suitcase');
INSERT INTO `icon` (`id`, `value`) VALUES (74, 'SuitcaseLine');
INSERT INTO `icon` (`id`, `value`) VALUES (75, 'Umbrella');
INSERT INTO `icon` (`id`, `value`) VALUES (76, 'AlarmClock');
INSERT INTO `icon` (`id`, `value`) VALUES (77, 'Medal');
INSERT INTO `icon` (`id`, `value`) VALUES (78, 'GoldMedal');
INSERT INTO `icon` (`id`, `value`) VALUES (79, 'Present');
INSERT INTO `icon` (`id`, `value`) VALUES (80, 'Mouse');
INSERT INTO `icon` (`id`, `value`) VALUES (81, 'Watch');
COMMIT;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '跳转路径 （为空时代表是一个父菜单）',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路由名称',
  `file_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Vue文件所在的位置',
  `is_token` int(1) DEFAULT '0' COMMENT '0 需要令牌认证。1 不需要令牌认证',
  `menu_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单的名称',
  `is_show_menu` int(1) NOT NULL DEFAULT '0' COMMENT '0 显示在左侧菜单。 1 不显示',
  `sort` int(11) NOT NULL COMMENT '排序字段',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `icon` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单管理';

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (12, NULL, NULL, NULL, 0, '系统管理', 0, 2, NULL, 'Setting');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (13, 'menu', 'menu', 'Menu', 0, '菜单管理', 0, 3, 12, 'MessageBox');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (14, 'main', 'main', 'Main', 0, '首页', 0, 0, NULL, 'House');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (15, 'personInfo', 'personInfo', 'PersonInfo', 0, '个人信息', 1, 1, NULL, 'User');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (16, 'roles', 'roles', 'Roles', 0, '角色管理', 0, 4, 12, 'UserFilled');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (17, 'user', 'user', 'User', 0, '用户管理', 0, 5, 12, 'User');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (18, 'note', 'note', 'Note', 0, '系统公告', 0, 6, 12, 'Suitcase');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (19, 'petCrousel', 'petCrousel', 'PetCrousel', 0, '宠物轮播图', 0, 5, NULL, 'Mouse');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (20, 'petCategory', 'petCategory', 'PetCategory', 0, '宠物分类管理', 0, 6, NULL, 'SuitcaseLine');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (21, 'petCenter', 'petCenter', 'PetCenter', 0, '宠物中心', 0, 7, NULL, 'Present');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (22, 'petVedio', 'petVedio', 'PetVedio', 0, '宠物日常视频管理', 0, 8, NULL, 'Crop');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (23, 'postCode', 'postCode', 'PostCode', 0, '帖子管理', 0, 10, NULL, 'Compass');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (24, 'comment', 'comment', 'Comment', 0, '评论管理', 0, 11, NULL, 'Male');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (25, 'petAdoptionCenter', 'petAdoptionCenter', 'PetAdoptionCenter', 0, '宠物领养管理', 0, 12, NULL, 'Pointer');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (26, 'petStar', 'petStar', 'PetStar', 0, '宠物明星值管理', 0, 13, NULL, 'MessageBox');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (27, 'petReservation', 'petReservation', 'PetReservation', 0, '宠物领养预约管理', 0, 14, NULL, 'FullScreen');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (28, 'goodsCenter', 'goodsCenter', 'GoodsCenter', 0, '商品中心', 0, 16, NULL, 'Search');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (29, 'orderCenter', 'orderCenter', 'OrderCenter', 0, '商品订单管理', 0, 17, NULL, 'Male');
COMMIT;

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '发布的内容',
  `create_time` datetime DEFAULT NULL COMMENT '发布的时间',
  `user_id` int(11) DEFAULT NULL COMMENT '发布人ID',
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发布人名称',
  `user_image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发布人头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统公告管理';

-- ----------------------------
-- Records of note
-- ----------------------------
BEGIN;
INSERT INTO `note` (`id`, `content`, `create_time`, `user_id`, `user_name`, `user_image`) VALUES (3, '我的微信号：【tw17608411971】,可加我微信进入免费答疑群，群内不定时发送优质项目源码，可用作毕设，课题，和学习项目！！！现在进群有优惠名额，毕设7折！！！', '2024-11-23 13:52:24', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png');
INSERT INTO `note` (`id`, `content`, `create_time`, `user_id`, `user_name`, `user_image`) VALUES (4, '接毕业设计,课题', '2024-11-23 13:52:45', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png');
INSERT INTO `note` (`id`, `content`, `create_time`, `user_id`, `user_name`, `user_image`) VALUES (5, '大家好！我是全栈维哥', '2024-11-23 13:53:01', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png');
COMMIT;

-- ----------------------------
-- Table structure for order_center
-- ----------------------------
DROP TABLE IF EXISTS `order_center`;
CREATE TABLE `order_center` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '购买数量',
  `order_price` decimal(10,2) NOT NULL COMMENT '订单总金额',
  `single_price` decimal(10,2) NOT NULL COMMENT '购买单价',
  `goods_center_id` int(11) NOT NULL COMMENT '商品ID',
  `user_id` int(11) DEFAULT NULL COMMENT '购买人ID',
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '购买人名称',
  `user_image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '购买人头像',
  `status` int(1) NOT NULL COMMENT '下单状态;0-已支付;1-已取消',
  `pay_mode` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '支付方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单管理中心';

-- ----------------------------
-- Records of order_center
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pet_adoption_center
-- ----------------------------
DROP TABLE IF EXISTS `pet_adoption_center`;
CREATE TABLE `pet_adoption_center` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '领养人ID',
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '领养人名称',
  `user_image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '领养人头像',
  `user_phone` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '领养人联系方式',
  `user_address` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '领养人地址',
  `pet_center_id` int(11) NOT NULL COMMENT '宠物ID',
  `pet_center_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '宠物名称',
  `adoption_status` int(11) DEFAULT NULL COMMENT '领养状态;0-领养中;1-已归还',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='宠物领养管理';

-- ----------------------------
-- Records of pet_adoption_center
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pet_category
-- ----------------------------
DROP TABLE IF EXISTS `pet_category`;
CREATE TABLE `pet_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '宠物类别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='宠物类别';

-- ----------------------------
-- Records of pet_category
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pet_center
-- ----------------------------
DROP TABLE IF EXISTS `pet_center`;
CREATE TABLE `pet_center` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pet_category_id` int(11) NOT NULL COMMENT '宠物类别ID',
  `pet_category_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '宠物类别名称',
  `image_url_list` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '宠物图像',
  `user_id` int(11) NOT NULL COMMENT '创建人ID',
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人名称',
  `user_image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `adoption_status` int(1) DEFAULT NULL COMMENT '领养状态;0-待领养;1-已领养',
  `pet_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '宠物姓名',
  `pet_sex` int(1) NOT NULL COMMENT '宠物性别;0-公;1-母;2-未知',
  `pet_age` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '宠物年龄',
  `pet_remark` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '宠物描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='宠物中心';

-- ----------------------------
-- Records of pet_center
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pet_crousel
-- ----------------------------
DROP TABLE IF EXISTS `pet_crousel`;
CREATE TABLE `pet_crousel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `image_url_list` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '轮播图片',
  `show_status` int(1) DEFAULT '0' COMMENT '是否显示;0-不显示;1-显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='宠物轮播图';

-- ----------------------------
-- Records of pet_crousel
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pet_reservation
-- ----------------------------
DROP TABLE IF EXISTS `pet_reservation`;
CREATE TABLE `pet_reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '预约人',
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '预约人姓名',
  `user_image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '预约人头像',
  `create_time` datetime DEFAULT NULL COMMENT '预约时间',
  `remark` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '领养宣言',
  `pet_center_id` int(11) NOT NULL COMMENT '宠物ID',
  `pet_center_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '宠物名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='宠物预约管理';

-- ----------------------------
-- Records of pet_reservation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pet_star
-- ----------------------------
DROP TABLE IF EXISTS `pet_star`;
CREATE TABLE `pet_star` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `star_num` int(11) DEFAULT '0' COMMENT '宠物明星值',
  `pet_center_id` int(11) NOT NULL COMMENT '宠物ID',
  `pet_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '宠物名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='宠物明星值管理';

-- ----------------------------
-- Records of pet_star
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pet_vedio
-- ----------------------------
DROP TABLE IF EXISTS `pet_vedio`;
CREATE TABLE `pet_vedio` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vedio_url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '宠物视频地址',
  `remark` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '视频描述',
  `pet_center_id` int(11) NOT NULL COMMENT '宠物ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='宠物日常视频';

-- ----------------------------
-- Records of pet_vedio
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for post_code
-- ----------------------------
DROP TABLE IF EXISTS `post_code`;
CREATE TABLE `post_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '发布内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发布时间',
  `image_url_list` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片',
  `user_id` int(11) NOT NULL COMMENT '发布人ID',
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '发布人名称',
  `user_image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发布人头像',
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '发布标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='宠物圈帖子管理';

-- ----------------------------
-- Records of post_code
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `menu_id` (`menu_id`),
  CONSTRAINT `role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色菜单管理';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
BEGIN;
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`) VALUES (24, 9, 14);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`) VALUES (25, 9, 15);
COMMIT;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色管理';

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` (`id`, `name`, `remark`) VALUES (7, 'ROLE_ADMIN', '管理员');
INSERT INTO `roles` (`id`, `name`, `remark`) VALUES (9, 'ROLE_USER', '普通用户');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登陆密码',
  `nick_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '邮箱',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `image_url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户头像',
  `role` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  `apply_status` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `role` (`role`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role`) REFERENCES `roles` (`name`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户管理';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `user_name`, `password`, `nick_name`, `email`, `age`, `phone`, `image_url`, `role`, `apply_status`) VALUES (7, '小维子', 'e10adc3949ba59abbe56e057f20f883e', '小维子', '2805487767@qq.com', 16, '17608411971', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png', 'ROLE_ADMIN', 0);
INSERT INTO `user` (`id`, `user_name`, `password`, `nick_name`, `email`, `age`, `phone`, `image_url`, `role`, `apply_status`) VALUES (11, '汤维', 'e10adc3949ba59abbe56e057f20f883e', '汤维🔥', '2805487766@qq.com', 30, '17608411979', 'http://localhost:9706/file/6f77801aca8f45678d2f337a6a7dbe3b.jpeg', 'ROLE_USER', 0);
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色管理';

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_token
-- ----------------------------
DROP TABLE IF EXISTS `user_token`;
CREATE TABLE `user_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Token 的值',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `overdue` int(11) NOT NULL COMMENT '多久过期  -1 用不过期。 大于-1 超过这个时间限制则该条数据会被删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Token的生成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=390 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户令牌管理';

-- ----------------------------
-- Records of user_token
-- ----------------------------
BEGIN;
INSERT INTO `user_token` (`id`, `token`, `user_id`, `overdue`, `create_time`) VALUES (389, '5fdf07e06a2606f9bedbc14ecd28fc9e', 7, -1, '2024-12-03 18:31:19');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
