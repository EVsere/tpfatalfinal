CREATE DATABASE  IF NOT EXISTS `tp_finaljeg` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tp_finaljeg`;
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: tp_finaljeg
-- ------------------------------------------------------
-- Server version	8.0.46

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
-- Table structure for table `barrio`
--

DROP TABLE IF EXISTS `barrio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `barrio` (
  `ID_Barrio` int NOT NULL AUTO_INCREMENT,
  `Superficie` double DEFAULT NULL,
  `numComu` int DEFAULT NULL,
  `PAsfalto` int DEFAULT NULL,
  `NLey` int DEFAULT NULL,
  `CMono` int DEFAULT NULL,
  `Consorcio` bit(1) DEFAULT NULL,
  `TipoDeBarrio` varchar(45) DEFAULT NULL,
  `Nombre` varchar(100) DEFAULT NULL,
  `EdPh` int DEFAULT NULL,
  `AguaPota` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID_Barrio`),
  UNIQUE KEY `uq_nombre_barrio` (`Nombre`),
  KEY `idx_numComu` (`numComu`),
  CONSTRAINT `fk_barrio_ncom` FOREIGN KEY (`numComu`) REFERENCES `comuna` (`NCom`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barrio`
--

LOCK TABLES `barrio` WRITE;
/*!40000 ALTER TABLE `barrio` DISABLE KEYS */;
INSERT INTO `barrio` VALUES (15,50,1,NULL,562389,NULL,NULL,'Urbanización Planificada','Flores',NULL,_binary ''),(16,90,3,89,NULL,NULL,NULL,'Trazado Formal','Avellaneda',25,NULL),(17,12,3,NULL,4558,NULL,NULL,'Urbanización Planificada','Palermo Soho',NULL,_binary '\0');
/*!40000 ALTER TABLE `barrio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comuna`
--

DROP TABLE IF EXISTS `comuna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comuna` (
  `nombreSede` varchar(50) DEFAULT NULL,
  `NCom` int NOT NULL,
  `JuntaComunal` varchar(45) DEFAULT NULL,
  `id_com` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_com`),
  UNIQUE KEY `uq_comuna_ncom` (`NCom`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comuna`
--

LOCK TABLES `comuna` WRITE;
/*!40000 ALTER TABLE `comuna` DISABLE KEYS */;
INSERT INTO `comuna` VALUES ('yesry',1,'y5esg',10),('blablabla',2,'qsy',12),('nosequeponer',3,'ya me aburri',13);
/*!40000 ALTER TABLE `comuna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `residente`
--

DROP TABLE IF EXISTS `residente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `residente` (
  `Dni` varchar(50) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `FNacimiento` date DEFAULT NULL,
  `id_res` int NOT NULL AUTO_INCREMENT,
  `FK_barrio` int DEFAULT NULL,
  `fk_SituacionLaboral` int DEFAULT NULL,
  `Tienehijos` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id_res`),
  UNIQUE KEY `uq_residente_situacion` (`fk_SituacionLaboral`),
  KEY `fk_barrio_idx` (`FK_barrio`),
  KEY `fk_SituacionLaboral_idx` (`fk_SituacionLaboral`),
  CONSTRAINT `fk_barrio` FOREIGN KEY (`FK_barrio`) REFERENCES `barrio` (`ID_Barrio`),
  CONSTRAINT `fk_SituacionLaboral` FOREIGN KEY (`fk_SituacionLaboral`) REFERENCES `situacion_laboral` (`ID_SituacionLaboral`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `residente`
--

LOCK TABLES `residente` WRITE;
/*!40000 ALTER TABLE `residente` DISABLE KEYS */;
INSERT INTO `residente` VALUES ('49092510','Ema Eres','2008-10-09',1,15,1,_binary '\0');
/*!40000 ALTER TABLE `residente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `situacion_laboral`
--

DROP TABLE IF EXISTS `situacion_laboral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `situacion_laboral` (
  `Ocupado` bit(1) DEFAULT NULL,
  `Desocupado` bit(1) DEFAULT NULL,
  `semiOcupado` bit(1) DEFAULT NULL,
  `ID_SituacionLaboral` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID_SituacionLaboral`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `situacion_laboral`
--

LOCK TABLES `situacion_laboral` WRITE;
/*!40000 ALTER TABLE `situacion_laboral` DISABLE KEYS */;
INSERT INTO `situacion_laboral` VALUES (_binary '\0',_binary '',_binary '\0',1);
/*!40000 ALTER TABLE `situacion_laboral` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-11-19 19:52:24
