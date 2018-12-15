package com.company.database.configuration;

public interface DbQueries extends DbConfiguration {

    String CREATE_TABLE_LANGUAGES = "CREATE TABLE languages ( " +
            "  id INT NOT NULL AUTO_INCREMENT, " +
            "  name VARCHAR(45) NOT NULL, " +
            "  PRIMARY KEY (id), " +
            "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE, " +
            "  UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE);";

    String CREATE_TABLE_LEVELS_OF_LANGUAGE = "CREATE TABLE levels_of_language ( " +
            "  id INT NOT NULL AUTO_INCREMENT, " +
            "  level VARCHAR(45) NOT NULL, " +
            "  PRIMARY KEY (id), " +
            "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE, " +
            "  UNIQUE INDEX level_UNIQUE (level ASC) VISIBLE);";


    String CREATE_TABLE_NAMES_OF_COURSES = "CREATE TABLE names_of_courses ( " +
            "  id INT NOT NULL AUTO_INCREMENT, " +
            "  name VARCHAR(45) NOT NULL, " +
            "  PRIMARY KEY (id), " +
            "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE, " +
            "  UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE);";

    String CREATE_TABLE_USERS = "CREATE TABLE users ( " +
            "  id INT NOT NULL AUTO_INCREMENT, " +
            "  first_name VARCHAR(45) NOT NULL, " +
            "  middle_name VARCHAR(45) NOT NULL, " +
            "  last_name VARCHAR(45) NOT NULL, " +
            "  login VARCHAR(45) NOT NULL, " +
            "  password VARCHAR(45) NOT NULL, " +
            "  birthdate DATE NOT NULL, " +
            "  gender ENUM('Мужской', 'Женский') NOT NULL, " +
            "  PRIMARY KEY (login), " +
            "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE, " +
            "  UNIQUE INDEX username_UNIQUE (login ASC) VISIBLE);";

    String CREATE_TABLE_STUDENTS = "CREATE TABLE students ( " +
            "  id INT NOT NULL AUTO_INCREMENT, " +
            "  username VARCHAR(45) NOT NULL, " +
            "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE, " +
            "  UNIQUE INDEX username_UNIQUE (username ASC) VISIBLE, " +
            "  PRIMARY KEY (username), " +
            "  CONSTRAINT student_username " +
            "    FOREIGN KEY (username) " +
            "    REFERENCES users (login) " +
            "    ON DELETE CASCADE " +
            "    ON UPDATE CASCADE);";

    String CREATE_TABLE_TEACHERS = "CREATE TABLE teachers ( " +
            "  id int(11) NOT NULL AUTO_INCREMENT, " +
            "  username varchar(45) NOT NULL, " +
            "  salary decimal(10,2) NOT NULL, " +
            "  bio varchar(1000) DEFAULT NULL, " +
            "  id_language int(11) NOT NULL, " +
            "  PRIMARY KEY (username), " +
            "  UNIQUE KEY id_UNIQUE (id), " +
            "  UNIQUE KEY username_UNIQUE (username), " +
            "  KEY lang_id_idx (id_language), " +
            "  CONSTRAINT lang_id FOREIGN KEY (id_language) REFERENCES languages (id) ON DELETE CASCADE ON UPDATE CASCADE, " +
            "  CONSTRAINT teacher_username FOREIGN KEY (username) REFERENCES users (login) ON DELETE CASCADE ON UPDATE CASCADE " +
            ");";

    String CREATE_TABLE_COURSES = "CREATE TABLE courses ( " +
            "  id INT NOT NULL AUTO_INCREMENT, " +
            "  id_name_of_course INT NOT NULL, " +
            "  id_language INT NOT NULL, " +
            "  id_level INT NOT NULL, " +
            "  username_teacher VARCHAR(45) NOT NULL, " +
            "  cost DOUBLE NOT NULL, " +
            "  start_date DATE NOT NULL, " +
            "  duration INT NOT NULL, " +
            "  description VARCHAR(1000) NULL, " +
            "  PRIMARY KEY (id), " +
            "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE, " +
            "  INDEX course_language_idx (id_language ASC) VISIBLE, " +
            "  INDEX course_name_idx (id_name_of_course ASC) VISIBLE, " +
            "  INDEX course_level_idx (id_level ASC) VISIBLE, " +
            "  INDEX course_teacher_idx (username_teacher ASC) VISIBLE, " +
            "  CONSTRAINT course_language " +
            "    FOREIGN KEY (id_language) " +
            "    REFERENCES languages (id) " +
            "    ON DELETE CASCADE " +
            "    ON UPDATE CASCADE, " +
            "  CONSTRAINT course_name " +
            "    FOREIGN KEY (id_name_of_course) " +
            "    REFERENCES names_of_courses (id) " +
            "    ON DELETE CASCADE " +
            "    ON UPDATE CASCADE, " +
            "  CONSTRAINT course_level " +
            "    FOREIGN KEY (id_level) " +
            "    REFERENCES levels_of_language (id) " +
            "    ON DELETE CASCADE " +
            "    ON UPDATE CASCADE, " +
            "  CONSTRAINT course_teacher " +
            "    FOREIGN KEY (username_teacher) " +
            "    REFERENCES teachers (username) " +
            "    ON DELETE CASCADE " +
            "    ON UPDATE CASCADE);";

    String CREATE_TABLE_ORDER_COURSES = "CREATE TABLE orders_courses ( " +
            "  id INT NOT NULL AUTO_INCREMENT, " +
            "  username_student VARCHAR(45) NOT NULL, " +
            "  id_course INT NOT NULL, " +
            "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE, " +
            "  PRIMARY KEY (username_student, id_course), " +
            "  INDEX order_course_idx (id_course ASC) VISIBLE, " +
            "  CONSTRAINT order_student " +
            "    FOREIGN KEY (username_student) " +
            "    REFERENCES students (username) " +
            "    ON DELETE CASCADE " +
            "    ON UPDATE CASCADE, " +
            "  CONSTRAINT order_course " +
            "    FOREIGN KEY (id_course) " +
            "    REFERENCES courses (id) " +
            "    ON DELETE CASCADE " +
            "    ON UPDATE CASCADE);";

    String CREATE_TABLE_HELP_STUDENTS = "CREATE TABLE help_students ( " +
            "  id INT NOT NULL AUTO_INCREMENT, " +
            "  student_username VARCHAR(45) NOT NULL, " +
            "  message VARCHAR(1000) NOT NULL, " +
            "  request_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
            "  PRIMARY KEY (id), " +
            "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE, " +
            "  INDEX username_student_idx (student_username ASC) VISIBLE, " +
            "  CONSTRAINT username_student " +
            "    FOREIGN KEY (student_username) " +
            "    REFERENCES students (username) " +
            "    ON DELETE CASCADE " +
            "    ON UPDATE CASCADE);";

    String [] INSERT_LANGUAGES = {
            "INSERT INTO languages (name) VALUES ('Английский');",
            "INSERT INTO languages (name) VALUES ('Немецкий');",
            "INSERT INTO languages (name) VALUES ('Китайский');",
            "INSERT INTO languages (name) VALUES ('Японский');",
            "INSERT INTO languages (name) VALUES ('Французский');",
            "INSERT INTO languages (name) VALUES ('Испанский');"
    };

    String [] INSERT_LEVELS_OF_LANGUAGE = {
            "INSERT INTO levels_of_language (level) VALUES ('A1');",
            "INSERT INTO levels_of_language (level) VALUES ('A2');",
            "INSERT INTO levels_of_language (level) VALUES ('B1');",
            "INSERT INTO levels_of_language (level) VALUES ('B2');",
            "INSERT INTO levels_of_language (level) VALUES ('C1');"
    };

    String [] INSERT_NAMES_OF_COURSES = {
            "INSERT INTO names_of_courses (name) VALUES ('Английский базовый');",
            "INSERT INTO names_of_courses (name) VALUES ('Английский для бизнеса');",
            "INSERT INTO names_of_courses (name) VALUES ('Английский для ИТ');",
            "INSERT INTO names_of_courses (name) VALUES ('Английский продвинутый');",
            "INSERT INTO names_of_courses (name) VALUES ('Испанский базовый');",
            "INSERT INTO names_of_courses (name) VALUES ('Китайский базовый');",
            "INSERT INTO names_of_courses (name) VALUES ('Немеций продвинутый');",
            "INSERT INTO names_of_courses (name) VALUES ('Немецкий базовый');",
            "INSERT INTO names_of_courses (name) VALUES ('Немецкий для детей');",
            "INSERT INTO names_of_courses (name) VALUES ('Французский базовый');",
            "INSERT INTO names_of_courses (name) VALUES ('Французский продвинутый');",
            "INSERT INTO names_of_courses (name) VALUES ('Японский базовый');"
    };

    String [] INSERT_STUDENTS = {
            "INSERT INTO users (first_name, middle_name, last_name, login, password, birthdate, gender) " +
                    "VALUES ('Иван', 'Антонович', 'Писляк', 'pislyakpetr@mail.ru', 'qwerty', '1996-09-12', 'Мужской');",

            "INSERT INTO students (username) VALUES ('pislyakpetr@mail.ru');",

            "INSERT INTO users (first_name, middle_name, last_name, login, password, birthdate, gender) " +
                    "VALUES ('Петр', 'Васильвич', 'Киселев', 'kisslevPP@yandex.by', 'qwerty', '1990-04-05', 'Мужской');",

            "INSERT INTO students (username) VALUES ('kisslevPP@yandex.by');",

            "INSERT INTO users (first_name, middle_name, last_name, login, password, birthdate, gender) " +
                    "VALUES ('Сергей', 'Павлович', 'Мошка', 'sergickmosh@gmail.com', 'qwerty', '1982-04-14', 'Мужской');",

            "INSERT INTO students (username) VALUES ('sergickmosh@gmail.com');",

            "INSERT INTO users (first_name, middle_name, last_name, login, password, birthdate, gender) " +
                    "VALUES ('Евгений', 'Андреевич', 'Борисов', 'borisov1996@yandex.ru', 'qwerty', '1996-12-11', 'Мужской');",

            "INSERT INTO students (username) VALUES ('borisov1996@yandex.ru');",

            "INSERT INTO users (first_name, middle_name, last_name, login, password, birthdate, gender) " +
                    "VALUES ('Екатерина', 'Петровна', 'Малышева', 'kateMal@icloud.com', 'qwerty', '1998-01-01', 'Женский');",

            "INSERT INTO students (username) VALUES ('kateMal@icloud.com');"
    };

    String [] INSERT_TEACHERS = {
            "INSERT INTO users (first_name, middle_name, last_name, login, password, birthdate, gender) " +
                    "VALUES ('Андрей', 'Иванович', 'Егоров', 'egorov@teacher.ru', '12345', '1988-12-13', 'Мужской');",

            "INSERT INTO teachers (username, salary, bio, id_language) VALUES ('egorov@teacher.ru', '700', '', 1);",

            "INSERT INTO users (first_name, middle_name, last_name, login, password, birthdate, gender) " +
                    "VALUES ('Сергей', 'Дмитриевич', 'Русольцев', 'rusolcev@teacher.ru', '12345', '1986-09-01', 'Мужской');",

            "INSERT INTO teachers (username, salary, bio, id_language) VALUES ('rusolcev@teacher.ru', '800', '', 6);",

            "INSERT INTO users (first_name, middle_name, last_name, login, password, birthdate, gender) " +
                    "VALUES ('Алина', 'Егоровна', 'Калодищ', 'kalodish@teacher.ru', '12345', '1990-04-05', 'Женский');",

            "INSERT INTO teachers (username, salary, bio, id_language) VALUES ('kalodish@teacher.ru', '800', '', 3);",

            "INSERT INTO users (first_name, middle_name, last_name, login, password, birthdate, gender) " +
                    "VALUES ('Иван', 'Петрович', 'Румяный', 'rumyaniy@teacher.ru', '12345', '1995-11-17', 'Мужской');",

            "INSERT INTO teachers (username, salary, bio, id_language) VALUES ('rumyaniy@teacher.ru', '800', '', 2);",

            "INSERT INTO users (first_name, middle_name, last_name, login, password, birthdate, gender) " +
                    "VALUES ('Сергей', 'Рустамович', 'Сокирка', 'sockirka@teacher.ru', '12345', '1981-03-10', 'Мужской');",

            "INSERT INTO teachers (username, salary, bio, id_language) VALUES ('sockirka@teacher.ru', '900', '', 5);",

            "INSERT INTO users (first_name, middle_name, last_name, login, password, birthdate, gender) " +
                    "VALUES ('Валентина', 'Витальевна', 'Шмидт', 'schmidt@teacher.ru', '12345', '1965-08-12', 'Женский');",

            "INSERT INTO teachers (username, salary, bio, id_language) VALUES ('schmidt@teacher.ru', '800', '', 4);"
    };

    String [] INSERT_COURSES = {
            "INSERT INTO courses (id_name_of_course, id_language, id_level, username_teacher, cost, start_date, duration, description) VALUES ('1', '1', '1', 'egorov@teacher.ru', '1200', '2019-03-01', '30','');",

            "INSERT INTO courses (id_name_of_course, id_language, id_level, username_teacher, cost, start_date, duration, description) VALUES ('2', '1', '3', 'egorov@teacher.ru', '1400', '2019-02-01', '30','');",

            "INSERT INTO courses (id_name_of_course, id_language, id_level, username_teacher, cost, start_date, duration, description) VALUES ('3', '1', '3', 'egorov@teacher.ru', '1400', '2019-02-01', '30','');",

            "INSERT INTO courses (id_name_of_course, id_language, id_level, username_teacher, cost, start_date, duration, description) VALUES ('4', '1', '5', 'egorov@teacher.ru', '1600', '2019-02-01', '30','');",

            "INSERT INTO courses (id_name_of_course, id_language, id_level, username_teacher, cost, start_date, duration, description) VALUES ('5', '6', '1', 'rusolcev@teacher.ru', '1200', '2019-04-01', '30','');",

            "INSERT INTO courses (id_name_of_course, id_language, id_level, username_teacher, cost, start_date, duration, description) VALUES ('6', '3', '1', 'kalodish@teacher.ru', '1200', '2019-04-01', '30','');",

            "INSERT INTO courses (id_name_of_course, id_language, id_level, username_teacher, cost, start_date, duration, description) VALUES ('7', '2', '5', 'rumyaniy@teacher.ru', '1600', '2019-02-01', '30','');",

            "INSERT INTO courses (id_name_of_course, id_language, id_level, username_teacher, cost, start_date, duration, description) VALUES ('8', '2', '1', 'rumyaniy@teacher.ru', '1200', '2019-02-01', '30','');",

            "INSERT INTO courses (id_name_of_course, id_language, id_level, username_teacher, cost, start_date, duration, description) VALUES ('9', '2', '2', 'rumyaniy@teacher.ru', '1300', '2019-02-01', '30','');",

            "INSERT INTO courses (id_name_of_course, id_language, id_level, username_teacher, cost, start_date, duration, description) VALUES ('10', '5', '1', 'sockirka@teacher.ru', '1200', '2019-03-01', '30','');",

            "INSERT INTO courses (id_name_of_course, id_language, id_level, username_teacher, cost, start_date, duration, description) VALUES ('11', '5', '5', 'sockirka@teacher.ru', '1600', '2019-04-01', '30','');",

            "INSERT INTO courses (id_name_of_course, id_language, id_level, username_teacher, cost, start_date, duration, description) VALUES ('12', '4', '1', 'kalodish@teacher.ru', '1200', '2019-03-01', '30','');"
    };

    String [] INSERT_ORDER_COURSES = {
            "INSERT INTO orders_courses (username_student, id_course) " +
                    "VALUES ('kateMal@icloud.com', '1');",
            "INSERT INTO orders_courses (username_student, id_course) " +
                    "VALUES ('kateMal@icloud.com', '10');",
            "INSERT INTO orders_courses (username_student, id_course) " +
                    "VALUES ('borisov1996@yandex.ru', '2');",
            "INSERT INTO orders_courses (username_student, id_course) " +
                    "VALUES ('kisslevPP@yandex.by', '8');",
            "INSERT INTO orders_courses (username_student, id_course) " +
                    "VALUES ('pislyakpetr@mail.ru', '3');"
    };
}
