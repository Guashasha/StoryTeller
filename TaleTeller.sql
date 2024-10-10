CREATE TABLE `Cuento` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `titulo` string NOT NULL,
  `texto_español` string NOT NULL,
  `completado` bool NOT NULL
);

CREATE TABLE `Palabra` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_cuento` int,
  `palabra_espanol` string NOT NULL,
  `palabra_ingles` string NOT NULL
);

CREATE TABLE `Pregunta` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_cuento` int,
  `pregunta_español` string NOT NULL,
  `pregunta_ingles` string NOT NULL,
  `respuesta` int,
  `lenguaje_respuesta` enum(espanol,ingles),
  `contestada` bool NOT NULL
);

ALTER TABLE `Palabra` ADD FOREIGN KEY (`id_cuento`) REFERENCES `Cuento` (`id`);

ALTER TABLE `Pregunta` ADD FOREIGN KEY (`id_cuento`) REFERENCES `Cuento` (`id`);

ALTER TABLE `Pregunta` ADD FOREIGN KEY (`respuesta`) REFERENCES `Palabra` (`id`);
