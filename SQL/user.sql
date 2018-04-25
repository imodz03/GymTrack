CREATE TABLE `User` (
  `UserID` varchar(36) NOT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` char(64) DEFAULT NULL,
  `bio` text,
  `photo` varchar(200) DEFAULT NULL,
  `preferences` json DEFAULT NULL,
  `BMI` smallint(6) DEFAULT NULL,
  `bodyFatPercentage` smallint(6) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
