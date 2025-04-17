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

 Date: 21/12/2024 16:58:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL,
  `content` longtext COLLATE utf8mb4_unicode_ci COMMENT '评论内容',
  `user_id` int(11) DEFAULT NULL COMMENT '评论人ID',
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评论人名称',
  `user_image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评论人头像',
  `create_time` datetime DEFAULT NULL COMMENT '评论时间',
  `link_id` bigint(20) NOT NULL COMMENT '所评论对象的ID',
  `type` int(11) NOT NULL COMMENT '评论类型;0-帖子评论;1-互评;2-商品评价',
  `type_id` bigint(20) NOT NULL COMMENT '类型ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评论表';

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` (`id`, `content`, `user_id`, `user_name`, `user_image`, `create_time`, `link_id`, `type`, `type_id`) VALUES (1868522663758106624, '9999', 13, '小汤', 'http://localhost:16667/file/8a4885ea7ed54b4dbb5f5864637c0ab5.jpeg', '2024-12-16 13:04:53', 3, 0, 3);
INSERT INTO `comment` (`id`, `content`, `user_id`, `user_name`, `user_image`, `create_time`, `link_id`, `type`, `type_id`) VALUES (1868551715504099328, '6666', 13, '小汤', 'http://localhost:16667/file/8a4885ea7ed54b4dbb5f5864637c0ab5.jpeg', '2024-12-16 15:00:20', 8, 0, 8);
INSERT INTO `comment` (`id`, `content`, `user_id`, `user_name`, `user_image`, `create_time`, `link_id`, `type`, `type_id`) VALUES (1870007380567134208, 'dasdasd', 12, '小邓', 'http://localhost:16667/file/a126f51ebdce41bb955951a82f78ecb3.jpeg', '2024-12-20 15:24:37', 9, 0, 9);
INSERT INTO `comment` (`id`, `content`, `user_id`, `user_name`, `user_image`, `create_time`, `link_id`, `type`, `type_id`) VALUES (1870007404076208128, 'dasdasdad1111', 12, '小邓', 'http://localhost:16667/file/a126f51ebdce41bb955951a82f78ecb3.jpeg', '2024-12-20 15:24:43', 1870007380567134208, 0, 9);
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
  `buy_number` int(11) DEFAULT '0' COMMENT '已购数量',
  `remark` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品中心';

-- ----------------------------
-- Records of goods_center
-- ----------------------------
BEGIN;
INSERT INTO `goods_center` (`id`, `name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `price`, `stock_number`, `buy_number`, `remark`) VALUES (1, '蔬菜双拼狗粮', '/file/26eedcdac92243ae8b170dac267c2a4a.jpg', 7, '小维子', 'http://localhost:16667/file/509eb96fd00648febe7ff17e64778975.jpeg', 30.00, 100, 0, '使用天然蔬菜搭配');
INSERT INTO `goods_center` (`id`, `name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `price`, `stock_number`, `buy_number`, `remark`) VALUES (2, '粗蛋白猫粮', '/file/d9c51832ca684e1a9854ed1b5656bd91.jpg', 7, '小维子', 'http://localhost:16667/file/509eb96fd00648febe7ff17e64778975.jpeg', 45.00, 100, 1, '拒绝挑嘴，软便克星');
INSERT INTO `goods_center` (`id`, `name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `price`, `stock_number`, `buy_number`, `remark`) VALUES (3, '萝卜干狗粮', '/file/22cef1368a2342cfa4970854948bc84a.jpg', 7, '小维子', 'http://localhost:16667/file/509eb96fd00648febe7ff17e64778975.jpeg', 188.00, 100, 2, '富含益生菌，工厂直销');
INSERT INTO `goods_center` (`id`, `name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `price`, `stock_number`, `buy_number`, `remark`) VALUES (4, '鲜肉犬粮', '/file/d862b310f5d54beeab3a646125f43497.jpg', 7, '小维子', 'http://localhost:16667/file/509eb96fd00648febe7ff17e64778975.jpeg', 200.00, 100, 0, '呵护肠胃，美毛亮毛');
INSERT INTO `goods_center` (`id`, `name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `price`, `stock_number`, `buy_number`, `remark`) VALUES (5, '宠物黑色卫衣', '/file/adbe6f2cdba2469e84249be1fa3590fc.jpg', 7, '小维子', 'http://localhost:16667/file/509eb96fd00648febe7ff17e64778975.jpeg', 177.00, 100, 2, '很保暖！！！很厚实');
INSERT INTO `goods_center` (`id`, `name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `price`, `stock_number`, `buy_number`, `remark`) VALUES (6, '绿色外套，猫狗同款', '/file/e92d327242d949a29aed52f1663be77e.jpg', 7, '小维子', 'http://localhost:16667/file/509eb96fd00648febe7ff17e64778975.jpeg', 78.00, 200, 2, '很可爱！！！');
INSERT INTO `goods_center` (`id`, `name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `price`, `stock_number`, `buy_number`, `remark`) VALUES (7, '创意搞怪变身背带裤', '/file/90708a116b524a38825ebce1cb641b49.jpg', 7, '小维子', 'http://localhost:16667/file/509eb96fd00648febe7ff17e64778975.jpeg', 120.00, 199, 3, '穿上很搞笑');
INSERT INTO `goods_center` (`id`, `name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `price`, `stock_number`, `buy_number`, `remark`) VALUES (8, '鲜肉犬粮【全犬通用】', '/file/79d018f82cb34ca99dc70cfe56be9fc2.jpg', 7, '小维子', 'http://localhost:16667/file/509eb96fd00648febe7ff17e64778975.jpeg', 200.00, 188, 2, '高营养吸收！！！');
INSERT INTO `goods_center` (`id`, `name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `price`, `stock_number`, `buy_number`, `remark`) VALUES (9, '防爆冲栓狗绳', '/file/43263c729e46475bbeaeb2e2bad90ecc.jpg', 7, '小维子', 'http://localhost:16667/file/509eb96fd00648febe7ff17e64778975.jpeg', 20.00, 888, 12, '防爆冲，防勒手');
INSERT INTO `goods_center` (`id`, `name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `price`, `stock_number`, `buy_number`, `remark`) VALUES (10, '夜间出行绳子', '/file/518b7c10031942fb8a766f63d0823211.jpg', 7, '小维子', 'http://localhost:16667/file/509eb96fd00648febe7ff17e64778975.jpeg', 70.00, 788, 2, '夜间出行必备！！！');
INSERT INTO `goods_center` (`id`, `name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `price`, `stock_number`, `buy_number`, `remark`) VALUES (11, '猫绳', '/file/70f6fa717e8842d0a0424a06a7514016.jpg', 7, '小维子', 'http://localhost:16667/file/509eb96fd00648febe7ff17e64778975.jpeg', 30.00, 120, 3, '很轻巧！！！');
INSERT INTO `goods_center` (`id`, `name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `price`, `stock_number`, `buy_number`, `remark`) VALUES (12, '宠物零食', '/file/3897add85fb54fcab0b153192a965b49.webp', 7, '小维子', 'http://localhost:16667/file/509eb96fd00648febe7ff17e64778975.jpeg', 67.00, 888, 8, '通用零食！！！');
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单管理';

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
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (23, 'postCode', 'postCode', 'PostCode', 0, '帖子管理', 0, 10, NULL, 'Compass');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (24, 'comment', 'comment', 'Comment', 0, '评论管理', 0, 11, NULL, 'Male');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (25, 'petAdoptionCenter', 'petAdoptionCenter', 'PetAdoptionCenter', 0, '宠物领养管理', 0, 12, NULL, 'Pointer');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (26, 'petStar', 'petStar', 'PetStar', 0, '宠物明星值管理', 0, 13, NULL, 'MessageBox');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (27, 'petReservation', 'petReservation', 'PetReservation', 0, '宠物领养预约管理', 0, 12, NULL, 'FullScreen');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (28, 'goodsCenter', 'goodsCenter', 'GoodsCenter', 0, '商品中心', 0, 16, NULL, 'Search');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (29, 'orderCenter', 'orderCenter', 'OrderCenter', 0, '商品订单管理', 0, 17, NULL, 'Male');
INSERT INTO `menu` (`id`, `path`, `name`, `file_path`, `is_token`, `menu_name`, `is_show_menu`, `sort`, `parent_id`, `icon`) VALUES (30, 'orderCarCenter', 'orderCarCenter', 'OrderCarCenter', 0, '购物车', 0, 12, NULL, 'GoldMedal');
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
-- Table structure for order_car_center
-- ----------------------------
DROP TABLE IF EXISTS `order_car_center`;
CREATE TABLE `order_car_center` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '购买数量',
  `single_price` decimal(10,2) NOT NULL COMMENT '购买单价',
  `goods_center_id` int(11) NOT NULL COMMENT '商品ID',
  `user_id` int(11) DEFAULT NULL COMMENT '购买人ID',
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '购买人名称',
  `user_image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '购买人头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='购物车模块';

-- ----------------------------
-- Records of order_car_center
-- ----------------------------
BEGIN;
INSERT INTO `order_car_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `user_id`, `user_name`, `user_image`) VALUES (2, '宠物零食', 1, 67.00, 12, 12, '小邓', 'http://localhost:16667/file/a126f51ebdce41bb955951a82f78ecb3.jpeg');
COMMIT;

-- ----------------------------
-- Table structure for order_center
-- ----------------------------
DROP TABLE IF EXISTS `order_center`;
CREATE TABLE `order_center` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_price` decimal(10,2) NOT NULL COMMENT '订单总金额',
  `user_id` int(11) DEFAULT NULL COMMENT '购买人ID',
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '购买人名称',
  `user_image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '购买人头像',
  `status` int(1) NOT NULL DEFAULT '-1' COMMENT '下单状态;-1已下单;0-已支付;1-已取消;2-已完成',
  `pay_mode` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '支付方式',
  `order_number` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单管理中心';

-- ----------------------------
-- Records of order_center
-- ----------------------------
BEGIN;
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (1, 67.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', -1, NULL, '1866759090056847360');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (2, 20.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', -1, NULL, '1866761526494392320');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (3, 20.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', -1, NULL, '1866772820257693696');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (4, 177.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', 0, '微信支付', '1866774918777036800');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (5, 67.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', -1, NULL, '1866786039009488896');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (6, 67.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', 0, '支付宝支付', '1866786365921931264');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (7, 200.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', 0, '支付宝支付', '1866786532469354496');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (8, 30.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', 0, '支付宝支付', '1866786900955738112');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (9, 97.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', -1, NULL, '1866787372521336832');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (10, 97.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', 0, '广发银行', '1866787717708361728');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (11, 97.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', 0, '微信支付', '1866787906036805632');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (12, 30.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', 0, '支付宝支付', '1866787960311099392');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (13, 67.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', 0, '支付宝支付', '1866788145216991232');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (14, 67.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', 1, '微信支付', '1866788491167379456');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (15, 517.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', 0, '支付宝支付', '1866799623064117248');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (16, 670.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', 1, '建设银行', '1866802007257505792');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (17, 70.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', 1, '建设银行', '1866802211297812480');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (18, 67.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', 1, '建设银行', '1867162375540555776');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (19, 200.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', 1, '中国银行', '1867162734262599680');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (20, 45.00, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', 0, '广发银行', '1868159969133711360');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (21, 134.00, 12, '小邓', 'http://localhost:16667/file/a126f51ebdce41bb955951a82f78ecb3.jpeg', 0, '中国银行', '1868193142639013888');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (22, 67.00, 12, '小邓', 'http://localhost:16667/file/a126f51ebdce41bb955951a82f78ecb3.jpeg', 0, '中国银行', '1868551349857259520');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (23, 20.00, 12, '小邓', 'http://localhost:16667/file/a126f51ebdce41bb955951a82f78ecb3.jpeg', 1, '微信支付', '1868555056057520128');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (24, 67.00, 12, '小邓', 'http://localhost:16667/file/a126f51ebdce41bb955951a82f78ecb3.jpeg', -1, NULL, '1870036324515319808');
INSERT INTO `order_center` (`id`, `order_price`, `user_id`, `user_name`, `user_image`, `status`, `pay_mode`, `order_number`) VALUES (25, 30.00, 12, '小邓', 'http://localhost:16667/file/a126f51ebdce41bb955951a82f78ecb3.jpeg', -1, NULL, '1870040265101676544');
COMMIT;

-- ----------------------------
-- Table structure for order_detail_center
-- ----------------------------
DROP TABLE IF EXISTS `order_detail_center`;
CREATE TABLE `order_detail_center` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '购买数量',
  `single_price` decimal(10,2) NOT NULL COMMENT '购买单价',
  `goods_center_id` int(11) NOT NULL COMMENT '商品ID',
  `order_center_id` int(11) NOT NULL COMMENT '订单ID',
  `order_number` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单详情';

-- ----------------------------
-- Records of order_detail_center
-- ----------------------------
BEGIN;
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (1, '宠物零食', 1, 67.00, 12, 1, '1866759090056847360');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (2, '防爆冲栓狗绳', 1, 20.00, 9, 2, '1866761526494392320');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (3, '防爆冲栓狗绳', 1, 20.00, 9, 3, '1866772820257693696');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (4, '宠物黑色卫衣', 1, 177.00, 5, 4, '1866774918777036800');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (5, '宠物零食', 1, 67.00, 12, 5, '1866786039009488896');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (6, '宠物零食', 1, 67.00, 12, 6, '1866786365921931264');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (7, '夜间出行绳子', 2, 70.00, 6, 7, '1866786532469354496');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (8, '猫绳', 2, 30.00, 3, 7, '1866786532469354496');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (9, '猫绳', 1, 30.00, 7, 8, '1866786900955738112');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (10, '宠物零食', 1, 67.00, 8, 9, '1866787372521336832');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (11, '猫绳', 1, 30.00, 7, 9, '1866787372521336832');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (12, '宠物零食', 1, 67.00, 8, 10, '1866787717708361728');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (13, '猫绳', 1, 30.00, 7, 10, '1866787717708361728');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (14, '宠物零食', 1, 67.00, 12, 11, '1866787906036805632');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (15, '猫绳', 1, 30.00, 11, 11, '1866787906036805632');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (16, '猫绳', 1, 30.00, 11, 12, '1866787960311099392');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (17, '宠物零食', 1, 67.00, 12, 13, '1866788145216991232');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (18, '宠物零食', 1, 67.00, 12, 14, '1866788491167379456');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (19, '宠物黑色卫衣', 1, 177.00, 5, 15, '1866799623064117248');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (20, '防爆冲栓狗绳', 10, 20.00, 9, 15, '1866799623064117248');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (21, '夜间出行绳子', 2, 70.00, 10, 15, '1866799623064117248');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (22, '宠物零食', 10, 67.00, 12, 16, '1866802007257505792');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (23, '夜间出行绳子', 1, 70.00, 10, 17, '1866802211297812480');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (24, '宠物零食', 1, 67.00, 12, 18, '1867162375540555776');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (25, '夜间出行绳子', 2, 70.00, 10, 19, '1867162734262599680');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (26, '猫绳', 2, 30.00, 11, 19, '1867162734262599680');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (27, '粗蛋白猫粮', 1, 45.00, 2, 20, '1868159969133711360');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (28, '宠物零食', 2, 67.00, 12, 21, '1868193142639013888');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (29, '宠物零食', 1, 67.00, 12, 22, '1868551349857259520');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (30, '防爆冲栓狗绳', 1, 20.00, 9, 23, '1868555056057520128');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (31, '宠物零食', 1, 67.00, 12, 24, '1870036324515319808');
INSERT INTO `order_detail_center` (`id`, `goods_name`, `number`, `single_price`, `goods_center_id`, `order_center_id`, `order_number`) VALUES (32, '猫绳', 1, 30.00, 11, 25, '1870040265101676544');
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
  `adoption_status` int(11) DEFAULT '2' COMMENT '领养状态;0-领养中;1-已归还;2-申请中',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='宠物领养管理';

-- ----------------------------
-- Records of pet_adoption_center
-- ----------------------------
BEGIN;
INSERT INTO `pet_adoption_center` (`id`, `user_id`, `user_name`, `user_image`, `user_phone`, `user_address`, `pet_center_id`, `pet_center_name`, `adoption_status`) VALUES (8, 13, '小汤', 'http://localhost:16667/file/8a4885ea7ed54b4dbb5f5864637c0ab5.jpeg', '1231232', '阿瑟dasd', 4, '小米粒', 0);
INSERT INTO `pet_adoption_center` (`id`, `user_id`, `user_name`, `user_image`, `user_phone`, `user_address`, `pet_center_id`, `pet_center_name`, `adoption_status`) VALUES (9, 13, '小汤', 'http://localhost:16667/file/8a4885ea7ed54b4dbb5f5864637c0ab5.jpeg', '17777777', '萨达萨达撒', 5, '二哈', 0);
INSERT INTO `pet_adoption_center` (`id`, `user_id`, `user_name`, `user_image`, `user_phone`, `user_address`, `pet_center_id`, `pet_center_name`, `adoption_status`) VALUES (10, 12, '小邓', 'http://localhost:16667/file/a126f51ebdce41bb955951a82f78ecb3.jpeg', '177777777', '长沙', 5, '二哈', 1);
INSERT INTO `pet_adoption_center` (`id`, `user_id`, `user_name`, `user_image`, `user_phone`, `user_address`, `pet_center_id`, `pet_center_name`, `adoption_status`) VALUES (11, 12, '小邓', 'http://localhost:16667/file/a126f51ebdce41bb955951a82f78ecb3.jpeg', '176666666', '萨达萨达', 9, '八戒', 0);
COMMIT;

-- ----------------------------
-- Table structure for pet_category
-- ----------------------------
DROP TABLE IF EXISTS `pet_category`;
CREATE TABLE `pet_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '宠物类别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='宠物类别';

-- ----------------------------
-- Records of pet_category
-- ----------------------------
BEGIN;
INSERT INTO `pet_category` (`id`, `category`) VALUES (1, '猫');
INSERT INTO `pet_category` (`id`, `category`) VALUES (2, '狗');
INSERT INTO `pet_category` (`id`, `category`) VALUES (3, '猪');
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
  `user_id` int(11) DEFAULT NULL COMMENT '创建人ID',
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人名称',
  `user_image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `adoption_status` int(1) DEFAULT '0' COMMENT '领养状态;0-待领养;1-已领养',
  `pet_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '宠物姓名',
  `pet_sex` int(1) NOT NULL COMMENT '宠物性别;0-公;1-母;2-未知',
  `pet_age` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '宠物年龄',
  `pet_remark` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '宠物描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='宠物中心';

-- ----------------------------
-- Records of pet_center
-- ----------------------------
BEGIN;
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (1, 2, '狗', '/file/b72cd77b236342749c5e18e41bce80ca.webp', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png', '2024-12-11 11:48:59', 0, '黄毛', 0, '3个月', '很乐观的一条小狗');
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (2, 1, '猫', '/file/c9a7d555c3e64b4f9268933e401ab29d.jpg', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png', '2024-12-11 11:50:01', 0, '黑蛋', 0, '2个月', '有点胆小');
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (3, 2, '狗', '/file/0dc77de136be4be999cf773a3bfbdd79.jpeg', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png', '2024-12-11 11:50:33', 0, '毛球', 1, '6个月', '喜欢拆家 ');
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (4, 1, '猫', '/file/1e952ab31d114181b59e699c0382a32a.jpeg', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png', '2024-12-11 11:51:10', 0, '小米粒', 1, '1个月', '爱干净，不喜欢陌生人');
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (5, 2, '狗', '/file/bb536eacd00f4fc9abc9bf8c5ea19e4e.webp', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png', '2024-12-11 11:52:58', 0, '二哈', 1, '9个月', '贼喜欢拆家,对小朋友很友善');
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (6, 1, '猫', '/file/b50846fda4d74871957384e86c957bbb.jpg', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png', '2024-12-11 11:53:54', 0, '毛坨', 1, '7个月', '很优雅的一只小猫');
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (7, 2, '狗', '/file/77b7a084e9cd4be9a9432599075e7450.jpg', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png', '2024-12-11 11:54:31', 0, '靓仔', 0, '6个月', '很聪明的小狗,喜欢干净');
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (8, 1, '猫', '/file/3d2503bd539b44e2b369880f94f9b19e.jpg', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png', '2024-12-11 11:55:22', 0, '贝壳', 1, '5个月', '毛茸茸，很可爱');
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (9, 3, '猪', '/file/a9180ba28dac484c96bfa8dbb3ce30d2.webp', 7, '小维子1', 'http://localhost:16667/file/509eb96fd00648febe7ff17e64778975.jpeg', '2024-12-16 15:09:11', 0, '八戒', 1, '7个月', '胖乎乎的 很可爱');
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (10, 2, '狗', '/file/b72cd77b236342749c5e18e41bce80ca.webp', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png', '2024-12-11 11:48:59', 0, '黄毛', 0, '3个月', '很乐观的一条小狗');
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (11, 1, '猫', '/file/c9a7d555c3e64b4f9268933e401ab29d.jpg', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png', '2024-12-11 11:50:01', 0, '黑蛋', 0, '2个月', '有点胆小');
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (12, 2, '狗', '/file/0dc77de136be4be999cf773a3bfbdd79.jpeg', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png', '2024-12-11 11:50:33', 0, '毛球', 1, '6个月', '喜欢拆家 ');
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (13, 1, '猫', '/file/1e952ab31d114181b59e699c0382a32a.jpeg', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png', '2024-12-11 11:51:10', 0, '小米粒', 1, '1个月', '爱干净，不喜欢陌生人');
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (14, 2, '狗', '/file/bb536eacd00f4fc9abc9bf8c5ea19e4e.webp', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png', '2024-12-11 11:52:58', 0, '二哈', 1, '9个月', '贼喜欢拆家,对小朋友很友善');
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (15, 1, '猫', '/file/b50846fda4d74871957384e86c957bbb.jpg', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png', '2024-12-11 11:53:54', 0, '毛坨', 1, '7个月', '很优雅的一只小猫');
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (16, 2, '狗', '/file/77b7a084e9cd4be9a9432599075e7450.jpg', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png', '2024-12-11 11:54:31', 0, '靓仔', 0, '6个月', '很聪明的小狗,喜欢干净');
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (17, 1, '猫', '/file/3d2503bd539b44e2b369880f94f9b19e.jpg', 7, '小维子', 'http://localhost:9706/file/de4d20b8f6924a65a81ba627d7db32ca.png', '2024-12-11 11:55:22', 0, '贝壳', 1, '5个月', '毛茸茸，很可爱');
INSERT INTO `pet_center` (`id`, `pet_category_id`, `pet_category_name`, `image_url_list`, `user_id`, `user_name`, `user_image`, `create_time`, `adoption_status`, `pet_name`, `pet_sex`, `pet_age`, `pet_remark`) VALUES (18, 3, '猪', '/file/a9180ba28dac484c96bfa8dbb3ce30d2.webp', 7, '小维子1', 'http://localhost:16667/file/509eb96fd00648febe7ff17e64778975.jpeg', '2024-12-16 15:09:11', 0, '八戒', 1, '7个月', '胖乎乎的 很可爱');
COMMIT;

-- ----------------------------
-- Table structure for pet_crousel
-- ----------------------------
DROP TABLE IF EXISTS `pet_crousel`;
CREATE TABLE `pet_crousel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `image_url_list` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '轮播图片',
  `show_status` int(1) DEFAULT '1' COMMENT '是否显示;0-不显示;1-显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='宠物轮播图';

-- ----------------------------
-- Records of pet_crousel
-- ----------------------------
BEGIN;
INSERT INTO `pet_crousel` (`id`, `image_url_list`, `show_status`) VALUES (1, '/file/48a2421158d04120a782f8380c3d2b9d.webp', 1);
INSERT INTO `pet_crousel` (`id`, `image_url_list`, `show_status`) VALUES (2, '/file/79cae853ef9c406380fac63135bcb9a6.webp', 1);
INSERT INTO `pet_crousel` (`id`, `image_url_list`, `show_status`) VALUES (3, '/file/9af35cb3367843b0bf420505c13af43a.jpeg', 1);
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
  `status` int(1) DEFAULT '1' COMMENT '预约状态;0-预约中;1-已预约;2-已领养;3-取消预约;4-放弃领养',
  `user_phone` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '领养人联系方式',
  `user_address` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '领养人地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='宠物预约管理';

-- ----------------------------
-- Records of pet_reservation
-- ----------------------------
BEGIN;
INSERT INTO `pet_reservation` (`id`, `user_id`, `user_name`, `user_image`, `create_time`, `remark`, `pet_center_id`, `pet_center_name`, `status`, `user_phone`, `user_address`) VALUES (3, 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', '2024-12-15 13:35:16', 'sdfsdfsdfsdf', 6, '毛坨', 1, '123123', 'qweqweqwe');
INSERT INTO `pet_reservation` (`id`, `user_id`, `user_name`, `user_image`, `create_time`, `remark`, `pet_center_id`, `pet_center_name`, `status`, `user_phone`, `user_address`) VALUES (4, 12, '小邓', 'http://localhost:16667/file/a126f51ebdce41bb955951a82f78ecb3.jpeg', '2024-12-16 11:59:56', '领养后，会好好待它！！！', 3, '毛球', 1, '16677777777', 'asdasd');
INSERT INTO `pet_reservation` (`id`, `user_id`, `user_name`, `user_image`, `create_time`, `remark`, `pet_center_id`, `pet_center_name`, `status`, `user_phone`, `user_address`) VALUES (5, 13, '小汤', 'http://localhost:16667/file/8a4885ea7ed54b4dbb5f5864637c0ab5.jpeg', '2024-12-16 12:03:51', '我会好好的对待它！！我会好好的对待它我会好好的对待它', 7, '靓仔', 1, '1667777777', '萨达萨达撒');
INSERT INTO `pet_reservation` (`id`, `user_id`, `user_name`, `user_image`, `create_time`, `remark`, `pet_center_id`, `pet_center_name`, `status`, `user_phone`, `user_address`) VALUES (6, 13, '小汤', 'http://localhost:16667/file/8a4885ea7ed54b4dbb5f5864637c0ab5.jpeg', '2024-12-16 12:04:15', '哈哈死嗲很大阿斯顿好风景啊是的啊就是带回家', 5, '二哈', 2, '17777777', '萨达萨达撒');
INSERT INTO `pet_reservation` (`id`, `user_id`, `user_name`, `user_image`, `create_time`, `remark`, `pet_center_id`, `pet_center_name`, `status`, `user_phone`, `user_address`) VALUES (7, 13, '小汤', 'http://localhost:16667/file/8a4885ea7ed54b4dbb5f5864637c0ab5.jpeg', '2024-12-16 12:04:33', '萨达萨达撒萨达说阿斯顿', 4, '小米粒', 2, '1231232', '阿瑟dasd');
INSERT INTO `pet_reservation` (`id`, `user_id`, `user_name`, `user_image`, `create_time`, `remark`, `pet_center_id`, `pet_center_name`, `status`, `user_phone`, `user_address`) VALUES (8, 12, '小邓', 'http://localhost:16667/file/a126f51ebdce41bb955951a82f78ecb3.jpeg', '2024-12-16 15:02:15', '我会善待它', 5, '二哈', 4, '177777777', '长沙');
INSERT INTO `pet_reservation` (`id`, `user_id`, `user_name`, `user_image`, `create_time`, `remark`, `pet_center_id`, `pet_center_name`, `status`, `user_phone`, `user_address`) VALUES (9, 12, '小邓', 'http://localhost:16667/file/a126f51ebdce41bb955951a82f78ecb3.jpeg', '2024-12-16 15:10:23', '我会每天喂很多好吃的给它', 9, '八戒', 2, '176666666', '萨达萨达');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='宠物明星值管理';

-- ----------------------------
-- Records of pet_star
-- ----------------------------
BEGIN;
INSERT INTO `pet_star` (`id`, `star_num`, `pet_center_id`, `pet_name`) VALUES (1, 10, 8, '贝壳');
INSERT INTO `pet_star` (`id`, `star_num`, `pet_center_id`, `pet_name`) VALUES (2, 24, 7, '靓仔');
INSERT INTO `pet_star` (`id`, `star_num`, `pet_center_id`, `pet_name`) VALUES (3, 22, 6, '毛坨');
INSERT INTO `pet_star` (`id`, `star_num`, `pet_center_id`, `pet_name`) VALUES (4, 2, 2, '黑蛋');
INSERT INTO `pet_star` (`id`, `star_num`, `pet_center_id`, `pet_name`) VALUES (5, 1, 4, '小米粒');
INSERT INTO `pet_star` (`id`, `star_num`, `pet_center_id`, `pet_name`) VALUES (6, 23, 5, '二哈');
INSERT INTO `pet_star` (`id`, `star_num`, `pet_center_id`, `pet_name`) VALUES (7, 1, 3, '毛球');
INSERT INTO `pet_star` (`id`, `star_num`, `pet_center_id`, `pet_name`) VALUES (8, 25, 9, '八戒');
INSERT INTO `pet_star` (`id`, `star_num`, `pet_center_id`, `pet_name`) VALUES (9, 5, 18, '八戒');
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
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发布标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='宠物圈帖子管理';

-- ----------------------------
-- Records of post_code
-- ----------------------------
BEGIN;
INSERT INTO `post_code` (`id`, `content`, `create_time`, `image_url_list`, `user_id`, `user_name`, `user_image`, `title`) VALUES (3, '岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，岁月难得成末，', '2024-12-13 14:16:18', '/file/6edcb701aebc4b81bdcd9dfdcf7a5097.webp,/file/f61413a4b6784b70a38a5f33715f9478.webp', 12, '小邓', 'http://localhost:16667/file/d2fb1907090b429199a7a10f39beb3bb.jpeg', 'xxx');
INSERT INTO `post_code` (`id`, `content`, `create_time`, `image_url_list`, `user_id`, `user_name`, `user_image`, `title`) VALUES (8, '99999', '2024-12-16 14:59:52', '/file/6bade9b80042465786c1372a36f3a1c4.jpeg,/file/e14bf93fe8a748129fc9c932de142d9c.jpeg', 12, '小邓', 'http://localhost:16667/file/a126f51ebdce41bb955951a82f78ecb3.jpeg', NULL);
INSERT INTO `post_code` (`id`, `content`, `create_time`, `image_url_list`, `user_id`, `user_name`, `user_image`, `title`) VALUES (9, 'asdadasdasdasd', '2024-12-20 15:24:25', '/file/f025434cd8164cf3ac54a45e9754ab88.jpeg', 12, '小邓', 'http://localhost:16667/file/a126f51ebdce41bb955951a82f78ecb3.jpeg', NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户管理';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `user_name`, `password`, `nick_name`, `email`, `age`, `phone`, `image_url`, `role`, `apply_status`) VALUES (7, '小维子', 'e10adc3949ba59abbe56e057f20f883e', '小维子1', '2805487767@qq.com', 16, '17608411971', 'http://localhost:16667/file/509eb96fd00648febe7ff17e64778975.jpeg', 'ROLE_ADMIN', 0);
INSERT INTO `user` (`id`, `user_name`, `password`, `nick_name`, `email`, `age`, `phone`, `image_url`, `role`, `apply_status`) VALUES (11, '汤维', 'e10adc3949ba59abbe56e057f20f883e', '汤维🔥', '2805487766@qq.com', 30, '17608411979', 'http://localhost:9706/file/6f77801aca8f45678d2f337a6a7dbe3b.jpeg', 'ROLE_USER', 0);
INSERT INTO `user` (`id`, `user_name`, `password`, `nick_name`, `email`, `age`, `phone`, `image_url`, `role`, `apply_status`) VALUES (12, '小邓', '202cb962ac59075b964b07152d234b70', '小邓', '1057513859@qq.com', 20, '122222211', 'http://localhost:16667/file/a126f51ebdce41bb955951a82f78ecb3.jpeg', 'ROLE_USER', 0);
INSERT INTO `user` (`id`, `user_name`, `password`, `nick_name`, `email`, `age`, `phone`, `image_url`, `role`, `apply_status`) VALUES (13, '小汤', 'e10adc3949ba59abbe56e057f20f883e', '小汤', '22222@qq.com', NULL, NULL, 'http://localhost:16667/file/8a4885ea7ed54b4dbb5f5864637c0ab5.jpeg', 'ROLE_USER', 0);
INSERT INTO `user` (`id`, `user_name`, `password`, `nick_name`, `email`, `age`, `phone`, `image_url`, `role`, `apply_status`) VALUES (14, '小邓', 'e10adc3949ba59abbe56e057f20f883e', '小邓', 'qwe', NULL, 'qwe', 'http://localhost:16667/file/a126f51ebdce41bb955951a82f78ecb3.jpeg', 'ROLE_USER', 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=454 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户令牌管理';

-- ----------------------------
-- Records of user_token
-- ----------------------------
BEGIN;
INSERT INTO `user_token` (`id`, `token`, `user_id`, `overdue`, `create_time`) VALUES (445, '2ee079abfa6f113b9f072d900985d7e0', 13, -1, '2024-12-16 15:15:18');
INSERT INTO `user_token` (`id`, `token`, `user_id`, `overdue`, `create_time`) VALUES (451, '1c549034cbc2e20edb6ae95f5734e176', 7, -1, '2024-12-19 10:55:41');
INSERT INTO `user_token` (`id`, `token`, `user_id`, `overdue`, `create_time`) VALUES (453, '82043b0198ee847799be4314b4066586', 12, -1, '2024-12-20 15:17:38');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
