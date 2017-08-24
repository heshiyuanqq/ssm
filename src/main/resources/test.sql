/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.28 : Database - znxy
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`znxy` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `znxy`;

/*Table structure for table `t_learn_record` */

DROP TABLE IF EXISTS `t_learn_record`;

CREATE TABLE `t_learn_record` (
  `learn_record_id` int(11) NOT NULL AUTO_INCREMENT,
  `video_id` int(11) NOT NULL COMMENT '跟视屏表关联',
  `learntime` datetime DEFAULT NULL COMMENT '什么时间学习的',
  `createtime` datetime NOT NULL,
  `updatetime` datetime NOT NULL,
  PRIMARY KEY (`learn_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_learn_record` */

/*Table structure for table `t_mycolleage` */

DROP TABLE IF EXISTS `t_mycolleage`;

CREATE TABLE `t_mycolleage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '跟用户表关联，外键',
  `vcid` int(11) DEFAULT NULL COMMENT '与视屏收藏表关联 。外键',
  `v_note_id` int(11) DEFAULT NULL COMMENT '与视屏笔记表关联。外键',
  `order_id` int(11) DEFAULT NULL COMMENT '与订单表关联。外键',
  `review_id` int(11) DEFAULT NULL COMMENT '与评论表关联。外键',
  `createtime` datetime NOT NULL,
  `updatetime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_mycolleage` */

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `orderid` int(11) NOT NULL,
  `ordertime` datetime NOT NULL COMMENT '订单时间',
  `order_source` varchar(20) DEFAULT NULL COMMENT '订单来源，是指文库，学院，课题',
  `order_uid` int(11) DEFAULT NULL COMMENT '与用户表关联 ，外键',
  `order_status` tinyint(4) DEFAULT NULL COMMENT '订单状态。0：未支付，1：已支付，2：取消订单。',
  `order_price` int(11) DEFAULT NULL COMMENT '订单价格',
  `productname` varchar(30) DEFAULT NULL COMMENT '商品名称',
  `createtime` datetime NOT NULL,
  `updatetime` datetime NOT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

/*Table structure for table `t_users` */

DROP TABLE IF EXISTS `t_users`;

CREATE TABLE `t_users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `realname` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `marriage` tinyint(5) DEFAULT NULL COMMENT '婚姻状况。1：未婚，2：已婚，3，离异，4：单身，5恋爱',
  `sex` tinyint(2) DEFAULT NULL COMMENT '性别。1：男，2：女',
  `birthday` date DEFAULT NULL,
  `work` int(10) DEFAULT NULL COMMENT '工作职位 。1：广告，2：航天，3：农业，4：汽车，5：计算机，6：建筑，7：教育，8：能源，9：金融，10：政府，11：传媒，12：医疗，13：零售，14：服务，15：网络，16：旅游，17：其他，18：未知',
  `blood` tinyint(4) DEFAULT NULL COMMENT '血型。1：AB,2：A,3：B,4:O',
  `place` varchar(20) DEFAULT NULL,
  `native` varchar(20) DEFAULT NULL COMMENT '居住地',
  `shape` tinyint(8) DEFAULT NULL COMMENT '体型。1：健美，2：苗条，3：丰满，4：中等身材，5：高大，6：小巧，7：运动型，8：未知',
  `character` tinyint(10) DEFAULT NULL COMMENT '性格。1：温柔，2：粗犷，3：开朗，4：沉默，5：活泼，6：内向，7：稳重，8：急躁',
  `headimg` varchar(30) DEFAULT NULL COMMENT '用户头像。保存的url',
  `phone` int(11) DEFAULT NULL,
  `login_name` varchar(30) NOT NULL COMMENT '登录账号',
  `password` varchar(30) NOT NULL,
  `user_status` tinyint(3) DEFAULT NULL COMMENT '用户状态。1：注册过的用户，2：未注册过的用户',
  `login_time` datetime NOT NULL,
  `last_login_time` datetime NOT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `device` varchar(20) DEFAULT NULL,
  `updatetime` datetime NOT NULL COMMENT '更新时间 与业务无关',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_users` */

/*Table structure for table `t_video_category` */

DROP TABLE IF EXISTS `t_video_category`;

CREATE TABLE `t_video_category` (
  `video_category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '视屏类别名称',
  `video_category_name` varchar(32) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT '父类',
  `sort_id` int(11) DEFAULT NULL COMMENT '排序',
  `createtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updatetime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `is_parent` tinyint(1) DEFAULT '0' COMMENT '收否有子节点',
  PRIMARY KEY (`video_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

/*Data for the table `t_video_category` */

insert  into `t_video_category`(`video_category_id`,`video_category_name`,`parent_id`,`sort_id`,`createtime`,`updatetime`,`is_parent`) values (1,'家电',-1,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',1),(2,'蔬菜',-1,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',1),(3,'农作物',-1,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',1),(4,'手机',1,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',1),(5,'电脑',1,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',1),(6,'电视',1,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',1),(7,'黄瓜',2,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',0),(8,'茄子',2,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',0),(9,'西红柿',2,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',0),(10,'水稻',3,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',0),(11,'小麦',3,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',0),(12,'玉米',3,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',0),(45,'诺基亚',4,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',0),(46,'苹果',4,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',0),(47,'家具',-1,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',0),(48,'椅子',47,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',0),(49,'桌子',47,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',0),(50,'建筑',-1,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',0),(51,'别墅',50,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',0),(52,'公寓',50,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',0),(53,'小区',50,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',0),(54,'秋黄瓜',7,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',0),(55,'哈哈哈',-1,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',0);

/*Table structure for table `t_video_collect` */

DROP TABLE IF EXISTS `t_video_collect`;

CREATE TABLE `t_video_collect` (
  `video_collect_id` int(11) NOT NULL AUTO_INCREMENT,
  `video_id` int(11) NOT NULL COMMENT '跟视频表关联',
  `video_collect_count` int(11) DEFAULT NULL COMMENT '视屏收藏量',
  `video_collect_time` datetime DEFAULT NULL COMMENT '视屏收藏时间',
  `createtime` datetime NOT NULL,
  `updatetime` datetime NOT NULL,
  PRIMARY KEY (`video_collect_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_video_collect` */

/*Table structure for table `t_video_note` */

DROP TABLE IF EXISTS `t_video_note`;

CREATE TABLE `t_video_note` (
  `video_note_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '视屏笔记编号',
  `video_id` int(11) NOT NULL COMMENT '与视屏表关联',
  `video_note_content` varchar(100) DEFAULT NULL COMMENT '视屏笔记内容',
  `video_note_time` datetime DEFAULT NULL COMMENT '做笔记的时间',
  `createtime` datetime NOT NULL,
  `updatetime` datetime NOT NULL,
  PRIMARY KEY (`video_note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_video_note` */

/*Table structure for table `t_videos` */

DROP TABLE IF EXISTS `t_videos`;

CREATE TABLE `t_videos` (
  `videoid` char(36) NOT NULL,
  `vdeio_category` int(11) DEFAULT NULL COMMENT '视屏类别',
  `video_instructor` varchar(20) DEFAULT NULL COMMENT '视屏讲师',
  `video_title` varchar(50) NOT NULL COMMENT '视屏名称',
  `video_section_id` int(11) DEFAULT NULL COMMENT '视屏章节编号',
  `video_section_name` varchar(50) DEFAULT NULL COMMENT '视屏章节名称',
  `video_url` varchar(20) DEFAULT NULL COMMENT '视屏存放地址',
  `video_cover_url` varchar(20) DEFAULT NULL COMMENT '视屏封面地址',
  `video_cover_status` tinyint(4) DEFAULT NULL COMMENT '视屏封面状态。1：上架，2：下架',
  `video_introduction` varchar(100) DEFAULT NULL COMMENT '视屏简介',
  `video_price` int(11) DEFAULT NULL COMMENT '视屏价格',
  `loadtime` datetime DEFAULT NULL COMMENT '上传时间',
  `video_score` int(11) DEFAULT '100' COMMENT '视屏评分',
  `video_status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '视屏状态。1：上架，2：下架',
  `down_id` int(11) DEFAULT '0' COMMENT '用来统计下载量',
  `brow_id` int(11) DEFAULT '0' COMMENT '用来统计浏览量',
  `createtime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `updatetime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '更新时间',
  `video_free_duration` bigint(10) DEFAULT NULL COMMENT '免费观看时长(秒)',
  `video_duration` bigint(20) DEFAULT NULL COMMENT '视频时长(秒)',
  `prev_cut_video_id` char(36) DEFAULT NULL COMMENT '前面截取的短视频id',
  `prev_cut_video_url` varchar(100) DEFAULT NULL COMMENT '前面截取的短视频的url',
  `video_cover_id` char(36) DEFAULT NULL COMMENT '视频封面对应图片的id',
  PRIMARY KEY (`videoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_videos` */

insert  into `t_videos`(`videoid`,`vdeio_category`,`video_instructor`,`video_title`,`video_section_id`,`video_section_name`,`video_url`,`video_cover_url`,`video_cover_status`,`video_introduction`,`video_price`,`loadtime`,`video_score`,`video_status`,`down_id`,`brow_id`,`createtime`,`updatetime`,`video_free_duration`,`video_duration`,`prev_cut_video_id`,`prev_cut_video_url`,`video_cover_id`) values ('992eab05-feab-4ad2-a450-4c5070a376ec',NULL,NULL,'第30课 html5 canvas图像合成实例之随机闪烁的星星_标清.mp4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,100,1,0,0,'1000-01-01 00:00:00','1000-01-01 00:00:00',111,2688,'0237ca50-8fca-4a22-8852-fb64d8f6aba1',NULL,'205423cb-01ef-44e4-819e-d01f92142b6f'),('bb471331-a5d3-447a-a110-dde5bd3a9cb4',NULL,NULL,'美女舍宾教练_标清.mp4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,100,1,0,0,'1000-01-01 00:00:00','1000-01-01 00:00:00',111,693,'71b158a7-a3af-4d08-9e3b-447fa0805b49',NULL,'2b0b935b-a530-484d-9875-1648993867b1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
