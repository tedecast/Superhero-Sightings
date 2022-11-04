DROP DATABASE IF EXISTS superheroDBTest; 

CREATE DATABASE superheroDBTest;

USE superheroDBTest;

CREATE TABLE Hero (
	HeroID INT PRIMARY KEY AUTO_INCREMENT, 
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
    HeroID INT NOT NULL, 
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

CREATE TABLE HeroOrganization (
	HeroID INT NOT NULL, 
    OrganizationID INT NOT NULL, 
    PRIMARY KEY PK_HeroOrganization(HeroID, OrganizationID), 
    CONSTRAINT FK_HeroOrganization_Hero FOREIGN KEY (HeroID)
		REFERENCES Hero (HeroID), 
	CONSTRAINT FK_HeroOrganization_Organization FOREIGN KEY (OrganizationID)
		REFERENCES `Organization` (OrganizationID)
);
