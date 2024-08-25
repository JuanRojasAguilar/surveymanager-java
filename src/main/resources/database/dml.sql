INSERT INTO roles(name) 
VALUES ("admin"),
("user");

INSERT INTO users(enable, username, password)
VALUES (true, "admin", "1234");

INSERT INTO users_roles(role_id, user_id)
VALUES (1, 1);

-- Crear un catálogo
INSERT INTO catalogs (`name`) VALUES ('Health Survey Catalog');

-- Crear la encuesta
INSERT INTO surveys (`name`, `description`) VALUES ('Health and Lifestyle Survey', 'A survey to assess health and lifestyle habits');

-- Crear capítulos de la encuesta
INSERT INTO chapter (survey_id, chapter_number, chapter_title) 
VALUES ((SELECT id FROM surveys WHERE `name` = 'Health and Lifestyle Survey'), '1', 'Personal Information'),
       ((SELECT id FROM surveys WHERE `name` = 'Health and Lifestyle Survey'), '2', 'Health Habits');

-- Preguntas del capítulo 1: Personal Information
INSERT INTO questions (chapter_id, question_number, response_type, question_text) 
VALUES 
    ((SELECT id FROM chapter WHERE chapter_number = '1'), '1.1', 'escrita', 'What is your full name?'),
    ((SELECT id FROM chapter WHERE chapter_number = '1'), '1.2', 'escrita', 'What is your age?'),
    ((SELECT id FROM chapter WHERE chapter_number = '1'), '1.3', 'seleccion', 'What is your gender?');

-- Preguntas del capítulo 2: Health Habits
INSERT INTO questions (chapter_id, question_number, response_type, question_text) 
VALUES 
    ((SELECT id FROM chapter WHERE chapter_number = '2'), '2.1', 'seleccion', 'How often do you exercise per week?'),
    ((SELECT id FROM chapter WHERE chapter_number = '2'), '2.2', 'seleccion', 'Do you follow a specific diet?'),
    ((SELECT id FROM chapter WHERE chapter_number = '2'), '2.3', 'seleccion multiple', 'Which of the following foods do you consume regularly?');

-- Opciones de respuesta para la pregunta 1.3 (Gender)
INSERT INTO response_options (question_id, option_text) 
VALUES 
    ((SELECT id FROM questions WHERE question_number = '1.1'), 'Nombre'),
    ((SELECT id FROM questions WHERE question_number = '1.2'), 'Edad'),
    ((SELECT id FROM questions WHERE question_number = '1.3'), 'Male'),
    ((SELECT id FROM questions WHERE question_number = '1.3'), 'Female'),
    ((SELECT id FROM questions WHERE question_number = '1.3'), 'Other');

-- Opciones de respuesta para la pregunta 2.1 (Exercise frequency)
INSERT INTO response_options (question_id, option_text) 
VALUES 
    ((SELECT id FROM questions WHERE question_number = '2.1'), 'None'),
    ((SELECT id FROM questions WHERE question_number = '2.1'), '1-2 times'),
    ((SELECT id FROM questions WHERE question_number = '2.1'), '3-4 times'),
    ((SELECT id FROM questions WHERE question_number = '2.1'), '5+ times');

-- Opciones de respuesta para la pregunta 2.2 (Diet)
INSERT INTO response_options (question_id, option_text) 
VALUES 
    ((SELECT id FROM questions WHERE question_number = '2.2'), 'Yes'),
    ((SELECT id FROM questions WHERE question_number = '2.2'), 'No');

-- Opciones de respuesta para la pregunta 2.3 (Regular food consumption)
INSERT INTO response_options (question_id, option_text) 
VALUES 
    ((SELECT id FROM questions WHERE question_number = '2.3'), 'Vegetables'),
    ((SELECT id FROM questions WHERE question_number = '2.3'), 'Fruits'),
    ((SELECT id FROM questions WHERE question_number = '2.3'), 'Meat'),
    ((SELECT id FROM questions WHERE question_number = '2.3'), 'Dairy Products'),
    ((SELECT id FROM questions WHERE question_number = '2.3'), 'Grains');


-- Opciones de respuesta con parentresponse_id (para la pregunta 2.2: Diet)
-- Subopciones si la respuesta es "Yes"
INSERT INTO response_options (question_id, option_text) 
VALUES 
    ((SELECT id FROM questions WHERE question_number = '2.2'), 'Vegetarian'),
    ((SELECT id FROM questions WHERE question_number = '2.2'), 'Vegan'),
    ((SELECT id FROM questions WHERE question_number = '2.2'), 'Keto');
