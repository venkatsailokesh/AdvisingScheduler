DROP VIEW AVAILSLOT;
DROP VIEW APPTSLOT;
DROP VIEW APPTFW;
DROP TABLE SLOT;
DROP TABLE APPOINTMENT;
DROP TABLE USER;

DROP DATABASE ADVISING;

CREATE DATABASE ADVISING;

USE ADVISING;

CREATE TABLE APPOINTMENT(   ApptID INTEGER NOT NULL AUTO_INCREMENT,
    ApptDate DATE NOT NULL,
    ApptStartHour INTEGER NOT NULL,
    ApptStartMin INTEGER NOT NULL,
    ApptEndHour INTEGER NOT NULL,
    ApptEndMin INTEGER NOT NULL,
    ApptType VARCHAR(20),
    Description VARCHAR(100),
    StudentID CHAR(10) NOT NULL,
    StudentName VARCHAR(20),
    StudentMajor VARCHAR(20),
    StudentEmail VARCHAR(40),
    AdvisorName VARCHAR(20) NOT NULL,
    PRIMARY KEY (ApptID)
);

-- CREATE TABLE APPOINTMENT(   ApptID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
--     ApptDate DATE NOT NULL,
--     ApptStartHour INTEGER NOT NULL,
--     ApptStartMin INTEGER NOT NULL,
--     ApptEndHour INTEGER NOT NULL,
--     ApptEndMin INTEGER NOT NULL,
--     ApptType VARCHAR(20),
--     Description VARCHAR(100),
--     StudentID CHAR(10) NOT NULL,
--     StudentName VARCHAR(20),
--     StudentMajor VARCHAR(20),
--     StudentEmail VARCHAR(40),
--     AdvisorName VARCHAR(20) NOT NULL
-- );

CREATE TABLE SLOT(   
    SlotID INTEGER NOT NULL AUTO_INCREMENT,
    SlotDate DATE NOT NULL,
    SlotStartHour INTEGER NOT NULL,
    SlotStartMin INTEGER NOT NULL,
    AdvisorUserId INTEGER NOT NULL,
    PRIMARY KEY (SlotID)
);
-- CREATE TABLE SLOT(   SlotID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
--     SlotDate DATE NOT NULL,
--     SlotStartHour INTEGER NOT NULL,
--     SlotStartMin INTEGER NOT NULL
-- );

CREATE TABLE USER( UserID INTEGER NOT NULL AUTO_INCREMENT,
    UserEmail VARCHAR(100) NOT NULL,
    UserPassword VARCHAR(20) NOT NULL,
    UserName VARCHAR(30),
    UserDepartment VARCHAR(30),
    UserRank INTEGER,
    PRIMARY KEY (UserID)
);
-- CREATE TABLE USER( UserID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
--     UserEmail VARCHAR(30) NOT NULL,
--     UserPassword VARCHAR(20) NOT NULL,
--     UserName VARCHAR(30),
--     UserDepartment VARCHAR(30),
--     UserRank INTEGER
-- );

-- password is: 12345
INSERT INTO USER (UserEmail, UserPassword, UserName, UserDepartment, UserRank) 
VALUES ('admin@mavs.uta.edu', '46792755', 'Admin', 'CSE', 1);

CREATE VIEW APPTFW AS
SELECT ApptID, ApptDate, ApptStartHour, ApptStartMin, ApptEndHour, ApptEndMin
From APPOINTMENT
ORDER BY ApptDate;

CREATE VIEW APPTSLOT AS
SELECT SLOT.*, APPTFW.ApptID
FROM SLOT, APPTFW
WHERE(SLOT.SlotDate = APPTFW.ApptDate AND SLOT.SlotStartHour <> APPTFW.ApptEndHour AND SLOT.SlotStartHour = APPTFW.ApptStartHour AND SLOT.SlotStartMin >= APPTFW.ApptStartMin)
OR (SLOT.SlotDate = APPTFW.ApptDate AND SLOT.SlotStartHour > APPTFW.ApptStartHour AND SLOT.SlotStartHour < APPTFW.ApptEndHour)
OR (SLOT.SlotDate = APPTFW.ApptDate AND SLOT.SlotStartHour <> APPTFW.ApptStartHour AND SLOT.SlotStartHour = APPTFW.ApptEndHour AND SLOT.SlotStartMin < APPTFW.ApptEndMin)
OR (SLOT.SlotDate = APPTFW.ApptDate AND SLOT.SlotStartHour = APPTFW.ApptStartHour AND SLOT.SlotStartHour = APPTFW.ApptEndHour AND SLOT.SlotStartMin >= APPTFW.ApptStartMin AND SLOT.SlotStartMin < APPTFW.ApptEndMin);


CREATE VIEW AVAILSLOT AS 
SELECT * FROM SLOT WHERE SlotID
NOT IN (SELECT SlotID FROM APPTSLOT);

alter table user
  add COLUMN UserStudentID INT,
  add COLUMN UserDatePasswordChanged DATE

create table ApplicationControl (
  ParamiterName varchar(30) not null,
  ParamiterValue VARCHAR(30) NOT NULL
);

insert into ApplicationControl
  (ParamiterName, ParamiterValue)
    VALUEs ("TempPasswordExpiresIn", "2");
insert into ApplicationControl
  (ParamiterName, ParamiterValue)
  VALUEs ("PasswordExpiresIn", "365");

-- CREATE VIEW AVAILSLOT AS 
-- SELECT * FROM SLOT
-- EXCEPT SELECT SlotID, SlotDate, SlotStartHour, SlotStartMin FROM APPTSLOT;