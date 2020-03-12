-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: interviewapp
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `body` varchar(300) NOT NULL,
  `added_by` varchar(45) DEFAULT NULL,
  `comment` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idquestions_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Table for storing questions, that will be tied in to tags';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (4,' JDK, JRE and JVM?',' Explain JDK, JRE and JVM?','Nishant','I love comments'),(6,'java main funtion','Explain public static void main(String args[]) in Java','Mauricio','Just explain'),(8,'java platform','Why Java is platform independent?','Pete','HINT: Private/public/protected'),(10,'OOP','Why Java is not 100% Object-oriented?','Pete','remember primitive data types?'),(11,'java classes','What is the difference between an Inner Class and a Sub-Class?','Pete','they are all classes'),(12,'java constructors','What are constructors in Java?','Nishant','no description'),(14,'java singleton class','What is singleton class in Java and how can we make a class singleton?','Morgan','singleton!!!!'),(15,'java access modifiers','What is an access modifier in java?','Morgan',' '),(16,'java arraayList and vectors','What is the difference between ArrayList and vector in Java?','Mauricio',' '),(17,'java equals and \'==\'','What is the difference between equals() and == in Java?','Mauricio','they cannot be equal of course'),(18,'Heap and Stack','What are the differences between Heap and Stack Memory in Java?','Pete','gotta say something'),(19,'java package','What is a package in Java? List down various advantages of packages','Nishant','crazy comments'),(20,'Pointers','Why pointers are not used in Java?','Adam','who needs pointers in java'),(21,'java compiler','What is JIT compiler in Java?','Adam','the code would not compile'),(22,'java class','Define a Java Class.','Adam','a school class?'),(23,'java OOPs','What are the main concepts of OOPs in Java?','Nishant','basic concepts bro!'),(24,'local variable vs instance variable','What is the difference between a local variable and an instance variable?','Nishant','no difference'),(25,'hibernate JPA','What is Java Persistence API (JPA)?','Morgan',NULL),(26,'hibernate vs jdbc','What are the advantages of Hibernate over JDBC','morgan','comments'),(27,'hibernate configuration file','What is hibernate configuration file?','Nishant','it is a configuration file'),(28,'hibernate Session','What is Hibernate Session and how to get it?','Nishant','hibernate sessions'),(29,'spring framework features','What are the different features of Spring Framework','Nishant',NULL),(30,'spring application components','What are the different components of a Spring application?','Nishant',NULL),(31,'spring dependency injection','In how many ways can Dependency Injection be done?','Nishant',''),(32,'spring bean scope',' How many bean scopes are supported by Spring?','Mauricio',NULL),(33,'AngularJS routes','Explain what Angular JS routes does ?','Mauricio',NULL),(34,'angular directives','Explain what are directives ? Mention some of the most commonly used directives in AngularJS application ?','Mauricio','routing in angular'),(35,'angular data binding','Explain what is data binding in AngularJS ?','Adam',NULL),(36,'string interpolation in angularJS','Explain what is string interpolation in Angular.js ?','Nishant',NULL),(37,'link vs compile in angularJS',' Explain what is the difference between link and compile in Angular.js?','Nishant','so many comments'),(38,'ngModel styling form','Mention what are the styling form that ngModel adds to CSS classes ?','Nishant',NULL),(39,'AngularJS scope','Mention what are the characteristics of “Scope”?','Nishant','scope scope scope'),(40,'who created AngularJS?','Who created Angular JS ?','Nishant','Don\'t know!'),(41,'collection framework','What is the Collection framework in Java?','Nishant',NULL),(42,'array vs collection','What are the main differences between array and collection?','Nishant',NULL),(43,'collection framework interfaces',' Explain various interfaces used in Collection framework?','Nishant',NULL),(44,'arrayList vs linkedList','What is the difference between ArrayList and LinkedList?','Adam ','Just know that they are differenta'),(45,'iterator vs listIterator','What is the difference between Iterator and ListIterator?','Adam',NULL),(46,'list vs set','What is the difference between List and Set?','Adam',NULL),(47,'HashSet vs TreeSet','What is the difference between HashSet and TreeSet?','Adam','amazing collections!!!!'),(48,'spring scope','  How do you provide configuration metadata to the Spring Container? ',NULL,'new comments do not hurt'),(121,'aaaaaaaaa','fdddd','dddd','ddd'),(127,'trsdesfd','fsssfs','dddd','ssssss'),(128,'cccc','ccc','cccc','ccc');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions_with_tags`
--

DROP TABLE IF EXISTS `questions_with_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions_with_tags` (
  `question_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`question_id`,`tag_id`),
  KEY `question_id_idx` (`question_id`),
  KEY `tag_id_idx` (`tag_id`),
  CONSTRAINT `question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `tag_id` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='This is a join table managed by JPA, where combination of the foreign keys (question.id and tag.id) will be its composite primary key.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions_with_tags`
--

LOCK TABLES `questions_with_tags` WRITE;
/*!40000 ALTER TABLE `questions_with_tags` DISABLE KEYS */;
INSERT INTO `questions_with_tags` VALUES (4,9),(4,11),(10,9),(17,9),(17,11),(18,11),(18,14),(19,11),(19,14),(21,9),(21,11),(21,17),(25,11),(25,16),(25,17),(26,11),(26,16),(27,11),(27,17),(28,11),(28,16),(28,17),(48,11),(48,14);
/*!40000 ALTER TABLE `questions_with_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Stores tags that will be related to questions';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (14,'AngularJS'),(22,'Collections'),(21,'Hibernate'),(9,'Java'),(19,'Javascript'),(17,'Level-10'),(16,'Level-11'),(11,'Level-12'),(20,'Selenium'),(10,'Spring'),(15,'SpringBoot'),(18,'Sql');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-12 16:20:14
