-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: storyteller
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cuento`
--

DROP TABLE IF EXISTS `cuento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `texto_espanol` mediumtext NOT NULL,
  `completado` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuento`
--

LOCK TABLES `cuento` WRITE;
/*!40000 ALTER TABLE `cuento` DISABLE KEYS */;
INSERT INTO `cuento` VALUES (1,'El arbol mágico','Hace mucho mucho tiempo, un niño paseaba por un prado en cuyo centro encontró un árbol con un cartel que decía: soy un árbol encantado, si dices las palabras mágicas, lo verás.\n\nEl niño trató de acertar el hechizo, y probó con abracadabra, supercalifragilisticoespialidoso, tan-ta-ta-chán, y muchas otras, pero nada. Rendido, se tiró suplicante, diciendo: \"¡por favor, arbolito!\", y entonces, se abrió una gran puerta en el árbol. Todo estaba oscuro, menos un cartel que decía: \"sigue haciendo magia\". Entonces el niño dijo \"¡¡Gracias, arbolito!!\", y se encendió dentro del árbol una luz que alumbraba un camino hacia una gran montaña de juguetes y chocolate.\n\nEl niño pudo llevar a todos sus amigos a aquel árbol y tener la mejor fiesta del mundo, y por eso se dice siempre que \"por favor\" y \"gracias\", son las palabras mágicas.',0),(2,'Porque se deprimió el dragón','Una lluviosa noche de octubre, un dragón solitario y amargado paseaba en lo más recóndito de su castillo. Melancólico, el dragón recordaba la triste noche en que fue el fin de su alegría, cuando el pueblo cobró venganza contra su familia por un malentendido. Bajo el efecto de una grave depresión, el dragón decidió encerrarse de por vida.',0),(3,'Un cuento de amistad','A un bosque entre montañas verdosas iban a jugar todas las tardes un par de pequeñas. Cierto día encontraron una ramita muy pequeña. Las dos niñas se encargaron de cuidar esa ramita. Le daban agua y la cuidaban mucho. Hasta que un día notaron que a la rama le estaban brotando flores. Las chicas crecieron y ese lugar seguía siendo su lugar preferido. Con el paso del tiempo la ramita se convirtió en un gran árbol. Esa noche pasó algo mágico, el árbol estaba rodeado de mariposas de colores y como era de noche las mariposas brillaban bajo la luz de la luna.',0),(4,'El ojo sin casa','Un ojo muy mirón, en busca de dónde vivir, paseaba todos los días por la calle de caracol. Por más que miraba, no encontraba nada. Pasaron los días y el ojo perdía las esperanzas, hasta que una señora se le acercó y le dijo: -¿Tú qué me miras? Llamó a un policía y al pobre ojo lo arrestaron. Él, muy triste, le contó todo al policía. Al escuchar su historia, le ofreció su casa. Aunque no era mucho, era todo para el ojo.',0),(5,'La guerra del planeta de los magos','Los magos del norte estaban inconformes con los magos del sur, ya que ellos eran más poderosos y poseían más territorio. Así que decidieron atacar al rey William, un mago de más de 6000 años que no pudo defenderse al ser tocado por la espada. Su hijo Rugart asumió el trono. Enfureció al enterase de quién asesinó a su padre. Estalló medio planeta con una poderosa llama de fuego. Por eso la magia terminó.',0),(6,'El elefante pantalones','El elefante don Luis podaba el pasto cuando de pronto pasó una cebra vendiendo pantalones. Se los quiso probar pero ninguno le quedaba. Entonces hizo un enorme berrinche, tan enorme como él y se tiró al piso y se arrastró por todo el lugar. Los animales estaban muy asustados. Pero la cebra le dijo que la siguiente semana pasaría con nuevos modelos y tallas más grandes. Esperó ansioso a la siguiente semana que volvió a pasar. Feliz, el elefante se probó tres y le quedaron súper bien.',0);
/*!40000 ALTER TABLE `cuento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `palabra`
--

DROP TABLE IF EXISTS `palabra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `palabra` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_cuento` int DEFAULT NULL,
  `palabra_espanol` varchar(30) NOT NULL,
  `palabra_ingles` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cuento` (`id_cuento`),
  CONSTRAINT `palabra_ibfk_1` FOREIGN KEY (`id_cuento`) REFERENCES `cuento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `palabra`
--

LOCK TABLES `palabra` WRITE;
/*!40000 ALTER TABLE `palabra` DISABLE KEYS */;
/*!40000 ALTER TABLE `palabra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pregunta`
--

DROP TABLE IF EXISTS `pregunta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pregunta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_cuento` int DEFAULT NULL,
  `pregunta_espanol` varchar(30) NOT NULL,
  `pregunta_ingles` varchar(30) NOT NULL,
  `respuesta` varchar(30) NOT NULL,
  `lenguaje_respuesta` enum('espanol','ingles') DEFAULT NULL,
  `contestada` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id_cuento` (`id_cuento`),
  CONSTRAINT `pregunta_ibfk_1` FOREIGN KEY (`id_cuento`) REFERENCES `cuento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pregunta`
--

LOCK TABLES `pregunta` WRITE;
/*!40000 ALTER TABLE `pregunta` DISABLE KEYS */;
/*!40000 ALTER TABLE `pregunta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-28 20:52:52
