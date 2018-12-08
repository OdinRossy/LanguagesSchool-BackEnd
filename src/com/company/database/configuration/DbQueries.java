package com.company.database.configuration;

public interface DbQueries extends DbConfiguration {

    String CREATE_TABLE_LANGUAGES = "CREATE TABLE " + DB_SCHEMA + ".languages (\n" +
            "  id INT NOT NULL AUTO_INCREMENT,\n" +
            "  name VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (id),\n" +
            "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,\n" +
            "  UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE);\n";

    String CREATE_TABLE_LEVELS_OF_LANGUAGE = "CREATE TABLE " + DB_SCHEMA + ".levels_of_language (\n" +
            "  id INT NOT NULL AUTO_INCREMENT,\n" +
            "  level VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (id),\n" +
            "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,\n" +
            "  UNIQUE INDEX level_UNIQUE (level ASC) VISIBLE);\n";


    String CREATE_TABLE_NAMES_OF_COURSES = "CREATE TABLE " + DB_SCHEMA + ".names_of_courses (\n" +
            "  id INT NOT NULL AUTO_INCREMENT,\n" +
            "  name VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (id),\n" +
            "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,\n" +
            "  UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE);\n";

    String CREATE_TABLE_STUDENTS = "CREATE TABLE " + DB_SCHEMA + ".students (\n" +
            "  id INT NOT NULL AUTO_INCREMENT,\n" +
            "  first_name VARCHAR(45) NOT NULL,\n" +
            "  middle_name VARCHAR(45) NOT NULL,\n" +
            "  last_name VARCHAR(45) NOT NULL,\n" +
            "  username VARCHAR(45) NOT NULL,\n" +
            "  password VARCHAR(45) NOT NULL,\n" +
            "  birthdate DATE NOT NULL,\n" +
            "  gender ENUM('Male', 'Female') NOT NULL,\n" +
            "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,\n" +
            "  PRIMARY KEY (username),\n" +
            "  UNIQUE INDEX username_UNIQUE (username ASC) VISIBLE);\n";

    String CREATE_TABLE_TEACHERS = "CREATE TABLE " + DB_SCHEMA + ".teachers (\n" +
            "  id INT NOT NULL AUTO_INCREMENT,\n" +
            "  first_name VARCHAR(45) NOT NULL,\n" +
            "  middle_name VARCHAR(45) NOT NULL,\n" +
            "  last_name VARCHAR(45) NOT NULL,\n" +
            "  username VARCHAR(45) NOT NULL,\n" +
            "  password VARCHAR(45) NOT NULL,\n" +
            "  birthdate DATE NOT NULL,\n" +
            "  gender ENUM('Male', 'Female') NOT NULL,\n" +
            "  salary DECIMAL(10,2) NOT NULL,\n" +
            "  bio VARCHAR(1000) NULL,\n" +
            "  id_language INT NOT NULL,\n" +
            "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,\n" +
            "  PRIMARY KEY (username),\n" +
            "  UNIQUE INDEX username_UNIQUE (username ASC) VISIBLE,\n" +
            "  INDEX language_idx (id_language ASC) VISIBLE,\n" +
            "  CONSTRAINT language\n" +
            "    FOREIGN KEY (id_language)\n" +
            "    REFERENCES " + DB_SCHEMA + ".languages (id)\n" +
            "    ON DELETE CASCADE\n" +
            "    ON UPDATE CASCADE);\n";

    String CREATE_TABLE_COURSES = "CREATE TABLE " + DB_SCHEMA + ".courses (\n" +
            "  id INT NOT NULL AUTO_INCREMENT,\n" +
            "  id_name_of_course INT NOT NULL,\n" +
            "  id_language INT NOT NULL,\n" +
            "  id_level INT NOT NULL,\n" +
            "  id_teacher INT NOT NULL,\n" +
            "  cost DOUBLE NOT NULL,\n" +
            "  start_date DATE NOT NULL,\n" +
            "  duration INT NOT NULL,\n" +
            "  description VARCHAR(1000) NULL,\n" +
            "  PRIMARY KEY (id),\n" +
            "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,\n" +
            "  INDEX course_language_idx (id_language ASC) VISIBLE,\n" +
            "  INDEX course_level_idx (id_level ASC) VISIBLE,\n" +
            "  INDEX course_name_idx (id_name_of_course ASC) VISIBLE,\n" +
            "  INDEX course_teacher_idx (id_teacher ASC) VISIBLE,\n" +
            "  CONSTRAINT course_language\n" +
            "    FOREIGN KEY (id_language)\n" +
            "    REFERENCES " + DB_SCHEMA + ".languages (id)\n" +
            "    ON DELETE CASCADE\n" +
            "    ON UPDATE CASCADE,\n" +
            "  CONSTRAINT course_level\n" +
            "    FOREIGN KEY (id_level)\n" +
            "    REFERENCES " + DB_SCHEMA + ".levels_of_language (id)\n" +
            "    ON DELETE CASCADE\n" +
            "    ON UPDATE CASCADE,\n" +
            "  CONSTRAINT course_name\n" +
            "    FOREIGN KEY (id_name_of_course)\n" +
            "    REFERENCES " + DB_SCHEMA + ".names_of_courses (id)\n" +
            "    ON DELETE CASCADE\n" +
            "    ON UPDATE CASCADE,\n" +
            "  CONSTRAINT course_teacher\n" +
            "    FOREIGN KEY (id_teacher)\n" +
            "    REFERENCES " + DB_SCHEMA + ".teachers (id)\n" +
            "    ON DELETE CASCADE\n" +
            "    ON UPDATE CASCADE);\n";

    String CREATE_TABLE_ORDER_COURSES = "CREATE TABLE " + DB_SCHEMA + ".orders_courses (\n" +
            "  id INT NOT NULL AUTO_INCREMENT,\n" +
            "  username_student VARCHAR(45) NOT NULL,\n" +
            "  id_course INT NOT NULL,\n" +
            "  PRIMARY KEY (username_student, id_course),\n" +
            "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,\n" +
            "  INDEX username_student_idx (username_student ASC) VISIBLE,\n" +
            "  INDEX order_course_idx (id_course ASC) VISIBLE,\n" +
            "  CONSTRAINT order_student\n" +
            "    FOREIGN KEY (username_student)\n" +
            "    REFERENCES " + DB_SCHEMA + ".students (username)\n" +
            "    ON DELETE CASCADE\n" +
            "    ON UPDATE CASCADE,\n" +
            "  CONSTRAINT order_course\n" +
            "    FOREIGN KEY (id_course)\n" +
            "    REFERENCES " + DB_SCHEMA + ".courses (id)\n" +
            "    ON DELETE CASCADE\n" +
            "    ON UPDATE CASCADE);\n";

    String CREATE_TABLE_HELP_STUDENTS = "CREATE TABLE " + DB_SCHEMA + ".help_students (\n" +
            "  id INT NOT NULL AUTO_INCREMENT,\n" +
            "  student_username VARCHAR(45) NOT NULL,\n" +
            "  message VARCHAR(1000) NOT NULL,\n" +
            "  request_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
            "  PRIMARY KEY (id),\n" +
            "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,\n" +
            "  INDEX username_student_idx (student_username ASC) VISIBLE,\n" +
            "  CONSTRAINT username_student\n" +
            "    FOREIGN KEY (student_username)\n" +
            "    REFERENCES " + DB_SCHEMA + ".students (username)\n" +
            "    ON DELETE CASCADE\n" +
            "    ON UPDATE CASCADE);\n";

    String INSERT_LANGUAGES = "INSERT INTO `languagesschool.languages (`name`) VALUES ('Английский');\n" +
            "INSERT INTO `languagesschool.`languages` (`name`) VALUES ('Немецкий');\n" +
            "INSERT INTO `languagesschool.`languages` (`name`) VALUES ('Китайский');\n" +
            "INSERT INTO `languagesschool.`languages` (`name`) VALUES ('Японский');\n" +
            "INSERT INTO `languagesschool.`languages` (`name`) VALUES ('Французский');\n" +
            "INSERT INTO `languagesschool.`languages` (`name`) VALUES ('Испанский');\n";

    String INSERT_LEVELS_OF_LANGUAGE = "INSERT INTO `languagesschool`.`levels_of_language` (`level`) VALUES ('A1');\n" +
            "INSERT INTO `languagesschool`.`levels_of_language` (`level`) VALUES ('A2');\n" +
            "INSERT INTO `languagesschool`.`levels_of_language` (`level`) VALUES ('B1');\n" +
            "INSERT INTO `languagesschool`.`levels_of_language` (`level`) VALUES ('B2');\n" +
            "INSERT INTO `languagesschool`.`levels_of_language` (`level`) VALUES ('C1');\n";

    String INSERT_NAMES_OF_COURSES = "INSERT INTO `languagesschool`.`names_of_courses` (`name`) VALUES ('Английский базовый');\n" +
            "INSERT INTO `languagesschool`.`names_of_courses` (`name`) VALUES ('Английский для бизнеса');\n" +
            "INSERT INTO `languagesschool`.`names_of_courses` (`name`) VALUES ('Английский для ИТ');\n" +
            "INSERT INTO `languagesschool`.`names_of_courses` (`name`) VALUES ('Английский продвинутый');\n" +
            "INSERT INTO `languagesschool`.`names_of_courses` (`name`) VALUES ('Испанский базовый');\n" +
            "INSERT INTO `languagesschool`.`names_of_courses` (`name`) VALUES ('Китайский базовый');\n" +
            "INSERT INTO `languagesschool`.`names_of_courses` (`name`) VALUES ('Немеций продвинутый');\n" +
            "INSERT INTO `languagesschool`.`names_of_courses` (`name`) VALUES ('Немецкий базовый');\n" +
            "INSERT INTO `languagesschool`.`names_of_courses` (`name`) VALUES ('Немецкий для детей');\n" +
            "INSERT INTO `languagesschool`.`names_of_courses` (`name`) VALUES ('Французский базовый');\n" +
            "INSERT INTO `languagesschool`.`names_of_courses` (`name`) VALUES ('Французский продвинутый');\n" +
            "INSERT INTO `languagesschool`.`names_of_courses` (`name`) VALUES ('Японский базовый');\n";

    String INSERT_STUDENTS = "INSERT INTO `languagesschool`.`students` (`first_name`, `middle_name`, `last_name`, `username`, `password`, `birthdate`, `gender`) VALUES ('Иван', 'Антонович', 'Писляк', 'pislyakpetr@mail.ru', 'qwerty', '1996-09-12', 'Male');\n" +
            "INSERT INTO `languagesschool`.`students` (`first_name`, `middle_name`, `last_name`, `username`, `password`, `birthdate`, `gender`) VALUES ('Петр', 'Васильвич', 'Киселев', 'kisslevPP@yandex.by', 'qwerty', '1990-04-05', 'Male');\n" +
            "INSERT INTO `languagesschool`.`students` (`first_name`, `middle_name`, `last_name`, `username`, `password`, `birthdate`, `gender`) VALUES ('Сергей', 'Павлович', 'Мошка', 'sergickmosh@gmail.com', 'qwerty', '1982-04-14', 'Male');\n" +
            "INSERT INTO `languagesschool`.`students` (`first_name`, `middle_name`, `last_name`, `username`, `password`, `birthdate`, `gender`) VALUES ('Евгений', 'Андреевич', 'Борисов', 'borisov1996@yandex.ru', 'qwerty', '1996-12-11', 'Male');\n" +
            "INSERT INTO `languagesschool`.`students` (`first_name`, `middle_name`, `last_name`, `username`, `password`, `birthdate`, `gender`) VALUES ('Екатерина', 'Петровна', 'Малышева', 'kateMal@icloud.com', 'qwerty', '1998-01-01', 'Female');\n";

    String INSERT_TEACHERS= "INSERT INTO `languagesschool`.`teachers` (`first_name`, `middle_name`, `last_name`, `username`, `password`, `birthdate`, `gender`, `salary`, `id_language`) VALUES ('Андрей', 'Иванович', 'Егоров', 'egorov@teacher.ru', '12345', '1988-12-13', 'Male', '700', '1');\n" +
            "INSERT INTO `languagesschool`.`teachers` (`first_name`, `middle_name`, `last_name`, `username`, `password`, `birthdate`, `gender`, `salary`, `id_language`) VALUES ('Сергей', 'Дмитрьевич', 'Русольцев', 'rusolcev@teacher.ru', '12345', '1986-09-01', 'Male', '800', '1');\n" +
            "INSERT INTO `languagesschool`.`teachers` (`first_name`, `middle_name`, `last_name`, `username`, `password`, `birthdate`, `gender`, `salary`, `id_language`) VALUES ('Алина', 'Егоровна', 'Калодищ', 'kalodish@teacher.ru', '12345', '1990-04-05', 'Male', '800', '2');\n" +
            "INSERT INTO `languagesschool`.`teachers` (`first_name`, `middle_name`, `last_name`, `username`, `password`, `birthdate`, `gender`, `salary`, `id_language`) VALUES ('Иван', 'Петрович', 'Румяный', 'rumyaniy@teacher.ru', '12345', '1995-11-17', 'Male', '800', '2');\n" +
            "INSERT INTO `languagesschool`.`teachers` (`first_name`, `middle_name`, `last_name`, `username`, `password`, `birthdate`, `gender`, `salary`, `id_language`) VALUES ('Сергей', 'Рустамович', 'Сокирка', 'sockirka@teacher.ru', '12345', '1981-03-10', 'Male', '900', '3');\n" +
            "INSERT INTO `languagesschool`.`teachers` (`first_name`, `middle_name`, `last_name`, `username`, `password`, `birthdate`, `gender`, `salary`, `id_language`) VALUES ('Елена', 'Михайловна', 'Пушкина', 'pyshkinaelena@teacher.ru', '12345', '1987-06-28', 'Female', '700', '3');\n" +
            "INSERT INTO `languagesschool`.`teachers` (`first_name`, `middle_name`, `last_name`, `username`, `password`, `birthdate`, `gender`, `salary`, `id_language`) VALUES ('Кристина', 'Антоновна', 'Фалей', 'faleiy@teacher.ru', '12345', '1992-01-30', 'Female', '700', '4');\n" +
            "INSERT INTO `languagesschool`.`teachers` (`first_name`, `middle_name`, `last_name`, `username`, `password`, `birthdate`, `gender`, `salary`, `id_language`) VALUES ('Ирина', 'Петровна', 'Муштакова', 'mushtakova@teacher.ru', '12345', '1972-04-29', 'Female', '750', '4');\n" +
            "INSERT INTO `languagesschool`.`teachers` (`first_name`, `middle_name`, `last_name`, `username`, `password`, `birthdate`, `gender`, `salary`, `id_language`) VALUES ('Константин', 'Сергевич', 'Ильин', 'ilyine@teacher.ru', '12345', '1999-08-14', 'Male', '900', '5');\n" +
            "INSERT INTO `languagesschool`.`teachers` (`first_name`, `middle_name`, `last_name`, `username`, `password`, `birthdate`, `gender`, `salary`, `id_language`) VALUES ('Валерий', 'Николаевич', 'Писняков', 'pisnyakov@teacher.ru', '1234', '1984-06-13', 'Male', '900', '6');\n";

    String INSERT_COURSES = "INSERT INTO `languagesschool`.`courses` (`id_name_of_course`, `id_language`, `id_level`, `id_teacher`, `cost`, `start_date`, `duration`) VALUES ('1', '1', '1', '1', '1200', '2019-09-01', '30');\n" +
            "INSERT INTO `languagesschool`.`courses` (`id_name_of_course`, `id_language`, `id_level`, `id_teacher`, `cost`, `start_date`, `duration`) VALUES ('2', '1', '2', '2', '1400', '2019-09-01', '60');\n" +
            "INSERT INTO `languagesschool`.`courses` (`id_name_of_course`, `id_language`, `id_level`, `id_teacher`, `cost`, `start_date`, `duration`) VALUES ('3', '1', '3', '1', '1400', '2019-10-01', '45');\n" +
            "INSERT INTO `languagesschool`.`courses` (`id_name_of_course`, `id_language`, `id_level`, `id_teacher`, `cost`, `start_date`, `duration`) VALUES ('4', '1', '5', '2', '1400', '2019-11-01', '55');\n" +
            "INSERT INTO `languagesschool`.`courses` (`id_name_of_course`, `id_language`, `id_level`, `id_teacher`, `cost`, `start_date`, `duration`) VALUES ('5', '2', '1', '4', '1000', '2019-09-01', '30');\n" +
            "INSERT INTO `languagesschool`.`courses` (`id_name_of_course`, `id_language`, `id_level`, `id_teacher`, `cost`, `start_date`, `duration`) VALUES ('6', '2', '2', '3', '1200', '2019-09-01', '45');\n" +
            "INSERT INTO `languagesschool`.`courses` (`id_name_of_course`, `id_language`, `id_level`, `id_teacher`, `cost`, `start_date`, `duration`) VALUES ('7', '2', '5', '4', '1400', '2019-11-01', '45');\n" +
            "INSERT INTO `languagesschool`.`courses` (`id_name_of_course`, `id_language`, `id_level`, `id_teacher`, `cost`, `start_date`, `duration`) VALUES ('8', '4', '1', '7', '2000', '2019-10-01', '30');\n" +
            "INSERT INTO `languagesschool`.`courses` (`id_name_of_course`, `id_language`, `id_level`, `id_teacher`, `cost`, `start_date`, `duration`) VALUES ('9', '3', '1', '5', '2000', '2019-09-01', '30');\n" +
            "INSERT INTO `languagesschool`.`courses` (`id_name_of_course`, `id_language`, `id_level`, `id_teacher`, `cost`, `start_date`, `duration`) VALUES ('10', '3', '2', '6', '2500', '2019-09-01', '60');\n" +
            "INSERT INTO `languagesschool`.`courses` (`id_name_of_course`, `id_language`, `id_level`, `id_teacher`, `cost`, `start_date`, `duration`) VALUES ('11', '5', '1', '9', '1200', '2019-09-01', '30');\n" +
            "INSERT INTO `languagesschool`.`courses` (`id_name_of_course`, `id_language`, `id_level`, `id_teacher`, `cost`, `start_date`, `duration`) VALUES ('12', '5', '4', '9', '1500', '2019-12-01', '45');\n";

    String INSERT_ORDER_COURSES = "INSERT INTO `languagesschool`.`orders_courses` (`username_student`, `id_course`) VALUES ('kateMal@icloud.com', '1');\n" +
            "INSERT INTO `languagesschool`.`orders_courses` (`username_student`, `id_course`) VALUES ('kateMal@icloud.com', '2');\n" +
            "INSERT INTO `languagesschool`.`orders_courses` (`username_student`, `id_course`) VALUES ('borisov1996@yandex.ru', '2');\n" +
            "INSERT INTO `languagesschool`.`orders_courses` (`username_student`, `id_course`) VALUES ('kisslevPP@yandex.by', '3');\n" +
            "INSERT INTO `languagesschool`.`orders_courses` (`username_student`, `id_course`) VALUES ('borisov1996@yandex.ru', '3');\n";
}
