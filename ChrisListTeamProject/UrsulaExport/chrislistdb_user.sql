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
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$10$6/krDDN8P/i/CANT5Kn2pO6FSkczr7QbNpaaz/wnMYUpFoy/.vy9C','Mike','Wazowski','user@me.com','555-5555',1),(2,'user','$2a$10$6/krDDN8P/i/CANT5Kn2pO6FSkczr7QbNpaaz/wnMYUpFoy/.vy9C','fake','user','user@me.com','555-5555',0),(3,'oldSocks24','$2a$10$6/krDDN8P/i/CANT5Kn2pO6FSkczr7QbNpaaz/wnMYUpFoy/.vy9C','Phil','Philipson','phil@phil.com','(320)-234-2342',0),(4,'LampLamp','$2a$10$6/krDDN8P/i/CANT5Kn2pO6FSkczr7QbNpaaz/wnMYUpFoy/.vy9C','Dalton','Galloway','daltong@gmail.com','(320)-234-23412',1),(5,'AnimeIsCool','$2a$10$6/krDDN8P/i/CANT5Kn2pO6FSkczr7QbNpaaz/wnMYUpFoy/.vy9C','Patricia','Patterson','PattyP@yahoo.com','(320)-234-2340',1),(6,'Ursie','$2a$10$6t6aUnGPR7JXqw05DmN//.73cJwlnL2A/VBotbA8aXuPZZMyroj3i','Ursie','Ursie','ursielg@gmail.com','(454) 345-0987',0),(7,'Phil','$2a$10$NFRrJ5pOZXJUm/nHl72/cuhJijmKNKoVaUD9qt0Cuscl61iPB3yma','Phil',' Jr.','phil@gmail.com','(345) 444-9087',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-17 11:24:22
