CREATE TABLE `Workout` (
  `WorkoutID` varchar(36) NOT NULL,
  `Exercises` varchar(36) DEFAULT NULL,
  `workoutName` varchar(100) DEFAULT NULL,
  `description` text,
  `category` enum('Weight Training','Cardio') DEFAULT NULL,
  `SetsID` varchar(36) DEFAULT NULL,
  `UserID` varchar(36) DEFAULT NULL,
  `public` tinyint(1) DEFAULT NULL,
  `dateOfWorkout` date DEFAULT NULL,
  PRIMARY KEY (`WorkoutID`),
  KEY `SetsID` (`SetsID`),
  KEY `UserID` (`UserID`),
  KEY `Exercises` (`Exercises`),
  CONSTRAINT `workout_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
