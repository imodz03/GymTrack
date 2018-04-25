CREATE TABLE `ExerciseList` (
  `ExerciseListID` varchar(36) NOT NULL,
  `ExerciseID` varchar(36) NOT NULL,
  PRIMARY KEY (`ExerciseListID`,`ExerciseID`),
  KEY `ExerciseID` (`ExerciseID`),
  CONSTRAINT `exerciselist_ibfk_1` FOREIGN KEY (`ExerciseID`) REFERENCES `ExerciseLibrary` (`ExerciseLibraryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
