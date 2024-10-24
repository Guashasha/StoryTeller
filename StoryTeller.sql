CREATE TABLE `Cuento` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `texto_espanol` mediumtext NOT NULL,
  `completado` tinyint(1) DEFAULT 0 NOT NULL
);

CREATE TABLE `Palabra` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_cuento` int,
  `palabra_espanol` varchar(30) NOT NULL,
  `palabra_ingles` varchar(30) NOT NULL
);

CREATE TABLE `Pregunta` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_cuento` int,
  `pregunta_espanol` varchar(30) NOT NULL,
  `pregunta_ingles` varchar(30) NOT NULL,
  `respuesta` varchar(30) NOT NULL,
  `lenguaje_respuesta` enum('espanol','ingles'),
  `contestada` tinyint(1) DEFAULT 0 NOT NULL
);

ALTER TABLE `Palabra` ADD FOREIGN KEY (`id_cuento`) REFERENCES `Cuento` (`id`);

ALTER TABLE `Pregunta` ADD FOREIGN KEY (`id_cuento`) REFERENCES `Cuento` (`id`);

ALTER TABLE `Pregunta` ADD FOREIGN KEY (`respuesta`) REFERENCES `Palabra` (`id`);
