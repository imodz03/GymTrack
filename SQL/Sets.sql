CREATE TABLE `Sets` (
  `SetsID` varchar(36) NOT NULL,
  `ExerciseID` varchar(36) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `reps` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `timeTaken` int(11) DEFAULT NULL,
  `distance` int(11) DEFAULT NULL,
  `SUID` varchar(36) DEFAULT NULL,
  KEY `ExerciseID` (`ExerciseID`),
  CONSTRAINT `sets_ibfk_1` FOREIGN KEY (`ExerciseID`) REFERENCES `ExerciseLibrary` (`ExerciseLibraryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
