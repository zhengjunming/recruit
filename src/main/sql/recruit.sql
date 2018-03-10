/*
Navicat MySQL Data Transfer

Source Server         : 小铭
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : recruit

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2018-03-10 09:40:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ip_black_list
-- ----------------------------
DROP TABLE IF EXISTS `ip_black_list`;
CREATE TABLE `ip_black_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(32) NOT NULL,
  `ip_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '报名表ID',
  `name` varchar(15) NOT NULL COMMENT '学生姓名',
  `student_id` varchar(11) NOT NULL COMMENT '学号',
  `sex` tinyint(2) NOT NULL COMMENT '性别',
  `grade` varchar(8) NOT NULL COMMENT '年级',
  `a_class` varchar(20) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `gpa` double(4,2) NOT NULL COMMENT '绩点',
  `fail` tinyint(2) NOT NULL COMMENT '是否挂科，1为挂科，0为没挂科',
  `c_score` int(4) NOT NULL COMMENT 'C语言理论成绩',
  `c_test_score` varchar(5) NOT NULL COMMENT 'C语言实验成绩',
  `en_score` int(4) NOT NULL COMMENT '英语成绩',
  `wish` tinyint(2) NOT NULL COMMENT '应征方向： 1前端,2后台,3嵌入式,4手游,5移动,6数据挖掘,7设计',
  `swap` tinyint(2) NOT NULL COMMENT '是否愿意服从应征方向调剂: int类型 1为服从 0为不服从',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=495 DEFAULT CHARSET=utf8 COMMENT='报名信息表';
