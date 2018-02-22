CREATE TABLE personer (
	userID int,
	userName varchar(40), 
        ini varchar(6),
        roles varchar(10),
        cpr varchar(11),
        passwd varchar(64)
                        );
Insert into personer(userID, userName, ini, roles, cpr, passwd) values
(1, 'test', 'tst', 'admin', '123456-8888', 'secure');
