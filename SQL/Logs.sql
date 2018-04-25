CREATE TABLE `Logs` (
  `LogID` varchar(36) NOT NULL,
  `SetID` varchar(36) DEFAULT NULL,
  `WorkoutID` varchar(36) DEFAULT NULL,
  `UserID` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`LogID`),
  KEY `SetID` (`SetID`),
  KEY `WorkoutID` (`WorkoutID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `logs_ibfk_2` FOREIGN KEY (`WorkoutID`) REFERENCES `Workout` (`WorkoutID`),
  CONSTRAINT `logs_ibfk_3` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
