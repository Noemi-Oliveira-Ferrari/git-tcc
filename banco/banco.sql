-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: db_daumhelp
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_categoria`
--

DROP TABLE IF EXISTS `tbl_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_categoria` (
  `id_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(50) NOT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_categoria`
--

LOCK TABLES `tbl_categoria` WRITE;
/*!40000 ALTER TABLE `tbl_categoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_cidade`
--

DROP TABLE IF EXISTS `tbl_cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_cidade` (
  `id_cidade` int(11) NOT NULL AUTO_INCREMENT,
  `cidade` varchar(100) NOT NULL,
  `id_micro` int(11) NOT NULL,
  PRIMARY KEY (`id_cidade`),
  KEY `fk_micro_idx` (`id_micro`),
  CONSTRAINT `fk_micro` FOREIGN KEY (`id_micro`) REFERENCES `tbl_microrregiao` (`id_micro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cidade`
--

LOCK TABLES `tbl_cidade` WRITE;
/*!40000 ALTER TABLE `tbl_cidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_endereco`
--

DROP TABLE IF EXISTS `tbl_endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_endereco` (
  `id_endereco` int(11) NOT NULL AUTO_INCREMENT,
  `logradouro` varchar(200) NOT NULL,
  `cep` varchar(100) NOT NULL,
  `bairro` varchar(100) NOT NULL,
  `id_cidade` int(11) NOT NULL,
  PRIMARY KEY (`id_endereco`),
  KEY `fk_cidade_idx` (`id_cidade`),
  CONSTRAINT `fk_cidade` FOREIGN KEY (`id_cidade`) REFERENCES `tbl_cidade` (`id_cidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_endereco`
--

LOCK TABLES `tbl_endereco` WRITE;
/*!40000 ALTER TABLE `tbl_endereco` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_microrregiao`
--

DROP TABLE IF EXISTS `tbl_microrregiao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_microrregiao` (
  `id_micro` int(11) NOT NULL AUTO_INCREMENT,
  `micro` varchar(100) NOT NULL,
  `id_uf` int(11) NOT NULL,
  PRIMARY KEY (`id_micro`),
  KEY `fk_uf_idx` (`id_uf`),
  CONSTRAINT `fk_uf` FOREIGN KEY (`id_uf`) REFERENCES `tbl_uf` (`id_uf`)
) ENGINE=InnoDB AUTO_INCREMENT=53002 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_microrregiao`
--

LOCK TABLES `tbl_microrregiao` WRITE;
/*!40000 ALTER TABLE `tbl_microrregiao` DISABLE KEYS */;
INSERT INTO `tbl_microrregiao` VALUES (11001,'Porto Velho',11),(11002,'Guajará-Mirim',11),(11003,'Ariquemes',11),(11004,'Ji-Paraná',11),(11005,'Alvorada D\'Oeste',11),(11006,'Cacoal',11),(11007,'Vilhena',11),(11008,'Colorado do Oeste',11),(12001,'Cruzeiro do Sul',12),(12002,'Tarauacá',12),(12003,'Sena Madureira',12),(12004,'Rio Branco',12),(12005,'Brasiléia',12),(13001,'Rio Negro',13),(13002,'Japurá',13),(13003,'Alto Solimões',13),(13004,'Juruá',13),(13005,'Tefé',13),(13006,'Coari',13),(13007,'Manaus',13),(13008,'Rio Preto da Eva',13),(13009,'Itacoatiara',13),(13010,'Parintins',13),(13011,'Boca do Acre',13),(13012,'Purus',13),(13013,'Madeira',13),(14001,'Boa Vista',14),(14002,'Nordeste de Roraima',14),(14003,'Caracaraí',14),(14004,'Sudeste de Roraima',14),(15001,'Óbidos',15),(15002,'Santarém',15),(15003,'Almeirim',15),(15004,'Portel',15),(15005,'Furos de Breves',15),(15006,'Arari',15),(15007,'Belém',15),(15008,'Castanhal',15),(15009,'Salgado',15),(15010,'Bragantina',15),(15011,'Cametá',15),(15012,'Tomé-Açu',15),(15013,'Guamá',15),(15014,'Itaituba',15),(15015,'Altamira',15),(15016,'Tucuruí',15),(15017,'Paragominas',15),(15018,'São Félix do Xingu',15),(15019,'Parauapebas',15),(15020,'Marabá',15),(15021,'Redenção',15),(15022,'Conceição do Araguaia',15),(16001,'Oiapoque',16),(16002,'Amapá',16),(16003,'Macapá',16),(16004,'Mazagão',16),(17001,'Bico do Papagaio',17),(17002,'Araguaína',17),(17003,'Miracema do Tocantins',17),(17004,'Rio Formoso',17),(17005,'Gurupi',17),(17006,'Porto Nacional',17),(17007,'Jalapão',17),(17008,'Dianópolis',17),(21001,'Litoral Ocidental Maranhense',21),(21002,'Aglomeração Urbana de São Luís',21),(21003,'Rosário',21),(21004,'Lençóis Maranhenses',21),(21005,'Baixada Maranhense',21),(21006,'Itapecuru Mirim',21),(21007,'Gurupi',21),(21008,'Pindaré',21),(21009,'Imperatriz',21),(21010,'Médio Mearim',21),(21011,'Alto Mearim e Grajaú',21),(21012,'Presidente Dutra',21),(21013,'Baixo Parnaíba Maranhense',21),(21014,'Chapadinha',21),(21015,'Codó',21),(21016,'Coelho Neto',21),(21017,'Caxias',21),(21018,'Chapadas do Alto Itapecuru',21),(21019,'Porto Franco',21),(21020,'Gerais de Balsas',21),(21021,'Chapadas das Mangabeiras',21),(22001,'Baixo Parnaíba Piauiense',22),(22002,'Litoral Piauiense',22),(22003,'Teresina',22),(22004,'Campo Maior',22),(22005,'Médio Parnaíba Piauiense',22),(22006,'Valença do Piauí',22),(22007,'Alto Parnaíba Piauiense',22),(22008,'Bertolínia',22),(22009,'Floriano',22),(22010,'Alto Médio Gurguéia',22),(22011,'São Raimundo Nonato',22),(22012,'Chapadas do Extremo Sul Piauiense',22),(22013,'Picos',22),(22014,'Pio IX',22),(22015,'Alto Médio Canindé',22),(23001,'Litoral de Camocim e Acaraú',23),(23002,'Ibiapaba',23),(23003,'Coreaú',23),(23004,'Meruoca',23),(23005,'Sobral',23),(23006,'Ipu',23),(23007,'Santa Quitéria',23),(23008,'Itapipoca',23),(23009,'Baixo Curu',23),(23010,'Uruburetama',23),(23011,'Médio Curu',23),(23012,'Canindé',23),(23013,'Baturité',23),(23014,'Chorozinho',23),(23015,'Cascavel',23),(23016,'Fortaleza',23),(23017,'Pacajus',23),(23018,'Sertão de Cratéus',23),(23019,'Sertão de Quixeramobim',23),(23020,'Sertão de Inhamuns',23),(23021,'Sertão de Senador Pompeu',23),(23022,'Litoral de Aracati',23),(23023,'Baixo Jaguaribe',23),(23024,'Médio Jaguaribe',23),(23025,'Serra do Pereiro',23),(23026,'Iguatu',23),(23027,'Várzea Alegre',23),(23028,'Lavras da Mangabeira',23),(23029,'Chapada do Araripe',23),(23030,'Caririaçu',23),(23031,'Barro',23),(23032,'Cariri',23),(23033,'Brejo Santo',23),(24001,'Mossoró',24),(24002,'Chapada do Apodi',24),(24003,'Médio Oeste',24),(24004,'Vale do Açu',24),(24005,'Serra de São Miguel',24),(24006,'Pau dos Ferros',24),(24007,'Umarizal',24),(24008,'Macau',24),(24009,'Angicos',24),(24010,'Serra de Santana',24),(24011,'Seridó Ocidental',24),(24012,'Seridó Oriental',24),(24013,'Baixa Verde',24),(24014,'Borborema Potiguar',24),(24015,'Agreste Potiguar',24),(24016,'Litoral Nordeste',24),(24017,'Macaíba',24),(24018,'Natal',24),(24019,'Litoral Sul',24),(25001,'Catolé do Rocha',25),(25002,'Cajazeiras',25),(25003,'Sousa',25),(25004,'Patos',25),(25005,'Piancó',25),(25006,'Itaporanga',25),(25007,'Serra do Teixeira',25),(25008,'Seridó Ocidental Paraibano',25),(25009,'Seridó Oriental Paraibano',25),(25010,'Cariri Ocidental',25),(25011,'Cariri Oriental',25),(25012,'Curimataú Ocidental',25),(25013,'Curimataú Oriental',25),(25014,'Esperança',25),(25015,'Brejo Paraibano',25),(25016,'Guarabira',25),(25017,'Campina Grande',25),(25018,'Itabaiana',25),(25019,'Umbuzeiro',25),(25020,'Litoral Norte',25),(25021,'Sapé',25),(25022,'João Pessoa',25),(25023,'Litoral Sul',25),(26001,'Araripina',26),(26002,'Salgueiro',26),(26003,'Pajeú',26),(26004,'Sertão do Moxotó',26),(26005,'Petrolina',26),(26006,'Itaparica',26),(26007,'Vale do Ipanema',26),(26008,'Vale do Ipojuca',26),(26009,'Alto Capibaribe',26),(26010,'Médio Capibaribe',26),(26011,'Garanhuns',26),(26012,'Brejo Pernambucano',26),(26013,'Mata Setentrional Pernambucana',26),(26014,'Vitória de Santo Antão',26),(26015,'Mata Meridional Pernambucana',26),(26016,'Itamaracá',26),(26017,'Recife',26),(26018,'Suape',26),(26019,'Fernando de Noronha',26),(27001,'Serrana do Sertão Alagoano',27),(27002,'Alagoana do Sertão do São Francisco',27),(27003,'Santana do Ipanema',27),(27004,'Batalha',27),(27005,'Palmeira dos Índios',27),(27006,'Arapiraca',27),(27007,'Traipu',27),(27008,'Serrana dos Quilombos',27),(27009,'Mata Alagoana',27),(27010,'Litoral Norte Alagoano',27),(27011,'Maceió',27),(27012,'São Miguel dos Campos',27),(27013,'Penedo',27),(28001,'Sergipana do Sertão do São Francisco',28),(28002,'Carira',28),(28003,'Nossa Senhora das Dores',28),(28004,'Agreste de Itabaiana',28),(28005,'Tobias Barreto',28),(28006,'Agreste de Lagarto',28),(28007,'Propriá',28),(28008,'Cotinguiba',28),(28009,'Japaratuba',28),(28010,'Baixo Cotinguiba',28),(28011,'Aracaju',28),(28012,'Boquim',28),(28013,'Estância',28),(29001,'Barreiras',29),(29002,'Cotegipe',29),(29003,'Santa Maria da Vitória',29),(29004,'Juazeiro',29),(29005,'Paulo Afonso',29),(29006,'Barra',29),(29007,'Bom Jesus da Lapa',29),(29008,'Senhor do Bonfim',29),(29009,'Irecê',29),(29010,'Jacobina',29),(29011,'Itaberaba',29),(29012,'Feira de Santana',29),(29013,'Jeremoabo',29),(29014,'Euclides da Cunha',29),(29015,'Ribeira do Pombal',29),(29016,'Serrinha',29),(29017,'Alagoinhas',29),(29018,'Entre Rios',29),(29019,'Catu',29),(29020,'Santo Antônio de Jesus',29),(29021,'Salvador',29),(29022,'Boquira',29),(29023,'Seabra',29),(29024,'Jequié',29),(29025,'Livramento do Brumado',29),(29026,'Guanambi',29),(29027,'Brumado',29),(29028,'Vitória da Conquista',29),(29029,'Itapetinga',29),(29030,'Valença',29),(29031,'Ilhéus-Itabuna',29),(29032,'Porto Seguro',29),(31001,'Unaí',31),(31002,'Paracatu',31),(31003,'Januária',31),(31004,'Janaúba',31),(31005,'Salinas',31),(31006,'Pirapora',31),(31007,'Montes Claros',31),(31008,'Grão Mogol',31),(31009,'Bocaiúva',31),(31010,'Diamantina',31),(31011,'Capelinha',31),(31012,'Araçuaí',31),(31013,'Pedra Azul',31),(31014,'Almenara',31),(31015,'Teófilo Otoni',31),(31016,'Nanuque',31),(31017,'Ituiutaba',31),(31018,'Uberlândia',31),(31019,'Patrocínio',31),(31020,'Patos de Minas',31),(31021,'Frutal',31),(31022,'Uberaba',31),(31023,'Araxá',31),(31024,'Três Marias',31),(31025,'Curvelo',31),(31026,'Bom Despacho',31),(31027,'Sete Lagoas',31),(31028,'Conceição do Mato Dentro',31),(31029,'Pará de Minas',31),(31030,'Belo Horizonte',31),(31031,'Itabira',31),(31032,'Itaguara',31),(31033,'Ouro Preto',31),(31034,'Conselheiro Lafaiete',31),(31035,'Guanhães',31),(31036,'Peçanha',31),(31037,'Governador Valadares',31),(31038,'Mantena',31),(31039,'Ipatinga',31),(31040,'Caratinga',31),(31041,'Aimorés',31),(31042,'Piuí',31),(31043,'Divinópolis',31),(31044,'Formiga',31),(31045,'Campo Belo',31),(31046,'Oliveira',31),(31047,'Passos',31),(31048,'São Sebastião do Paraíso',31),(31049,'Alfenas',31),(31050,'Varginha',31),(31051,'Poços de Caldas',31),(31052,'Pouso Alegre',31),(31053,'Santa Rita do Sapucaí',31),(31054,'São Lourenço',31),(31055,'Andrelândia',31),(31056,'Itajubá',31),(31057,'Lavras',31),(31058,'São João Del Rei',31),(31059,'Barbacena',31),(31060,'Ponte Nova',31),(31061,'Manhuaçu',31),(31062,'Viçosa',31),(31063,'Muriaé',31),(31064,'Ubá',31),(31065,'Juiz de Fora',31),(31066,'Cataguases',31),(32001,'Barra de São Francisco',32),(32002,'Nova Venécia',32),(32003,'Colatina',32),(32004,'Montanha',32),(32005,'São Mateus',32),(32006,'Linhares',32),(32007,'Afonso Cláudio',32),(32008,'Santa Teresa',32),(32009,'Vitória',32),(32010,'Guarapari',32),(32011,'Alegre',32),(32012,'Cachoeiro de Itapemirim',32),(32013,'Itapemirim',32),(33001,'Itaperuna',33),(33002,'Santo Antônio de Pádua',33),(33003,'Campos dos Goytacazes',33),(33004,'Macaé',33),(33005,'Três Rios',33),(33006,'Cantagalo-Cordeiro',33),(33007,'Nova Friburgo',33),(33008,'Santa Maria Madalena',33),(33009,'Bacia de São João',33),(33010,'Lagos',33),(33011,'Vale do Paraíba Fluminense',33),(33012,'Barra do Piraí',33),(33013,'Baía da Ilha Grande',33),(33014,'Vassouras',33),(33015,'Serrana',33),(33016,'Macacu-Caceribu',33),(33017,'Itaguaí',33),(33018,'Rio de Janeiro',33),(35001,'Jales',35),(35002,'Fernandópolis',35),(35003,'Votuporanga',35),(35004,'São José do Rio Preto',35),(35005,'Catanduva',35),(35006,'Auriflama',35),(35007,'Nhandeara',35),(35008,'Novo Horizonte',35),(35009,'Barretos',35),(35010,'São Joaquim da Barra',35),(35011,'Ituverava',35),(35012,'Franca',35),(35013,'Jaboticabal',35),(35014,'Ribeirão Preto',35),(35015,'Batatais',35),(35016,'Andradina',35),(35017,'Araçatuba',35),(35018,'Birigui',35),(35019,'Lins',35),(35020,'Bauru',35),(35021,'Jaú',35),(35022,'Avaré',35),(35023,'Botucatu',35),(35024,'Araraquara',35),(35025,'São Carlos',35),(35026,'Rio Claro',35),(35027,'Limeira',35),(35028,'Piracicaba',35),(35029,'Pirassununga',35),(35030,'São João da Boa Vista',35),(35031,'Mogi Mirim',35),(35032,'Campinas',35),(35033,'Amparo',35),(35034,'Dracena',35),(35035,'Adamantina',35),(35036,'Presidente Prudente',35),(35037,'Tupã',35),(35038,'Marília',35),(35039,'Assis',35),(35040,'Ourinhos',35),(35041,'Itapeva',35),(35042,'Itapetininga',35),(35043,'Tatuí',35),(35044,'Capão Bonito',35),(35045,'Piedade',35),(35046,'Sorocaba',35),(35047,'Jundiaí',35),(35048,'Bragança Paulista',35),(35049,'Campos do Jordão',35),(35050,'São José dos Campos',35),(35051,'Guaratinguetá',35),(35052,'Bananal',35),(35053,'Paraibuna/Paraitinga',35),(35054,'Caraguatatuba',35),(35055,'Registro',35),(35056,'Itanhaém',35),(35057,'Osasco',35),(35058,'Franco da Rocha',35),(35059,'Guarulhos',35),(35060,'Itapecerica da Serra',35),(35061,'São Paulo',35),(35062,'Mogi das Cruzes',35),(35063,'Santos',35),(41001,'Paranavaí',41),(41002,'Umuarama',41),(41003,'Cianorte',41),(41004,'Goioerê',41),(41005,'Campo Mourão',41),(41006,'Astorga',41),(41007,'Porecatu',41),(41008,'Floraí',41),(41009,'Maringá',41),(41010,'Apucarana',41),(41011,'Londrina',41),(41012,'Faxinal',41),(41013,'Ivaiporã',41),(41014,'Assaí',41),(41015,'Cornélio Procópio',41),(41016,'Jacarezinho',41),(41017,'Ibaiti',41),(41018,'Wenceslau Braz',41),(41019,'Telêmaco Borba',41),(41020,'Jaguariaíva',41),(41021,'Ponta Grossa',41),(41022,'Toledo',41),(41023,'Cascavel',41),(41024,'Foz do Iguaçu',41),(41025,'Capanema',41),(41026,'Francisco Beltrão',41),(41027,'Pato Branco',41),(41028,'Pitanga',41),(41029,'Guarapuava',41),(41030,'Palmas',41),(41031,'Prudentópolis',41),(41032,'Irati',41),(41033,'União da Vitória',41),(41034,'São Mateus do Sul',41),(41035,'Cerro Azul',41),(41036,'Lapa',41),(41037,'Curitiba',41),(41038,'Paranaguá',41),(41039,'Rio Negro',41),(42001,'São Miguel do Oeste',42),(42002,'Chapecó',42),(42003,'Xanxerê',42),(42004,'Joaçaba',42),(42005,'Concórdia',42),(42006,'Canoinhas',42),(42007,'São Bento do Sul',42),(42008,'Joinville',42),(42009,'Curitibanos',42),(42010,'Campos de Lages',42),(42011,'Rio do Sul',42),(42012,'Blumenau',42),(42013,'Itajaí',42),(42014,'Ituporanga',42),(42015,'Tijucas',42),(42016,'Florianópolis',42),(42017,'Tabuleiro',42),(42018,'Tubarão',42),(42019,'Criciúma',42),(42020,'Araranguá',42),(43001,'Santa Rosa',43),(43002,'Três Passos',43),(43003,'Frederico Westphalen',43),(43004,'Erechim',43),(43005,'Sananduva',43),(43006,'Cerro Largo',43),(43007,'Santo Ângelo',43),(43008,'Ijuí',43),(43009,'Carazinho',43),(43010,'Passo Fundo',43),(43011,'Cruz Alta',43),(43012,'Não-Me-Toque',43),(43013,'Soledade',43),(43014,'Guaporé',43),(43015,'Vacaria',43),(43016,'Caxias do Sul',43),(43017,'Santiago',43),(43018,'Santa Maria',43),(43019,'Restinga Seca',43),(43020,'Santa Cruz do Sul',43),(43021,'Lajeado-Estrela',43),(43022,'Cachoeira do Sul',43),(43023,'Montenegro',43),(43024,'Gramado-Canela',43),(43025,'São Jerônimo',43),(43026,'Porto Alegre',43),(43027,'Osório',43),(43028,'Camaquã',43),(43029,'Campanha Ocidental',43),(43030,'Campanha Central',43),(43031,'Campanha Meridional',43),(43032,'Serras de Sudeste',43),(43033,'Pelotas',43),(43034,'Jaguarão',43),(43035,'Litoral Lagunar',43),(50001,'Baixo Pantanal',50),(50002,'Aquidauana',50),(50003,'Alto Taquari',50),(50004,'Campo Grande',50),(50005,'Cassilândia',50),(50006,'Paranaíba',50),(50007,'Três Lagoas',50),(50008,'Nova Andradina',50),(50009,'Bodoquena',50),(50010,'Dourados',50),(50011,'Iguatemi',50),(51001,'Aripuanã',51),(51002,'Alta Floresta',51),(51003,'Colíder',51),(51004,'Parecis',51),(51005,'Arinos',51),(51006,'Alto Teles Pires',51),(51007,'Sinop',51),(51008,'Paranatinga',51),(51009,'Norte Araguaia',51),(51010,'Canarana',51),(51011,'Médio Araguaia',51),(51012,'Alto Guaporé',51),(51013,'Tangará da Serra',51),(51014,'Jauru',51),(51015,'Alto Paraguai',51),(51016,'Rosário Oeste',51),(51017,'Cuiabá',51),(51018,'Alto Pantanal',51),(51019,'Primavera do Leste',51),(51020,'Tesouro',51),(51021,'Rondonópolis',51),(51022,'Alto Araguaia',51),(52001,'São Miguel do Araguaia',52),(52002,'Rio Vermelho',52),(52003,'Aragarças',52),(52004,'Porangatu',52),(52005,'Chapada dos Veadeiros',52),(52006,'Ceres',52),(52007,'Anápolis',52),(52008,'Iporá',52),(52009,'Anicuns',52),(52010,'Goiânia',52),(52011,'Vão do Paranã',52),(52012,'Entorno de Brasília',52),(52013,'Sudoeste de Goiás',52),(52014,'Vale do Rio dos Bois',52),(52015,'Meia Ponte',52),(52016,'Pires do Rio',52),(52017,'Catalão',52),(52018,'Quirinópolis',52),(53001,'Brasília',53);
/*!40000 ALTER TABLE `tbl_microrregiao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_profissional`
--

DROP TABLE IF EXISTS `tbl_profissional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_profissional` (
  `id_profissional` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(200) NOT NULL,
  `foto` varchar(255) NOT NULL,
  `cnpj` varchar(20) DEFAULT NULL,
  `cpf` varchar(20) DEFAULT NULL,
  `senha` varchar(200) NOT NULL,
  `data_nasc` date NOT NULL,
  `valor_hora` double NOT NULL,
  `id_endereco` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  PRIMARY KEY (`id_profissional`),
  KEY `fk_endereco_idx` (`id_endereco`),
  KEY `fk_categoria_idx` (`id_categoria`),
  CONSTRAINT `fk_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `tbl_categoria` (`id_categoria`),
  CONSTRAINT `fk_endereco` FOREIGN KEY (`id_endereco`) REFERENCES `tbl_endereco` (`id_endereco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_profissional`
--

LOCK TABLES `tbl_profissional` WRITE;
/*!40000 ALTER TABLE `tbl_profissional` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_profissional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_subcategoria`
--

DROP TABLE IF EXISTS `tbl_subcategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_subcategoria` (
  `id_subcategoria` int(11) NOT NULL AUTO_INCREMENT,
  `subcategoria` varchar(100) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  PRIMARY KEY (`id_subcategoria`),
  KEY `fk_categoria_idx` (`id_categoria`),
  CONSTRAINT `fk_categoria_sub` FOREIGN KEY (`id_categoria`) REFERENCES `tbl_categoria` (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_subcategoria`
--

LOCK TABLES `tbl_subcategoria` WRITE;
/*!40000 ALTER TABLE `tbl_subcategoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_subcategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_uf`
--

DROP TABLE IF EXISTS `tbl_uf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_uf` (
  `id_uf` int(11) NOT NULL AUTO_INCREMENT,
  `uf` varchar(2) NOT NULL,
  `estado` varchar(50) NOT NULL,
  PRIMARY KEY (`id_uf`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_uf`
--

LOCK TABLES `tbl_uf` WRITE;
/*!40000 ALTER TABLE `tbl_uf` DISABLE KEYS */;
INSERT INTO `tbl_uf` VALUES (11,'RO','Rondônia'),(12,'AC','Acre'),(13,'AM','Amazonas'),(14,'RR','Roraima'),(15,'PA','Pará'),(16,'AP','Amapá'),(17,'TO','Tocantins'),(21,'MA','Maranhão'),(22,'PI','Piauí'),(23,'CE','Ceará'),(24,'RN','Rio Grande do Norte'),(25,'PB','Paraíba'),(26,'PE','Pernambuco'),(27,'AL','Alagoas'),(28,'SE','Sergipe'),(29,'BA','Bahia'),(31,'MG','Minas Gerais'),(32,'ES','Espírito Santo'),(33,'RJ','Rio de Janeiro'),(35,'SP','São Paulo'),(41,'PR','Paraná'),(42,'SC','Santa Catarina'),(43,'RS','Rio Grande do Sul'),(50,'MS','Mato Grosso do Sul'),(51,'MT','Mato Grosso'),(52,'GO','Goiás'),(53,'DF','Distrito Federal');
/*!40000 ALTER TABLE `tbl_uf` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-27 14:25:20
