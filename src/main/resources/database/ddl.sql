DROP DATABASE IF EXISTS sourvey_jr;

CREATE DATABASE sourvey_jr;
USE sourvey_jr

-- tables for user login
CREATE TABLE roles (
    id INT UNSIGNED AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    CONSTRAINT pk_id_roles PRIMARY KEY (id)
);

CREATE TABLE users (
    id INT UNSIGNED AUTO_INCREMENT,
    enable boolean,
    username VARCHAR(12) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    CONSTRAINT pk_id_users PRIMARY KEY (id)
);

CREATE TABLE users_roles (
    role_id INT UNSIGNED,
    user_id INT UNSIGNED,
    CONSTRAINT pk_role_id_user_id PRIMARY KEY (role_id, user_id),
    CONSTRAINT fk_role_id_users_roles FOREIGN KEY (role_id)
    REFERENCES roles(id),
    CONSTRAINT fk_user_id_users_roles FOREIGN KEY (user_id)
    REFERENCES users(id) 
);

-- survey database tables

CREATE TABLE catalogs (
    id INT UNSIGNED AUTO_INCREMENT,
    create_at TIMESTAMP(6) DEFAULT (NOW()),
    update_at TIMESTAMP(6),
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_id_catalogs PRIMARY KEY (id)
);   

CREATE TABLE surveys (
    id INT UNSIGNED AUTO_INCREMENT,
    created_at TIMESTAMP(6) DEFAULT (NOW()),
    update_at TIMESTAMP(6),
    `description` VARCHAR(255),
    `name` VARCHAR(255),
    CONSTRAINT pk_id_surveys PRIMARY KEY (id)
); 

CREATE TABLE chapter (
    id INT UNSIGNED AUTO_INCREMENT,
    created_at TIMESTAMP(6) DEFAULT (NOW()),
    survey_id INT UNSIGNED,
    update_at TIMESTAMP(6),
    chapter_number VARCHAR(50),
    chapter_title VARCHAR(50),
    CONSTRAINT pk_id_chapter PRIMARY KEY(id),
    CONSTRAINT fk_survey_id_chapter FOREIGN KEY (survey_id)
    REFERENCES surveys(id) ON DELETE CASCADE
);

CREATE TABLE questions (
    id INT UNSIGNED AUTO_INCREMENT,
    chapter_id INT UNSIGNED NOT NULL,
    created_at TIMESTAMP(6) DEFAULT (NOW()),
    update_at TIMESTAMP(6),
    question_number VARCHAR(10) NOT NULL,
    response_type VARCHAR(20),
    comment_question TEXT,
    question_text TEXT NOT NULL,
    CONSTRAINT pk_id_questions PRIMARY KEY (id),
    CONSTRAINT fk_chapter_id_questions FOREIGN KEY (chapter_id)
    REFERENCES chapter(id) ON DELETE CASCADE
);

CREATE TABLE response_options (
    id INT UNSIGNED AUTO_INCREMENT,
    option_value INT(4) UNSIGNED,
    categorycatalog_id INT UNSIGNED,
    create_at TIMESTAMP(6) DEFAULT (NOW()), 
    parentresponse_id INT UNSIGNED,
    question_id INT UNSIGNED,
    update_at TIMESTAMP(6),
    comment_response TEXT,
    option_text TEXT,
    CONSTRAINT pk_id_response_options PRIMARY KEY (id),
    CONSTRAINT fk_question_id_response_options FOREIGN KEY (question_id)
    REFERENCES questions(id) ON DELETE CASCADE,
    CONSTRAINT fk_parentresponse_id_response_options FOREIGN KEY (parentresponse_id)
    REFERENCES response_options(id),
    CONSTRAINT fk_categoryecatalog_id_response_options FOREIGN KEY (categorycatalog_id)
    REFERENCES catalogs(id) ON DELETE CASCADE
);

CREATE TABLE subresponse_options (
    id INT UNSIGNED AUTO_INCREMENT,
    subresponse_number INT(4) UNSIGNED,
    create_at TIMESTAMP(6) DEFAULT (NOW()),
    responseoptions_id INT UNSIGNED,
    update_at TIMESTAMP(6),
    subresponse_text VARCHAR(255),
    CONSTRAINT pk_id_subresponse_options PRIMARY KEY (id),
    CONSTRAINT fk_responseoptions_id_subresponse_options FOREIGN KEY(responseoptions_id)
    REFERENCES response_options(id) ON DELETE CASCADE
);

CREATE TABLE response_questions (
    id INT UNSIGNED AUTO_INCREMENT,
    response_id INT UNSIGNED,
    subresponse_id INT UNSIGNED,
    responsetext VARCHAR(80),
    CONSTRAINT pk_id_response_questions PRIMARY KEY (id),
    CONSTRAINT fk_response_id_response_questions FOREIGN KEY (response_id)
    REFERENCES response_options(id),
    CONSTRAINT fk_subresponse_id_subresponse_options FOREIGN KEY (subresponse_id)
    REFERENCES subresponse_options(id) ON DELETE CASCADE
);

DELIMITER $$
CREATE TRIGGER before_update_catalogs
BEFORE UPDATE ON catalogs 
FOR EACH ROW 
BEGIN
    SET NEW.update_at = NOW();
END $$

CREATE TRIGGER before_update_surveys
BEFORE UPDATE ON surveys
FOR EACH ROW
BEGIN
    SET NEW.update_at = NOW();
END $$

CREATE TRIGGER before_update_chapter
BEFORE UPDATE ON chapter
FOR EACH ROW 
BEGIN
    SET NEW.update_at = NOW();

    UPDATE response_options
    SET update_at = NOW()
    WHERE categorycatalog_id = OLD.id;
END $$

CREATE TRIGGER before_update_questions
BEFORE UPDATE ON questions
FOR EACH ROW
BEGIN 
    SET NEW.update_at = NOW();

    UPDATE response_options
    SET update_at = NOW()
    WHERE question_id = OLD.id;
END $$

CREATE TRIGGER before_update_response_options
BEFORE UPDATE ON response_options
FOR EACH ROW
BEGIN
    SET NEW.update_at = NOW();
END $$

CREATE TRIGGER before_update_subresponse_options
BEFORE UPDATE ON subresponse_options
FOR EACH ROW
BEGIN
    SET NEW.update_at = NOW();
END $$

DELIMITER ;