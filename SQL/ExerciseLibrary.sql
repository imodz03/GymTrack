CREATE TABLE `ExerciseLibrary` (
  `ExerciseLibraryID` varchar(36) NOT NULL,
  `exerciseName` varchar(50) DEFAULT NULL,
  `description` text,
  `category` enum('Aerobic','Anaerobic','Strength Training','Stretching','Balancing') DEFAULT NULL,
  `bodypart` enum('Arms','Legs','Back','Chest','Shoulders') DEFAULT NULL,
  PRIMARY KEY (`ExerciseLibraryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
