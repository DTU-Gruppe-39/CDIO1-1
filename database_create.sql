CREATE DATABASE IF NOT EXISTS cdio_2Semester;
USE cdio_2Semester;
CREATE TABLE IF NOT EXISTS personer (
	userID int NOT NULL AUTO_INCREMENT,
	userName varchar(48), 
        ini varchar(6),
        roles varchar(36),
        cpr varchar(11),
        passwd varchar(64),
        PRIMARY KEY (userID)
                        );
INSERT INTO personer(userName, ini, roles, cpr, passwd) VALUES
('test', 'tst', 'admin', '123456-8888', 'secure');

