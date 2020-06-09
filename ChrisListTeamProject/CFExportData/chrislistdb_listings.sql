-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: chrislistdb
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `listings`
--

DROP TABLE IF EXISTS `listings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `listings` (
  `ListingId` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(45) NOT NULL,
  `City` varchar(30) NOT NULL,
  `ListDate` date NOT NULL,
  `content` mediumtext NOT NULL,
  `isDeleted` tinyint(1) NOT NULL DEFAULT '0',
  `conditionId` int NOT NULL,
  `id` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`ListingId`),
  KEY `conditionId` (`conditionId`),
  KEY `id` (`id`),
  CONSTRAINT `listings_ibfk_1` FOREIGN KEY (`conditionId`) REFERENCES `conditions` (`conditionId`),
  CONSTRAINT `listings_ibfk_2` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listings`
--

LOCK TABLES `listings` WRITE;
/*!40000 ALTER TABLE `listings` DISABLE KEYS */;
INSERT INTO `listings` VALUES (1,'Old Socks','Bloomington','2016-04-06','These are my old socks. I do not want them in my house anymore.',0,3,3,50.00),(2,'Old Ladder','Richfield','2016-04-04','Old ladder. It is old, but is still a ladder.',0,3,3,100.00),(3,'Cool Lamp','Minneapolis','2016-04-06','This is a cool lamp. New. Is in the shape of a Cool Lamp',0,1,4,1.00),(4,'Anime Figure','Hopkins','2016-04-05','This is a Dragonball Z character. Selling it becuase I don\'t identify with the character anymore',0,2,5,1000.00),(5,'Bugs Bunny','Shoreview','2020-03-25','not the real one',0,2,4,27.00),(6,'Old coffee mug','St Paul','2019-02-20','A dirty mug with coffee stains',0,2,4,1.00),(7,'Waffle Maker','Hopkins','2016-03-05','New, but I like Pancakes better',0,1,5,50.00),(8,'Cheetah','Minneapolis','2016-04-13','New, but violent',0,1,5,1000.00),(9,'My Little Pony Figure','Hopkins','2016-04-10','Like new, but I outgrew ponies',0,2,5,1000.00),(10,'minnie','test','2020-04-16','<p>dasfda</p>',0,1,2,0.01),(11,'dumb test','dumbville','2020-04-16','sdfasdfa',0,1,2,0.01),(12,'Monkeys!','hooville','2020-04-17','<p>Group of 4 monkeys for sale from reputable dealer. Meet in alley off 5th St downtown and ask for Buddy.</p>',0,1,2,10000.00),(13,'Bananas','Maplewood','2020-04-17','for pet monkeys, best price in town.',0,1,2,1.99),(14,'Woody Figure Toystory','Hugo','2020-04-17','this is a toy from the original toy story',0,3,2,12.00);
/*!40000 ALTER TABLE `listings` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-17 11:27:00
