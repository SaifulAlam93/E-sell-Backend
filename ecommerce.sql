/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.2.31-MariaDB : Database - my-ecommerce
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`my-ecommerce` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `my-ecommerce`;

/*Table structure for table `file_data` */

DROP TABLE IF EXISTS `file_data`;

CREATE TABLE `file_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `file_data` */

/*Table structure for table `file_upload` */

DROP TABLE IF EXISTS `file_upload`;

CREATE TABLE `file_upload` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` datetime NOT NULL,
  `image_no` int(11) DEFAULT NULL,
  `image_type` varchar(255) DEFAULT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

/*Data for the table `file_upload` */

insert  into `file_upload`(`id`,`date_created`,`image_no`,`image_type`,`last_updated`,`name`,`type`,`url`) values 
(1,'2023-05-25 10:09:49',0,'test','2023-05-25 10:09:49','bean-to-bar-chocolate.webp','image/webp','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/bean-to-bar-chocolate.webp'),
(2,'2023-05-25 10:09:49',1,'test','2023-05-25 10:09:49','dark_chocolate_1200x750-7c14e9eb43f38f61c7f66f2beb75903f.jpg','image/jpeg','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/dark_chocolate_1200x750-7c14e9eb43f38f61c7f66f2beb75903f.jpg'),
(3,'2023-05-25 10:09:49',2,'test','2023-05-25 10:09:49','sugar_free_milk_chocolate_bar_29619ac1-b14b-45cc-889e-a172f5ea10c8_grande.webp','image/webp','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/sugar_free_milk_chocolate_bar_29619ac1-b14b-45cc-889e-a172f5ea10c8_grande.webp'),
(4,'2023-05-25 10:14:59',0,'test','2023-05-25 10:14:59','cake1.jpg','image/jpeg','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/cake1.jpg'),
(5,'2023-05-25 10:14:59',1,'test','2023-05-25 10:14:59','cake2.jpg','image/jpeg','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/cake2.jpg'),
(6,'2023-05-25 10:14:59',2,'test','2023-05-25 10:14:59','cake3.jpg','image/jpeg','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/cake3.jpg'),
(7,'2023-05-25 10:56:09',0,'test','2023-05-25 10:56:09','ice-2.webp','image/webp','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/ice-2.webp'),
(8,'2023-05-25 10:56:31',0,'test','2023-05-25 10:56:31','ice-1.jpg','image/jpeg','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/ice-1.jpg'),
(9,'2023-05-25 10:56:45',0,'test','2023-05-25 10:56:45','ice-3.jpg','image/jpeg','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/ice-3.jpg'),
(10,'2023-05-27 05:48:20',0,'test','2023-05-27 05:48:20','cambg_7.jpg','image/jpeg','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/cambg_7.jpg'),
(11,'2023-05-27 05:49:15',0,'test','2023-05-27 05:49:15','cambg_7.jpg','image/jpeg','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/cambg_7.jpg'),
(12,'2023-05-28 05:07:48',0,'test','2023-05-28 05:07:48','Saiful.jpg','image/jpeg','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/Saiful.jpg'),
(13,'2023-05-28 05:14:30',0,'test','2023-05-28 05:14:30','Profile-Image.png','image/png','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/Profile-Image.png'),
(14,'2023-05-28 05:18:41',0,'test','2023-05-28 05:18:41','Profile-Image.png','image/png','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/Profile-Image.png'),
(15,'2023-05-28 05:20:57',0,'test','2023-05-28 05:20:57','saifulN.png','image/png','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/saifulN.png'),
(16,'2023-05-28 05:21:58',0,'test','2023-05-28 05:21:58','saifulN.png','image/png','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/saifulN.png'),
(17,'2023-05-28 05:24:11',0,'test','2023-05-28 05:24:11','saifulN.png','image/png','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/saifulN.png'),
(18,'2023-05-28 05:25:36',0,'test','2023-05-28 05:25:36','saifulN.png','image/png','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/saifulN.png'),
(19,'2023-05-28 06:38:17',0,'test','2023-05-28 06:38:17','dark_chocolate_1200x750-7c14e9eb43f38f61c7f66f2beb75903f.jpg','image/jpeg','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/dark_chocolate_1200x750-7c14e9eb43f38f61c7f66f2beb75903f.jpg'),
(20,'2023-05-28 06:38:20',1,'test','2023-05-28 06:38:20','ice-1.jpg','image/jpeg','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/ice-1.jpg'),
(21,'2023-05-28 06:38:20',2,'test','2023-05-28 06:38:20','ice-3.jpg','image/jpeg','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/ice-3.jpg'),
(22,'2023-05-28 06:38:20',3,'test','2023-05-28 06:38:20','cake2.jpg','image/jpeg','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/cake2.jpg'),
(23,'2023-05-29 06:03:51',0,'test','2023-05-29 06:03:51','abc.jpg','image/jpeg','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/abc.jpg'),
(24,'2023-05-29 06:03:51',1,'test','2023-05-29 06:03:51','cambg_1.jpg','image/jpeg','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/cambg_1.jpg'),
(25,'2023-05-29 06:03:51',2,'test','2023-05-29 06:03:51','cambg_3.jpg','image/jpeg','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/cambg_3.jpg'),
(26,'2023-05-29 06:03:51',3,'test','2023-05-29 06:03:51','cambg_7.jpg','image/jpeg','I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/cambg_7.jpg');

/*Table structure for table `image_data` */

DROP TABLE IF EXISTS `image_data`;

CREATE TABLE `image_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `imagedata` blob DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `image_data` */

/*Table structure for table `order_details` */

DROP TABLE IF EXISTS `order_details`;

CREATE TABLE `order_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `price` double DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `orders_id` bigint(20) DEFAULT NULL,
  `products_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKint27bl8qoql1ksaw8ik7cq95` (`orders_id`),
  KEY `FK2h8sqod2uhp3381vnr8f9unyj` (`products_id`),
  CONSTRAINT `FK2h8sqod2uhp3381vnr8f9unyj` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKint27bl8qoql1ksaw8ik7cq95` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `order_details` */

insert  into `order_details`(`id`,`date_created`,`last_updated`,`price`,`quantity`,`orders_id`,`products_id`) values 
(1,'2024-03-10 06:41:02','2024-03-10 06:41:02',900,1,1,5),
(2,'2024-03-10 06:41:02','2024-03-10 06:41:02',900,1,1,4);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` datetime NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `house_no` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `last_updated` datetime NOT NULL,
  `order_address1` varchar(255) DEFAULT NULL,
  `order_address2` varchar(255) DEFAULT NULL,
  `order_city` varchar(255) DEFAULT NULL,
  `order_state` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `shipping` int(11) DEFAULT NULL,
  `tax` int(11) DEFAULT NULL,
  `total_price` int(11) DEFAULT NULL,
  `total_product_amount` int(11) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKge89vdw8aj1myh6ax2ys6vmbm` (`user_id`),
  CONSTRAINT `FKge89vdw8aj1myh6ax2ys6vmbm` FOREIGN KEY (`user_id`) REFERENCES `user01` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `orders` */

insert  into `orders`(`id`,`date_created`,`email`,`first_name`,`house_no`,`last_name`,`last_updated`,`order_address1`,`order_address2`,`order_city`,`order_state`,`phone`,`shipping`,`tax`,`total_price`,`total_product_amount`,`zip`,`user_id`) values 
(1,'2024-03-10 06:41:02','gmsifulalam93@gmail.com','gmsifulalam93@gmail.com','fgfg','gmsifulalam93@gmail.com','2024-03-10 06:41:02','gmsifulalam93@gmail.com','Dhaka','Dhaka.','','+8801712922516',180,90,2070,2,'1230','aaa');

/*Table structure for table `product_category` */

DROP TABLE IF EXISTS `product_category`;

CREATE TABLE `product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_description` varchar(255) DEFAULT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `product_category` */

insert  into `product_category`(`id`,`category_description`,`category_name`,`date_created`,`last_updated`) values 
(1,'electronics','ELECTRONICS','2023-05-27 05:34:45','2023-05-27 05:34:45'),
(2,'electronics','Food','2023-05-27 05:37:56','2023-05-27 05:37:56'),
(3,'Fashion Items','Fashion','2023-05-29 05:17:49','2023-05-29 05:17:49'),
(4,'Apparel Items','Apparel','2023-05-29 05:18:10','2023-05-29 05:18:10'),
(5,'Furniture Items','Furniture','2023-05-29 05:18:23','2023-05-29 05:18:23'),
(6,'Jewelry Items','Jewelry','2023-05-29 05:18:49','2023-05-29 05:18:49'),
(7,'Shoes Items','Shoes','2023-05-29 05:19:19','2023-05-29 05:19:19'),
(8,'Grocery store Items','Grocery store','2023-05-29 05:19:36','2023-05-29 05:19:36'),
(9,'Pet Items','Pet','2023-05-29 05:20:08','2023-05-29 05:20:08');

/*Table structure for table `product_file` */

DROP TABLE IF EXISTS `product_file`;

CREATE TABLE `product_file` (
  `products_id` bigint(20) NOT NULL,
  `file_upload_id` bigint(20) NOT NULL,
  PRIMARY KEY (`products_id`,`file_upload_id`),
  KEY `FK2souytmrrvieng59d3uiaqwit` (`file_upload_id`),
  CONSTRAINT `FK2souytmrrvieng59d3uiaqwit` FOREIGN KEY (`file_upload_id`) REFERENCES `file_upload` (`id`),
  CONSTRAINT `FKgr1kjrusx5ribv378f17o7fox` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `product_file` */

insert  into `product_file`(`products_id`,`file_upload_id`) values 
(1,1),
(1,2),
(1,3),
(2,4),
(2,5),
(2,6),
(3,7),
(4,8),
(5,9),
(6,10),
(7,18),
(13,23),
(13,24),
(13,25),
(13,26);

/*Table structure for table `product_option` */

DROP TABLE IF EXISTS `product_option`;

CREATE TABLE `product_option` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `color` varchar(255) DEFAULT NULL,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `products_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4aerif52bya3poa29ysm5lh0e` (`products_id`),
  CONSTRAINT `FK4aerif52bya3poa29ysm5lh0e` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `product_option` */

insert  into `product_option`(`id`,`color`,`date_created`,`last_updated`,`quantity`,`size`,`products_id`) values 
(7,'green','2023-05-29 06:02:06','2023-05-29 06:02:06',50,'l',13),
(8,'red','2023-05-29 06:02:06','2023-05-29 06:02:06',50,'m',13);

/*Table structure for table `products` */

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `current_stock` int(11) DEFAULT NULL,
  `date_created` datetime NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `last_updated` datetime NOT NULL,
  `long_desc` longtext DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `short_desc` varchar(255) DEFAULT NULL,
  `total_quantity` int(11) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  `unit_weight` int(11) DEFAULT NULL,
  `product_category_id` bigint(20) DEFAULT NULL,
  `unit_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb74tk5jq8jwurq34dks9agydq` (`product_category_id`),
  CONSTRAINT `FKb74tk5jq8jwurq34dks9agydq` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `products` */

insert  into `products`(`id`,`current_stock`,`date_created`,`image`,`last_updated`,`long_desc`,`product_name`,`short_desc`,`total_quantity`,`unit_price`,`unit_weight`,`product_category_id`,`unit_type`) values 
(1,300,'2023-05-25 10:09:49',NULL,'2023-05-25 10:09:49','Dark chocolate is rich in disease-fighting antioxidants. Studies show it can help reduce blood pressure and lower the risk of heart disease.\nStudies show that dark chocolate — when it is not loaded with sugar and saturated fat — is indeed a heart-healthy chocolate treat and more.\n“Eating chocolate is healthy when it is dark chocolate,” says Poulina Uddin, MD, a cardiologist at Scripps Clinic. “Studies show that eating a small amount of dark chocolate regularly can benefit your health.','Dark Chocolate','In moderation, dark chocolate is a heart-healthy chocolate',300,400,100,2,NULL),
(2,100,'2023-05-25 10:14:59',NULL,'2023-05-25 10:14:59','This no-bake easy chocolate cheesecake is a stunner and wonderfully rich recipe, so it will serve a ...\nIngredients\n200g/7oz chocolate digestive biscuits, crushed to fine crumbs\n75g/3oz butter, melted and cooled slightly\n800g/1lb 12oz cream cheese\nicing sugar, to taste','chocolate cake','Nothing beats a classic chocolate cake recipe except, well, maybe these rich chocolate creations.',100,900,400,1,NULL),
(3,100,'2023-05-25 10:56:09',NULL,'2023-05-25 10:56:09','Ingredients for Pistachio Ice Cream:\nEgg yolks – are essential for the soft and creamy texture in the ice cream and added thickness.\nGranulated sugar\nHeavy cream and evaporated milk – the cream and evaporated milk give the ice creamy it’s soft, creamy taste and texture. You can replace the evaporated milk with whole milk (or 2%, but it won be as creamy).\nSalt – to bring out flavors.\nVanilla extract and almond extract – the best highlight flavors with pistachio.\nWhole shelled pistachios – I like to buy them already shelled. We use half of the pistachios to flavor and color the simmering milk, and then stir pistachio pieces into the ice cream at the end.','Homemade Vanilla Ice Cream','This easy homemade Pistachio Ice Cream recipe is luxuriously creamy with the perfect balance of sweet vanilla flavor and hints of nuttiness from crushed pistachios.',100,900,400,4,NULL),
(4,100,'2023-05-25 10:56:31',NULL,'2023-05-25 10:56:31','Ingredients for Pistachio Ice Cream:\nEgg yolks – are essential for the soft and creamy texture in the ice cream and added thickness.\nGranulated sugar\nHeavy cream and evaporated milk – the cream and evaporated milk give the ice creamy it’s soft, creamy taste and texture. You can replace the evaporated milk with whole milk (or 2%, but it won be as creamy).\nSalt – to bring out flavors.\nVanilla extract and almond extract – the best highlight flavors with pistachio.\nWhole shelled pistachios – I like to buy them already shelled. We use half of the pistachios to flavor and color the simmering milk, and then stir pistachio pieces into the ice cream at the end.','Chocolate Ice Cream: Ingredient & Substitutions','This easy homemade Pistachio Ice Cream recipe is luxuriously creamy with the perfect balance of sweet vanilla flavor and hints of nuttiness from crushed pistachios.',100,900,400,3,NULL),
(5,100,'2023-05-25 10:56:45',NULL,'2023-05-25 10:56:45','Ingredients for Pistachio Ice Cream:\nEgg yolks – are essential for the soft and creamy texture in the ice cream and added thickness.\nGranulated sugar\nHeavy cream and evaporated milk – the cream and evaporated milk give the ice creamy it’s soft, creamy taste and texture. You can replace the evaporated milk with whole milk (or 2%, but it won be as creamy).\nSalt – to bring out flavors.\nVanilla extract and almond extract – the best highlight flavors with pistachio.\nWhole shelled pistachios – I like to buy them already shelled. We use half of the pistachios to flavor and color the simmering milk, and then stir pistachio pieces into the ice cream at the end.','Pistachio Ice Cream','This easy homemade Pistachio Ice Cream recipe is luxuriously creamy with the perfect balance of sweet vanilla flavor and hints of nuttiness from crushed pistachios.',100,900,400,2,NULL),
(6,100,'2023-05-27 05:48:20',NULL,'2023-05-27 05:48:20','Ingredients for Pistachio Ice Cream:\nEgg yolks – are essential for the soft and creamy texture in the ice cream and added thickness.\nGranulated sugar\nHeavy cream and evaporated milk – the cream and evaporated milk give the ice creamy it’s soft, creamy taste and texture. You can replace the evaporated milk with whole milk (or 2%, but it won be as creamy).\nSalt – to bring out flavors.\nVanilla extract and almond extract – the best highlight flavors with pistachio.\nWhole shelled pistachios – I like to buy them already shelled. We use half of the pistachios to flavor and color the simmering milk, and then stir pistachio pieces into the ice cream at the end.','Pistachio Ice Cream','This easy homemade Pistachio Ice Cream recipe is luxuriously creamy with the perfect balance of sweet vanilla flavor and hints of nuttiness from crushed pistachios.',100,900,400,1,NULL),
(7,100,'2023-05-27 05:49:15',NULL,'2023-05-27 05:49:15','Ingredients for Pistachio Ice Cream:\nEgg yolks – are essential for the soft and creamy texture in the ice cream and added thickness.\nGranulated sugar\nHeavy cream and evaporated milk – the cream and evaporated milk give the ice creamy it’s soft, creamy taste and texture. You can replace the evaporated milk with whole milk (or 2%, but it won be as creamy).\nSalt – to bring out flavors.\nVanilla extract and almond extract – the best highlight flavors with pistachio.\nWhole shelled pistachios – I like to buy them already shelled. We use half of the pistachios to flavor and color the simmering milk, and then stir pistachio pieces into the ice cream at the end.','Pistachio Ice Cream','This easy homemade Pistachio Ice Cream recipe is luxuriously creamy with the perfect balance of sweet vanilla flavor and hints of nuttiness from crushed pistachios.',100,900,400,1,NULL),
(13,100,'2023-05-29 06:02:06',NULL,'2023-05-29 06:02:06','sdfsdg sdgfdgr dfgdfgh dfg dgdfghd fged drgdgbfdghfhfgh df dfghfghfhfghf','T-Shirt','hfghrfgjfg ffffffffffffffffffffff fffffffffffdddddddddddddddddddd',100,200,0,3,NULL);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_name` varchar(255) NOT NULL,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `role_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `role` */

insert  into `role`(`role_name`,`date_created`,`last_updated`,`role_description`) values 
('ROLE_ADMIN','2024-03-10 03:37:19','2024-03-10 03:37:19','ROLE_ADMIN'),
('ROLE_MODERATOR','2024-03-10 03:37:33','2024-03-10 03:37:33','ROLE_MODERATOR'),
('ROLE_USER','2024-03-10 03:37:44','2024-03-10 03:37:44','ROLE_USER');

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` enum('ROLE_USER','ROLE_MODERATOR','ROLE_ADMIN') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `roles` */

/*Table structure for table `user01` */

DROP TABLE IF EXISTS `user01`;

CREATE TABLE `user01` (
  `user_name` varchar(255) NOT NULL,
  `account_non_expired` bit(1) DEFAULT NULL,
  `account_non_locked` bit(1) DEFAULT NULL,
  `credentials_non_expired` bit(1) DEFAULT NULL,
  `date_created` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `last_updated` datetime NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_first_name` varchar(255) DEFAULT NULL,
  `user_last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_name`),
  UNIQUE KEY `UK_p4tw0qer2ld2dqlnqswhyfqwt` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user01` */

insert  into `user01`(`user_name`,`account_non_expired`,`account_non_locked`,`credentials_non_expired`,`date_created`,`email`,`enabled`,`last_updated`,`password`,`user_first_name`,`user_last_name`) values 
('aaa',NULL,NULL,NULL,'2024-03-10 04:10:25','aaa@gmail.com',NULL,'2024-03-10 04:10:25','$2a$10$.7FDYG9QErsxUFMjKmscD.7xMyUdhPQJj3ouJRh.OSaVSJ1CeY0Yq',NULL,NULL),
('admin',NULL,NULL,NULL,'2024-03-10 06:44:34','admin@gmail.com',NULL,'2024-03-10 06:44:34','$2a$10$O6JeBw9QWWIk59B6KBW.ReiKb4avRKTjv0QWlChFgtg0vNy1mmTHa',NULL,NULL),
('admin123',NULL,NULL,NULL,'2024-03-10 06:46:23','admin123@gmail.com',NULL,'2024-03-10 06:46:23','$2a$10$ASJNRb33JejIUEeoI71TpeWTGa4Aw4gZXo0rkovhSjoYY7e8qUgvu',NULL,NULL);

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_roles` */

/*Table structure for table `userrole` */

DROP TABLE IF EXISTS `userrole`;

CREATE TABLE `userrole` (
  `user_name` varchar(255) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`user_name`,`role_name`),
  KEY `FKsjmd7ov79njy9pcdv4jqweixo` (`role_name`),
  CONSTRAINT `FKk1aw4ivo0u09waweuilur0wxw` FOREIGN KEY (`user_name`) REFERENCES `user01` (`user_name`),
  CONSTRAINT `FKsjmd7ov79njy9pcdv4jqweixo` FOREIGN KEY (`role_name`) REFERENCES `role` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `userrole` */

insert  into `userrole`(`user_name`,`role_name`) values 
('aaa','ROLE_USER'),
('admin','ROLE_ADMIN'),
('admin123','ROLE_MODERATOR');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`id`,`email`,`password`,`username`) values 
(1,'gmsaiful71@gmail.com','$2a$10$fH21J9PsfzOy/xCeH.QHbe4k028XLsz66mVEFKjtuU2bXMQwOyK9e','admin123'),
(2,'gmsaiful72@gmail.com','$2a$10$D0YpCKsOhv9Yj8hwOnl7yOjoeGB9cNci6IMZKTHOUKAgO6l.QP7Gm','admin1234'),
(3,'gmsaiful73@gmail.com','$2a$10$ZR2N7WWzI7Wiug04GyHmN.0kYaidXGtnBOQORVav0iQZwojj9/wjC','admin12345'),
(4,'s71@gmail.com','$2a$10$LT.Xxs6vaXAwdu48qww4TunLCbQaxdkvDj7jt7M7jwtSkQDIteYqq','s71'),
(5,'admin@gmail.com','$2a$10$81a73fOSFVQ0mYjSLreFse0V43ZS79y/nm.JTEA1c6icgoH4QNk.G','admin');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
