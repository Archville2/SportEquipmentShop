-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sportshop
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'John','Smith','js@mia.com','777-22-33','john_s','smith_pass','user'),(2,'Mike','Tyson','mike@tyson.com','555-233-111','boxer','winner','user'),(102,'Ivan','Ali','q@d.by','123321','log','pas','user'),(103,'Nik','Doomer','nik@doomer.by','23822112','doom','forrever','admin'),(104,'James','Bond','bon@james.mia.uk','007-007','agent','zero','user'),(106,'Arni','Zwrtz','arni@zw.au','2000-100','t2000','olymp','user'),(107,'Max','Benji','1223311','asd@rt.by','qwer','asdf','user'),(108,'o1','oo1','+123456789','oo@mail.ru','o','o','user'),(109,'Nikolas','Papandreus','322111221','greece@gov.gr','zeuss','god','user'),(110,'Nikolas','Papandreus','322111221','greece@gov.gr','zeuss','god','user'),(111,'Gray','Wolf','wolfy@forest.com','3211111222','gray','wolf','user'),(112,'anonimus','smith','as@ii.ny','1231123','an','an','user'),(113,'anonimus','smith','as@ii.ny','1231123','ans','an','user'),(114,'one','two','one@two.by','112131','one','two','user'),(115,'one','two','one@two.by','112131','oneaa','two','user'),(116,'one','two','one@two.by','112131','oneaaaa','two','user');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment` (
  `id` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `manufaturer` varchar(45) DEFAULT NULL,
  `cost` int(11) DEFAULT NULL,
  `img` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (1,'велосипед','Аист-100','обычный велосипед','МВЗ',300,'bycicle01s.jpg'),(2,'самокат','Бегунок-БГ1','обычный самокат','РоллКо',20,'scooter01s.jpg'),(3,'лыжи','Телеханы-21','старые советские лыжи','Телеханы',12,'ski01.jpg'),(4,'палатка','Шелтер VFX','обычная палатка на 3 человека.','CampCo',45,'tent01.jpg');
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rented_items`
--

DROP TABLE IF EXISTS `rented_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rented_items` (
  `client_id` int(11) DEFAULT NULL,
  `equipment_id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `length` int(11) DEFAULT NULL,
  PRIMARY KEY (`equipment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rented_items`
--

LOCK TABLES `rented_items` WRITE;
/*!40000 ALTER TABLE `rented_items` DISABLE KEYS */;
INSERT INTO `rented_items` VALUES (102,1,'2017-06-04',30),(102,3,'2017-06-04',30);
/*!40000 ALTER TABLE `rented_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `types`
--

DROP TABLE IF EXISTS `types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `types` (
  `type` varchar(45) NOT NULL,
  `folder` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `types`
--

LOCK TABLES `types` WRITE;
/*!40000 ALTER TABLE `types` DISABLE KEYS */;
INSERT INTO `types` VALUES ('велосипед','Велоспорт'),('лыжи','Зима'),('палатка','Отдых'),('самокат','Велоспорт');
/*!40000 ALTER TABLE `types` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-04 19:04:27
