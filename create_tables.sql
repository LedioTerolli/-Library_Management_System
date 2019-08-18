SELECT * FROM EMPLOYEE;
SELECT * FROM BRANCH;
SELECT * FROM BOOK;
SELECT * FROM LOAN;
SELECT * FROM HOLD;
SELECT * FROM PATRON;

SELECT * FROM BOOK WHERE ID = 33;

CREATE TABLE BOOK (
	ID BIGINT PRIMARY KEY,
    COVER_URL VARCHAR(100),
    TITLE VARCHAR (150),
    AUTHOR VARCHAR(100),
    CATEGORY VARCHAR(50),
    BRANCH_ID INT,
    AVAILABLE BOOLEAN NOT NULL
);

DELETE FROM BOOK;

ALTER TABLE BOOK
MODIFY TITLE VARCHAR(100);

CREATE TABLE PATRON (
	USERNAME VARCHAR(40) PRIMARY KEY,
    FIRST_NAME VARCHAR(20),
    LAST_NAME VARCHAR(20),
    EMAIL VARCHAR (40),
    PASSWORD VARCHAR (20),
    DOB DATE
);

CREATE TABLE LOAN (
	BOOK_ID BIGINT,
    PATRON_USERNAME VARCHAR (40),
    START_DATE DATE,
    DUE_DATE DATE,
    COST DECIMAL (6,2),
    PRIMARY KEY(BOOK_ID),
    FOREIGN KEY(BOOK_ID) REFERENCES BOOK(ID) ON DELETE CASCADE,
	FOREIGN KEY(PATRON_USERNAME) REFERENCES PATRON(USERNAME) ON DELETE SET NULL
);

DROP TABLE LOAN;

CREATE TABLE HOLD (
	BOOK_ID BIGINT,
    PATRON_USERNAME VARCHAR (40),
    START_DATE DATE,
    PRIMARY KEY (BOOK_ID, PATRON_USERNAME),
    FOREIGN KEY(BOOK_ID) REFERENCES BOOK(ID) ON DELETE CASCADE,
    FOREIGN KEY(PATRON_USERNAME) REFERENCES PATRON(USERNAME) ON DELETE CASCADE
);

CREATE TABLE EMPLOYEE (
  EMP_ID INT PRIMARY KEY,
  FIRST_NAME VARCHAR(40),
  LAST_NAME VARCHAR(40),
  DOB DATE,
  SEX VARCHAR(1),
  SALARY INT,
  SUPER_ID INT,
  BRANCH_ID INT
);

ALTER TABLE EMPLOYEE
ADD FOREIGN KEY(SUPER_ID)
REFERENCES EMPLOYEE(EMP_ID)
ON DELETE SET NULL;

CREATE TABLE BRANCH (
  BRANCH_ID INT PRIMARY KEY,
  BRANCH_NAME VARCHAR(40),
  LOCATION VARCHAR (40),
  MGR_ID INT,
  FOREIGN KEY(MGR_ID) REFERENCES EMPLOYEE(EMP_ID) ON DELETE SET NULL
);

ALTER TABLE EMPLOYEE
ADD FOREIGN KEY(BRANCH_ID)
REFERENCES BRANCH(BRANCH_ID)
ON DELETE SET NULL;
