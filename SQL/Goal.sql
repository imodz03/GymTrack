CREATE TABLE `Goal` (
  `GoalID` varchar(36) NOT NULL,
  `SetID` varchar(36) DEFAULT NULL,
  `UserID` varchar(36) DEFAULT NULL,
  `targetDate` date DEFAULT NULL,
  `dateAchieved` date DEFAULT NULL,
  `GoalName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`GoalID`),
  KEY `SetID` (`SetID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `goal_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
