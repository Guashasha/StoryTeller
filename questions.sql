use storyteller;

DELETE FROM pregunta;
INSERT INTO pregunta (
  id_cuento, pregunta_espanol, pregunta_ingles, respuesta, lenguaje_respuesta
) VALUES ( 1, "¿que significa tree?", "what does tree mean?", "arbol", "espanol"), (2, "¿que significa sad?", "what does sad mean?", "triste", "espanol"), (3, "¿que significa friendship?", "what does friendship mean?", "amistad", "espanol"), (4, "¿que significa eye?", "what does eye mean?", "ojo", "espanol"), (5, "¿que significa wizards?", "what does wizards mean?", "magos", "espanol"), (6, "¿que significa elephant?", "what does elephant mean?", "elefante", "espanol");

DELETE FROM palabra;
INSERT INTO palabra (
  id_cuento, palabra_espanol, palabra_ingles
) VALUES (1, "arbol", "tree"), (2, "triste", "sad"), (3, "amistad", "friendship"), (4, "ojo", "eye"), (5, "magos", "wizards"), (6, "elefante", "elephant");
