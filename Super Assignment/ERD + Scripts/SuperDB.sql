DROP DATABASE IF EXISTS superDB; 

CREATE DATABASE superDB;

USE superDB;

CREATE TABLE Super (
	superID INT PRIMARY KEY AUTO_INCREMENT, 
    SuperpowerID INT NOT NULL, 
    `Type` VARCHAR (10) NOT NULL, 
    `Name` VARCHAR (50) NOT NULL, 
    `Description`  VARCHAR(255) NOT NULL
);

CREATE TABLE Superpower (
	SuperpowerID INT PRIMARY KEY AUTO_INCREMENT, 
    `Name` VARCHAR(50) NOT NULL, 
    `Description` VARCHAR(255) NOT NULL
);

CREATE TABLE Sighting (
	SightingID INT PRIMARY KEY AUTO_INCREMENT,
    SuperID INT NOT NULL, 
    LocationID INT NOT NULL, 
    `Date` DATE NOT NULL,
    `Description` VARCHAR(255) NOT NULL
);

CREATE TABLE Location (
	LocationID INT PRIMARY KEY AUTO_INCREMENT, 
    `Name` VARCHAR(50) NOT NULL, 
    `Description` VARCHAR(255) NOT NULL, 
    Address VARCHAR(255) NOT NULL, 
    Latitude VARCHAR(50) NOT NULL, 
    Longitude VARCHAR(50) NOT NULL
);

CREATE TABLE `Organization` (
	OrganizationID INT PRIMARY KEY AUTO_INCREMENT, 
    `Name` VARCHAR(50) NOT NULL, 
    `Description` VARCHAR(255) NOT NULL, 
    Address VARCHAR(255) NOT NULL, 
    ContactInfo VARCHAR(100) NOT NULL, 
    `Type` VARCHAR(10) NOT NULL
);

CREATE TABLE SuperOrganization (
	SuperID INT NOT NULL, 
    OrganizationID INT NOT NULL, 
    PRIMARY KEY PK_SuperOrganization(SuperID, OrganizationID), 
    CONSTRAINT FK_SuperOrganization_Super FOREIGN KEY (SuperID)
		REFERENCES Super (SuperID), 
	CONSTRAINT FK_SuperOrganization_Organization FOREIGN KEY (OrganizationID)
		REFERENCES `Organization` (OrganizationID)
);
