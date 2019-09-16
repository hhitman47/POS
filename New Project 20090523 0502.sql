-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.30-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema ic_invent
--

CREATE DATABASE IF NOT EXISTS ic_invent;
USE ic_invent;

--
-- Definition of table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `birthday` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `app_user`
--

/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` (`id`,`first_name`,`last_name`,`birthday`) VALUES 
 (1,'a','a','0001-01-01 00:00:00'),
 (2,'Ali','Hussain','0002-02-02 00:00:00'),
 (3,'c','c','0003-03-03 00:00:00');
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;


--
-- Definition of table `items`
--

DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(65) DEFAULT NULL,
  `stock_quantity` float DEFAULT NULL,
  `price` double DEFAULT NULL,
  `threshold` int(10) unsigned DEFAULT NULL,
  `barcode` double DEFAULT NULL,
  `full_quantity` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `items`
--

/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` (`id`,`name`,`description`,`stock_quantity`,`price`,`threshold`,`barcode`,`full_quantity`) VALUES 
 (1,'Tussue 1',NULL,97,30,5,0,120),
 (2,'Uniball',NULL,99,50,10,4902778913772,100),
 (3,'Rose Petal',NULL,97,75,20,0,150),
 (4,'Ball',NULL,99,50,5,NULL,200),
 (5,'Milk',NULL,99,40,60,NULL,100),
 (6,'Meat',NULL,99,150,10,NULL,110),
 (7,'Uniball Pen',NULL,100,52,3,NULL,100),
 (8,'Uniball Marker',NULL,100,90,5,NULL,200);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;


--
-- Definition of table `sales`
--

DROP TABLE IF EXISTS `sales`;
CREATE TABLE `sales` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `item_name` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `number` int(10) unsigned DEFAULT NULL,
  `barcode` double DEFAULT NULL,
  `payment_type` varchar(20) DEFAULT NULL,
  `item_in_db` tinyint(1) DEFAULT NULL,
  `bill_id` int(10) unsigned DEFAULT NULL,
  `loan_to` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sales`
--

/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` (`id`,`item_name`,`price`,`discount`,`quantity`,`date_time`,`number`,`barcode`,`payment_type`,`item_in_db`,`bill_id`,`loan_to`) VALUES 
 (7,'Uniball',50,0,1,'2009-05-23 03:18:26',1,4902778913772,'Cash',1,7,''),
 (8,'Milk',40,0,1,'2009-05-23 03:18:27',2,0,'Cash',1,7,''),
 (9,'Ball',50,0,1,'2009-05-23 03:18:29',3,0,'Cash',1,7,''),
 (10,'Meat',150,0,1,'2009-05-23 03:18:30',4,0,'Cash',1,7,''),
 (11,'as',25,0,1,'2009-05-23 03:18:33',5,NULL,'Cash',0,7,''),
 (12,'as',88,0,1,'2009-05-23 03:18:36',6,NULL,'Cash',0,7,''),
 (13,'test',985,0,1,'2009-05-23 03:18:41',7,NULL,'Cash',0,7,'');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;


--
-- Definition of table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`,`username`,`password`,`name`,`role`) VALUES 
 (4,'Ishtiaq','786786','Ishtiaq Hussain','administrator'),
 (6,'Waheed','722','Waheed Khan','casher'),
 (7,'Aftab','123','Malik Aftab','add item'),
 (8,'ali','786','Ali Khan','add item');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
