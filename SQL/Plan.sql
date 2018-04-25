CREATE TABLE `Plan` (
  `PlanID` varchar(36) NOT NULL,
  `PlanName` varchar(100) DEFAULT NULL,
  `Description` text,
  `UserID` varchar(36) DEFAULT NULL,
  `Public` tinyint(1) DEFAULT NULL,
  `Repeats` tinyint(3) unsigned DEFAULT NULL,
  `startDate` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PlanID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `plan_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
