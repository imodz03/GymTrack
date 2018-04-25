CREATE TABLE `PlannedWorkouts` (
  `PWID` varchar(36) DEFAULT NULL,
  `PlanID` varchar(36) DEFAULT NULL,
  `WorkoutID` varchar(36) DEFAULT NULL,
  `WorkoutDay` tinyint(3) unsigned DEFAULT NULL,
  `WorkoutDOW` enum('Mon','Tue','Wed','Thu','Fri','Sat','Sun') DEFAULT NULL,
  KEY `PlanID` (`PlanID`),
  KEY `WorkoutID` (`WorkoutID`),
  CONSTRAINT `plannedworkouts_ibfk_1` FOREIGN KEY (`PlanID`) REFERENCES `Plan` (`PlanID`),
  CONSTRAINT `plannedworkouts_ibfk_2` FOREIGN KEY (`WorkoutID`) REFERENCES `Workout` (`WorkoutID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
