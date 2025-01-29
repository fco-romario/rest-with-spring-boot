CREATE TABLE IF NOT EXISTS `books` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `launch_date` date NOT NULL,
  `price` double NOT NULL,
  `author` varchar(180) NOT NULL,
  `title` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
);