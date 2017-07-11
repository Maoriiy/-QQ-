/*
Navicat MySQL Data Transfer

Source Server         : jspkeshe
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : jspkeshe

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-07-06 21:36:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `userAccount` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `message` varchar(30) NOT NULL,
  `stateID` int(10) unsigned NOT NULL,
  `time` time(6) NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userAccount` (`userAccount`),
  KEY `stateID` (`stateID`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`userAccount`) REFERENCES `users` (`account`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`stateID`) REFERENCES `dynamic_state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('10039', '1', '有毒啊你', '1', '16:05:30.000000', '2017-07-05');
INSERT INTO `comment` VALUES ('10001', '21', '我最喜欢喝可乐了', '58', '21:16:02.000000', '2017-07-06');

-- ----------------------------
-- Table structure for dynamic_state
-- ----------------------------
DROP TABLE IF EXISTS `dynamic_state`;
CREATE TABLE `dynamic_state` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `time` time(6) NOT NULL,
  `message` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `isImage` int(1) NOT NULL DEFAULT '0',
  `publisher` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `publisher` (`publisher`),
  CONSTRAINT `dynamic_state_ibfk_1` FOREIGN KEY (`publisher`) REFERENCES `users` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dynamic_state
-- ----------------------------
INSERT INTO `dynamic_state` VALUES ('1', '2017-06-30', '19:54:20.000000', '深夜报复社会', '903b4728gy1fh74i6miwoj20go0ak0uj.jpg', '1', '10001');
INSERT INTO `dynamic_state` VALUES ('2', '2017-07-04', '11:05:03.000000', '差不多就是一只废喵了', '9bd522c1gy1fh71m4pjgjg20970ad4qp.gif', '1', '10039');
INSERT INTO `dynamic_state` VALUES ('33', '2017-07-06', '18:51:35.000000', 'hahaha', null, '0', '10043');
INSERT INTO `dynamic_state` VALUES ('58', '2017-07-06', '21:12:24.000000', '画得好厉害啊', '9bd522c1gy1fhaaf9xiw1g20cw07knpe.gif', '1', '10040');

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `userAccount` int(10) unsigned NOT NULL,
  `commentID` int(10) unsigned NOT NULL,
  `message` varchar(30) NOT NULL,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `time` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userAccount` (`userAccount`),
  KEY `commentID` (`commentID`),
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`userAccount`) REFERENCES `users` (`account`),
  CONSTRAINT `reply_ibfk_2` FOREIGN KEY (`commentID`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------

-- ----------------------------
-- Table structure for star
-- ----------------------------
DROP TABLE IF EXISTS `star`;
CREATE TABLE `star` (
  `account` int(10) unsigned NOT NULL,
  `stateID` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `account` (`account`),
  KEY `stateID` (`stateID`),
  CONSTRAINT `star_ibfk_1` FOREIGN KEY (`account`) REFERENCES `users` (`account`),
  CONSTRAINT `star_ibfk_2` FOREIGN KEY (`stateID`) REFERENCES `dynamic_state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of star
-- ----------------------------
INSERT INTO `star` VALUES ('10001', '1', '1');
INSERT INTO `star` VALUES ('10039', '2', '3');
INSERT INTO `star` VALUES ('10001', '2', '4');
INSERT INTO `star` VALUES ('10039', '1', '5');
INSERT INTO `star` VALUES ('10046', '33', '55');
INSERT INTO `star` VALUES ('10040', '58', '63');
INSERT INTO `star` VALUES ('10040', '2', '64');
INSERT INTO `star` VALUES ('10001', '58', '65');
INSERT INTO `star` VALUES ('10042', '58', '66');
INSERT INTO `star` VALUES ('10042', '33', '67');
INSERT INTO `star` VALUES ('10042', '2', '68');
INSERT INTO `star` VALUES ('10042', '1', '69');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(20) NOT NULL,
  `account` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(10) NOT NULL,
  `icon` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=10048 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('超级管理员', '110', '110', '8cfb14c2d8ae5cd4e3313deeff079d31.png');
INSERT INTO `users` VALUES ('王大同', '10001', '123', '123.png');
INSERT INTO `users` VALUES ('不正常人类研究中心', '10039', 'W123...', '9bd522c1jw8f11c33g8ztj208c08cmxs.jpg');
INSERT INTO `users` VALUES ('马大头', '10040', 'wangY.', 'IMG_0968.JPG');
INSERT INTO `users` VALUES ('dwwwww', '10041', '@Aa123', 'IMG_0983.JPG');
INSERT INTO `users` VALUES ('asdasdad', '10042', 'wangY.', 'IMG_0788.JPG');
INSERT INTO `users` VALUES ('myq', '10043', 'Czg4545', 'IMG_0788.JPG');
INSERT INTO `users` VALUES ('afyfuyg', '10044', '12aS!', 'IMG_0809.JPG');
INSERT INTO `users` VALUES ('123a', '10045', 'aaaAAA`', 'IMG_0810.JPG');
INSERT INTO `users` VALUES ('q10002', '10046', '123.wW', null);
INSERT INTO `users` VALUES ('wang', '10047', 'Ww@#$', 'IMG_0885.GIF');
