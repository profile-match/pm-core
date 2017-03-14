-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: IMP
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

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
-- Table structure for table `AVIS`
--

DROP TABLE IF EXISTS `AVIS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AVIS` (
  `ID` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `note` int(11) NOT NULL,
  `candidat_id` bigint(20) DEFAULT NULL,
  `recruteur_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK1algf4f7ugpwj8m1y1uohu9qy` (`candidat_id`),
  KEY `FKqm7bpa5d6lq4wv16batlnrw24` (`recruteur_id`),
  CONSTRAINT `FK1algf4f7ugpwj8m1y1uohu9qy` FOREIGN KEY (`candidat_id`) REFERENCES `Candidat` (`id`),
  CONSTRAINT `FKqm7bpa5d6lq4wv16batlnrw24` FOREIGN KEY (`recruteur_id`) REFERENCES `Recruteur` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AVIS`
--

LOCK TABLES `AVIS` WRITE;
/*!40000 ALTER TABLE `AVIS` DISABLE KEYS */;
/*!40000 ALTER TABLE `AVIS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CERTIFICATION`
--

DROP TABLE IF EXISTS `CERTIFICATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CERTIFICATION` (
  `certification_id` bigint(20) NOT NULL,
  `annee` datetime DEFAULT NULL,
  `intitule` varchar(255) DEFAULT NULL,
  `obligatoire` int(11) NOT NULL,
  PRIMARY KEY (`certification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CERTIFICATION`
--

LOCK TABLES `CERTIFICATION` WRITE;
/*!40000 ALTER TABLE `CERTIFICATION` DISABLE KEYS */;
INSERT INTO `CERTIFICATION` VALUES (6,NULL,'BARAN',2),(8,NULL,'BARAN',2);
/*!40000 ALTER TABLE `CERTIFICATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Candidat`
--

DROP TABLE IF EXISTS `Candidat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Candidat` (
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `isBanned` bit(1) NOT NULL,
  `isMale` bit(1) NOT NULL,
  `isSuspended` bit(1) NOT NULL,
  `loisirs` varchar(255) DEFAULT NULL,
  `naissance` datetime DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `telfix` varchar(255) DEFAULT NULL,
  `telperso` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Candidat`
--

LOCK TABLES `Candidat` WRITE;
/*!40000 ALTER TABLE `Candidat` DISABLE KEYS */;
INSERT INTO `Candidat` VALUES (2,'qsdqsdqsd','a@a','\0','','\0','Music','2017-03-14 23:00:00','Antoine ',NULL,'Baranouu','06060606060606','0505050505');
/*!40000 ALTER TABLE `Candidat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Candidat_CertificationCandidat`
--

DROP TABLE IF EXISTS `Candidat_CertificationCandidat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Candidat_CertificationCandidat` (
  `Candidat_id` bigint(20) NOT NULL,
  `certifications_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_ffwycwsscktipw72npjgda7oq` (`certifications_id`),
  KEY `FKmcc531nptdqo9dktw9fhije9t` (`Candidat_id`),
  CONSTRAINT `FKetv85r4mw9opvhuu9xjiap4dm` FOREIGN KEY (`certifications_id`) REFERENCES `CertificationCandidat` (`id`),
  CONSTRAINT `FKmcc531nptdqo9dktw9fhije9t` FOREIGN KEY (`Candidat_id`) REFERENCES `Candidat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Candidat_CertificationCandidat`
--

LOCK TABLES `Candidat_CertificationCandidat` WRITE;
/*!40000 ALTER TABLE `Candidat_CertificationCandidat` DISABLE KEYS */;
INSERT INTO `Candidat_CertificationCandidat` VALUES (2,3);
/*!40000 ALTER TABLE `Candidat_CertificationCandidat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Candidat_Competence`
--

DROP TABLE IF EXISTS `Candidat_Competence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Candidat_Competence` (
  `id` bigint(20) NOT NULL,
  `id_comp` bigint(20) NOT NULL,
  KEY `FK7mdwuypmn4iwm31ss06do766o` (`id_comp`),
  KEY `FKd7o7q4m7hlgvx9wk88tc0idfh` (`id`),
  CONSTRAINT `FK7mdwuypmn4iwm31ss06do766o` FOREIGN KEY (`id_comp`) REFERENCES `Competence` (`id`),
  CONSTRAINT `FKd7o7q4m7hlgvx9wk88tc0idfh` FOREIGN KEY (`id`) REFERENCES `Candidat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Candidat_Competence`
--

LOCK TABLES `Candidat_Competence` WRITE;
/*!40000 ALTER TABLE `Candidat_Competence` DISABLE KEYS */;
INSERT INTO `Candidat_Competence` VALUES (2,4);
/*!40000 ALTER TABLE `Candidat_Competence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Candidat_ExperiencePro`
--

DROP TABLE IF EXISTS `Candidat_ExperiencePro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Candidat_ExperiencePro` (
  `Candidat_id` bigint(20) NOT NULL,
  `experiencePro_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_197ydqfp2o0xojn7w5owymnum` (`experiencePro_id`),
  KEY `FKfu74vaqc808owtfj4k0x0xow2` (`Candidat_id`),
  CONSTRAINT `FKfu74vaqc808owtfj4k0x0xow2` FOREIGN KEY (`Candidat_id`) REFERENCES `Candidat` (`id`),
  CONSTRAINT `FKkr1x3s1v97i1a5k194i6tnba9` FOREIGN KEY (`experiencePro_id`) REFERENCES `ExperiencePro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Candidat_ExperiencePro`
--

LOCK TABLES `Candidat_ExperiencePro` WRITE;
/*!40000 ALTER TABLE `Candidat_ExperiencePro` DISABLE KEYS */;
/*!40000 ALTER TABLE `Candidat_ExperiencePro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Candidat_Formation`
--

DROP TABLE IF EXISTS `Candidat_Formation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Candidat_Formation` (
  `Candidat_id` bigint(20) NOT NULL,
  `formation_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_jnrrr8i4lyt6pigl9ffqetwkr` (`formation_id`),
  KEY `FKefj53y9yd1oe68iqchslwf065` (`Candidat_id`),
  CONSTRAINT `FKefj53y9yd1oe68iqchslwf065` FOREIGN KEY (`Candidat_id`) REFERENCES `Candidat` (`id`),
  CONSTRAINT `FKgqyxvx919nqnrgkdv09u1ydlc` FOREIGN KEY (`formation_id`) REFERENCES `Formation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Candidat_Formation`
--

LOCK TABLES `Candidat_Formation` WRITE;
/*!40000 ALTER TABLE `Candidat_Formation` DISABLE KEYS */;
/*!40000 ALTER TABLE `Candidat_Formation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CertificationCandidat`
--

DROP TABLE IF EXISTS `CertificationCandidat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CertificationCandidat` (
  `id` bigint(20) NOT NULL,
  `certification` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CertificationCandidat`
--

LOCK TABLES `CertificationCandidat` WRITE;
/*!40000 ALTER TABLE `CertificationCandidat` DISABLE KEYS */;
INSERT INTO `CertificationCandidat` VALUES (3,'BARAN');
/*!40000 ALTER TABLE `CertificationCandidat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Competence`
--

DROP TABLE IF EXISTS `Competence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Competence` (
  `id` bigint(20) NOT NULL,
  `competence` varchar(255) DEFAULT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Competence`
--

LOCK TABLES `Competence` WRITE;
/*!40000 ALTER TABLE `Competence` DISABLE KEYS */;
INSERT INTO `Competence` VALUES (4,'DIDIER',0);
/*!40000 ALTER TABLE `Competence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DOSSIER`
--

DROP TABLE IF EXISTS `DOSSIER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DOSSIER` (
  `poste_id` bigint(20) NOT NULL,
  `afficher_moyenne` int(11) NOT NULL,
  `date_publication` datetime DEFAULT NULL,
  `equipe_concernee` varchar(255) DEFAULT NULL,
  `id_recruteur` int(11) NOT NULL,
  `indice_salaire` varchar(255) DEFAULT NULL,
  `intitule` varchar(255) DEFAULT NULL,
  `lieu_travail` varchar(255) DEFAULT NULL,
  `organisation` varchar(255) DEFAULT NULL,
  `point_attention` varchar(255) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `resume` varchar(255) DEFAULT NULL,
  `salaire_max` int(11) NOT NULL,
  `salaire_min` int(11) NOT NULL,
  `type_contrat` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`poste_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DOSSIER`
--

LOCK TABLES `DOSSIER` WRITE;
/*!40000 ALTER TABLE `DOSSIER` DISABLE KEYS */;
INSERT INTO `DOSSIER` VALUES (5,0,'2017-03-14 15:09:23','qdsdqsd',1,'AT-1212','CHET DE PROJET','qsdqsdqsd','qsdqsd','','CHEF DE PROJET','C\'est bien venez',2300,1200,'CDD');
/*!40000 ALTER TABLE `DOSSIER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ExperiencePro`
--

DROP TABLE IF EXISTS `ExperiencePro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ExperiencePro` (
  `id` bigint(20) NOT NULL,
  `date_debut` datetime DEFAULT NULL,
  `date_fin` datetime DEFAULT NULL,
  `description_entreprise` varchar(255) DEFAULT NULL,
  `intitule_de_poste` varchar(255) DEFAULT NULL,
  `missions_effectuees` varchar(255) DEFAULT NULL,
  `nom_entreprise` varchar(255) DEFAULT NULL,
  `pays` varchar(255) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ExperiencePro`
--

LOCK TABLES `ExperiencePro` WRITE;
/*!40000 ALTER TABLE `ExperiencePro` DISABLE KEYS */;
/*!40000 ALTER TABLE `ExperiencePro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FONCTIONNELLE`
--

DROP TABLE IF EXISTS `FONCTIONNELLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FONCTIONNELLE` (
  `fonctionnelle_id` bigint(20) NOT NULL,
  `intitule` varchar(255) DEFAULT NULL,
  `obligatoire` int(11) NOT NULL,
  PRIMARY KEY (`fonctionnelle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FONCTIONNELLE`
--

LOCK TABLES `FONCTIONNELLE` WRITE;
/*!40000 ALTER TABLE `FONCTIONNELLE` DISABLE KEYS */;
INSERT INTO `FONCTIONNELLE` VALUES (9,'sdqsd',2);
/*!40000 ALTER TABLE `FONCTIONNELLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Formation`
--

DROP TABLE IF EXISTS `Formation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Formation` (
  `id` bigint(20) NOT NULL,
  `date_debut_format` datetime DEFAULT NULL,
  `date_fin_format` datetime DEFAULT NULL,
  `description_formation` varchar(255) DEFAULT NULL,
  `etablissement` varchar(255) DEFAULT NULL,
  `intitule_de_formation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Formation`
--

LOCK TABLES `Formation` WRITE;
/*!40000 ALTER TABLE `Formation` DISABLE KEYS */;
/*!40000 ALTER TABLE `Formation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Formation_Recruteur`
--

DROP TABLE IF EXISTS `Formation_Recruteur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Formation_Recruteur` (
  `formation_id` bigint(20) NOT NULL,
  `annee` datetime DEFAULT NULL,
  `intitule` varchar(255) DEFAULT NULL,
  `obligatoire` int(11) NOT NULL,
  PRIMARY KEY (`formation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Formation_Recruteur`
--

LOCK TABLES `Formation_Recruteur` WRITE;
/*!40000 ALTER TABLE `Formation_Recruteur` DISABLE KEYS */;
/*!40000 ALTER TABLE `Formation_Recruteur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LANGUE`
--

DROP TABLE IF EXISTS `LANGUE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LANGUE` (
  `langue_id` bigint(20) NOT NULL,
  `intitule` varchar(255) DEFAULT NULL,
  `obligatoire` int(11) NOT NULL,
  PRIMARY KEY (`langue_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LANGUE`
--

LOCK TABLES `LANGUE` WRITE;
/*!40000 ALTER TABLE `LANGUE` DISABLE KEYS */;
/*!40000 ALTER TABLE `LANGUE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `METIER`
--

DROP TABLE IF EXISTS `METIER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `METIER` (
  `metier_id` bigint(20) NOT NULL,
  `intitule` varchar(255) DEFAULT NULL,
  `obligatoire` int(11) NOT NULL,
  PRIMARY KEY (`metier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `METIER`
--

LOCK TABLES `METIER` WRITE;
/*!40000 ALTER TABLE `METIER` DISABLE KEYS */;
INSERT INTO `METIER` VALUES (7,'DIDIER',2),(10,'DIDIER',2),(11,'TEST',2);
/*!40000 ALTER TABLE `METIER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MessageSignalementCandidat`
--

DROP TABLE IF EXISTS `MessageSignalementCandidat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MessageSignalementCandidat` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `idCandidat` int(11) NOT NULL,
  `idReported` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MessageSignalementCandidat`
--

LOCK TABLES `MessageSignalementCandidat` WRITE;
/*!40000 ALTER TABLE `MessageSignalementCandidat` DISABLE KEYS */;
/*!40000 ALTER TABLE `MessageSignalementCandidat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POSTE_CANDIDAT_POSTULE`
--

DROP TABLE IF EXISTS `POSTE_CANDIDAT_POSTULE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POSTE_CANDIDAT_POSTULE` (
  `poste_id` bigint(20) NOT NULL,
  `candidat_id` bigint(20) NOT NULL,
  PRIMARY KEY (`poste_id`,`candidat_id`),
  KEY `FKnpesw9neg72yfjbvydb11ludy` (`candidat_id`),
  CONSTRAINT `FKnpesw9neg72yfjbvydb11ludy` FOREIGN KEY (`candidat_id`) REFERENCES `Candidat` (`id`),
  CONSTRAINT `FKr8kcpv0wn79pngcsk6b9xqtsy` FOREIGN KEY (`poste_id`) REFERENCES `DOSSIER` (`poste_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POSTE_CANDIDAT_POSTULE`
--

LOCK TABLES `POSTE_CANDIDAT_POSTULE` WRITE;
/*!40000 ALTER TABLE `POSTE_CANDIDAT_POSTULE` DISABLE KEYS */;
INSERT INTO `POSTE_CANDIDAT_POSTULE` VALUES (5,2);
/*!40000 ALTER TABLE `POSTE_CANDIDAT_POSTULE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POSTE_CERTIFICATION`
--

DROP TABLE IF EXISTS `POSTE_CERTIFICATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POSTE_CERTIFICATION` (
  `poste_id` bigint(20) NOT NULL,
  `certification_id` bigint(20) NOT NULL,
  PRIMARY KEY (`poste_id`,`certification_id`),
  KEY `FK2lluliglodhvi3wkall8rhco5` (`certification_id`),
  CONSTRAINT `FK2lluliglodhvi3wkall8rhco5` FOREIGN KEY (`certification_id`) REFERENCES `CERTIFICATION` (`certification_id`),
  CONSTRAINT `FKitxh0h8hqtydxv2v0wallvklt` FOREIGN KEY (`poste_id`) REFERENCES `DOSSIER` (`poste_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POSTE_CERTIFICATION`
--

LOCK TABLES `POSTE_CERTIFICATION` WRITE;
/*!40000 ALTER TABLE `POSTE_CERTIFICATION` DISABLE KEYS */;
INSERT INTO `POSTE_CERTIFICATION` VALUES (5,8);
/*!40000 ALTER TABLE `POSTE_CERTIFICATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POSTE_FONCTIONNELLE`
--

DROP TABLE IF EXISTS `POSTE_FONCTIONNELLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POSTE_FONCTIONNELLE` (
  `poste_id` bigint(20) NOT NULL,
  `fonctionnelle_id` bigint(20) NOT NULL,
  PRIMARY KEY (`poste_id`,`fonctionnelle_id`),
  KEY `FKu6ob0jge41pcec4i1962xqm6` (`fonctionnelle_id`),
  CONSTRAINT `FK812fjmn3huu106759jvbj05ga` FOREIGN KEY (`poste_id`) REFERENCES `DOSSIER` (`poste_id`),
  CONSTRAINT `FKu6ob0jge41pcec4i1962xqm6` FOREIGN KEY (`fonctionnelle_id`) REFERENCES `FONCTIONNELLE` (`fonctionnelle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POSTE_FONCTIONNELLE`
--

LOCK TABLES `POSTE_FONCTIONNELLE` WRITE;
/*!40000 ALTER TABLE `POSTE_FONCTIONNELLE` DISABLE KEYS */;
INSERT INTO `POSTE_FONCTIONNELLE` VALUES (5,9);
/*!40000 ALTER TABLE `POSTE_FONCTIONNELLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POSTE_FORMATION`
--

DROP TABLE IF EXISTS `POSTE_FORMATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POSTE_FORMATION` (
  `poste_id` bigint(20) NOT NULL,
  `formation_id` bigint(20) NOT NULL,
  PRIMARY KEY (`poste_id`,`formation_id`),
  KEY `FK3wph6n58m0vrdlh2xg8sfsiro` (`formation_id`),
  CONSTRAINT `FK3wph6n58m0vrdlh2xg8sfsiro` FOREIGN KEY (`formation_id`) REFERENCES `Formation_Recruteur` (`formation_id`),
  CONSTRAINT `FKstkje11wjnijh7xapnw897dvg` FOREIGN KEY (`poste_id`) REFERENCES `DOSSIER` (`poste_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POSTE_FORMATION`
--

LOCK TABLES `POSTE_FORMATION` WRITE;
/*!40000 ALTER TABLE `POSTE_FORMATION` DISABLE KEYS */;
/*!40000 ALTER TABLE `POSTE_FORMATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POSTE_LANGUE`
--

DROP TABLE IF EXISTS `POSTE_LANGUE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POSTE_LANGUE` (
  `poste_id` bigint(20) NOT NULL,
  `langue_id` bigint(20) NOT NULL,
  PRIMARY KEY (`poste_id`,`langue_id`),
  KEY `FKc9l6bakpoelqk5wj8u75au73l` (`langue_id`),
  CONSTRAINT `FKc9l6bakpoelqk5wj8u75au73l` FOREIGN KEY (`langue_id`) REFERENCES `LANGUE` (`langue_id`),
  CONSTRAINT `FKicpbd28ki0m2t7op2myroi6ou` FOREIGN KEY (`poste_id`) REFERENCES `DOSSIER` (`poste_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POSTE_LANGUE`
--

LOCK TABLES `POSTE_LANGUE` WRITE;
/*!40000 ALTER TABLE `POSTE_LANGUE` DISABLE KEYS */;
/*!40000 ALTER TABLE `POSTE_LANGUE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POSTE_METIER`
--

DROP TABLE IF EXISTS `POSTE_METIER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POSTE_METIER` (
  `poste_id` bigint(20) NOT NULL,
  `metier_id` bigint(20) NOT NULL,
  PRIMARY KEY (`poste_id`,`metier_id`),
  KEY `FKb9t545w832v6gsrxkq3mr3p1c` (`metier_id`),
  CONSTRAINT `FKb9t545w832v6gsrxkq3mr3p1c` FOREIGN KEY (`metier_id`) REFERENCES `METIER` (`metier_id`),
  CONSTRAINT `FKtli57kabqa107vgfsihw6pot9` FOREIGN KEY (`poste_id`) REFERENCES `DOSSIER` (`poste_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POSTE_METIER`
--

LOCK TABLES `POSTE_METIER` WRITE;
/*!40000 ALTER TABLE `POSTE_METIER` DISABLE KEYS */;
INSERT INTO `POSTE_METIER` VALUES (5,10),(5,11);
/*!40000 ALTER TABLE `POSTE_METIER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POSTE_SAVOIR_ETRE`
--

DROP TABLE IF EXISTS `POSTE_SAVOIR_ETRE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POSTE_SAVOIR_ETRE` (
  `poste_id` bigint(20) NOT NULL,
  `savoir_etre_id` bigint(20) NOT NULL,
  PRIMARY KEY (`poste_id`,`savoir_etre_id`),
  KEY `FKlramh18s0x2p90s4fr04v4scr` (`savoir_etre_id`),
  CONSTRAINT `FKlramh18s0x2p90s4fr04v4scr` FOREIGN KEY (`savoir_etre_id`) REFERENCES `SAVOIR_ETRE` (`savoir_etre_id`),
  CONSTRAINT `FKqxlktdenvqi9kpqcmw9eaclem` FOREIGN KEY (`poste_id`) REFERENCES `DOSSIER` (`poste_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POSTE_SAVOIR_ETRE`
--

LOCK TABLES `POSTE_SAVOIR_ETRE` WRITE;
/*!40000 ALTER TABLE `POSTE_SAVOIR_ETRE` DISABLE KEYS */;
/*!40000 ALTER TABLE `POSTE_SAVOIR_ETRE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POSTE_SAVOIR_FAIRE`
--

DROP TABLE IF EXISTS `POSTE_SAVOIR_FAIRE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POSTE_SAVOIR_FAIRE` (
  `poste_id` bigint(20) NOT NULL,
  `savoir_faire_id` bigint(20) NOT NULL,
  PRIMARY KEY (`poste_id`,`savoir_faire_id`),
  KEY `FKq8q5yvqqohco7jbc2ckrvvmjo` (`savoir_faire_id`),
  CONSTRAINT `FK2lqs6vmur7l7krg5ryufv2my4` FOREIGN KEY (`poste_id`) REFERENCES `DOSSIER` (`poste_id`),
  CONSTRAINT `FKq8q5yvqqohco7jbc2ckrvvmjo` FOREIGN KEY (`savoir_faire_id`) REFERENCES `SAVOIR_FAIRE` (`savoir_faire_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POSTE_SAVOIR_FAIRE`
--

LOCK TABLES `POSTE_SAVOIR_FAIRE` WRITE;
/*!40000 ALTER TABLE `POSTE_SAVOIR_FAIRE` DISABLE KEYS */;
/*!40000 ALTER TABLE `POSTE_SAVOIR_FAIRE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POSTE_SAVOIR_SPECIFICATION`
--

DROP TABLE IF EXISTS `POSTE_SAVOIR_SPECIFICATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POSTE_SAVOIR_SPECIFICATION` (
  `poste_id` bigint(20) NOT NULL,
  `savoir_specification_id` bigint(20) NOT NULL,
  PRIMARY KEY (`poste_id`,`savoir_specification_id`),
  KEY `FK4aql51awux40p09eto1ik4hux` (`savoir_specification_id`),
  CONSTRAINT `FK4aql51awux40p09eto1ik4hux` FOREIGN KEY (`savoir_specification_id`) REFERENCES `SAVOIR_SPECIFICATION` (`savoir_specification_id`),
  CONSTRAINT `FKf0m3hf37dsqgm00qt772mwqfr` FOREIGN KEY (`poste_id`) REFERENCES `DOSSIER` (`poste_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POSTE_SAVOIR_SPECIFICATION`
--

LOCK TABLES `POSTE_SAVOIR_SPECIFICATION` WRITE;
/*!40000 ALTER TABLE `POSTE_SAVOIR_SPECIFICATION` DISABLE KEYS */;
/*!40000 ALTER TABLE `POSTE_SAVOIR_SPECIFICATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POSTE_TECHNIQUE`
--

DROP TABLE IF EXISTS `POSTE_TECHNIQUE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POSTE_TECHNIQUE` (
  `poste_id` bigint(20) NOT NULL,
  `technique_id` bigint(20) NOT NULL,
  PRIMARY KEY (`poste_id`,`technique_id`),
  KEY `FK7j2h82gsqxq2wfoxl95yptvga` (`technique_id`),
  CONSTRAINT `FK7j2h82gsqxq2wfoxl95yptvga` FOREIGN KEY (`technique_id`) REFERENCES `TECHNIQUE` (`technique_id`),
  CONSTRAINT `FKlv9ek8isg3tbsh6bo622itunh` FOREIGN KEY (`poste_id`) REFERENCES `DOSSIER` (`poste_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POSTE_TECHNIQUE`
--

LOCK TABLES `POSTE_TECHNIQUE` WRITE;
/*!40000 ALTER TABLE `POSTE_TECHNIQUE` DISABLE KEYS */;
INSERT INTO `POSTE_TECHNIQUE` VALUES (5,12);
/*!40000 ALTER TABLE `POSTE_TECHNIQUE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Recruteur`
--

DROP TABLE IF EXISTS `Recruteur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Recruteur` (
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `isBanned` bit(1) NOT NULL,
  `isMale` bit(1) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `telfix` varchar(255) DEFAULT NULL,
  `telperso` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Recruteur`
--

LOCK TABLES `Recruteur` WRITE;
/*!40000 ALTER TABLE `Recruteur` DISABLE KEYS */;
INSERT INTO `Recruteur` VALUES (1,NULL,'r@r','\0','\0',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `Recruteur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SAVOIR_ETRE`
--

DROP TABLE IF EXISTS `SAVOIR_ETRE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SAVOIR_ETRE` (
  `savoir_etre_id` bigint(20) NOT NULL,
  `intitule` varchar(255) DEFAULT NULL,
  `obligatoire` int(11) NOT NULL,
  PRIMARY KEY (`savoir_etre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SAVOIR_ETRE`
--

LOCK TABLES `SAVOIR_ETRE` WRITE;
/*!40000 ALTER TABLE `SAVOIR_ETRE` DISABLE KEYS */;
/*!40000 ALTER TABLE `SAVOIR_ETRE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SAVOIR_FAIRE`
--

DROP TABLE IF EXISTS `SAVOIR_FAIRE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SAVOIR_FAIRE` (
  `savoir_faire_id` bigint(20) NOT NULL,
  `intitule` varchar(255) DEFAULT NULL,
  `obligatoire` int(11) NOT NULL,
  PRIMARY KEY (`savoir_faire_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SAVOIR_FAIRE`
--

LOCK TABLES `SAVOIR_FAIRE` WRITE;
/*!40000 ALTER TABLE `SAVOIR_FAIRE` DISABLE KEYS */;
/*!40000 ALTER TABLE `SAVOIR_FAIRE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SAVOIR_SPECIFICATION`
--

DROP TABLE IF EXISTS `SAVOIR_SPECIFICATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SAVOIR_SPECIFICATION` (
  `savoir_specification_id` bigint(20) NOT NULL,
  `intitule` varchar(255) DEFAULT NULL,
  `obligatoire` int(11) NOT NULL,
  PRIMARY KEY (`savoir_specification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SAVOIR_SPECIFICATION`
--

LOCK TABLES `SAVOIR_SPECIFICATION` WRITE;
/*!40000 ALTER TABLE `SAVOIR_SPECIFICATION` DISABLE KEYS */;
/*!40000 ALTER TABLE `SAVOIR_SPECIFICATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TECHNIQUE`
--

DROP TABLE IF EXISTS `TECHNIQUE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TECHNIQUE` (
  `technique_id` bigint(20) NOT NULL,
  `intitule` varchar(255) DEFAULT NULL,
  `obligatoire` int(11) NOT NULL,
  PRIMARY KEY (`technique_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TECHNIQUE`
--

LOCK TABLES `TECHNIQUE` WRITE;
/*!40000 ALTER TABLE `TECHNIQUE` DISABLE KEYS */;
INSERT INTO `TECHNIQUE` VALUES (12,'TEST',2);
/*!40000 ALTER TABLE `TECHNIQUE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (13),(13),(13),(13),(13),(13),(13),(13),(13),(13),(13),(13),(13),(13),(13),(13),(13),(13);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utilisateur` (
  `email` varchar(45) NOT NULL,
  `id` bigint(20) NOT NULL,
  `motdepasse` varchar(45) DEFAULT NULL,
  `safe` int(11) NOT NULL,
  `tokenacces` varchar(255) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES ('a@a',2,'a',1000,NULL,'C'),('r@r',1,'r',1000,NULL,'R');
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-14 15:30:21
