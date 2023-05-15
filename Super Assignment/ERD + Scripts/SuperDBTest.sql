DROP DATABASE IF EXISTS superDBTest; 

CREATE DATABASE superDBTest;

USE superDBTest;

CREATE TABLE `Super` (
	SuperID INT PRIMARY KEY AUTO_INCREMENT, 
    PowerID INT NULL, 
    `Type` VARCHAR (10) NOT NULL, 
    `Name` VARCHAR (50) NOT NULL, 
    `Description`  VARCHAR(255) NOT NULL
);

CREATE TABLE Power (
	PowerID INT PRIMARY KEY AUTO_INCREMENT, 
    `Name` VARCHAR(50) NOT NULL, 
    `Description` VARCHAR(255) NOT NULL
);

ALTER TABLE `Super`
ADD CONSTRAINT FK_SuperPower
FOREIGN KEY FK_SuperPower (PowerID)
REFERENCES Power (PowerID);

CREATE TABLE Sighting (
	SightingID INT PRIMARY KEY AUTO_INCREMENT,
    SuperID INT NOT NULL, 
    LocationID INT NOT NULL, 
    `Date` DATE NOT NULL,
    `Description` VARCHAR(255) NOT NULL
);

ALTER TABLE Sighting 
ADD CONSTRAINT FK_SightingSuper
FOREIGN KEY FK_SightingSuper (SuperID)
REFERENCES `Super` (SuperID);

CREATE TABLE Location (
	LocationID INT PRIMARY KEY AUTO_INCREMENT, 
    `Name` VARCHAR(50) NOT NULL, 
    `Description` VARCHAR(255) NOT NULL, 
    Address VARCHAR(255) NOT NULL, 
    Latitude VARCHAR(50) NOT NULL, 
    Longitude VARCHAR(50) NOT NULL
);

ALTER TABLE Sighting 
ADD CONSTRAINT FK_SightingLocation
FOREIGN KEY FK_SightingLocation (LocationID)
REFERENCES Location (LocationID);

CREATE TABLE `Organization` (
	OrganizationID INT PRIMARY KEY AUTO_INCREMENT, 
    `Name` VARCHAR(50) NOT NULL, 
    `Description` VARCHAR(255) NOT NULL, 
    Address VARCHAR(255) NOT NULL, 
    ContactInfo VARCHAR(100) NULL, 
    `Type` VARCHAR(10) NOT NULL
);

CREATE TABLE SuperOrganization (
	SuperID INT NOT NULL, 
    OrganizationID INT NOT NULL, 
    PRIMARY KEY PK_SuperOrganization(SuperID, OrganizationID), 
    CONSTRAINT FK_SuperOrganization_Super FOREIGN KEY (SuperID)
		REFERENCES `Super` (SuperID), 
	CONSTRAINT FK_SuperOrganization_Organization FOREIGN KEY (OrganizationID)
		REFERENCES `Organization` (OrganizationID)
);