/*
Navicat MySQL Data Transfer

Source Server         : 20190424
Source Server Version : 50717
Source Host           : localhost:3307
Source Database       : shopping

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-11-26 20:11:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ju_cart
-- ----------------------------
DROP TABLE IF EXISTS `ju_cart`;
CREATE TABLE `ju_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `quantity` int(11) NOT NULL COMMENT '购买数量',
  `checked` int(4) DEFAULT '1' COMMENT '1:选中 0:未选中',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ju_cart
-- ----------------------------

-- ----------------------------
-- Table structure for ju_category
-- ----------------------------
DROP TABLE IF EXISTS `ju_category`;
CREATE TABLE `ju_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父类id',
  `name` varchar(50) NOT NULL COMMENT '类别名称',
  `status` int(4) DEFAULT '1' COMMENT '类别状态 1:正常 0:废弃',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_leaf` tinyint(1) NOT NULL COMMENT '判断是否是叶子节点 是0 如果不是为1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ju_category
-- ----------------------------
INSERT INTO `ju_category` VALUES ('1', '0', '家用电器', '1', '2019-06-24 14:12:17', '2019-11-26 11:33:13', '1');
INSERT INTO `ju_category` VALUES ('2', '0', '移动电话', '1', '2019-06-24 14:12:34', '2019-11-26 11:29:21', '1');
INSERT INTO `ju_category` VALUES ('3', '0', '电脑', '1', '2019-06-24 14:12:48', '2019-06-24 14:12:50', '1');
INSERT INTO `ju_category` VALUES ('4', '1', '电视', '1', '2019-06-24 14:13:23', '2019-06-24 14:13:25', '1');
INSERT INTO `ju_category` VALUES ('5', '4', '超薄电视', '1', '2019-06-24 14:14:07', '2019-06-24 14:14:09', '0');
INSERT INTO `ju_category` VALUES ('7', '4', '全面屏电视', '1', '2019-06-24 16:11:34', '2019-06-24 16:11:34', '0');

-- ----------------------------
-- Table structure for ju_order
-- ----------------------------
DROP TABLE IF EXISTS `ju_order`;
CREATE TABLE `ju_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id,主键',
  `order_no` bigint(20) NOT NULL COMMENT '订单编号',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `payment` decimal(20,2) NOT NULL COMMENT '付款总金额，单位元，保留两位小数',
  `payment_type` int(4) NOT NULL DEFAULT '1' COMMENT '支付方式 1:线上支付 ',
  `status` int(10) NOT NULL COMMENT '订单状态 0-已取消  10-未付款 20-已付款 30-已发货 40-已完成  50-已关闭',
  `shipping_id` int(11) NOT NULL COMMENT '收货地址id',
  `postage` int(10) NOT NULL DEFAULT '0' COMMENT '运费',
  `payment_time` datetime DEFAULT NULL COMMENT '已付款时间',
  `send_time` datetime DEFAULT NULL COMMENT '已发货时间',
  `close_time` datetime DEFAULT NULL COMMENT '已关闭时间',
  `end_time` datetime DEFAULT NULL COMMENT '已结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '已创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no_index` (`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ju_order
-- ----------------------------
INSERT INTO `ju_order` VALUES ('1', '1562226413272', '2', '2000.00', '1', '0', '1', '0', null, null, null, null, '2019-07-04 15:46:49', null);
INSERT INTO `ju_order` VALUES ('2', '1562226633094', '2', '100.00', '1', '10', '1', '0', null, null, null, null, '2019-07-04 15:48:53', null);
INSERT INTO `ju_order` VALUES ('3', '1562226733361', '2', '50000.00', '1', '20', '1', '0', '2019-07-09 10:04:31', null, null, null, '2019-07-04 15:50:53', null);
INSERT INTO `ju_order` VALUES ('4', '1562226823047', '3', '50602000.00', '1', '10', '1', '0', null, null, null, null, '2019-07-04 15:52:04', null);

-- ----------------------------
-- Table structure for ju_order_item
-- ----------------------------
DROP TABLE IF EXISTS `ju_order_item`;
CREATE TABLE `ju_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单明细id,主键',
  `order_no` bigint(20) NOT NULL COMMENT '订单编号',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `product_name` varchar(100) NOT NULL COMMENT '商品名称',
  `product_image` varchar(100) DEFAULT NULL COMMENT '商品主图',
  `current_unit_price` decimal(20,2) NOT NULL COMMENT '下单时商品的价格，元为单位，保留两位小数',
  `quantity` int(10) NOT NULL COMMENT '商品的购买数量',
  `total_price` decimal(20,2) NOT NULL COMMENT '商品的总价格，元为单位，保留两位小数',
  `create_time` datetime DEFAULT NULL COMMENT '已创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `order_no_index` (`order_no`) USING BTREE,
  KEY `order_no_user_id_index` (`order_no`,`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ju_order_item
-- ----------------------------
INSERT INTO `ju_order_item` VALUES ('1', '1562226823047', '1', '1', '奥迪', '1.jpg', '100000.00', '6', '600000.00', '2019-07-04 15:52:04', '2019-07-04 15:52:04');
INSERT INTO `ju_order_item` VALUES ('2', '1562226823047', '1', '2', '红旗', '5.jpg', '5000000.00', '10', '50000000.00', '2019-07-04 15:52:04', '2019-07-04 15:52:04');
INSERT INTO `ju_order_item` VALUES ('3', '1562226823047', '1', '5', '奥拓', '8.jpg', '2000.00', '1', '2000.00', '2019-07-04 15:52:04', '2019-07-04 15:52:04');

-- ----------------------------
-- Table structure for ju_payinfo
-- ----------------------------
DROP TABLE IF EXISTS `ju_payinfo`;
CREATE TABLE `ju_payinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` bigint(20) NOT NULL COMMENT '订单编号',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `pay_platform` int(4) NOT NULL DEFAULT '1' COMMENT '1:支付宝 2:微信',
  `platform_status` varchar(50) DEFAULT NULL COMMENT '支付状态',
  `platform_number` varchar(100) DEFAULT NULL COMMENT '流水号',
  `create_time` datetime DEFAULT NULL COMMENT '已创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ju_payinfo
-- ----------------------------
INSERT INTO `ju_payinfo` VALUES ('1', '1562226733361', '1', '1', 'TRADE_SUCCESS', '2019070922001447981000054512', '2019-07-09 13:13:50', null);

-- ----------------------------
-- Table structure for ju_product
-- ----------------------------
DROP TABLE IF EXISTS `ju_product`;
CREATE TABLE `ju_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `category_id` int(11) NOT NULL COMMENT '商品所属的类别id,值引用类别表的id',
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `detail` text COMMENT '商品详情',
  `subtitle` varchar(200) DEFAULT NULL COMMENT '商品副标题',
  `main_image` varchar(100) DEFAULT NULL COMMENT '商品主图',
  `sub_images` varchar(200) DEFAULT NULL COMMENT '商品子图',
  `price` decimal(20,2) NOT NULL COMMENT '商品价格,总共20位，小数2位，整数18位',
  `stock` int(11) DEFAULT NULL COMMENT '商品库存',
  `status` int(6) DEFAULT '1' COMMENT '商品状态 1:在售 2:下架 3:删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `name_index` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ju_product
-- ----------------------------
INSERT INTO `ju_product` VALUES ('1', '1', '奥迪', '真便宜', '真酷', '1.jpg', '1.jpg,2.jpg', '100000.00', '94', '1', '2019-06-26 09:59:49', '2019-07-04 15:52:04');
INSERT INTO `ju_product` VALUES ('2', '2', '红旗', '太帅了', '你买不起', '5.jpg', '1.jpg,2.jpg', '5000000.00', '9990', '2', '2019-06-27 09:31:43', '2019-07-04 15:52:04');
INSERT INTO `ju_product` VALUES ('3', '3', '五菱', '真结实', '满大街跑', '6.jpg', '1.jpg,2.jpg', '10000.00', '100', '1', '2019-06-27 09:32:32', '2019-06-27 09:32:34');
INSERT INTO `ju_product` VALUES ('4', '4', '夏利', '真好看', '限量', '7.jpg', '1.jpg', '10000.00', '300', '1', '2019-06-27 09:33:10', '2019-06-27 09:33:12');
INSERT INTO `ju_product` VALUES ('5', '5', '奥拓', '真带劲', '太晓了', '8.jpg', '1.jpg', '2000.00', '2999', '1', '2019-06-27 10:21:59', '2019-07-04 15:52:04');
INSERT INTO `ju_product` VALUES ('7', '6', '宝马', '没什么好的', '别看了 看奔驰去吧', '1569655406870宝马31.jpg', null, '600000.10', '500', '1', '2019-09-28 15:23:27', '2019-09-28 15:23:27');
INSERT INTO `ju_product` VALUES ('8', '12', 'emm', '老师，郭子健上课玩', '手机', '1569655469600emm786.jpg', null, '1.10', '1', '1', '2019-09-28 15:24:29', '2019-09-28 15:24:29');
INSERT INTO `ju_product` VALUES ('9', '7', '野生奥特曼', '纯野生奥特曼 ', '昨天刚抓到的', '1569655482797野生奥特曼744.jpg', null, '99999999.10', '1', '1', '2019-09-28 15:24:42', '2019-09-28 15:24:42');
INSERT INTO `ju_product` VALUES ('10', '8', '二手航空母舰', '二手航空母舰原厂原漆', '送3年保养', '1569656279304二手航空母舰95.jpg', null, '999999999.10', '1', '1', '2019-09-28 15:37:59', '2019-09-28 15:37:59');
INSERT INTO `ju_product` VALUES ('11', '6', '奔驰', '没什么好的', '别看了 看奔驰去吧', '1570589119798奔驰657.svg', null, '500.10', '500', '1', '2019-10-09 10:45:19', '2019-10-09 10:45:19');
INSERT INTO `ju_product` VALUES ('12', '8', '孤儿', '专抢adc 又不会输', '孤儿ad', '1570589120098孤儿637.jpg', null, '10.10', '10', '1', '2019-10-09 10:45:20', '2019-10-09 10:45:20');
INSERT INTO `ju_product` VALUES ('13', '16', '五粮液', '很好喝', '喝了酒上瘾', '1570589135570五粮液186.jpg', null, '121.10', '12', '1', '2019-10-09 10:45:35', '2019-10-09 10:45:35');
INSERT INTO `ju_product` VALUES ('14', '2', '郭子健', '上课', '玩手机', '1570589238191郭子健757.jpg', null, '2.10', '2', '3', '2019-10-09 10:47:18', '2019-10-09 10:47:18');
INSERT INTO `ju_product` VALUES ('15', '6', '李白', '打野李白', '李白贼溜', '1570589263311李白385.png', null, '5555555555.10', '3', '1', '2019-10-09 10:47:43', '2019-10-09 10:47:43');
INSERT INTO `ju_product` VALUES ('16', '1', '妈妈我想吃烤山药', '吃，吃大块儿的', '谢谢妈妈，妈妈真好', '1570589294340妈妈我想吃烤山药953.jpg', null, '5.10', '999', '1', '2019-10-09 10:48:14', '2019-10-09 10:48:14');
INSERT INTO `ju_product` VALUES ('17', '2', '跨6出金币', '1：65出', '需要的联系', '1570606973193跨6出金币584.jpg', null, '9999999999999999.99', '1', '1', '2019-10-09 15:42:53', '2019-10-09 15:42:53');
INSERT INTO `ju_product` VALUES ('18', '6324', '孙笑川', '人人都4孙笑川', '@带带大师兄', '1570608184235孙笑川134.jpg', null, '99.10', '1', '2', '2019-10-09 16:03:04', '2019-10-09 16:03:04');

-- ----------------------------
-- Table structure for ju_shipping
-- ----------------------------
DROP TABLE IF EXISTS `ju_shipping`;
CREATE TABLE `ju_shipping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `receiver_name` varchar(20) DEFAULT NULL COMMENT '收货姓名',
  `receiver_phone` varchar(20) DEFAULT NULL COMMENT '收货固定电话',
  `receiver_mobile` varchar(20) DEFAULT NULL COMMENT '收货移动电话',
  `receiver_province` varchar(20) DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(20) DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `receiver_zip` varchar(6) DEFAULT NULL COMMENT '邮编',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ju_shipping
-- ----------------------------
INSERT INTO `ju_shipping` VALUES ('1', '1', '白展堂', '0225987456', '18888888888', '天津', '天津', '空港', '西七道', '300000', '2019-07-03 09:30:49', '2019-07-03 09:30:49');
INSERT INTO `ju_shipping` VALUES ('2', '1', '铜掌柜', '0225987456', '18888888889', '天津', '天津', '空港', '西七道同福客栈', '300000', '2019-07-03 09:31:30', '2019-07-03 09:31:30');
INSERT INTO `ju_shipping` VALUES ('3', '1', '郭芙蓉', '0225987456', '18888888889', '天津', '天津', '空港', '西七道同福客栈', '300000', '2019-07-03 09:33:32', '2019-07-03 09:33:32');
INSERT INTO `ju_shipping` VALUES ('4', '1', '吕秀才', '0225987456', '18888888889', '天津', '天津', '空港', '西七道同福客栈', '300000', '2019-07-03 09:33:45', '2019-07-03 09:33:45');

-- ----------------------------
-- Table structure for ju_user
-- ----------------------------
DROP TABLE IF EXISTS `ju_user`;
CREATE TABLE `ju_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `phone` varchar(11) NOT NULL COMMENT '联系方式',
  `question` varchar(100) NOT NULL COMMENT '密保问题',
  `answer` varchar(100) NOT NULL COMMENT '答案',
  `role` int(4) NOT NULL DEFAULT '0' COMMENT '用户角色',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_index` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ju_user
-- ----------------------------
INSERT INTO `ju_user` VALUES ('2', '李四', '123456', '2121@qq.com', 'sdadasasd', 'dsaasdsd', 'sdaasdads', '0', '2019-06-21 10:03:36', '2019-06-21 10:03:36');
INSERT INTO `ju_user` VALUES ('3', '赵六', 'E10ADC3949BA59ABBE56E057F20F883E', '2121212@qq.com', 'sdadasasd', 'dsaasdsd', 'sdaasdads', '0', '2019-06-21 10:24:53', '2019-06-21 10:24:53');
INSERT INTO `ju_user` VALUES ('4', 'xiaoming', '123456', '123@qq.com', '18258888888', '你的家乡在哪', '黑龙江', '0', '2019-10-14 15:03:00', '2019-10-14 15:03:00');
INSERT INTO `ju_user` VALUES ('5', 'xiaohong', '123456', '123@qq.com', '18258888888', '你的家乡在哪', '吉林', '1', '2019-10-14 15:05:46', '2019-10-14 15:05:46');
INSERT INTO `ju_user` VALUES ('8', '赵七', '123456', '1478521445@qq.com', '14785247845', '你的家乡在哪里', '在东北', '0', '2019-10-15 16:25:43', '2019-10-15 16:25:43');
INSERT INTO `ju_user` VALUES ('9', '赵八', '123456', '45454@qq.com', '12345677897', '你的家乡在哪里', '在东北', '1', '2019-10-15 16:34:09', '2019-10-15 16:34:09');
INSERT INTO `ju_user` VALUES ('10', '赵九', '123456', '1478521445@qq.com', '14785247845', '你的家乡在哪里', '在东北', '0', '2019-10-15 16:36:11', '2019-10-15 16:36:11');
