-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bank_data
-- ------------------------------------------------------
-- Server version	8.0.25

set foreign_key_checks = 0;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `deposit_withdraw_transactions`
--

DROP TABLE IF EXISTS `deposit_withdraw_transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deposit_withdraw_transactions` (
  `transaction_id` int unsigned NOT NULL AUTO_INCREMENT,
  `amount` int unsigned DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `transaction_type` varchar(255) DEFAULT NULL,
  `by_user` int unsigned NOT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `FKb71xkjjysnf5p8wrf78wkjpdl` (`by_user`),
  CONSTRAINT `FKb71xkjjysnf5p8wrf78wkjpdl` FOREIGN KEY (`by_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deposit_withdraw_transactions`
--

LOCK TABLES `deposit_withdraw_transactions` WRITE;
/*!40000 ALTER TABLE `deposit_withdraw_transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `deposit_withdraw_transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (24);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int unsigned NOT NULL,
  `balance` float NOT NULL,
  `token_in_database` int DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_type` varchar(255) NOT NULL,
  PRIMARY KEY (`role_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transfer_transactions`
--

DROP TABLE IF EXISTS `transfer_transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transfer_transactions` (
  `transaction_id` int unsigned NOT NULL AUTO_INCREMENT,
  `amount` int unsigned DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `transaction_type` varchar(255) DEFAULT NULL,
  `by_user` int unsigned NOT NULL,
  `to_user` int unsigned NOT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `FKqrsyh0hv9t4np688yl1f4sbpi` (`by_user`),
  KEY `FK9c7cj199kdg16xtvv0h98mluo` (`to_user`),
  CONSTRAINT `FK9c7cj199kdg16xtvv0h98mluo` FOREIGN KEY (`to_user`) REFERENCES `users` (`id`),
  CONSTRAINT `FKqrsyh0hv9t4np688yl1f4sbpi` FOREIGN KEY (`by_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer_transactions`
--

LOCK TABLES `transfer_transactions` WRITE;
/*!40000 ALTER TABLE `transfer_transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `transfer_transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_has_roles`
--

DROP TABLE IF EXISTS `user_has_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_has_roles` (
  `id` int unsigned NOT NULL,
  `role_type` varchar(255) NOT NULL,
  KEY `FKi0wqulv88r289l3tndbvn2ho6` (`role_type`),
  KEY `FKop2qllss6wrj6joeqsxd9h1u9` (`id`),
  CONSTRAINT `FKi0wqulv88r289l3tndbvn2ho6` FOREIGN KEY (`role_type`) REFERENCES `roles` (`role_type`),
  CONSTRAINT `FKop2qllss6wrj6joeqsxd9h1u9` FOREIGN KEY (`id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('admin'),('user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_has_roles`
--

LOCK TABLES `user_has_roles` WRITE;
/*!40000 ALTER TABLE `user_has_roles` DISABLE KEYS */;
INSERT INTO `user_has_roles` VALUES (1,'user'),(1,'admin'),(2,'user'),(3,'user'),(4,'user'),(5,'user'),(6,'user'),(7,'user'),(8,'user'),(9,'user'),(10,'user'),(11,'user'),(12,'user'),(13,'user'),(14,'user'),(15,'user'),(16,'user'),(17,'user'),(18,'user'),(19,'user'),(20,'user'),(21,'user'),(22,'user'),(23,'user');
/*!40000 ALTER TABLE `user_has_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,30000,0,'$2a$10$B2r7FPHrkEnNGa3TsjiDPeBRJLMc.KbEp8e9QpGpBHrT8yKlF.gB2','John'),(2,500,0,'$2a$10$d1hGobeTyHn7pFbor8otveDuOOpTbH/p6/AfxRJ6nZfvgzcMOJxky','Matt'),(3,5050,0,'$2a$10$4d5Y90LNMZiUjCD0a67yc.1.vGFYLjI52f8.18GcH8/LWrJBpMtX2','Blake'),(4,5050,0,'$2a$10$qt6xrz42SFetPAc3tygAmOpPhpTe15NqDP2ttdcfpV18En6JcsTiG','Candelaria'),(5,5050,0,'$2a$10$/PYzhLlmaWeiS4.5mchvHOzmF8v3olU6nE0xtcseq2b4/FvD.ZAZO','Leonia'),(6,5050,0,'$2a$10$c14sdHgPKQ2OwaOKoUHdfOymbSxMvGYbI4DASvzd6.C.ND1tqU/oy','Vanda'),(7,5050,0,'$2a$10$J3a2i1FQMZLj5TsuOsPDuujkrwILr7CKKXi0M0nT.03wbzXxEOKmG','Maya'),(8,5050,0,'$2a$10$PfQSxnfgxb0fcjUyrrbr6uWNzxl4uQAXfAFCtMJ2leq3lZfBSO5za','Tiera'),(9,5050,0,'$2a$10$X6.Bqz3lk9dh1b5iFmBng.sVNITrFSG23NIceBk2KgN7CuAevK65q','Neil'),(10,5050,0,'$2a$10$eT9rpPOvyRSOZE2VTFvb..jgOkuHDBhocZ5nMuNj15n7aM/O7zYXi','Providencia'),(11,5050,0,'$2a$10$k67agPkvTGLoPdwA0WAoi.ldmFw6LNHcC02qhDbxn3V9sXfbCHqUS','Truman'),(12,5050,0,'$2a$10$4I4BN25v/80vyMUtNMSfM.uZ1VwdVkbwzWRFCy0korB8mFuNkSrdu','Pamila'),(13,5050,0,'$2a$10$KLO2KQG08FCEIt6NrmIPv.ruNHAihyiQ/RmA8sJQziWLehqufbdAK','Keshia'),(14,5050,0,'$2a$10$fDNvTxbmRqzTOH8rOh4pVubU5IzKi66Jrff9dt9Ne5KDcWYqtrkYK','Hermelinda'),(15,5050,0,'$2a$10$Imd8hOoNanp2dS0wjWDcNuAMZj7AZU4y.BYsV2/YRedlRCcDqwPkm','Milford'),(16,5050,0,'$2a$10$y2tmU9Z0iS0v5OS5Dy04OOapnSMy.3DJ0dxkxnzONLXUnIEDplrnu','Cristopher'),(17,5050,0,'$2a$10$bl4G5f9jKtVhWe9iCworsueQ.VzY8gsIRIT6uER9CACwkeBe9Qw9C','Kareem'),(18,5050,0,'$2a$10$nUTWailJy1n6Fc9Efn3qtunTN8Mt/LkY07W0f/Runk7q6MCg9AOzi','Guy'),(19,5050,0,'$2a$10$EBfmrzfwZSIIGt3zZ.PtOO7J.rD/VBSL7xvtPdYmeeewsOKotaLfK','Su'),(20,5050,0,'$2a$10$ndLBykBaAFV2IAK.ElYiDuEzc6oBNxisTyqV14cDArQC2zQl0li..','Mariano'),(21,5050,0,'$2a$10$X06ZxwSRHLIKlBL9gitrD.iwk8a2zhBqA0FGR/UhkhvFXt/gkwE/2','Dayle'),(22,5050,0,'$2a$10$eQPparL0zDer0aDtfMAtO.3lqSFwqF77LleYmU9aIiyQR/b5w0u4G','Boyd'),(23,5050,0,'$2a$10$vEfDGbhSJyaCZp/E.cLApOxJW4XSthbxVRl.kjFr9T6R//0pTsoG2','Janeth');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bank_data'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
set foreign_key_checks = 1;
-- Dump completed on 2021-07-04 15:45:37