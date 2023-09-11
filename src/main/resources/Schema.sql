--Script para prueba en H2
CREATE TABLE IF NOT EXISTS `Owner` (
  `ownerId` VARCHAR(255) PRIMARY KEY,
  `name` VARCHAR(255),
  `firstLastName` VARCHAR(255),
  `secondLastName` VARCHAR(255),
  `licensePlate` VARCHAR(255),
  `phoneNumber` VARCHAR(255),
  `email` VARCHAR(255)
);