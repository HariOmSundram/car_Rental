CREATE DATABASE  IF NOT EXISTS `p08_carrental` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `p08_carrental`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: p08_carrental
-- ------------------------------------------------------
-- Server version	8.2.0

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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `agency_id` int NOT NULL,
  `car_id` int NOT NULL,
  `booking_date` date NOT NULL,
  `rental_duration` int NOT NULL,
  `journey_date` date NOT NULL,
  `status_id` int NOT NULL,
  `token_amount` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `customer_id` (`customer_id`),
  KEY `agency_id` (`agency_id`),
  KEY `car_id` (`car_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`agency_id`) REFERENCES `car_rental_agency` (`agency_id`),
  CONSTRAINT `booking_ibfk_3` FOREIGN KEY (`car_id`) REFERENCES `car` (`car_id`),
  CONSTRAINT `booking_ibfk_4` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,1,1,1,'2024-12-01',5,'2024-12-06',1,500),(2,1,1,2,'2024-12-05',3,'2024-12-08',1,1000),(3,1,1,3,'2024-12-10',7,'2024-12-17',2,1500),(4,1,1,1,'2024-12-01',5,'2024-12-06',1,1600),(5,1,1,1,'2024-12-01',5,'2024-12-06',1,500);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `car_id` int NOT NULL AUTO_INCREMENT,
  `category_id` int NOT NULL,
  `agency_id` int NOT NULL,
  `daily_rent` decimal(10,2) NOT NULL,
  `registration_number` varchar(225) NOT NULL,
  `kilometers_run` int DEFAULT NULL,
  `year_of_purchase` int DEFAULT NULL,
  `model_id` int NOT NULL,
  `is_available` tinyint(1) DEFAULT '1',
  `image` longblob,
  PRIMARY KEY (`car_id`),
  UNIQUE KEY `registration_number` (`registration_number`),
  KEY `category_id` (`category_id`),
  KEY `agency_id` (`agency_id`),
  KEY `model_id` (`model_id`),
  CONSTRAINT `car_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `car_ibfk_2` FOREIGN KEY (`agency_id`) REFERENCES `car_rental_agency` (`agency_id`) ON DELETE RESTRICT,
  CONSTRAINT `car_ibfk_3` FOREIGN KEY (`model_id`) REFERENCES `car_model` (`model_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,1,1,1500.00,'MH01ABC1234',12000,2020,1,1,NULL),(2,2,1,2500.00,'MH01XYZ5678',15000,2021,2,1,NULL),(3,3,1,4000.00,'MH01DEF9876',8000,2022,3,1,NULL),(4,4,1,5000.00,'MH01GHI1357',5000,2023,4,1,NULL),(5,5,1,6000.00,'MH01JKL2468',3000,2024,5,1,NULL),(6,1,1,1800.00,'MH02ABC2345',10000,2020,6,1,NULL),(7,2,1,2800.00,'MH02XYZ6789',12000,2021,7,1,NULL),(8,3,1,4500.00,'MH02DEF2345',7000,2022,8,1,NULL),(9,4,1,5500.00,'MH02GHI2468',6000,2023,9,1,NULL),(10,5,1,6500.00,'MH02JKL1234',2000,2024,10,1,NULL),(11,1,1,1500.00,'MH01ABC1434',12000,2020,1,1,NULL);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_availability`
--

DROP TABLE IF EXISTS `car_availability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_availability` (
  `availability_id` int NOT NULL AUTO_INCREMENT,
  `car_id` int NOT NULL,
  `availability_date` date NOT NULL,
  `is_available` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`availability_id`),
  KEY `car_id` (`car_id`),
  CONSTRAINT `car_availability_ibfk_1` FOREIGN KEY (`car_id`) REFERENCES `car` (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_availability`
--

LOCK TABLES `car_availability` WRITE;
/*!40000 ALTER TABLE `car_availability` DISABLE KEYS */;
INSERT INTO `car_availability` VALUES (1,1,'2024-12-06',0),(2,2,'2024-12-08',0),(3,3,'2024-12-17',1),(4,4,'2024-12-06',1),(5,5,'2024-12-06',1);
/*!40000 ALTER TABLE `car_availability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_manufacturer`
--

DROP TABLE IF EXISTS `car_manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_manufacturer` (
  `manufacturer_id` int NOT NULL AUTO_INCREMENT,
  `manufacturer_name` varchar(45) NOT NULL,
  PRIMARY KEY (`manufacturer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_manufacturer`
--

LOCK TABLES `car_manufacturer` WRITE;
/*!40000 ALTER TABLE `car_manufacturer` DISABLE KEYS */;
INSERT INTO `car_manufacturer` VALUES (1,'Toyota'),(2,'Honda'),(3,'Ford'),(4,'BMW'),(5,'Audi'),(6,'Mercedes'),(7,'Hyundai'),(8,'Tata'),(9,'Maruti Suzuki'),(10,'Nissan');
/*!40000 ALTER TABLE `car_manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_model`
--

DROP TABLE IF EXISTS `car_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_model` (
  `model_id` int NOT NULL AUTO_INCREMENT,
  `model_name` varchar(45) NOT NULL,
  `manufacturer_id` int NOT NULL,
  `fuel_id` int NOT NULL,
  `seat_count` int DEFAULT NULL,
  PRIMARY KEY (`model_id`),
  KEY `manufacturer_id` (`manufacturer_id`),
  KEY `fuel_id` (`fuel_id`),
  CONSTRAINT `car_model_ibfk_1` FOREIGN KEY (`manufacturer_id`) REFERENCES `car_manufacturer` (`manufacturer_id`),
  CONSTRAINT `car_model_ibfk_2` FOREIGN KEY (`fuel_id`) REFERENCES `fuel` (`fuel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_model`
--

LOCK TABLES `car_model` WRITE;
/*!40000 ALTER TABLE `car_model` DISABLE KEYS */;
INSERT INTO `car_model` VALUES (1,'Corolla',1,1,5),(2,'Civic',2,1,5),(3,'Mustang',3,1,2),(4,'X5',4,1,5),(5,'A6',5,1,4),(6,'Elantra',6,1,5),(7,'Altroz',7,2,5),(8,'Swift',8,2,5),(9,'Micra',9,2,4),(10,'Sunny',10,1,5);
/*!40000 ALTER TABLE `car_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_rental_agency`
--

DROP TABLE IF EXISTS `car_rental_agency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_rental_agency` (
  `agency_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `city_id` int NOT NULL,
  `address` varchar(255) NOT NULL,
  `contact` varchar(15) NOT NULL,
  `gst_no` varchar(15) NOT NULL,
  PRIMARY KEY (`agency_id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `gst_no` (`gst_no`),
  KEY `city_id` (`city_id`),
  CONSTRAINT `car_rental_agency_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `car_rental_agency_ibfk_2` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_rental_agency`
--

LOCK TABLES `car_rental_agency` WRITE;
/*!40000 ALTER TABLE `car_rental_agency` DISABLE KEYS */;
INSERT INTO `car_rental_agency` VALUES (1,3,1,'Mumbai, Maharashtra','9876543210','GST1234567890');
/*!40000 ALTER TABLE `car_rental_agency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) NOT NULL,
  `description` text,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'SUV','Sport Utility Vehicle'),(2,'Sedan','Standard sedan vehicles'),(3,'Sports','Luxury sports cars'),(4,'Luxury','High-end luxury cars'),(5,'Convertible','Cars with convertible roofs');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `city_id` int NOT NULL AUTO_INCREMENT,
  `city_name` varchar(100) NOT NULL,
  PRIMARY KEY (`city_id`),
  UNIQUE KEY `city_name` (`city_name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (8,'Ahmedabad'),(3,'Bangalore'),(4,'Chennai'),(2,'Delhi'),(6,'Hyderabad'),(9,'Jaipur'),(5,'Kolkata'),(10,'Lucknow'),(1,'Mumbai'),(7,'Pune');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `city_id` int NOT NULL,
  `address` varchar(255) NOT NULL,
  `adhar_number` varchar(25) NOT NULL,
  `driving_license_no` varchar(20) NOT NULL,
  `contact` varchar(45) NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `adhar_number` (`adhar_number`),
  UNIQUE KEY `driving_license_no` (`driving_license_no`),
  KEY `city_id` (`city_id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `customer_ibfk_2` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,2,1,'Mumbai, Maharashtra','1234-5678-9012','DL1234567890','9876543210'),(2,4,7,'Pimpri Chinchwad','986525003014','411933311','8225073049');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fuel`
--

DROP TABLE IF EXISTS `fuel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fuel` (
  `fuel_id` int NOT NULL AUTO_INCREMENT,
  `fuel_type` varchar(45) NOT NULL,
  PRIMARY KEY (`fuel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel`
--

LOCK TABLES `fuel` WRITE;
/*!40000 ALTER TABLE `fuel` DISABLE KEYS */;
INSERT INTO `fuel` VALUES (1,'Petrol'),(2,'Diesel'),(3,'Electric'),(4,'CNG');
/*!40000 ALTER TABLE `fuel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mode_of_payment`
--

DROP TABLE IF EXISTS `mode_of_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mode_of_payment` (
  `mode_id` int NOT NULL AUTO_INCREMENT,
  `mode_name` varchar(50) NOT NULL,
  PRIMARY KEY (`mode_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mode_of_payment`
--

LOCK TABLES `mode_of_payment` WRITE;
/*!40000 ALTER TABLE `mode_of_payment` DISABLE KEYS */;
INSERT INTO `mode_of_payment` VALUES (1,'Cash'),(2,'Credit Card'),(3,'Debit Card'),(4,'Online Payment');
/*!40000 ALTER TABLE `mode_of_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `booking_id` int NOT NULL,
  `amount_to_pay` decimal(10,2) NOT NULL,
  `payment_date` date NOT NULL,
  `mode_id` int NOT NULL,
  PRIMARY KEY (`payment_id`),
  UNIQUE KEY `booking_id` (`booking_id`),
  KEY `mode_id` (`mode_id`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`booking_id`) ON DELETE CASCADE,
  CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`mode_id`) REFERENCES `mode_of_payment` (`mode_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,1,7500.00,'2024-12-01',3),(2,2,7500.00,'2024-12-05',2);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(225) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Admin'),(3,'Agency'),(2,'Customer');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `status_id` int NOT NULL AUTO_INCREMENT,
  `status_name` varchar(45) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Booked'),(2,'Available'),(3,'Unavailable'),(4,'In Service');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin_user','admin_pass','admin@car_rental.com',1),(2,'customer_user','customer_pass','customer@car_rental.com',2),(3,'agency_user','agency_pass','agency@gmail.com',3),(4,'ShreeNivas','Srinivas@123','ShreeNivasjdv@gmail.com',2),(6,'Ramesh','Ramesh','Ramesh@car_rental.com',1),(7,'admin_user','$2a$10$EcPQR3SxooboOwGDYsaAXus79HfukMR77N5WDdtNOERUrTJ.ZmQZe','admin@example.com',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-09 18:54:45
