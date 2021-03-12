-- MySQL dump 10.13  Distrib 8.0.23, for Linux (x86_64)
--
-- Host: localhost    Database: bd201235
-- ------------------------------------------------------
-- Server version	8.0.23-0ubuntu0.20.04.1

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
-- Dumping data for table `cita`
--

LOCK TABLES `cita` WRITE;
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
INSERT INTO `cita` VALUES (19,'24/03/21',12,3),(16,'26/03/21',10,3),(18,'23/03/21',11,2),(12,'26/03/21',7,2),(11,'18/03/21',6,3);
/*!40000 ALTER TABLE `cita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `duenom`
--

LOCK TABLES `duenom` WRITE;
/*!40000 ALTER TABLE `duenom` DISABLE KEYS */;
INSERT INTO `duenom` VALUES (17,'Tobias','las palmas','87871','tobias10@gmail.com'),(16,'magin','comitan','9613454','titk@hotmail.com'),(18,'lazaro','las marias','987671','laz@ids.com'),(19,'junior','revolucion','87651','juniorleop@hotmail.com'),(13,'carlos','lomas','23433','carlos@gmail.com'),(12,'jona','revolucion','34565','jona@gmail.com');
/*!40000 ALTER TABLE `duenom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `mascota`
--

LOCK TABLES `mascota` WRITE;
/*!40000 ALTER TABLE `mascota` DISABLE KEYS */;
INSERT INTO `mascota` VALUES (7,'tere',13,8),(6,'robbin',12,7),(11,'coco',17,12),(10,'solvino',16,11),(12,'kaiser',18,13),(15,'oliver',19,16),(14,'pega',19,15);
/*!40000 ALTER TABLE `mascota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `medicamento`
--

LOCK TABLES `medicamento` WRITE;
/*!40000 ALTER TABLE `medicamento` DISABLE KEYS */;
INSERT INTO `medicamento` VALUES (1,654321,'Bolfo - Talco Antipulgas','Propoxur','10/10/25'),(2,123456,'Golden Dog - Enjuage Bucal Antisarro','agentes tensoactivos','10/10/25'),(3,135790,'BROSIN TUBO','Sulfato de Neomicina','10/10/25'),(4,98765,'BRAVO SPRAY','Permetrina','10/10/25'),(23,212121,'ketorolaco','coca','18/12/25');
/*!40000 ALTER TABLE `medicamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `servicio`
--

LOCK TABLES `servicio` WRITE;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
INSERT INTO `servicio` VALUES (1,'Cirugia',500),(2,'Vacuna',50),(3,'Baño',150);
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipom`
--

LOCK TABLES `tipom` WRITE;
/*!40000 ALTER TABLE `tipom` DISABLE KEYS */;
INSERT INTO `tipom` VALUES (1,'Perro','Hembra','husky'),(2,'Gato','Macho','Persa'),(3,'Serpiente','Hembra','Cascabel'),(4,'Murcielago','Macho','negro'),(5,'perro','Macho','pastor'),(6,'perro','Macho','bol'),(7,'perro','Hembra','calle'),(8,'gato','Macho','negra'),(12,'cotorro','Hembra','verde'),(11,'jirafa','Macho','alta'),(13,'perro','Macho','blanco'),(16,'serpiente','Hembra','nano'),(15,'canguro','Hembra','blanca');
/*!40000 ALTER TABLE `tipom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (4,'ozel','123456','false','admin'),(5,'kevin','123456','false','user');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-12 10:18:52
